package com.lc.controller;

import com.lc.pojo.JsonResult;
import com.lc.pojo.PageObject;
import com.lc.pojo.Rolers;
import com.lc.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/role")
public class RolersController {
    @Autowired
    private RolesService rolesService;

    @RequestMapping("/doFindPageObjects")
    @ResponseBody
    public JsonResult findPageObject(String name, Integer pageCurrent) {
        PageObject<Rolers> pageObj = rolesService.findPageObj(name, pageCurrent);
        return new JsonResult(pageObj);
    }
}
