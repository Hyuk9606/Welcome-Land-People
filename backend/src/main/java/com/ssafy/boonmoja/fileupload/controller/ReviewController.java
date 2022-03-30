package com.ssafy.boonmoja.fileupload.controller;

import com.ssafy.boonmoja.api.entity.user.User;
import com.ssafy.boonmoja.api.repository.travel.DayRepository;
import com.ssafy.boonmoja.api.repository.user.UserRepository;
import com.ssafy.boonmoja.common.ApiResponse;
import com.ssafy.boonmoja.fileupload.dto.ReviewDto;
import com.ssafy.boonmoja.fileupload.model.Review;
import com.ssafy.boonmoja.fileupload.repository.ReviewRepository;
import com.ssafy.boonmoja.fileupload.service.ReviewService;
import com.ssafy.boonmoja.oauth.annotation.CurrentUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;


// CTRL + ALT + L : 코드 정렬
@Slf4j
@RequiredArgsConstructor    // DI
@RestController
@RequestMapping("/api/review")
//@PreAuthorize("hasAuthority('ROLE_USER')")
public class ReviewController {

    private final UserRepository userRepo;
    private final DayRepository dayRepo;
    private final ReviewRepository reviewRepo;
    private final ReviewService reviewService;

    // travel id로 리뷰보기
    // success
    @GetMapping("/{travelId}")
    public Map getReivewBoard(@PathVariable Long travelId) {
        Review review = reviewRepo.findByTravel(travelId);
        String text = review.getText();
        List<String> imageList = new LinkedList<>();
        for (Binary b : review.getImage()) {
            imageList.add(Base64.getEncoder().encodeToString(b.getData()));
        }
        // Base64를 사용하는 이유 : XML, JSON, REST API등 문자열 기반 데이터를 주고받는 환경에서 multi-form을 다룰경우 함께 사용된다.
        // 즉, 여기서는 text와 image 같이 보내주기 위해서 base64로 이미지를 보낸다.
        // 프론트에서 나타내기 위해 byte[] 로 반환된 이미지 데이터를 Base64 문자열로 변환
        JSONObject object = new JSONObject();
        object.put("image",imageList);
        object.put("text",text);

        return object;
    }

// dto 형태로 텍스트만 넣기
//    @PostMapping("/text")
//    public String addReviewImage(@RequestBody ReviewDto reviewDto){
//        Review review = new Review(reviewDto);
//        reviewRepo.save(review);
//        return review.getId();
//    }

    // success
    // text, image vaule값은 입력받지 않아도 key는 있어야함
    @PostMapping()
    public Review addReviews(@RequestParam("travelId") Long travelId,@RequestParam("text") String text, @RequestParam("image") List<MultipartFile> image) throws IOException {
        // 프론트에서 이미지를 배열타입으로 보냄

        Review review = new Review(travelId,text);
        List<Binary> imageList = new LinkedList<>();
        if(image != null){
            for (MultipartFile m : image) {
                imageList.add(new Binary(BsonBinarySubType.BINARY, m.getBytes()));
            }
            review.setImage(imageList);
        }

        review = reviewRepo.save(review);
        return review;


        //log.info("photoTest: {},{}", id, text);

        //return ApiResponse.success("data","OK");
    }


    /// 안돼ㅐㅐㅐㅐ
//    @PutMapping("/text/{id}")
//    public Review  putReivewText(@PathVariable String id, @RequestBody ReviewDto reviewDto) {
////        log.info("before update: {}",reviewRepo.findById(reviewSeq).get().getText());
//////        String str = reviewService.updateReviewText(reviewSeq,reviewDto);
//////        log.info("after update: {}", str);
//
//            Review review = reviewRepo.findById(id).get();
//            review.setText(reviewDto.getText());
//            reviewRepo.save(review);
////            reviewRepo.deleteById(id);
//
//            return review;
////        return reviewService.updateReviewText(id,reviewDto);
////        Review review = reviewDto.toEntity();
////        review.setId(id);
////        reviewRepo.save(review);
//
////        return ApiResponse.success("data","OK");
//    }

    //success
    // 사진하나씩삭제
    @DeleteMapping("/{travelId}/{imageId}")
    public void deleteReviewPhoto(@PathVariable Long travelId, @PathVariable int imageId){
        reviewService.deleteImage(travelId,imageId);
    }


    // success
    @DeleteMapping("/{travelId}")
    public void deleteAll(@PathVariable Long travelId){
        reviewRepo.deleteById(travelId);
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
