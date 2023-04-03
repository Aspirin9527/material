package com.amx.material.controller;

import com.amx.material.entity.ResponseResult;
import com.amx.material.entity.SysUser;
import com.amx.material.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lin
 * @date 2023年04月03日 15:37
 */
@RestController
@RequestMapping
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody SysUser sysUser){
       return loginService.login(sysUser);
    }

    @GetMapping("/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

}
