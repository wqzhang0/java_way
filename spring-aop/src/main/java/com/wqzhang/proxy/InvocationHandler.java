package com.wqzhang.proxy;

import java.lang.reflect.Method;

/**
 * Created by wqzhang on 2017/7/5.
 */
public interface InvocationHandler {
    void invoke(Object o, Method m);
}
