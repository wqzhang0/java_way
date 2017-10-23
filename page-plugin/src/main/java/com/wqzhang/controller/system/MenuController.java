package com.wqzhang.controller.system;

import com.wqzhang.controller.BaseController;
import com.wqzhang.model.Page;
import com.wqzhang.model.PageData;
import com.wqzhang.service.system.menu.MenuManager;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wqzhang on 2017/9/27.
 *
 * @author wqzhang
 */
@Controller
@RequestMapping("menuController")
public class MenuController extends BaseController {

    @Resource(name = "menuService")
    MenuManager menuService;


    @RequiresPermissions("sys:menu:list")
    @RequestMapping("list")
    public ModelAndView list(Page pg) throws Exception {
        ModelAndView mv = this.getModelAndViwe();
        String str = menuService.list();
        String firstTreeId = menuService.getFirstTreeId();
        mv.addObject("menuList", str);
        mv.addObject("firstTreeId", firstTreeId);
        mv.setViewName("/menu/menu_list");
        return mv;
    }

    /**
     * 对角色进行权限分配
     *
     * @param pg
     * @return
     * @throws Exception
     */
    @RequestMapping("assignPermissions")
    @ResponseBody
    public Object assignPermissions(Page pg) throws Exception {
//        获取用户登录的id  角色
//        对这个角色下的所有权限置为空
//        再重新分配权限
//        涉及到redis读取
//        shiro保存用户id
//        shiro鉴权
//        事务回滚
        return 0;
    }

    @RequestMapping("userMenu")
    @RequiresPermissions("system:menuController:userMenu")
    public ModelAndView userMenu(Page pg) throws Exception {
        ModelAndView mv = this.getModelAndViwe();
//        String str = menuService.menuList(""); //这里填入页面输入的账号
//        mv.addObject("menuList", str);
        mv.setViewName("/menu/user_menu_list");
        return mv;
    }

}
