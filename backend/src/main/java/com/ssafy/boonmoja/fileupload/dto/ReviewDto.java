package com.ssafy.boonmoja.fileupload.dto;//package com.ssafy.boonmoja.fileupload.dto;
//
import com.ssafy.boonmoja.fileupload.model.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

@Setter
@Getter
@NoArgsConstructor
public class ReviewDto {

    private String id;
    private String text;
    private Long travel;

}
