package com.cbz.myusercenterafter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cbz.myusercenterafter.common.BaseResponse;
import com.cbz.myusercenterafter.common.ErrorCode;
import com.cbz.myusercenterafter.common.ResultUtils;
import com.cbz.myusercenterafter.exception.BusinessException;
import com.cbz.myusercenterafter.model.User;
import com.cbz.myusercenterafter.model.request.UserLoginRequest;
import com.cbz.myusercenterafter.model.request.UserRegisterRequest;
import com.cbz.myusercenterafter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.cbz.myusercenterafter.constant.UserConstant.ADMIN_ROLE;
import static com.cbz.myusercenterafter.constant.UserConstant.USER_LOGIN_STATE;

/**
 * @author cbz
 * @version 1.0
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest){
        if (userRegisterRequest == null){
//            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数为空");
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String planetCode = userRegisterRequest.getPlanetCode();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword, planetCode)){
            return null;
        }
        long result =  userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
//        return new BaseResponse<>(0,result,"ok");
        return ResultUtils.success(result);
    }

    @PostMapping("/login")
    public  BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request){
        if (userLoginRequest == null){
            return null;
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)){
            return null;
        }

       User user = userService.userLogin(userAccount, userPassword,request);
//        return new BaseResponse<>(0,user,"ok");
        return ResultUtils.success(user);
    }


    @PostMapping("/logout")
    public BaseResponse<Integer> userLoginOut(HttpServletRequest request){
        if (request == null){
            return null;
        }
        return ResultUtils.success(userService.userLogOut(request));
    }
    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request){
        Object userObject = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currenUser = (User) userObject;
        if (currenUser == null){
            return null;
        }
        long userId = currenUser.getId();
        // todo 校验用户是否合法
        User user = userService.getById(userId);
        User safetyUser = userService.getSafetyUser(user);// 脱敏
        return ResultUtils.success(safetyUser);
    }

    @GetMapping("/search")
    public BaseResponse<List<User>> searchUsers(String username,HttpServletRequest request) {
       if (!isAdmin(request)){
//           return new ArrayList<>();
           return ResultUtils.success(new ArrayList<>());
       }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
       if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("username" , username);
        }
        List<User> userList = userService.list(queryWrapper);
        List<User> users = userList.stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList());
        return ResultUtils.success(users);

    }


    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody long id,HttpServletRequest request){
        if (!isAdmin(request)){
            return null;
        }
        if (id <= 0){
            return null;
        }
        return ResultUtils.success(userService.removeById(id));
    }
    /*
     * 获取当前用户登录态，信息接口
     */

    private boolean isAdmin(HttpServletRequest request){
        //仅管理员可查询
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        return user != null && user.getUserRole() == ADMIN_ROLE;
    }
}
