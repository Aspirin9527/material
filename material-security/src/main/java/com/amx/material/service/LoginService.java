package com.amx.material.service;

import com.amx.material.entity.ResponseResult;
import com.amx.material.entity.SysUser;

/**
 * @author lin
 * @date 2023年04月03日 15:39
 */
public interface LoginService {

    ResponseResult login(SysUser sysUser);


}
