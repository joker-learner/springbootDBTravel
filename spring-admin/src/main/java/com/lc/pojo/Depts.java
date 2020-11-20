package com.lc.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Depts implements Serializable {

    private static final long serialVersionUID = 4870217342673391059L;

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
