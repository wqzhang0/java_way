package com.wqzhang.user.service.impl;

import com.wqzhang.model.Page;
import com.wqzhang.model.PageData;
import com.wqzhang.user.mapper.UserMapper;
import com.wqzhang.user.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wqzhang on 2017/9/14.
 */
@Service("userService")
public class UserService implements UserManager {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void insertUser(PageData pd) {
        userMapper.insertUser(pd);
    }

    @Override
    public PageData getUser(PageData pd) {

        PageData results = userMapper.getUser(pd);

        System.out.print(results.size());
        return results;
    }

    @Override
    public List<PageData> listAllUser() {
        return userMapper.listAllUser();
    }

    /*
    分页查询方法,传入page对象,查询需要的结果
    但是不能更改Page对象信息

    如果需要实现自动封装查询结果数目信息  需要返回PageData对象    将Page封装到PageDate对象里供消费者使用
     */
    @Override
    public List<PageData> userlistPageNoPage(Page page) {
        List<PageData> pds = userMapper.userlistPage(page);
        return pds;
    }

    @Override
    public PageData userlistPage(Page page) {
        PageData pd = new PageData();
        List<PageData> users = userMapper.userlistPage(page);
        pd.put("list", users);
        pd.put("page", page);
        return pd;
    }

    @Override
    public PageData getUserByAccount(String acccount) {
        PageData pd = userMapper.getUserByAccount(acccount);
        return pd;
    }

    @Override
    public Set<String> listPerms(Long userId) {
        List<PageData> perms = userMapper.listPermsByUserId(userId);

        Set<String> setPerms = new HashSet<>();
        for (PageData pd : perms) {
            setPerms.add(pd.getString("PERM"));
        }
        return setPerms;
    }


}
