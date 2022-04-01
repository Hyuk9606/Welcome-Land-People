package com.ssafy.boonmoja.fileupload.repository;

import com.ssafy.boonmoja.fileupload.model.Review;
import org.bson.types.ObjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.Repository;

public interface ReviewRepository extends MongoRepository<Review, String> {
    void deleteByTravel(Long travel);
    Review findByTravel(Long travel);
}
