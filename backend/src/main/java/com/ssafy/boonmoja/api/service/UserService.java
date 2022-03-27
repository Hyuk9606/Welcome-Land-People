package com.ssafy.boonmoja.api.service;

import com.ssafy.boonmoja.api.entity.contents.Contents;
import com.ssafy.boonmoja.api.entity.joinTable.UserContents;
import com.ssafy.boonmoja.api.entity.user.User;
import com.ssafy.boonmoja.api.repository.contents.ContentsRepository;
import com.ssafy.boonmoja.api.repository.joinTable.UserContentsRepository;
import com.ssafy.boonmoja.api.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
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
        } else {
            log.info("Like 삭제 - user : {}, contents : {}", userId, contentsId);
            userContentsRepository.delete(userContents);
        }
    }
//    public List<Place> getUserLikePlaces(String userId){
//        User user = userRepository.findByUserId(userId);
//
//    }
    
}
