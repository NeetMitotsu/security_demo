package com.yuudati.securitydemo.controller;

import lombok.Data;

/**
 * @Author Administrator李新栋 [lxd3808@163.com]
 * @Date 2018/11/28 9:01
 */
@Data
public class JwtAuthenticationResponse {
    private final String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

}
