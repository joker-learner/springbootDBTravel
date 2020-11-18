package com.lc.service;

import com.lc.pojo.PageObject;
import com.lc.pojo.SysUser;
import com.lc.pojo.SysUsersDeptVo;

import java.util.Map;

public interface UserService {
    //登录验证
    void login(String username, String pass);
    //注册
    PageObject<SysUsersDeptVo> findPageObject(String username, Integer pageCurrent);

    int saveObject(SysUser entity, Integer[] roleIds);

    Map<String, Object> findObjectById(Integer userId);

    int updateObject(SysUser entity, Integer[] roleIds);

    int validById(Integer id, Integer valid);

    int upudatePwd(String pwd, String newPwd, String cfgPwd);

}
