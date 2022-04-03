package com.ssafy.boonmoja.api.controller.user;

import com.ssafy.boonmoja.api.dto.UserDto;
import com.ssafy.boonmoja.api.entity.contents.Contents;
import com.ssafy.boonmoja.api.entity.user.User;
import com.ssafy.boonmoja.api.service.UserRefreshTokenService;
import com.ssafy.boonmoja.api.service.UserService;
import com.ssafy.boonmoja.common.ApiResponse;
import com.ssafy.boonmoja.oauth.annotation.CurrentUser;
import com.ssafy.boonmoja.oauth.token.AuthTokenProvider;
import com.ssafy.boonmoja.utils.CookieUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.ssafy.boonmoja.utils.ApiUtils.ApiResult;
import static com.ssafy.boonmoja.utils.ApiUtils.success;

@Tag(name = "user", description = "유저관리 API")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    
    private final UserService userService;
    private final UserRefreshTokenService userRefreshTokenService;
    private final AuthTokenProvider tokenProvider;
    
    @Operation(summary = "유저 정보 조회", description = "header에 있는 AuthenticationToken으로," +
            " 로그인한 유저의 정보를 조회합니다. 토큰이 없다면 로그인하는 과정이 필요합니다.")
    @GetMapping
    public ApiResponse getUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUser(principal.getUsername());
        log.info("user조회 - {} ", user);
        
        return ApiResponse.success("user", UserDto.of(user));
    }
    
    @Operation(summary = "회원가입", description = "회원가입 요청")
    @Parameters({
            @Parameter(name = "user", description = "{\n" +
                    "    \"user_id\" : \"test5@test.com\",\n" +
                    "    \"username\" : \"임혁\",\n" +
                    "    \"password\" : \"test5@test.com\"\n" +
                    "}"),
    })
    @PostMapping("/join")
    public ApiResult join(@RequestBody UserDto user) {
        log.info("회원가입 - {}", user);
        userService.signUpUser(user);
        return success("OK!!");
    }
    
    @Operation(summary = "로그아웃", description = "로그아웃 요청")
    @Parameters({
            @Parameter(name = "userId", description = "유저의 아이디 ex) test@test.com"),
    })
    @PostMapping("/logout")
    public ApiResult<String> logout(HttpServletRequest request, HttpServletResponse response, @CurrentUser String userId) {
        log.info("로그아웃 - {}", userId);
        userRefreshTokenService.deleteToken(userId);
        CookieUtil.deleteCookie(request, response, "refresh_token");
        return success("OK!!");
    }
    
    @Operation(summary = "좋아요", description = "좋아요버튼 클릭 시 동작(토글방식)")
    @Parameters({
            @Parameter(name = "contentsId", description = "컨텐츠의 PK"),
    })
    @GetMapping("/like/{contentsId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ApiResult<String> doLikeContents(@CurrentUser String userId, @PathVariable String contentsId) {
        userService.likeContents(userId, contentsId);
        return success("OK!!");
    }
    
    @Operation(summary = "찜목록", description = "좋아요 한 목록 조회")
    @GetMapping("/contents")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ApiResult<List<Contents>> getUserLikedContents(@CurrentUser String userId){
        List<Contents> contentsList = userService.getUserLikedContents(userId);
        return success(contentsList);
    }
    
}
