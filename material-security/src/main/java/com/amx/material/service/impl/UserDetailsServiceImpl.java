package com.amx.material.service.impl;

import com.amx.material.entity.LoginUser;
import com.amx.material.entity.SysUser;
import com.amx.material.mapper.MenuMapper;
import com.amx.material.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author lin
 * @date 2023年04月03日 15:02
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername,username);
        SysUser sysUser = userMapper.selectOne(wrapper);
        if (Objects.isNull(sysUser)){
            throw new RuntimeException("用户名或密码错误");
        }
        //获取权限
        try {
            List<String> permissions = menuMapper.getPermissionByUserId(sysUser.getId());
        }
        catch (Exception e){
            e.printStackTrace();
        }

        //封装成UserDetails对象返回
        return new LoginUser(sysUser,new LinkedList<>());
    }
}
