package com.wqzhang.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * @author wqzhang
 */
public class ShiroUtils {
    public static Subject getSubjct() {
        return SecurityUtils.getSubject();
    }


//    public static SysUserDO getUser() {
//        return (SysUserDO) getSubjct().getPrincipal();
//    }

//    public static Long getUserId() {
//        return getUser().getUserId();
//    }

    public static void logout() {
        getSubjct().logout();
    }

    public static Session getSession() {
        return getSubjct().getSession();
    }
}
