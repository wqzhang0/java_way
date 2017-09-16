package com.wqzhang.service.system.impl;

import com.wqzhang.model.Page;
import com.wqzhang.model.PageData;
import com.wqzhang.service.system.LoginManager;
import com.wqzhang.user.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
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

        PageData userInfo = PageData userService.getUser(page.getPd());
        System.out.print(userInfo.size());
        return userInfo;
    }
}
