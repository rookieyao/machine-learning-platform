package com.pzj.project.common.enums;

/**
 * @Author yaoqi
 * @Description
 * @Date 2022/6/7 17:02
 **/
public enum ErrorCodeEnum {

    SYSTEM_ERROR(500, "系统异常!"),

    PARAM_ERROR(400, "参数错误!"),

    PARAM_NOT_NULL(203, "参数不能为空!"),

    ORIGIN_DATA_NAME_EXISTED(10001, "原始数据名称已经存在!"),

    ID_NOT_NULL(10002, "id不能为空!"),

    File_NOT_NULL(10003, "文件不能为空!"),

    UNABLE_TO_DELETE(10004, "当前状态不能删除"),

    RESOURCE_BUSY(10005, "当前训练资源正在被占用，请稍后再试"),

    EXIST_SERVICE_VERSION(10006, "当前版本的服务已存在该环境中"),

    UNEXIST_SERVICE_VERSION(10007, "当前版本的服务不存在该环境中"),

    UNEXIST_SERVICE_REPORT(10008, "服务不存在评估报告，不允许上线!"),

    ;

    protected final int code;
    protected final String message;

    ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return this.getCode() + "-" + this.getMessage();
    }
}
