package com.ssafy.boonmoja.fileupload.service;

import com.ssafy.boonmoja.fileupload.dto.ReviewDto;
import com.ssafy.boonmoja.fileupload.model.Review;
import com.ssafy.boonmoja.fileupload.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

//import org.springframework.transaction.annotation.Transactional; // 이 트렌젝션 아니야ㅜㅜㅜㅜㅜㅜㅜㅜㅜ
import javax.transaction.Transactional; //이거야ㅜㅜㅜㅜㅜㅜㅜ

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepo;

//    @Transactional
//    public String updateReviewText(String id, ReviewDto reviewDto){
////        Review review = reviewRepo.findById(id).orElseThrow(
////                () -> new IllegalAccessError("id 존재안함")
////        );
//        Review review = reviewRepo.findById(id).get();
////        review.updateText(reviewDto);
//
//        return review.getText();
//    }

    public void deleteImage(Long travelId, int imageId){
        Review review = reviewRepo.findByTravel(travelId);
        List<Binary> list = review.getImage();
        if(list != null) {
            list.remove(imageId);
            review.setImage(list);
        }
        reviewRepo.save(review);
    }


}
