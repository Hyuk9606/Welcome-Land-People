package com.ssafy.boonmoja.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Schema(description = "여행 일정")
public class DayDto {
    
    @Schema(description = "여행 계획 PK")
    Long travelSeq;
    
    @Schema(description = "몇일차?")
    Integer day;
    
    @Schema(description = "컨텐츠 PK")
    String contentsId;
    
    @Schema(description = "컨텐츠의 이름")
    String title;
    
    @Schema(description = "컨텐츠 순서")
    int dayContentsNo;
}
