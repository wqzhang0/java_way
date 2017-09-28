package com.wqzhang.controller.demo;

import com.wqzhang.controller.BaseController;
import com.wqzhang.model.Page;
import com.wqzhang.model.PageData;
import com.wqzhang.service.system.login.LoginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by wqzhang on 2017/9/18.
 * demo页面跳转页
 */
@Controller
@RequestMapping("/demoShowController")
public class DemoShowController extends BaseController {


    @Autowired
    @Qualifier("loginService")
    LoginManager loginService;

    @RequestMapping("/goDataTablesDemoAjax")
    public ModelAndView goDataTablesDemo(Page page) {
        ModelAndView mv = new ModelAndView();
        List<PageData> users = loginService.listAllUser();
        //热部署 测试
        users.get(0).put("NAME","HOT boot success");
        mv.addObject("users", users);
        mv.setViewName("/test/datatableDemo");
        return mv;
    }

    @RequestMapping("/goTreeDemo")
    public ModelAndView goDataTablesDemo() {
        ModelAndView mv = this.getModelAndViwe();

        mv.setViewName("/menu/menu_list");
        return mv;
    }
}
