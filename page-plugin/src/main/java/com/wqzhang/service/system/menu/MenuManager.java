package com.wqzhang.service.system.menu;

/**
 * Created by wqzhang on 2017/9/28.
 *
 * @author wqzhang
 */
public interface MenuManager {
    /**
     * 列出菜单信息
     *
     * @return
     * @throws Exception
     */
    String list() throws Exception;

    /**
     * 获取第一个菜单的信息
     *
     * @return
     * @throws Exception
     */
    String getFirstTreeId() throws Exception;

    /**
     * 根据用户账号 获取对应的菜单
     *
     * @param userAccount
     * @return
     * @throws Exception
     */
    String menuList(String userAccount) throws Exception;
}
