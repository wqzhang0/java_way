package com.wqzhang.proxy;

/**
 * Created by wqzhang on 2017/7/5.
 */
public class UserManager implements UserManagerImpl {
    @Override
    public void addUser() {
        System.out.println("add user");

    }

    @Override
    public void deleteUser() {
        System.out.println("delete user ");

    }
}
