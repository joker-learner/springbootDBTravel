package com.lc.controller;

import com.lc.pojo.JsonResult;
import com.lc.pojo.PageObject;
import com.lc.pojo.SysUsersDeptVo;
import com.lc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userServiceimple;

    @RequestMapping("/doLogin")
    @ResponseBody
    public JsonResult loginCheck(String username, String password, String isRememberMe) {
        userServiceimple.login(username, password);
        return new JsonResult(1, "登陆成功。。", null);
    }

    @RequestMapping("/doFindPageObjects")
    @ResponseBody     //PageObjects   包含数据 和 分页信息
    public JsonResult doFindPageObjects(String username, Integer pageCurrent) {
        PageObject<SysUsersDeptVo> pageObject = userServiceimple.findPageObject(username, pageCurrent);
        return new JsonResult(pageObject);
    }
}
