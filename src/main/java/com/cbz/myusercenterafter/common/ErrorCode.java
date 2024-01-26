package com.cbz.myusercenterafter.common;

/**
 * 错误码
 * @author cbz
 * @version 1.0
 */
public enum ErrorCode {

    SUCCESS(0, "成功", ""),
    PARAMS_ERROR(40000, "请求参数错误", ""),
    NULL_ERROR(40001, "请求数据为空", ""),
    NOT_LOGIN(40002, "用户未登录", ""),
    NO_AUTH(40003, "无权限", ""),
    ACCOUNT_PWD_NOT_EXIST(40004, "账号或密码错误", ""),
    SYSTEM_ERROR(50000, "系统错误", "");

    private final int code;
    private final String message;
    private final String description;

    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
