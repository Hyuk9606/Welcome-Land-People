package com.ssafy.boonmoja.api.dto;

import com.ssafy.boonmoja.api.entity.contents.Contents;
import com.ssafy.boonmoja.api.entity.joinTable.UserContents;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContentsDto {
    
    private String contentsId;
    
    private String title;
    
    private String label;
    
    private String address;
    
    private String roadAddress;
    
    private String introduction;
    
    private String latitude;
    
    private String longitude;
    
    private String postCode;
    
    private String phoneNo;
    
    private String imgPath;
    
    private String thumbnailPath;
    
    public static ContentsDto of(UserContents userContents){
        Contents contents = userContents.getContents();
        return ContentsDto.builder()
                .contentsId(contents.getContentsId())
                .title(contents.getTitle())
                .label(contents.getLabel())
                .address(contents.getAddress())
                .roadAddress(contents.getRoadAddress())
                .introduction(contents.getIntroduction())
                .latitude(contents.getLatitude())
                .longitude(contents.getLongitude())
                .postCode(contents.getPostCode())
                .phoneNo(contents.getPhoneNo())
                .imgPath(contents.getImgPath())
                .thumbnailPath(contents.getThumbnailPath())
                .build();
    }
}
