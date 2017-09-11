package com.wqzhang.service.system.impl;

import com.wqzhang.model.Page;
import com.wqzhang.service.system.LoginManager;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wqzhang on 2017/9/12.
 */
@Service
@RequestMapping("loginService")
public class LoginService implements LoginManager {

    public Object checkLogin(Page page) {

        return null;
    }
}
