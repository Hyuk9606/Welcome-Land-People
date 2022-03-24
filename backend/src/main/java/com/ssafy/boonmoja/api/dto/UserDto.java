package com.ssafy.boonmoja.api.dto;

import com.ssafy.boonmoja.api.entity.joinTable.UserPlace;
import com.ssafy.boonmoja.api.entity.user.User;
import com.ssafy.boonmoja.oauth.entity.ProviderType;
import com.ssafy.boonmoja.oauth.entity.RoleType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Entity로 선언되어있는 User 객체는 Join되어 만들어지는 Collection인 UserPlace를 Field로 가지고 있기 때문에
 * Frontend 단으로 보낼 수 없음.
 *
 * User의 UserPlace는 List<UserPlace>타입
 * UserDto의 UserPlace는 List<Long> 타입
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer userSeq;
    
    private String userId;
    
    private String username;
    
    private String password;
    
    private String email;
    
    private ProviderType providerType;
    
    private RoleType roleType;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime modifiedAt;
    
    private List<Long> userPlaces = new ArrayList<>();
    
    
    public static UserDto of(User user){
        List<Long> userPlaces = user.getUserPlaces().stream().map(UserPlace::getUserPlaceSeq).collect(Collectors.toList());
        
        return UserDto.builder()
                .userSeq(user.getUserSeq())
                .userId(user.getUserId())
                .username(user.getUsername())
                .password("")
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .modifiedAt(user.getModifiedAt())
                .providerType(user.getProviderType())
                .roleType(user.getRoleType())
                .userPlaces(userPlaces)
                .build();
    }
}
