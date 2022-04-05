package com.ssafy.boonmoja.api.controller.travel;

import com.ssafy.boonmoja.api.dto.TravelDto;
import com.ssafy.boonmoja.api.entity.travel.Travel;
import com.ssafy.boonmoja.api.service.TravelService;
import com.ssafy.boonmoja.api.service.UserService;
import com.ssafy.boonmoja.oauth.annotation.CurrentUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ssafy.boonmoja.utils.ApiUtils.ApiResult;
import static com.ssafy.boonmoja.utils.ApiUtils.success;

@Tag(name = "Travel", description = "내 여행계획 관리 API")
@RestController
@RequestMapping("/api/travel")
@RequiredArgsConstructor
@Slf4j
@PreAuthorize("hasAuthority('ROLE_USER')")
public class TravelController {
    
    private final UserService userService;
    private final TravelService travelService;
    
//    @PostMapping
//    @Operation(summary = "여행계획 등록", description = "여행계획 등록하기 {\n" +
//            "    \"travelTitle\":\"여행계획 이름\",\n" +
//            "    \"startAt\":\"2022-04-01\",\n" +
//            "    \"endAt\":\"2022-04-05\"\n" +
//            "}")
//    public ApiResult<String> insertTravel(@CurrentUser String userId, @DateTimeFormat(pattern = "YYYY-MM-DD") @RequestBody Travel travel) {
//        log.info("travel - {}", travel);
//        travelService.insertTravel(userId, travel);
//
//        return success("OK!!");
//    }
    
    @GetMapping
    @Operation(summary = "내 여행계획 목록 조회", description = "내가 만든 여행계획 목록 조회")
    public ApiResult<List<TravelDto>> getMyTravelList(@CurrentUser String userId) {
        List<TravelDto> travelList = travelService.getUserTravel(userId);
        return success(travelList);
    }
    
    
    @DeleteMapping("/{travelSeq}")
    @Operation(summary = "여행계획 삭제", description = "여행계획 삭제하기")
    @Parameters({
            @Parameter(name = "travelSeq", description = "Travel의 PK")
    })
    public ApiResult<String> deleteTravel(@CurrentUser String userId, @PathVariable Long travelSeq){
        travelService.deleteTravel(userId, travelSeq);
        return success("OK!!");
    }
    
    
    @PutMapping
    @Operation(summary = "여행계획 수정", description = "여행계획 수정하기 travelSeq 필요"+
            "{\n" +
            "    \"travelSeq\":\"4\",\n" +
            "    \"travelTitle\":\"여행2\",\n" +
            "    \"startAt\":\"2022-04-01\",\n" +
            "    \"endAt\":\"2022-04-05\"\n" +
            "}")
    public ApiResult<String> updateTravel(@CurrentUser String userId, @RequestBody Travel travel){
        log.info("update : {}", travel);
        travelService.updateTravel(userId, travel);
        return success("OK!!");
    }
    
    
    @PutMapping("/finish/{travelSeq}")
    @Operation(summary = "여행계획 완료", description = "여행계획 완료처리하기, isFinished: false -> true 만약 true일 때 누르면 false로 바뀜")
    @Parameters({
            @Parameter(name = "travelSeq", description = "Travel의 PK")
    })
    public ApiResult<String> travelFinish(@CurrentUser String userId, @PathVariable Long travelSeq){
        travelService.travelFinish(userId, travelSeq);
        return success("OK!!");
    }
    
    
    
}
