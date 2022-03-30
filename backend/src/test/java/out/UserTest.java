package out;


import com.ssafy.boonmoja.api.dto.UserDto;
import com.ssafy.boonmoja.api.entity.joinTable.UserContents;
import com.ssafy.boonmoja.api.entity.user.User;
import com.ssafy.boonmoja.api.repository.contents.ContentsRepository;
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

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    UserService userService;
    
    @Autowired
    ContentsRepository contentsRepository;
    
    @Before
    public void start() {
        System.out.println("---------------테스트 싀작---------------");
    }
    
    @After
    public void end() {
        System.out.println("---------------테스트 끝---------------");
    }
    
    @Test
    @Transactional
    public void getUserTest(){
//        User user = userService.getUser("test@test.com");
        User user = userService.getUser("skdltm117@nate.com");
        List<UserContents> userContents = user.getUserContents();
        for(UserContents c : userContents){
            System.out.print(c.getContents().getContentsId() + " ");
        }
        System.out.println(UserDto.of(user));
        
        
    }

}
