package com.ssafy.boonmoja.api.repository.travel;

import com.ssafy.boonmoja.api.entity.travel.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelRepository extends JpaRepository<Travel, Long> {
    Travel findByTravelSeq(Long trableSeq);
}
