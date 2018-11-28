package com.yuudati.securitydemo.service;

import com.google.common.collect.Lists;
import com.yuudati.securitydemo.dao.SysUserMapper;
import com.yuudati.securitydemo.pojo.JwtUser;
import com.yuudati.securitydemo.pojo.SysRole;
import com.yuudati.securitydemo.pojo.SysUser;
import com.yuudati.securitydemo.pojo.SysUserExample;
import com.yuudati.securitydemo.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author Administrator李新栋 [lxd3808@163.com]
 * @Date 2018/11/27 16:13
 */
@Service
public class AuthService {

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    private SysUserMapper userMapper;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public AuthService(
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JwtTokenUtil jwtTokenUtil,
            SysUserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userMapper = userMapper;
    }

    public SysUser register(SysUser userToAdd){
        final String username = userToAdd.getUsername();
        final SysUserExample userExample = new SysUserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        final List<SysUser> users = userMapper.selectByExample(userExample);
        if (users != null && users.size() > 0){
            return null;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userToAdd.getPassword();
        userToAdd.setPassword(encoder.encode(rawPassword));
        userToAdd.setRoles(Lists.newArrayList(new SysRole(1, "用户", "ROLE_USER")));
        final int insert = userMapper.insert(userToAdd);
        if (insert < 1){
            throw new RuntimeException("INSERT ERROR");
        }
        return userToAdd;
    }

    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    public String refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.canTokenBeRefreshed(token, new Date())){
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }

}
