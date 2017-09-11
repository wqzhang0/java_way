package com.wqzhang.service.system;

import com.wqzhang.model.Page;

/**
 * Created by wqzhang on 2017/9/12.
 */
public interface LoginManager {

    /**
     * 用户登录校验  返回检验结果
     * @return
     */
    Object checkLogin(Page page);
}
