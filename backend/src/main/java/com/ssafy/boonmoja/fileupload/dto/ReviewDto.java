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
//    private String reviewSeq;
    private String id;
    private String text;
    private Long travel;
//    public Review toEntity(){
//        Review review = new Review();
////        review.setReviewSeq(reviewSeq);
////        review.setReviewId(reviewId);
//        review.setText(text);
//        return review;
//    }
}
