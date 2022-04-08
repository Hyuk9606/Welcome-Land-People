package com.ssafy.boonmoja.api.repository.joinTable;

import com.ssafy.boonmoja.api.entity.joinTable.DayContents;
import com.ssafy.boonmoja.api.entity.travel.Day;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayContentsRepository extends JpaRepository<DayContents, Long> {
    DayContents findByDay(Day day);
    void deleteAllByDay(Day day);
}
