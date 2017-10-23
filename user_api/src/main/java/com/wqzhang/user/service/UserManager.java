package com.wqzhang.user.service;

import com.wqzhang.model.Page;
import com.wqzhang.model.PageData;

import java.util.List;
import java.util.Set;

/**
 * Created by wqzhang on 2017/9/14.
 *
 * @author wqzhang
 */
public interface UserManager {

    /**
     * 增加用户
     *
     * @param pd
     */
    void insertUser(PageData pd);

    /**
     * 获取用户信息
     *
     * @param pd
     * @return
     */
    PageData getUser(PageData pd);

    /**
     * 列车所有的用户
     *
     * @return
     */
    List<PageData> listAllUser();


    /**
     * 分页查询方法,传入page对象,查询需要的结果 但是不能更改Page对象信息
     * 如果需要实现自动封装查询结果数目信息  需要返回PageData对象    将Page封装到PageDate对象里供消费者使用
     *
     * @param page
     * @return List<PageData>
     */
    List<PageData> userlistPageNoPage(Page page);


    /**
     * 用户信息列表(分页实现)
     *
     * @param page
     * @return
     */
    PageData userlistPage(Page page);

    /**
     * 根据用户账号获取用户信息
     *
     * @param acccount
     * @return
     */
    PageData getUserByAccount(String acccount);

    /**
     * 根据用户id获取能访问的菜单权限
     *
     * @param userId
     * @return
     */
    Set<String> listPerms(Long userId);


}
