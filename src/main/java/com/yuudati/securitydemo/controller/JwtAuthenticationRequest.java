package com.yuudati.securitydemo.controller;

import lombok.Data;

/**
 * @Author Administrator李新栋 [lxd3808@163.com]
 * @Date 2018/11/28 9:00
 */
@Data
public class JwtAuthenticationRequest {
    private String username;
    private String password;
}
