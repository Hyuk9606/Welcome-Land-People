package com.ssafy.boonmoja.api.repository.joinTable;

import com.ssafy.boonmoja.api.entity.contents.Contents;
import com.ssafy.boonmoja.api.entity.joinTable.UserContents;
import com.ssafy.boonmoja.api.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserContentsRepository extends JpaRepository<UserContents,Long> {
    UserContents findByUserIsAndContentsIs(User user, Contents contents);
    List<UserContents> findByUser(User user);
}
