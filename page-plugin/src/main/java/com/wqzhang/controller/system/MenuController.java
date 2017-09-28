package com.wqzhang.controller.system;

import com.wqzhang.controller.BaseController;
import com.wqzhang.model.Page;
import com.wqzhang.model.PageData;
import com.wqzhang.service.system.menu.MenuManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wqzhang on 2017/9/27.
 */
@Controller
@RequestMapping("menuController")
public class MenuController extends BaseController {

    @Resource(name = "menuService")
    MenuManager menuService;

    @RequestMapping("list")
    public ModelAndView list(Page pg) throws Exception {
        ModelAndView mv = this.getModelAndViwe();
       String str = menuService.list();
        mv.addObject("menuList", str);
        mv.setViewName("/menu/menu_list");
        return mv;
    }
}
