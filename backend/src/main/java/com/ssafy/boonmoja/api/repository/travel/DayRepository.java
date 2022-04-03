package com.ssafy.boonmoja.api.repository.travel;

import com.ssafy.boonmoja.api.entity.travel.Day;
import com.ssafy.boonmoja.api.entity.travel.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DayRepository extends JpaRepository<Day,Long> {
    Day findByDaySeq(Long daySeq);
    List<Day> findByTravel(Travel travel);
    Day findByDayAndTravel(Integer day, Travel travel);
}
