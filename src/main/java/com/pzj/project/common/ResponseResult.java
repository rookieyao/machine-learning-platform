package com.pzj.project.common;

import lombok.Data;

/**
 * @author tongsq
 * @date 2021/10/12
 **/
@Data
public class ResponseResult<T> {

    private  Integer code;
    private  String  msg;
    private T data;
}
