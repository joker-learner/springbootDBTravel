package com.lc.service;

import com.lc.pojo.PageObject;
import com.lc.pojo.SysUsersDeptVo;

public interface UserService {
    //登录验证
    void login(String username ,String pass);

    //注册
    PageObject<SysUsersDeptVo> findPageObject(String username , Integer pageCurrent);

}
