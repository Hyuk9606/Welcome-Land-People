package com.ssafy.boonmoja.api.repository.user;

import com.ssafy.boonmoja.api.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserId(String userId);
    User findByUsername(String username);
}
