package com.yuudati.securitydemo.service;

import com.yuudati.securitydemo.dao.SysRoleMapper;
import com.yuudati.securitydemo.dao.SysUserMapper;
import com.yuudati.securitydemo.dao.SysUserRoleMapper;
import com.yuudati.securitydemo.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Administrator李新栋 [lxd3808@163.com]
 * @Date 2018/11/27 14:54
 */
@Service
public class JwtUserDetailService implements UserDetailsService {

    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        final SysUserExample example = new SysUserExample();
        example.createCriteria().andUsernameEqualTo(s);
        List<SysUser> users = userMapper.selectByExample(example);
        if (users == null || users.size() < 1) {
            throw new RuntimeException();
        } else {
            SysUser user = users.get(0);
            final SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
            sysUserRoleExample.createCriteria().andUserIdEqualTo(user.getId());
            final List<SysUserRole> sysUserRoles = userRoleMapper.selectByExample(sysUserRoleExample);
            List<SysRole> roles = sysUserRoles.stream()
                    .map(SysUserRole::getRoleId)
                    .map(id -> roleMapper.selectByPrimaryKey(id))
                    .collect(Collectors.toList());
            user.setRoles(roles);
            return JwtUserFactory.create(user);
        }
    }
}
