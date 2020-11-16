package com.lc.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class SysMenus {

    //菜单ID
    private Long id;
    //菜单名称
    private String name;
    //上级菜单
    private Long parentId;
    //类型
    private Long type;
    //排序号
    private Long sort;
    //菜单URL
    private String url;
    //授权标识
    private String permission;
    /**备注*/
    private String note;
    /**创建用户*/
    private String createdUser;

    private String modifiedUser;
    private Date createdTime;
    private Date modifiedTime;
}