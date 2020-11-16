package com.lc.controller;

import com.lc.pojo.JsonResult;
import com.lc.pojo.Node;
import com.lc.pojo.SysMenus;
import com.lc.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping("/findObjects")
    @ResponseBody
    public JsonResult findMenuObjects() {
        List<Map<String, Object>> menulist = sysMenuService.findObjects();
        return new JsonResult(menulist);
    }

    @RequestMapping("/doDeleteObject")
    @ResponseBody
    public JsonResult doDeleteObjectById(Long id) {
        sysMenuService.deleteMenuObject(id);
        return new JsonResult("delete is ok");
    }

    @RequestMapping("/doSaveObject")
    @ResponseBody
    public JsonResult doSaveObject(SysMenus entity){
        int i = sysMenuService.saveObject(entity);
        return new JsonResult("save is ok");
    }

    @RequestMapping("/doUpdateObject")
    @ResponseBody
    public JsonResult doUpdateObject(SysMenus entity){
        int i = sysMenuService.updateMenuObject(entity);
        return new JsonResult("update is ok");
    }

    @RequestMapping("/doFindZtreeMenuNodes")
    @ResponseBody
    public JsonResult doFindZtreeMenuNodes(){
        List<Node> ztreeMenuNodes = sysMenuService.findZtreeMenuNodes();
        return new JsonResult(ztreeMenuNodes);
    }

}
