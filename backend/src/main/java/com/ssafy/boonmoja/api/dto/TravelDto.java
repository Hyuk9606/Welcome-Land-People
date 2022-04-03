package com.ssafy.boonmoja.api.dto;

import com.ssafy.boonmoja.api.entity.travel.Travel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Schema(description = "여행계획")
public class TravelDto {
    
    @Schema(description = "기본 키")
    private Long travelSeq;
    
    @Schema(description = "여행 제목")
    private String travelTitle;
    
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    @Schema(description = "여행 시작일")
    private LocalDate startAt;
    
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    @Schema(description = "여행 종료일")
    private LocalDate endAt;
    
    @Builder.Default
    @Schema(description = "종료 여부",defaultValue = "false")
    private Boolean isFinished = false;
    
    public static TravelDto of(Travel travel){
        String userId = travel.getUser().getUserId();
        return TravelDto.builder()
                .travelSeq(travel.getTravelSeq())
                .travelTitle(travel.getTravelTitle())
                .startAt(travel.getStartAt())
                .endAt(travel.getEndAt())
                .isFinished(travel.getIsFinished())
                .build();
    }
}
