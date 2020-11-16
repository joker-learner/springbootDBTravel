package com.lc.service;

import com.lc.pojo.PageObject;
import com.lc.pojo.SysUsers;

public interface UserService {
    //登录验证
    void login(String username ,String pass);

    //注册
    PageObject<SysUsers> findPageObject(String username , Integer pageCurrent);

}
