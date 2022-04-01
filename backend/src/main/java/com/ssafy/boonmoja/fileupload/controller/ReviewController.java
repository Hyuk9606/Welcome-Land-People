package com.ssafy.boonmoja.fileupload.controller;

import com.ssafy.boonmoja.fileupload.dto.ReviewResDto;
import com.ssafy.boonmoja.fileupload.model.Review;
import com.ssafy.boonmoja.fileupload.repository.ReviewRepository;
import com.ssafy.boonmoja.fileupload.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import static com.ssafy.boonmoja.utils.ApiUtils.ApiResult;
import static com.ssafy.boonmoja.utils.ApiUtils.success;


// CTRL + ALT + L : 코드 정렬
@Slf4j  // 로깅
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/review")
//@PreAuthorize("hasAuthority('ROLE_USER')")
public class ReviewController {

    private final ReviewRepository reviewRepo;
    private final ReviewService reviewService;

    @Operation(summary = "여행 리뷰 올리기", description = "여행번호로 여행리뷰(이미지,텍스트)를 업로드한다.")
    @Parameters({
            @Parameter(name = "travel", description = "여행번호"),
            @Parameter(name = "text", description = "텍스트"),
            @Parameter(name = "image", description = "여행 사진")
    })
    @PostMapping("/{travel}")
    public ApiResult<Review> addReviews(@PathVariable Long travel, @RequestParam("text") String text, @RequestParam("image") List<MultipartFile> image) throws Exception {
        // 프론트에서 이미지를 배열타입으로 보냄
        Review reviewResult;
        // 아직 해당 여행 리뷰가 없을 경우
        if(reviewRepo.findByTravel(travel) == null){
            if(text.equals("") && image.get(0).isEmpty()) throw new Exception("저장할 내용이 없습니다.");
            Review review = new Review(travel,text);
            List<Binary> imageList = new LinkedList<>();
            if(image != null){
                for (MultipartFile m : image) {
                    if(!m.isEmpty()) imageList.add(new Binary(BsonBinarySubType.BINARY, m.getBytes()));
                }
                review.setImage(imageList);
            }
            reviewResult = reviewRepo.save(review);
        }else throw new Exception("잘못된 요청입니다. 이미 기록한 여행이에요.");

        return success(reviewResult);
        //log.info("photoTest: {},{}", id, text);
    }

    // travel id로 여행리뷰보기
    @Operation(summary = "여행 리뷰 조회", description = "여행번호로 여행리뷰(이미지,텍스트)를 조회한다.")
    @Parameters({
            @Parameter(name = "travel", description = "여행번호")
    })
    @GetMapping("/{travel}")
    public ApiResult<ReviewResDto> getReivewBoard(@PathVariable Long travel) {
        Review review = reviewRepo.findByTravel(travel);
        String text = review.getText();
        List<String> imageList = new LinkedList<>();
        for (Binary b : review.getImage()) {
            imageList.add(Base64.getEncoder().encodeToString(b.getData()));
        }
        // Base64를 사용하는 이유 : XML, JSON, REST API등 문자열 기반 데이터를 주고받는 환경에서 multi-form을 다룰경우 함께 사용된다.
        // 즉, 여기서는 text와 image 같이 보내주기 위해서 base64로 이미지를 보낸다.
        // 프론트에서 나타내기 위해 byte[] 로 반환된 이미지 데이터를 Base64 문자열로 변환
//        JSONObject object = new JSONObject();
//        object.put("image",imageList);
//        object.put("text",text);
        ReviewResDto reviewResDto = new ReviewResDto(text,imageList,travel);
        return success(reviewResDto);
    }

    /// 존재하는 리뷰일 때 이미지 추가하기
    @Operation(summary = "여행 이미지 추가하기", description = "존재하는 리뷰일 때, 여행번호로 여행리뷰(이미지)를 추가한다.")
    @Parameters({
            @Parameter(name = "travel", description = "여행번호"),
            @Parameter(name = "image", description = "여행 사진")
    })
    @PutMapping("/image/{travel}")
    public ApiResult<Review> putReivewImage(@PathVariable Long travel, @RequestParam("image") List<MultipartFile> image) throws Exception {
        // 프론트에서 이미지를 배열타입으로 보냄
        Review reviewResult;
        if(reviewRepo.findByTravel(travel) != null){
            Review review = reviewRepo.findByTravel(travel);

            List<Binary> imageList = reviewRepo.findByTravel(travel).getImage();
            if(image != null){
                for (MultipartFile m : image) {
                    imageList.add(new Binary(BsonBinarySubType.BINARY, m.getBytes()));
                }
                review.setImage(imageList);
            }
            reviewResult = reviewRepo.save(review);
        }else throw new Exception("잘못된 요청입니다. 수정할 리뷰가 없어요.");

        return success(reviewResult);
        //log.info("photoTest: {},{}", id, text);
    }

    // 텍스트 수정
    @Operation(summary = "여행 리뷰 텍스트 수정하기", description = "여행번호로 여행 텍스트리뷰를 수정한다.")
    @Parameters({
            @Parameter(name = "travel", description = "여행번호"),
            @Parameter(name = "text", description = "텍스트"),
            @Parameter(name = "image", description = "여행 사진")
    })
    @PutMapping("/text/{travel}")
    public ApiResult<Review> putReivewText(@PathVariable Long travel, @RequestParam("text") String text) throws Exception {
        Review reviewResult;
        if(reviewRepo.findByTravel(travel) != null) {
            Review review = reviewRepo.findByTravel(travel);
            review.setText(text);
            reviewResult = reviewRepo.save(review);
        }else throw new Exception("잘못된 요청입니다. 수정할 리뷰가 없어요.");

        return success(reviewResult);
        //log.info("photoTest: {},{}", id, text);
    }

    // 사진하나씩삭제
    @Operation(summary = "여행리뷰사진 하나씩 삭제하기", description = "여행번호와 여행이미지리스트번호로 사진을 삭제한다.")
    @Parameters({
            @Parameter(name = "travel", description = "여행번호"),
            @Parameter(name = "image", description = "이미지번호")
    })
    @DeleteMapping("/{travel}/{imageId}")
    public ApiResult<String> deleteReviewPhoto(@PathVariable Long travel, @PathVariable int imageId){
        reviewService.deleteImage(travel,imageId);
        return success("OK!!");
    }

    //이미지만 전체 삭제
    @Operation(summary = "선택한 여행리뷰의 모든사진 삭제하기", description = "여행번호로 해당 여행의 모든 사진을 삭제한다.")
    @Parameters({
            @Parameter(name = "travel", description = "여행번호"),
    })
    @DeleteMapping("/images/{travel}")
    public ApiResult<String> deletAllReviewPhotos(@PathVariable Long travel){
        Review review = reviewRepo.findByTravel(travel);
        review.setImage(null);
        return success("OK!!");
    }

    // 해당여행 리뷰(텍스트,이미지)전체삭제
    @Operation(summary = "해당 여행 리뷰(텍스트,이미지)전체 삭제", description = "여행번호로 해당 여행의 모든 사진과 텍스트를 삭제한다.")
    @Parameters({
            @Parameter(name = "travel", description = "여행번호"),
    })
    @DeleteMapping("/{travel}")
    public ApiResult<String> deleteAll(@PathVariable Long travel){
        reviewRepo.deleteByTravel(travel);
        return success("OK!!");
    }


/******************************* 하단은 템플릿엔진 버전 ********************************************/
//    // 이거는 그냥 테스트 모든 review 보기
//    @GetMapping("/all")
//    public List<Review> getReviews() {
//        if (reviewRepo.findAll() == null) return null;
//        return reviewRepo.findAll();
//    }

    //    @GetMapping("/photos/{id}")
//    public String getPhoto(@PathVariable String id, Model model) {
//        Photo photo = reviewService.getPhoto(id);
//        model.addAttribute("title", photo.getTitle());
//        model.addAttribute("image", Base64.getEncoder().encodeToString(photo.getImage().getData()));
//        // 프론트에서 나타내기 위해 byte[] 로 반환된 이미지 데이터를 Base64 문자열로 변환
//        return "photos";
//    }

//    @GetMapping("/photos/upload")
//    public String uploadPhoto(Model model) {
//        model.addAttribute("message", "hello");
//        return "uploadPhoto";
//    }

//    @PostMapping("/photos/add")
//    //@RequestParam("title") String title, @RequestParam("image")
//    //@RequestPart PhotoDto photoDto, @RequestPart MultipartFile imgFile
//    public String addPhoto(@RequestParam("title") String title, @RequestParam("image") MultipartFile imgFile, Model model) throws IOException {
////        String id = reviewService.addPhoto(title, image);
//        String id = reviewService.addPhoto(title, imgFile);
//////        return "redirect:/photos/" + id;
////        System.out.println(photoDto.getTitle());
//        log.info("photoTest: {},{}",id,title);
////        return id;
//
//        return "redirect:/photos/" + id;
//    }
}
