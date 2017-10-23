package com.wqzhang.controller;

import com.wqzhang.model.Page;
import com.wqzhang.model.PageData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wqzhang on 2017/8/15.
 *
 * @author wqzhang
 */
public class BaseController {
    protected Page getPage() {
        Page page = new Page();
        return page;
    }

    /**
     * new PageData对象
     *
     * @return
     */
    public PageData getPageData() {
        return new PageData(this.getRequest());
    }

    public ModelAndView getModelAndViwe() {
        return new ModelAndView();
    }

    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

//    @RequestMapping("/")
//    public ModelAndView home() throws Exception {
//        ModelAndView mv = this.getModelAndViwe();
//        mv.setViewName("index");
//        return mv;
//    }
}
