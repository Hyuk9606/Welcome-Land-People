package com.ssafy.boonmoja.api.service;

import com.ssafy.boonmoja.api.dto.UserDto;
import com.ssafy.boonmoja.api.entity.contents.Contents;
import com.ssafy.boonmoja.api.entity.joinTable.UserContents;
import com.ssafy.boonmoja.api.entity.user.User;
import com.ssafy.boonmoja.api.repository.contents.ContentsRepository;
import com.ssafy.boonmoja.api.repository.joinTable.UserContentsRepository;
import com.ssafy.boonmoja.api.repository.user.UserRepository;
import com.ssafy.boonmoja.oauth.entity.RoleType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ContentsRepository contentsRepository;
    private final UserContentsRepository userContentsRepository;
    
    public User getUser(String userId) {
        return userRepository.findByUserId(userId);
    }
    
    public void likeContents(String userId, String contentsId) {
        User user = userRepository.findByUserId(userId);
        Contents contents = contentsRepository.findByContentsIdIs(contentsId);
        
        UserContents userContents = userContentsRepository.findByUserIsAndContentsIs(user, contents);
        if (!user.getUserContents().contains(userContents)) {
            log.info("Like 추가 - user : {}, contents : {}", userId, contentsId);
            userContents = UserContents.builder().user(user).contents(contents).build();
            userContentsRepository.save(userContents);
        }
    }
    public void dislikeContents(String userId, String contentsId) {
        User user = userRepository.findByUserId(userId);
        Contents contents = contentsRepository.findByContentsIdIs(contentsId);
        
        UserContents userContents = userContentsRepository.findByUserIsAndContentsIs(user, contents);
        if (user.getUserContents().contains(userContents)){
            log.info("Like 삭제 - user : {}, contents : {}", userId, contentsId);
            userContentsRepository.delete(userContents);
        }
    }
    
    public void signUpUser(UserDto user){
        userRepository.save(User.builder()
                .userId(user.getUserId())
                .roleType(RoleType.USER)
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .username(user.getUsername())
                .email(user.getUserId())
                .password(passwordEncoder.encode(user.getPassword()))
                .roleType(RoleType.USER)
                .userContents(new ArrayList<>())
                .build()).getUserSeq();
    }
    public List<Contents> getUserLikedContents(String userId){
        User user = userRepository.findByUserId(userId);
        List<Contents> contentsList = userContentsRepository.findByUser(user).stream().map(o->o.getContents()).collect(Collectors.toList());
        return contentsList;
    }
    
}
