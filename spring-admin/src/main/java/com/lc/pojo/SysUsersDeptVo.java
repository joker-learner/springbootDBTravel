package com.lc.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm" ,timezone = "GMT+8")
    private Date createdTime;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm" ,timezone = "GMT+8")
    private Date modifiedTime;

    private String createdUser;

    private String modifiedUser;

}