package out;

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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TravelTest {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    TravelRepository travelRepository;
    
    @Autowired
    DayRepository dayRepository;
    
    @Autowired
    ContentsRepository contentsRepository;
    
    @Autowired
    DayContentsRepository dayContentsRepository;
    
    @Test
    @Transactional
    @Commit
    public void travelInsertTest(){
        User user = userRepository.findByUserId("test@test.com");
        Travel travel = Travel.builder()
                .travelTitle("여행 여행~")
                .user(user)
                .startAt(LocalDate.now())
                .endAt(LocalDate.now().plusDays(4))
                .build();
        
        travelRepository.save(travel);
    }
    
    @Test
    @Transactional
    @Commit
    public void dayInsertTest(){
        Travel travel = travelRepository.findByTravelSeq(1L);
        dayRepository.save(Day.builder().travel(travel).build());
    }
    
    @Test
    @Transactional
    @Commit
    public void dayContentsTest(){
        Contents contents = contentsRepository.findByContentsIdIs("1");
        Day day = dayRepository.findByDaySeq(1L);
        
        dayContentsRepository.save(DayContents.builder().day(day).contents(contents).build());
    }

}
