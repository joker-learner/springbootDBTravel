package com.lc.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class SysUsersDeptVo {
    private Long id;

    private String username;

    private String password;

    private String salt;

    private String email;

    private String mobile;

    private int valid = 1;

//    private int deptId;

    private Depts depts;

    private Date createdTime;

    private Date modifiedTime;

    private String createdUser;

    private String modifiedUser;

}