package com.manaz.vo;

import com.manaz.enums.ExceptionEnums;

import java.time.LocalDateTime;

/**
 * @author zxw
 * @date 2020/7/19 16:06
 */
public class JsonResult {
    private Integer code;
    private String msg;
    private Object data;
    private long timestamp;

    public JsonResult(ExceptionEnums exceptionEnums) {
        this.code = exceptionEnums.getCode();
        this.msg = exceptionEnums.getMsg();
        this.data = exceptionEnums.getData();
        this.timestamp = System.currentTimeMillis();
    }

    public JsonResult(Integer code, String msg, Object data, long timestamp) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.timestamp = timestamp;
    }

    public JsonResult(Integer code, String msg, long timestamp) {
        this.code = code;
        this.msg = msg;
        this.timestamp = timestamp;
    }
}
