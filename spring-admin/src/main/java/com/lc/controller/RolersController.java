package com.lc.controller;

import com.lc.pojo.JsonResult;
import com.lc.pojo.PageObject;
import com.lc.pojo.Rolers;
import com.lc.pojo.SysRoleMenuVo;
import com.lc.service.RolesService;
import com.lc.utils.ServiceException;
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

    @RequestMapping("/doDeleteObject")
    @ResponseBody
    public JsonResult doDeleteObjectById(Long id) {
        int i = rolesService.deleteObjectById(id);
        return new JsonResult("delete is ok");
    }

    @RequestMapping("/doFindObjectById")
    @ResponseBody
    public JsonResult doFindObjectById(Long id) {  //role_Id
        if (id == null || id == 0) {
            throw new ServiceException("没有传过来正确待查找id");
        }
        SysRoleMenuVo roleMenuVo = rolesService.findObjectById(id);
        return new JsonResult(roleMenuVo);
    }

    @RequestMapping("/doSaveObject")
    @ResponseBody
    public JsonResult saveRoleObject(Rolers rolers, Integer... menuIds) {
        //插入 角色
        int i = rolesService.insertRoleObject(rolers, menuIds);
        return new JsonResult("save is ok");
    }

    @RequestMapping("/doUpdateObject")
    @ResponseBody
    public JsonResult doUpdateRoleObject(Rolers rolers, Integer... menuIds) {
        int i = rolesService.updatRoleObject(rolers, menuIds);
        return new JsonResult("update is ok");
    }

    @RequestMapping("/doFindRoles")
    @ResponseBody
    public JsonResult doFindRoles(){
        return new JsonResult(rolesService.findObjects());
    }
}
