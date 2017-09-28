package com.wqzhang.service.system.login.impl;

import com.wqzhang.model.Page;
import com.wqzhang.model.PageData;
import com.wqzhang.service.system.login.LoginManager;
import com.wqzhang.user.service.UserManager;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wqzhang on 2017/9/12.
 */
@Service
@RequestMapping("loginService")
public class LoginService implements LoginManager {
    @Resource(name = "userService")
    private UserManager userService;

    public Object checkLogin(Page page) {

        PageData userInfo = userService.getUser(page.getPd());
        System.out.print(userInfo.size());
        return userInfo;
    }

    public List<PageData> listAllUser() {
        return userService.listAllUser();
    }
}
