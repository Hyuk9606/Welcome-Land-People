package com.ssafy.boonmoja.api.repository.joinTable;

import com.ssafy.boonmoja.api.entity.joinTable.DayContents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayContentsRepository extends JpaRepository<DayContents, Long> {
    
}
