package com.yuudati.securitydemo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @Author Administrator李新栋 [lxd3808@163.com]
 * @Date 2018/11/27 14:36
 */
@Data
public class JwtUser implements UserDetails {
    private static final long serialVersionUID = 1979354749635951761L;
    @Setter
    private final Integer id;
    private final String username;
    @Setter
    private final String password;
    private Collection<? extends GrantedAuthority> authorities;
    private final Byte enable;

    public JwtUser(Integer id, String username, String password,  Collection<? extends GrantedAuthority> authorities, Byte enable){
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.enable = enable;
    }

    /**
     * 账户是否未过期
      * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账户是否未锁定
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码是否未过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账户是否激活
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
