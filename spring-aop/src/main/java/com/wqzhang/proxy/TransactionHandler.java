package com.wqzhang.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by wqzhang on 2017/7/5.
 */
public class TransactionHandler implements InvocationHandler {
    @Override
    public void invoke(Object o, Method m) {
        System.out.println("invoke is called by Proxy  --start");
        try {
            m.invoke(userManager);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println("invoke is called by Proxy  --end");
    }

    UserManager userManager;

    public TransactionHandler(UserManager userManager) {
        this.userManager = userManager;
    }
}
