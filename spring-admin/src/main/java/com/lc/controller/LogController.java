package com.lc.controller;

import com.lc.pojo.JsonResult;
import com.lc.pojo.LogEntity;
import com.lc.pojo.PageObject;
import com.lc.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logServiceImpl;

    @RequestMapping("/doFindPageObjects")
    @ResponseBody
    public JsonResult doFindPageObjects(String username, Integer pageCurrent) {
        PageObject<LogEntity> pageObject = logServiceImpl.findPageObjecLog(username, pageCurrent);

        return new JsonResult(pageObject);
    }

    @RequestMapping("/doDeleteObjects")
    @ResponseBody
    public JsonResult doDeleteObjects(Integer... ids) {
        //批量删除
        int i = logServiceImpl.deletLogByIds(ids);  //穿的是字符串数组
        return new JsonResult("delete is ok");
    }
}
