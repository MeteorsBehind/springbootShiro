package com.shiro.springshiroauth.service;

import com.shiro.springshiroauth.pojo.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User queryUserByName(String name){
        User user = new User();
        user.setName(name);
        if("admin".equals(name)){
            user.setPwd("aoto");
        }else if("yangb".equals(name)){
            user.setPwd("young");
        }else if("张三".equals(name)){
            user.setPwd("zhangsan");
        }
        return user;
    }
}
