package com.manaz.enums;

import lombok.Data;

/**
 * @author zxw
 * @date 2020/7/19 16:04
 */
public enum ExceptionEnums {
    USER_OR_PASSWORD_ERROR(400, "用户名或密码错误");
    private Integer code;
    private String msg;
    private Object data;

    ExceptionEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    ExceptionEnums(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
