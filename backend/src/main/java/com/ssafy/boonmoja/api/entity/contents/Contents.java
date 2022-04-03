package com.ssafy.boonmoja.api.entity.contents;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "CONTENTS")
public class Contents implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "CONTENTS_ID")
    private String contentsId;
    
    @Column(name = "TITLE")
    private String title;
    
    @Column(name="LABEL")
    private String label;
    
    @Column(name="ADDRESS")
    private String address;
    
    @Column(name="ROAD_ADDRESS")
    private String roadAddress;
    
    @Column(name="INTRODUCTION",length = 1000)
    private String introduction;
    
    @Column(name="LATITUDE")
    private String latitude;
    
    @Column(name="LONGITUDE")
    private String longitude;
    
    @Column(name="POST_CODE")
    private String postCode;
    
    @Column(name="PHONE_NO")
    private String phoneNo;
    
    @Column(name="IMG_PATH")
    private String imgPath;
    
    @Column(name="THUMBNAIL_PATH")
    private String thumbnailPath;
    
    @Override
    public String toString() {
        return "Contents{" +
                "contentsId='" + contentsId + '\'' +
                ", title='" + title + '\'' +
                ", label='" + label + '\'' +
                ", address='" + address + '\'' +
                ", roadAddress='" + roadAddress + '\'' +
                ", introduction='" + introduction + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", postCode='" + postCode + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", thumbnailPath='" + thumbnailPath + '\'' +
                '}';
    }
}
