package com.cbz.myusercenterafter.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author cbz
 * @version 1.0
 * 统一规范返回类型
 */
@Data
public class BaseResponse<T> implements Serializable {

    private int code;

    private  T data;

    private String message;

    private String description;


    public BaseResponse(int code, T data, String message,String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;
    }

    public BaseResponse(int code, T data,String message) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = "";
    }

    public BaseResponse(int code, T data) {
        this.code = code;
        this.data = data;
        this.message = "";
        this.description = "";
    }

    public BaseResponse(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.data = null;
        this.message = errorCode.getMessage();
        this.description = errorCode.getDescription();
    }

}
