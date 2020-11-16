package com.lc.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class SysUsers {
    private Long id;

    private String username;

    private String password;

    private String salt;

    private String email;

    private String mobile;

    private int valid;

    private int deptId;

    private Date createdTime;

    private Date modifiedTime;

    private String createdUser;

    private String modifiedUser;

}