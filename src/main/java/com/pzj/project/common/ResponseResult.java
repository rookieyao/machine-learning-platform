package com.pzj.project.common;

import lombok.Data;

import java.util.HashMap;

/**
 * @author tongsq
 * @date 2021/10/12
 **/
@Data
public class ResponseResult<T> {

    private  Integer code;
    private  String  msg;
    private T data;

    /**
     * @param code code 200 处理成功 500 处理失败
     * @param msg  响应错误内容
     * @param data 响应的数据
     * @return
     */
    public HashMap<String, Object> setResult(Integer code, String msg, Object data) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", code);
        result.put("msg", msg);
        result.put("data", data);
        return result;
    }

    /**
     * 提供处理请求响应成功的情况下
     *
     * @param data
     * @return
     */
    public HashMap<String, Object> setResultOk(Object data) {
        return setResult(200, "ok", data);
    }

    public HashMap<String, Object> setResultOSuccess(String msg) {
        return setResult(200, msg, null);
    }

    /**
     * 提供处理请求失败情况下
     *
     * @param msg
     * @return
     */
    public HashMap<String, Object> setResultError(String msg) {
        return setResult(500, msg, null);
    }
}
