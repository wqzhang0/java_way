package com.wqzhang.user.service.impl;

import com.wqzhang.model.PageData;
import com.wqzhang.user.mapper.UserMapper;
import com.wqzhang.user.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by wqzhang on 2017/9/14.
 */
@Service("userService")
public class UserService implements UserManager {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void insertUser(PageData pd) {
    }

    @Override
    public PageData getUser(PageData pd) {

        PageData results =   userMapper.getUser(pd);

        System.out.print(results.size());
        return results;
    }

}
