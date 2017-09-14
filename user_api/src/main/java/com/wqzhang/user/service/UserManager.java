package com.wqzhang.user.service;

import com.wqzhang.model.PageData;

/**
 * Created by wqzhang on 2017/9/14.
 */
public interface UserManager {
    void insertUser(PageData pd);

    PageData getUser(PageData pd);

}
