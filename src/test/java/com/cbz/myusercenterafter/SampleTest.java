package com.cbz.myusercenterafter;

import com.cbz.myusercenterafter.mapper.UserMapper;
import com.cbz.myusercenterafter.model.User;
import org.junit.Assert;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import javax.annotation.Resource;
import java.util.List;

/**
 * @author cbz
 * @version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SampleTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);

    }
}
