package com.lc.controller;

import com.lc.pojo.Depts;
import com.lc.pojo.JsonResult;
import com.lc.pojo.Node;
import com.lc.service.DeptService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dept")
public class DepementController {

    @Autowired
    private DeptService deptService;

    @RequestMapping("/doFindObjects")
    @ResponseBody
    public JsonResult doFindObjects() {
        List<Map<String, Object>> deptList = deptService.findAllObject();
        return new JsonResult(deptList);
    }

    @RequestMapping("/doDeleteObject")
    @ResponseBody
    public JsonResult doDeleteObject(@Param("id") Integer id) {
        int i = deptService.deleteDepById(id);
        return new JsonResult("delete is ok");
    }

    @RequestMapping("/doFindZTreeNodes")
    @ResponseBody
    public JsonResult doFindZTreeNodes() {
        List<Node> ztreeMenuNodes = deptService.findZTreeDept();
        return new JsonResult(ztreeMenuNodes);
    }

    @RequestMapping("/doSaveObject")
    @ResponseBody
    public JsonResult doSaveObject(Depts deptsObject) {

        int i = deptService.saveDepeObject(deptsObject);
        return new JsonResult(1, "save is ok", null);

    }

    @RequestMapping("/doUpdateObject")
    @ResponseBody
    public JsonResult doUpdateObject(Depts deptsObject) {

        int i = deptService.updateDeptObject(deptsObject);
        return new JsonResult("update is ok");

    }
}

