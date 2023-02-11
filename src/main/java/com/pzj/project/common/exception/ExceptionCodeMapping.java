package com.pzj.project.common.exception;


import java.util.HashMap;
import java.util.Map;

public class ExceptionCodeMapping {

    public static final Map<Integer, Object> CODE_MAPPING = new HashMap<>();
    static {
        CODE_MAPPING.put(0, "成功");
        CODE_MAPPING.put(100010100, "缺少授权信息");
        CODE_MAPPING.put(100010101, "accessToken错误或已过期");
        CODE_MAPPING.put(100010103, "appId不合法");
        CODE_MAPPING.put(100010104, "timestamp过期");
        CODE_MAPPING.put(100010105, "签名错误");
        CODE_MAPPING.put(100010106, "请求地址错误");
        CODE_MAPPING.put(100010108, "请求方法错误");
        CODE_MAPPING.put(100010109, "服务处理超时");
        CODE_MAPPING.put(100010201, "缺少参数");
    }
}
