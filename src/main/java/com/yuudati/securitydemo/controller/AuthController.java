package com.yuudati.securitydemo.controller;

import com.google.common.base.Strings;
import com.yuudati.securitydemo.pojo.SysUser;
import com.yuudati.securitydemo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Administrator李新栋 [lxd3808@163.com]
 * @Date 2018/11/27 16:39
 */
@RestController
public class AuthController {

    /**
     * header key
     */
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    @PostMapping(value = "${jwt.route.authentication.path}")
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest){
        final String token = authService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }
    @PostMapping(value = "${jwt.route.authentication.refresh}")
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request){
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if (Strings.isNullOrEmpty(token)){
            return ResponseEntity.badRequest().body(null);
        }else {
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        }
    }
    @PostMapping(value = "${jwt.route.authentication.register}")
    public SysUser register(@RequestBody SysUser user){
        return authService.register(user);
    }

}
