package com.ssafy.boonmoja.api.repository.image;

import com.ssafy.boonmoja.api.entity.image.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review, String> {
    void deleteByTravel(Long travel);
    Review findByTravel(Long travel);
}
