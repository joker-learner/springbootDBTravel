package com.lc.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Rolers implements Serializable {
    private static final long serialVersionUID = -9098468287474994908L;

    private Long id;

    private String name;

    private String note;

    private Date createdTime;

    private Date modifiedTime;

    private String createdUser;

    private String modifiedUser;
}
