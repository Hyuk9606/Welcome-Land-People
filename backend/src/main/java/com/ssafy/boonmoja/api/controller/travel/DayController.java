package com.ssafy.boonmoja.api.controller.travel;

import com.ssafy.boonmoja.api.dto.DayDto;
import com.ssafy.boonmoja.api.service.DayService;
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

@Tag(name = "Day", description = "내 여행계획의 일정 관리 API")
@RestController
@RequestMapping("/api/travel/day")
@RequiredArgsConstructor
@Slf4j
@PreAuthorize("hasAuthority('ROLE_USER')")
public class DayController {
    
    private final DayService dayService;
    
    @Operation(summary = "여행 일정 조회", description = "여행 계획의 일정 조회")
    @Parameters({
            @Parameter(name = "travelSeq", description = "여행의 PK")
    })
    @GetMapping("/{travelSeq}")
    public ApiResult<List<DayDto>> getTravelDays(@CurrentUser String userId, @PathVariable Long travelSeq) {
        List<DayDto> dayDtoList = dayService.getTravelDay(userId, travelSeq);
        return success(dayDtoList);
    }
    
    @Operation(summary = "여행 일정 저장", description = "내 여행계획에 작성한 여행일정 저장하기.")
    @Parameters({
            @Parameter(name = "travelSeq", description = "여행의 PK"),
            @Parameter(name = "dayDtoList", description = "[\n" +
                    "    {\n" +
                    "        \"contentsId\":\"CNTS_000000000000571\",\n" +
                    "        \"day\":1,\n" +
                    "        \"dayContentsNo\":1\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"contentsId\":\"CNTS_000000000000591\",\n" +
                    "        \"day\":1,\n" +
                    "        \"dayContentsNo\":2\n" +
                    "    }\n" +
                    "]\n")
    })
    @PostMapping(value = "/{travelSeq}")
    public ApiResult<String> saveDay(@CurrentUser String userId,
                                    @PathVariable Long travelSeq,
                                    @RequestBody List<DayDto> dayDtoList) {
        dayService.saveDay(userId, travelSeq, dayDtoList);
        return success("OK!!");
    }
    
    @Operation(summary = "Day 삭제", description = "내 여행계획 중 지정한 Day 삭제. 삭제 시 이후 Day들이 하루씩 앞으로 당겨짐.")
    @Parameters({
            @Parameter(name = "travelSeq", description = "현재 여행의 PK"),
            @Parameter(name = "day", description = "삭제하려는 여행의 day (?일차) 1,2,3 ...")
    })
    @DeleteMapping("/{travelSeq}/{day}")
    public ApiResult<String> deleteDay(@CurrentUser String userId, @PathVariable Long travelSeq, @PathVariable Integer day) {
        dayService.deleteDay(userId, travelSeq, day);
        return success("OK!!");
    }
}
