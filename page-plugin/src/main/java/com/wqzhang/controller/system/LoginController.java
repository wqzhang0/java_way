package com.wqzhang.controller.system;

import com.wqzhang.controller.BaseController;
import com.wqzhang.model.Page;
import com.wqzhang.model.PageData;
import com.wqzhang.service.system.login.LoginManager;
import com.wqzhang.shiro.Const;
import com.wqzhang.shiro.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wqzhang on 2017/9/12.
 *
 * @author wqzhang
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


    @RequestMapping("/toLogin")
    public ModelAndView toLogin() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/index");
        return mv;
    }


    @ResponseBody
    @RequestMapping("/login")
    public Object login() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        UsernamePasswordToken token = new UsernamePasswordToken("wqzhang", "123");
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            resultMap.put("RESULT", "SUCCESS");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            //密码错误
            resultMap.put("RESULT", "FAKSE");
        }

//        loginService.checkLogin(page);
        return resultMap;
    }

    @ResponseBody
    @RequestMapping("/logout")
    public Object logout() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.logout();
            resultMap.put("RESULT", "SUCCESS");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            //密码错误
            resultMap.put("RESULT", "FAKSE");
        }
        return resultMap;
    }

}
