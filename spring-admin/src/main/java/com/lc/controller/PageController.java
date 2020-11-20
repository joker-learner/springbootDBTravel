package com.lc.controller;

import com.lc.pojo.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    //直接去主页
    @RequestMapping("/doIndexUI")
    public String toIndex(Model model) {
        Subject subject = SecurityUtils.getSubject();
        SysUser principal = (SysUser)subject.getPrincipal();
        String username = principal.getUsername();
        model.addAttribute("username",username);
        return "/modules/pages/starter";
    }

    @RequestMapping("/doPageUI")  //加载导航条
    public String doPageUI() {
        return "modules/pages/common/page";
    }

    //去登陆页
    @RequestMapping("/")
    public String toLogin() {
        return "/modules/pages/login";
    }

    @RequestMapping("/menu/menu_list")
    public String menu_List() {
        return "/modules/pages/sys/menu_list";
    }

    @RequestMapping("/menu/menu_edit")
    public String toMenuEdit() {
        return "/modules/pages/sys/menu_edit";
    }

    @RequestMapping("/log/log_list")
    public String toLogList() {
        return "/modules/pages/sys/log_list";
    }

    @RequestMapping("/dept/dept_list")
    public String todepList() {
        return "/modules/pages/sys/dept_list";
    }

    @RequestMapping("/dept/dept_edit")   //去到dept_edit界面
    public String todept_edit() {
        return "/modules/pages/sys/dept_edit";
    }

    @RequestMapping("/user/user_list")
    public String toUserList() {
        return "/modules/pages/sys/user_list";
    }

    @RequestMapping("/user/user_edit")
    public String toUserEdit() {
        return "/modules/pages/sys/user_edit";
    }

    @RequestMapping("/role/role_list")
    public String toRoleList(){
        return "/modules/pages/sys/role_list";
    }

    @RequestMapping("/role/role_edit")
    public String toRoleEditList(){
        return "/modules/pages/sys/role_edit";
    }


    @RequestMapping("/user/pwd_edit")
    public String toPassEdit(){
        return "/modules/pages/sys/pwd_edit";
    }

    @RequestMapping("/doLogout")
    public String toLogout(){
        return "/modules/pages/login";
    }
}

