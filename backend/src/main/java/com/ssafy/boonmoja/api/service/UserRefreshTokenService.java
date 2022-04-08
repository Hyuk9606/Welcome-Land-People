package com.ssafy.boonmoja.api.service;

import com.ssafy.boonmoja.api.entity.user.UserRefreshToken;
import com.ssafy.boonmoja.api.repository.user.UserRefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRefreshTokenService {

    private final UserRefreshTokenRepository userRefreshTokenRepository;

    public Long deleteToken(String userId){
        UserRefreshToken refreshToken = userRefreshTokenRepository.findByUserId(userId);
        userRefreshTokenRepository.deleteById(refreshToken.getRefreshTokenSeq());
        if(userRefreshTokenRepository.findByUserId(userId) != null){
            return -1L;
        }
        return 1L;
    }

}
