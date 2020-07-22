package com.manaz.vo;

import com.manaz.enums.ExceptionEnums;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zxw
 * @date 2020/7/19 16:06
 */
@Data
public class JsonResult {
    private Integer code;
    private String msg;
    private Object data;
    private long timestamp;

    public JsonResult() {
    }

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

    public static JsonResult error() {
        JsonResult jsonResult = new JsonResult(ExceptionEnums.ERROR);
        return jsonResult;
    }

    public static JsonResult error(Object data) {
        JsonResult jsonResult = new JsonResult(ExceptionEnums.ERROR);
        jsonResult.setData(data);
        return jsonResult;
    }

    public static JsonResult error(ExceptionEnums exceptionEnums){
        JsonResult jsonResult = new JsonResult();
        jsonResult.code = exceptionEnums.getCode();
        jsonResult.msg = exceptionEnums.getMsg();
        jsonResult.data = exceptionEnums.getData();
        jsonResult.timestamp = System.currentTimeMillis();
        return jsonResult;
    }

    public static JsonResult error(String msg) {
        JsonResult jsonResult = new JsonResult(ExceptionEnums.ERROR);
        jsonResult.setMsg(msg);
        return jsonResult;
    }

    public static JsonResult error(Object data, String msg) {
        JsonResult jsonResult = new JsonResult(ExceptionEnums.ERROR);
        jsonResult.setData(data);
        jsonResult.setMsg(msg);
        return jsonResult;
    }

    public static JsonResult ok() {
        JsonResult jsonResult = new JsonResult(ExceptionEnums.SUCCESS);
        return jsonResult;
    }

    public static JsonResult ok(Object data) {
        JsonResult jsonResult = new JsonResult(ExceptionEnums.ERROR);
        jsonResult.setData(data);
        return jsonResult;
    }

    public static JsonResult ok(String msg) {
        JsonResult jsonResult = new JsonResult(ExceptionEnums.SUCCESS);
        jsonResult.setMsg(msg);
        return jsonResult;
    }

    public static JsonResult ok(Object data, String msg) {
        JsonResult jsonResult = new JsonResult(ExceptionEnums.SUCCESS);
        jsonResult.setData(data);
        jsonResult.setMsg(msg);
        return jsonResult;
    }


}
