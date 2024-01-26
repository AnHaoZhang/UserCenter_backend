package com.cbz.myusercenterafter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.cbz.*.mapper")
public class MyuserCenterAfterApplication {

    public static void main(String[] args) {
        System.out.printf("Hello!");
        SpringApplication.run(MyuserCenterAfterApplication.class, args);
    }

}
