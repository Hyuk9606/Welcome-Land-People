package com.ssafy.boonmoja.api.service;

import com.ssafy.boonmoja.api.dto.TravelDto;
import com.ssafy.boonmoja.api.entity.travel.Travel;
import com.ssafy.boonmoja.api.entity.user.User;
import com.ssafy.boonmoja.api.repository.travel.TravelRepository;
import com.ssafy.boonmoja.api.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TravelService {
    
    private final UserRepository userRepository;
    private final TravelRepository travelRepository;
    
    public List<TravelDto> getUserTravel(String userId) {
        User user = userRepository.findByUserId(userId);
        List<TravelDto> travelList = travelRepository.findByUser(user).stream().map(o -> TravelDto.of(o)).collect(Collectors.toList());
        return travelList;
    }
    
    public void insertTravel(String userId, Travel travel) {
        User user = userRepository.findByUserId(userId);
        travel.setUser(user);
        travelRepository.save(travel);
    }
    
    public void deleteTravel(String userId, Long travelSeq) {
        User user = userRepository.findByUserId(userId);
        Travel travel = travelRepository.findByTravelSeq(travelSeq);
        if (travel.getUser().equals(user))
            travelRepository.delete(travel);
    }
    
    @Transactional
    public void updateTravel(String userId, Travel travel) {
        User user = userRepository.findByUserId(userId);
        if (travel.getUser().equals(user)) {
            travelRepository.save(travel);
        }
    }
    
    @Transactional
    public void travelFinish(String userId, Long travelSeq) {
        User user = userRepository.findByUserId(userId);
        Travel travel = travelRepository.findByTravelSeq(travelSeq);
        if (travel.getUser().equals(user)) {
            travel.setIsFinished(!travel.getIsFinished());
            travelRepository.save(travel);
        }
    }
}
