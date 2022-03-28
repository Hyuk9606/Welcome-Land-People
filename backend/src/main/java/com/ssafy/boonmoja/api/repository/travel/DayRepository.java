package com.ssafy.boonmoja.api.repository.travel;

import com.ssafy.boonmoja.api.entity.travel.Day;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayRepository extends JpaRepository<Day,Long> {
    Day findByDaySeq(Long daySeq);
}
