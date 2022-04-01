package com.ssafy.boonmoja.api.repository.image;


import com.ssafy.boonmoja.api.entity.image.MapView;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MapViewRepository extends MongoRepository<MapView, String> {

    MapView findByUserAndLoc(String user, Integer loc);
    void deleteByUserAndLoc(String user, Integer loc);
}
