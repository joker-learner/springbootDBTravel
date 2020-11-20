package com.lc.controller;

import com.lc.pojo.JsonResult;
import com.lc.pojo.PageObject;
import com.lc.pojo.SysUser;
import com.lc.pojo.SysUsersDeptVo;
import com.lc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userServiceimple;

    @RequestMapping("/doLogin")
    @ResponseBody
    public JsonResult loginCheck(String username, String password, boolean isRememberMe) {
        userServiceimple.login(username, password,isRememberMe);
        return new JsonResult(1, "登陆成功。。", null);
    }

    //查
    @RequestMapping("/doFindPageObjects")
    @ResponseBody     //PageObjects   包含数据 和 分页信息
    public JsonResult doFindPageObjects(String username, Integer pageCurrent) {
        PageObject<SysUsersDeptVo> pageObject = userServiceimple
                .findPageObject(username, pageCurrent);
        return new JsonResult(pageObject);
    }

    //增
    @RequestMapping("/doSaveObject")
    @ResponseBody
    public JsonResult doSaveObject(SysUser entity, Integer[] roleIds) {
        userServiceimple.saveObject(entity, roleIds);
        return new JsonResult("save ok");
    }

    //根据id找
    @RequestMapping("/doFindObjectById")
    @ResponseBody
    public JsonResult doFindObjectById(Integer id) {
        Map<String, Object> map = userServiceimple.findObjectById(id);
        return new JsonResult(map);
    }

    //改
    @RequestMapping("/doUpdateObject")
    @ResponseBody
    public JsonResult doUpdateObject(SysUser entity, Integer[] roleIds) {
        userServiceimple.updateObject(entity, roleIds);
        return new JsonResult("update ok");
    }

    @RequestMapping("/doValidById")
    @ResponseBody
    public JsonResult doValidById(Integer id, Integer valid) {
        userServiceimple.validById(id, valid);
        return new JsonResult("update ok");
    }

    @RequestMapping("/doUpdatePassword")
    @ResponseBody
    public JsonResult doUpdatePassword(String pwd, String newPwd, String cfgPwd){
        int i = userServiceimple.upudatePwd(pwd, newPwd, cfgPwd);
        return new JsonResult("更新密码成功");
    }

}
