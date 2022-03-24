package com.ssafy.boonmoja.api.repository.place;

import com.ssafy.boonmoja.api.entity.place.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    Place findByPlaceName(String name);
//    Place findByPlaceId(String placeId);
}
