package com.wqzhang;

import com.wqzhang.model.PageData;
import com.wqzhang.user.mapper.UserMapper;
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
	UserService userService;

	@Test
	public void contextLoads() {
		userService.getUser(new PageData());
	}

}
