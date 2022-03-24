package com.ssafy.boonmoja.api.repository.joinTable;

import com.ssafy.boonmoja.api.entity.joinTable.UserPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPlaceRepository extends JpaRepository<UserPlace,Long> {

}
