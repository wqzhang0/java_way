package com.wqzhang;

import com.wqzhang.model.PageData;
import com.wqzhang.user.service.UserManager;
import com.wqzhang.user.service.impl.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceApplicationTests {

    @Autowired
    UserManager userService;

    @Test
    public void contextLoads() {
        PageData userPd = new PageData();
        userPd.put("STUDENT_ID",1);
        userService.getUser(userPd);
    }

}
