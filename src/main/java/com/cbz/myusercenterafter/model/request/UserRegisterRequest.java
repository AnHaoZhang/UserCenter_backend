package com.cbz.myusercenterafter.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author cbz
 * @version 1.0
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = -8821051460056385302L;

    private String userAccount;
    private String userPassword;
    private String checkPassword;
    private String planetCode;
}
