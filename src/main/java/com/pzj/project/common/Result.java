package com.pzj.project.common;


import com.pzj.project.common.enums.ErrorCodeEnum;
import com.pzj.project.common.exception.CheckException;

import java.io.Serializable;

public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer status;
    private String message;
    private T data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static Result<?> success(Object data) {
        Result<Object> result = new Result<>();
        result.setData(data);
        result.setStatus(ResultCode.SUCCESS.getCode());
        result.setMessage("操作成功");
        return result;
    }

    public static Result<?> success(Object data, String message) {
        Result<Object> result = new Result<>();
        result.setData(data);
        result.setStatus(ResultCode.SUCCESS.getCode());
        result.setMessage(message);
        return result;
    }

    public static Result<?> success() {
        Result<Object> result = new Result<>();
        result.setStatus(ResultCode.SUCCESS.getCode());
        result.setMessage("操作成功");
        return result;
    }

    public static Result<?> error(Integer code, String message, Exception e) {
        Result<Object> result = new Result<>();
        result.setStatus(code);
        result.setMessage(message);
        if (e != null) {
            e.printStackTrace();
        }
        return result;
    }

    public static Result<?> error(Integer code, String message) {
        Result<Object> result = new Result<>();
        result.setStatus(code);
        result.setMessage(message);
        return result;
    }

    public static Result<?> error(ErrorCodeEnum errorCodeEnum) {
        Result<Object> result = new Result<>();
        result.setStatus(errorCodeEnum.getCode());
        result.setMessage(errorCodeEnum.getMessage());
        return result;
    }

    public static Result<?> error(Integer code, String message, Object data) {
        Result<Object> result = new Result<>();
        result.setStatus(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static Result<?> error(CheckException errorCode) {
        Result<Object> result = new Result<>();
        result.setStatus(errorCode.getStatus());
        result.setMessage(errorCode.getMessage());
        return result;
    }

    public static Result<?> error(String message, Exception e) {
        Result<Object> result = new Result<>();
        result.setStatus(ErrorCodeEnum.SYSTEM_ERROR.getCode());
        result.setMessage(message);
        if (e != null) {
            e.printStackTrace();
        }
        return result;
    }

    public static Result<?> error(String message) {
        Result<Object> result = new Result<>();
        result.setStatus(ErrorCodeEnum.SYSTEM_ERROR.getCode());
        result.setMessage(message);
        return result;
    }
}
