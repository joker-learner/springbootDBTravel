package com.lc.pojo;

import lombok.Data;

@Data
public class JsonResult {

    private Integer state = 1;

    private String message = "ok";

    private Object data;

    public JsonResult(Integer state, String message, Object data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public JsonResult(Throwable e) {
        this.state = 0;
        this.message = e.getMessage();
    }

    public JsonResult(String message) {
        this.message = message;
    }

    public JsonResult(Object data) {
        this.data = data;
    }

    public JsonResult() {
    }
}
