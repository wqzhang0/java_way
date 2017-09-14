package com.wqzhang.proxy;
import java.lang.reflect.Method;

class $Proxy1 implements com.wqzhang.proxy.UserManagerImpl{
    public $Proxy1(InvocationHandler h) {
        this.h = h;
    }
    com.wqzhang.proxy.InvocationHandler h;
    @Override
    public  void deleteUser() {
        try {
        Method md = com.wqzhang.proxy.UserManagerImpl.class.getMethod("deleteUser");
           System.out.println("start");
           h.invoke(this, md);
        }catch(Exception e) {e.printStackTrace();}
    }
    @Override
    public  void addUser() {
        try {
        Method md = com.wqzhang.proxy.UserManagerImpl.class.getMethod("addUser");
           System.out.println("start");
           h.invoke(this, md);
        }catch(Exception e) {e.printStackTrace();}
    }

}