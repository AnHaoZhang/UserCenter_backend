package com.cbz.myusercenterafter.service;

import com.cbz.myusercenterafter.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZCB
 * @description 针对表【user(用户信息表)】的数据库操作Service
 * @createDate 2023-02-27 18:02:48
 */
public interface UserService extends IService<User> {

    //    /**
//     * 用户登录状态
//     */
    String USER_LOGIN_STATE = "userLoginState";

    /**
     * 用户注释
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密
     * @param checkPassword 校验密码常
     * @return 新用户 id
     */

    long userRegister(String userAccount, String userPassword, String checkPassword, String planetCode);

    /**
     * @param userAccount
     * @param userPassword
     * @param request
     * @return
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户脱敏
     *
     * @param originUser
     * @return
     */
    User getSafetyUser(User originUser);

    /**
     *用户注销
     * @param request
     * @return
     */
    int userLogOut(HttpServletRequest request);
}
