package com.cbz.myusercenterafter.service;
import java.util.Date;


import com.cbz.myusercenterafter.model.User;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;



/**
 * @author cbz
 * @version 1.0
 */
@SpringBootTest
public class UserServiceTest {

    @Resource UserService userService;

    @Test
    public void testAddUser(){
        User user = new User();
        user.setUsername("cb");
        user.setUserAccount("123");
        user.setAvatarUrl("https://cn.bing.com/images/search?view=detailV2&ccid=uPx%2bFbZ4&id=F0CBC98CC5331358BFACA3EAAFE5D25B867CF64D&thid=OIP.uPx-FbZ4nf2U05kCoJ7_1QHaE8&mediaurl=https%3a%2f%2fimg.zcool.cn%2fcommunity%2f01c8f15aeac135a801207fa16836ae.jpg%401280w_1l_2o_100sh.jpg&exph=854&expw=1280&q=%e5%9b%be%e7%89%87&simid=608025777355622214&FORM=IRPRST&ck=9643F6ACA7E6059D6136863E7F0C3B0B&selectedIndex=0&idpp=overlayview&ajaxhist=0&ajaxserp=0");
        user.setGender(0);
        user.setUserPassword("xxxx");
        user.setPhone("12345");
        user.setEmail("97212");
        user.setUserStatus(0);
        boolean result = userService.save(user);
        System.out.println(user.getId());
        Assertions.assertTrue(result);


    }


    @Test
    void userRegister() {
        String userAccount = "yupi";
        String userPassword = "";
        String checkPassword = "123456";
        String planetCode = "123";
        long result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
        Assertions.assertEquals(-1, result);
        userAccount ="yu";
        result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
        Assertions.assertEquals(-1, result);
        userAccount = "yupi";
        userPassword ="123456";
        result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
        Assertions.assertEquals(-1, result);
        userAccount = "yu pi";
        userPassword ="12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
        Assertions.assertEquals(-1, result);
        checkPassword ="123456789";
        result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
        Assertions.assertEquals(-1, result);
        userAccount = "dogyupi";
        checkPassword ="12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
        Assertions.assertEquals(-1, result);
        userAccount ="yupi";
        result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
        Assertions.assertEquals(-1, result);
//        Assertions.assertTrue(result>0);

    }
}
