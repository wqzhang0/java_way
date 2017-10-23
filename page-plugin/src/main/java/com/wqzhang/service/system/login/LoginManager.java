package com.wqzhang.service.system.login;

import com.wqzhang.model.Page;
import com.wqzhang.model.PageData;

import java.util.List;

/**
 * Created by wqzhang on 2017/9/12.
 *
 * @author wqzhang
 */
public interface LoginManager {

    /**
     * 用户登录校验  返回检验结果
     *
     * @param page
     * @return
     */
    Object checkLogin(Page page);

    /**
     * 列出所有用户
     *
     * @return
     */
    List<PageData> listAllUser();

}
