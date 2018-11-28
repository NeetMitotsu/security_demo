package com.yuudati.securitydemo.pojo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Administrator李新栋 [lxd3808@163.com]
 * @Date 2018/11/27 14:42
 */
public class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(SysUser user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getRoles()),
                user.getEnable()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<SysRole> roles) {
        return roles.stream()
                .map(SysRole::getEnName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}
