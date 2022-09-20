package com.shiro.springshiroauth.config;

import com.shiro.springshiroauth.pojo.User;
import com.shiro.springshiroauth.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.print("执行了授权");

        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();

        //拿到当前登录的对象
        Subject subject= SecurityUtils.getSubject();

        User user=(User)subject.getPrincipal(); //拿到User对象

        //张三能看，李四不能看
        if(user.getName().equals("张三")){
            info.addStringPermission("user:add");
        }

        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.print("执行了认证");

//        String name="root";
//        String password="123456";

        UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken)authenticationToken;

//        if(!usernamePasswordToken.getUsername().equals(name)){
//            return null;//抛出异常
//
//        }
        User user = userService.queryUserByName(usernamePasswordToken.getUsername());

        if(user==null){
            return null;
        }

        Subject currentSubject=SecurityUtils.getSubject();

        Session session=currentSubject.getSession();

        session.setAttribute("loginUser",user);


        return new SimpleAuthenticationInfo(user,user.getPwd(),"");
    }


}



