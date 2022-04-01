package com.ssafy.boonmoja.fileupload.repository;

import com.ssafy.boonmoja.fileupload.model.MapView;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MapViewRepository extends MongoRepository<MapView, String> {

    MapView findByUserAndLoc(String user, Integer loc);
    void deleteByUserAndLoc(String user, Integer loc);
}
