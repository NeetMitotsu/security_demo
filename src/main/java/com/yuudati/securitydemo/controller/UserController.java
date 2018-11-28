package com.yuudati.securitydemo.controller;

import com.yuudati.securitydemo.dao.SysUserMapper;
import com.yuudati.securitydemo.pojo.SysUser;
import com.yuudati.securitydemo.pojo.SysUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Administrator李新栋 [lxd3808@163.com]
 * @Date 2018/11/27 15:15
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private SysUserMapper userMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/getAll")
    public List<SysUser> getUsers(){
        return userMapper.selectByExample(new SysUserExample());
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping(value = "/getOne")
    public SysUser getOne(){
        return userMapper.selectByPrimaryKey(1);
    }

}
