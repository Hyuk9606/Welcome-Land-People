package com.ssafy.boonmoja.api.dto.review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResDto {
    private String text;
    private List<String> image;
    private Long travel;
}
