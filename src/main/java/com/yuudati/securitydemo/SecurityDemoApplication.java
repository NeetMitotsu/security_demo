package com.yuudati.securitydemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 * @Auther lxd3808@gmail.com
 * @Date 2018/11/27
 */
@SpringBootApplication
@MapperScan(basePackages = "com.yuudati.securitydemo.dao")
public class SecurityDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityDemoApplication.class, args);
    }
}
