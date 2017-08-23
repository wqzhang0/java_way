package com.wqzhang.proxy;

import java.lang.reflect.Method;

/**
 * Created by wqzhang on 2017/7/5.
 */
public class TestMain {
    public static void main(String[] args) {

        InvocationHandler invocationHandler = new TransactionHandler(new UserManager());

        try {
            UserManagerImpl userMgr = (UserManagerImpl) Proxy.newProxyInstance(UserManagerImpl.class, invocationHandler);
            userMgr.addUser();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
