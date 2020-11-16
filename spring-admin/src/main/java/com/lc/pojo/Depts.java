package com.lc.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Depts {
    private Long id;

    private String name;

    private int parentId;

    private int sort;

    private String note;

    private Date createdTime;

    private Date modifiedTime;

    private String createdUser;

    private String modifiedUser;

}
