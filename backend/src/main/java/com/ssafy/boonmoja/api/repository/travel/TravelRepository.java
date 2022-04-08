package com.ssafy.boonmoja.api.repository.travel;

import com.ssafy.boonmoja.api.entity.travel.Travel;
import com.ssafy.boonmoja.api.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelRepository extends JpaRepository<Travel, Long> {
    Travel findByTravelSeq(Long trableSeq);
    List<Travel> findByUser(User user);
    
}
