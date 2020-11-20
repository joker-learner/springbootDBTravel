package com.lc.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class LogEntity implements Serializable {

    private static final long serialVersionUID = -4957756353296440100L;

    private Long id;

    private String username;

    private String operation;

    private String method;

    private String params;

    private Long time;

    private String ip;

    private Date createdTime;
}