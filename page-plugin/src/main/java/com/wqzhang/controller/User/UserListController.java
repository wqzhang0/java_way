package com.wqzhang.controller.User;

import com.wqzhang.controller.BaseController;
import com.wqzhang.model.Page;
import com.wqzhang.model.PageData;
import com.wqzhang.user.service.UserManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wqzhang on 2017/9/20.
 */
@Controller
@RequestMapping("/userListController")
public class UserListController extends BaseController {

    @Resource(name = "userService")
    private UserManager userService;

    @RequestMapping("/list")
    public ModelAndView list(Page page) {
        PageData resultPageData = userService.userlistPage(page);
        ModelAndView mv = this.getModelAndViwe();
        page = (Page) resultPageData.get("page");
        mv.addObject("list", resultPageData.get("list"));
        mv.setViewName("/user/user_list");
        mv.addObject("page", page);
        return mv;
    }

}
