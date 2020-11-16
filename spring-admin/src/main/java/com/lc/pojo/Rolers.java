package com.lc.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Rolers {
    private Long id;

    private String name;

    private String note;

    private Date createdTime;

    private Date modifiedTime;

    private String createdUser;

    private String modifiedUser;
}
