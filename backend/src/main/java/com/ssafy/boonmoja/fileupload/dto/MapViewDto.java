package com.ssafy.boonmoja.fileupload.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.Binary;

@Setter
@Getter
@NoArgsConstructor
public class MapViewDto {

    private String mapType;
    private String color;
    private Binary image;

}
