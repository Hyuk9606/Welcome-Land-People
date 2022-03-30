package out;

import com.ssafy.boonmoja.api.entity.contents.Contents;
import com.ssafy.boonmoja.api.entity.joinTable.UserContents;
import com.ssafy.boonmoja.api.entity.user.User;
import com.ssafy.boonmoja.api.repository.contents.ContentsRepository;
import com.ssafy.boonmoja.api.repository.joinTable.UserContentsRepository;
import com.ssafy.boonmoja.api.repository.user.UserRepository;
import com.ssafy.boonmoja.api.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LikeTest {
    
    @Autowired
    UserContentsRepository userContentsRepository;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    UserService userService;
    
    @Autowired
    ContentsRepository contentsRepository;
    
    
    @Before
    public void start() {
        System.out.println("---------------테스트 시작---------------");
    }
    
    @After
    public void end() {
        System.out.println("---------------테스트 끝---------------");
    }
    
    @Test
    public void addContents() {
//        Place place = new Place();
//        place.setPlaceName("장소4");
//        contentsRepository.save(Contents.builder().contentsId("1").title("장소1").label("숙소").build());
//        contentsRepository.save(Contents.builder().contentsId("2").title("장소2").label("숙소").build());
//        contentsRepository.save(Contents.builder().contentsId("3").title("장소3").label("숙소").build());
//        contentsRepository.save(Contents.builder().contentsId("4").title("장소4").label("숙소").build());
//        contentsRepository.save(Contents.builder().contentsId("5").title("장소5").label("숙소").build());
        contentsRepository.save(Contents.builder().contentsId("7").title("장소7").label("음식점").build());
    }
    
    @Test
    public void getContentsList() {
        String label = "숙소";
        List<Contents> contentsList = contentsRepository.findByLabelIs(label);
        
        for (Contents c : contentsList) {
            System.out.println(c.getContentsId() + " " + c.getTitle() + " " + c.getLabel());
        }
    }
    
    @Test
    public void addUserContents() {
        Contents contents = contentsRepository.findByContentsIdIs("1");
        User user = userRepository.findByUserId("test@test.com");
        
        userContentsRepository.save(UserContents.builder().user(user).contents(contents).build());
        System.out.println("응?");
    }
    
    @Test
    @Transactional
    public void likeTest() {
        String userId = "test@test.com";
        String contentsId = "4";
        
        userService.likeContents(userId, contentsId);
//        List<UserContents> list = userContentsRepository.findAll();
//        for(UserContents uc : list){
//            System.out.println(uc.getUserContentsSeq());
//        }
//        User user = userRepository.findByUserId(userId);
//        System.out.println(UserDto.of(user).getUserContents());
        
    }
    
    @Test
    @Transactional
    public void existUserContentsTest(){
        User user = userRepository.findByUserId("test@test.com");
//        List<UserContents> list = user.getUserContents();
//        for(UserContents uc : list){
//            System.out.println(uc.getUserContentsSeq());
//        }
        Contents contents = contentsRepository.findByContentsIdIs("4");
        UserContents userContents = userContentsRepository.findByUserIsAndContentsIs(user,contents);
        System.out.println("===========");
        System.out.println(user.getUserContents().contains(userContents));
    }
}
