package com.lc.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class LogEntity {
    private Long id;

    private String username;

    private String operation;

    private String method;

    private String params;

    private Long time;

    private String ip;

    private Date createdTime;
}