package com.wqzhang.controller.system;

import com.wqzhang.controller.BaseController;
import com.wqzhang.model.Page;
import com.wqzhang.service.system.LoginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by wqzhang on 2017/9/12.
 */
@Controller
@RequestMapping("/loginController")
public class LoginController extends BaseController {


    //    把DAO实现类注入到action的service接口(注意不要是service的实现类)中，注入时不要new 这个注入的类，因为spring会自动注入，如果手动再new的话会出现错误，
    //    然后属性加上@Autowired后不需要getter()和setter()方法，Spring也会自动注入。
    //    当接口存在两个实现类的时候必须使用@Qualifier指定注入哪个实现类，否则可以省略，只写@Autowired。
    @Autowired
    @Qualifier("loginService")
    LoginManager loginService;

    @RequestMapping("/login.do")
    public ModelAndView login(Page page) {
        loginService.checkLogin(page);
        return new ModelAndView();
    }
}
