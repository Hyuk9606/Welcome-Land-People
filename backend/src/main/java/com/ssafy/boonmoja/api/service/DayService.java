package com.ssafy.boonmoja.api.service;

import com.ssafy.boonmoja.api.dto.DayDto;
import com.ssafy.boonmoja.api.entity.contents.Contents;
import com.ssafy.boonmoja.api.entity.joinTable.DayContents;
import com.ssafy.boonmoja.api.entity.travel.Day;
import com.ssafy.boonmoja.api.entity.travel.Travel;
import com.ssafy.boonmoja.api.entity.user.User;
import com.ssafy.boonmoja.api.repository.contents.ContentsRepository;
import com.ssafy.boonmoja.api.repository.joinTable.DayContentsRepository;
import com.ssafy.boonmoja.api.repository.travel.DayRepository;
import com.ssafy.boonmoja.api.repository.travel.TravelRepository;
import com.ssafy.boonmoja.api.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DayService {
    
    private final UserRepository userRepository;
    private final TravelRepository travelRepository;
    private final DayRepository dayRepository;
    private final DayContentsRepository dayContentsRepository;
    private final ContentsRepository contentsRepository;
    
    
    @Transactional
    public void saveDay(String userId, Long travelSeq, List<DayDto> dayDtoList) {
        User user = userRepository.findByUserId(userId);
        Travel travel = null;
        int maxDay = 0;
        if(travelSeq == null){
            Travel tmp = Travel.builder()
                    .user(user)
                    .travelTitle("제목을 입력하세요")
                    .startAt(LocalDate.now())
                    .endAt(LocalDate.now())
                    .build();
            travel = travelRepository.save(tmp);
        }
        else{
            travel = travelRepository.findByTravelSeq(travelSeq);
        }
        
        if (!travel.getUser().getUserSeq().equals(user.getUserSeq())) return;
        
        List<Day> days = dayRepository.findByTravel(travel);
        
        if (!days.isEmpty()) {
            for (Day day : days) {
                dayContentsRepository.deleteAllByDay(day);
                dayRepository.delete(day);
            }
        }
        
        Collections.sort(dayDtoList, (o1, o2) -> {
            if (o1.getDay().equals(o2.getDay())) return Integer.compare(o1.getDayContentsNo(), o2.getDayContentsNo());
            return Integer.compare(o1.getDay(), o2.getDay());
        });
        
        maxDay = dayDtoList.get(dayDtoList.size()-1).getDay();
        travel.setEndAt(travel.getStartAt().plusDays(maxDay-1));
        travelRepository.save(travel);
        
        Day day = null;
        for (DayDto dayDto : dayDtoList) {
            Contents contents = contentsRepository.findByContentsIdIs(dayDto.getContentsId());
            
            if (dayRepository.findByDayAndTravel(dayDto.getDay(), travel) == null)
                dayRepository.save(Day.builder().day(dayDto.getDay()).travel(travel).build());
            
            if (day == null || !day.getDay().equals(dayDto.getDay()))
                day = dayRepository.findByDayAndTravel(dayDto.getDay(), travel);
            
            dayContentsRepository.save(DayContents.builder().day(day).contents(contents).dayContentsNo(dayDto.getDayContentsNo()).build());
        }
        
    }
    
    @Transactional
    public void deleteDay(String userId, Long travelSeq, Integer day) {
        User user = userRepository.findByUserId(userId);
        Travel travel = travelRepository.findByTravelSeq(travelSeq);
        
        if (!travel.getUser().equals(user)) return;
        
        List<Day> days = dayRepository.findByTravel(travel);
        
        if (day < days.size()) {
            Day target = dayRepository.findByDayAndTravel(day, travel);
            DayContents targetContents = dayContentsRepository.findByDay(target);
            if (targetContents != null) dayContentsRepository.delete(targetContents);
            dayRepository.delete(target);
            
            for (Day tmp : days) {
                if (tmp.getDay() > day) {
                    tmp.setDay(tmp.getDay() - 1);
                    dayRepository.save(tmp);
                }
            }
        }
    }
    
    public List<DayDto> getTravelDay(String userId, Long travelSeq) {
        User user = userRepository.findByUserId(userId);
        Travel travel = travelRepository.findByTravelSeq(travelSeq);
        
        if (!travel.getUser().equals(user)) return null;
        
        List<Day> days = dayRepository.findByTravel(travel);
        
        List<DayDto> dayDtos = new ArrayList<>();
        for (Day tmp : days) {
            DayContents dayContents = dayContentsRepository.findByDay(tmp);
            DayDto dayDto = DayDto.builder().travelSeq(travelSeq).day(tmp.getDay()).contentsId(dayContents.getContents().getContentsId()).title(dayContents.getContents().getTitle()).dayContentsNo(dayContents.getDayContentsNo()).build();
            dayDtos.add(dayDto);
        }
        return dayDtos;
    }
    
}
