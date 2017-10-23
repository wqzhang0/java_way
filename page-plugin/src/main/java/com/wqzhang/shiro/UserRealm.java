package com.wqzhang.shiro;

import com.wqzhang.model.PageData;
import com.wqzhang.user.service.UserManager;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by wqzhang on 2017/10/12.
 *
 * @author wqzhang
 */
public class UserRealm extends AuthorizingRealm {


    @Autowired
    private UserManager userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //权限验证  如要在这里获取登陆用户的所有权限
//        Long userId = ShiroUtils.getUserId();

        Long userId = Long.valueOf(ShiroUtils.getSession().getAttribute(Const.USER_ID).toString());
        Set<String> perms = userService.listPerms(userId);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(perms);
        //返回权限信息
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //这里是验证登陆
        String userAccount = (String) authenticationToken.getPrincipal();


//        String password = authenticationToken.getCredentials();

        PageData userInfo = userService.getUserByAccount(userAccount);

        if (userInfo == null) {
            //没找到帐号
            throw new UnknownAccountException();
        }
        // 将用户信息存放在Session中
        //后期移动到Redis中
        ShiroUtils.getSession().setAttribute(Const.USER_ID, userInfo.get("id"));
        ShiroUtils.getSession().setAttribute(Const.USER_ROLE_ID, userInfo.get("role_id"));


        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                //用户名
                userAccount,
                //密码
                userInfo.get("password"),
                //realm name
                getName()
        );
        //返回数据库中的账号信息
        return authenticationInfo;
    }
}
