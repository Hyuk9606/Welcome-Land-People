package com.ssafy.boonmoja.api.controller.user;

import com.ssafy.boonmoja.api.entity.user.User;
import com.ssafy.boonmoja.api.repository.user.UserRepository;
import com.ssafy.boonmoja.api.service.UserRefreshTokenService;
import com.ssafy.boonmoja.api.service.UserService;
import com.ssafy.boonmoja.common.ApiResponse;
import com.ssafy.boonmoja.oauth.entity.RoleType;
import com.ssafy.boonmoja.oauth.token.AuthTokenProvider;
import com.ssafy.boonmoja.utils.CookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    
    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRefreshTokenService userRefreshTokenService;
    private final AuthTokenProvider tokenProvider;
    
    @GetMapping
    public ApiResponse getUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUser(principal.getUsername());
        log.info("user조회 - {} ", user.getUsername());
        
        return ApiResponse.success("user", user);
    }
    
    @PostMapping("/join")
    public ApiResponse join(@RequestBody Map<String, String> user) {
        log.info("회원가입 - {}",user.get("user_id"));
        int result = userRepository.save(User.builder()
                .userId(user.get("user_id"))
                .roleType(RoleType.USER)
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .username(user.get("username"))
                .email(user.get("user_id"))
                .password(passwordEncoder.encode(user.get("password")))
                .roleType(RoleType.USER)
                .build()).getUserSeq();
        
        return ApiResponse.success("data","ok");
    }
    @PostMapping("/logout")
    public ApiResponse logout(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,String> user){
        log.info("로그아웃 - {}",user.get("user_id"));
        long isDelete = userRefreshTokenService.deleteToken(user.get("user_id"));
        if(isDelete == 1){
            CookieUtil.deleteCookie(request, response, "refresh_token");
            return ApiResponse.success("data","success");
        }
        return ApiResponse.fail();

    }
    
//    @PostMapping("/login")
//    public ApiResponse login(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            @RequestBody AuthReqModel authReqModel
//    ) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        authReqModel.getId(),
//                        authReqModel.getPassword()
//                )
//        );
//        log.info("로그인 시도 userId - {}", authReqModel.getId());
//        String userId = authReqModel.getId();
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        Date now = new Date();
//        AuthToken accessToken = tokenProvider.createAuthToken(
//                userId,
//                ((UserPrincipal) authentication.getPrincipal()).getRoleType().getCode(),
//                new Date(now.getTime() + appProperties.getAuth().getTokenExpiry())
//        );
//
//        long refreshTokenExpiry = appProperties.getAuth().getRefreshTokenExpiry();
//        AuthToken refreshToken = tokenProvider.createAuthToken(
//                appProperties.getAuth().getTokenSecret(),
//                new Date(now.getTime() + refreshTokenExpiry)
//        );
//
//        // userId refresh token 으로 DB 확인
//        UserRefreshToken userRefreshToken = userRefreshTokenRepository.findByUserId(userId);
//        if (userRefreshToken == null) {
//            // 없는 경우 새로 등록
//            userRefreshToken = new UserRefreshToken(userId, refreshToken.getToken());
//            userRefreshTokenRepository.saveAndFlush(userRefreshToken);
//        } else {
//            // DB에 refresh 토큰 업데이트
//            userRefreshToken.setRefreshToken(refreshToken.getToken());
//        }
//
//        int cookieMaxAge = (int) refreshTokenExpiry / 60;
//        CookieUtil.deleteCookie(request, response, REFRESH_TOKEN);
//        CookieUtil.addCookie(response, REFRESH_TOKEN, refreshToken.getToken(), cookieMaxAge);
//
//        return ApiResponse.success("token", accessToken.getToken());
//    }
}
