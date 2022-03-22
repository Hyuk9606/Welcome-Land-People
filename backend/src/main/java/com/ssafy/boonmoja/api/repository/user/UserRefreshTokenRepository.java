package com.ssafy.boonmoja.api.repository.user;

import com.ssafy.boonmoja.api.entity.user.UserRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken, Long> {
    UserRefreshToken findByUserId(String userId);
    UserRefreshToken findByUserIdAndRefreshToken(String userId, String refreshToken);
//    Long deleteUserRefreshTokenBy(Long refreshTokenSeq);
}
