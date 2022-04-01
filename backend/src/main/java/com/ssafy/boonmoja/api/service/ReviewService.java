package com.ssafy.boonmoja.api.service;

import com.ssafy.boonmoja.api.entity.image.Review;
import com.ssafy.boonmoja.api.repository.image.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;

import java.util.List;

//import org.springframework.transaction.annotation.Transactional; // 이 트렌젝션 아니야ㅜㅜㅜㅜㅜㅜㅜㅜㅜ


@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepo;

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
