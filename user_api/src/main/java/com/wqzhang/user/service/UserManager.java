package com.wqzhang.user.service;

import com.wqzhang.model.Page;
import com.wqzhang.model.PageData;

import java.util.List;

/**
 * Created by wqzhang on 2017/9/14.
 */
public interface UserManager {
    void insertUser(PageData pd);

    PageData getUser(PageData pd);

    List<PageData> listAllUser();

    //分页获取用户信息

    /**
     * 分页查询方法,传入page对象,查询需要的结果 但是不能更改Page对象信息
     * 如果需要实现自动封装查询结果数目信息  需要返回PageData对象    将Page封装到PageDate对象里供消费者使用
     */
     List<PageData> userlistPageNoPage(Page page);


     PageData userlistPage(Page page);

}
