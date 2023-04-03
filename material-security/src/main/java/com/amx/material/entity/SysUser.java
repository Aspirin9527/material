package com.amx.material.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author lin
 * @date 2023年04月03日 14:23
 */
@Data
@TableName(value = "sys_user")
public class SysUser {

    @TableId
    private Integer id;

    private String username;

    private String password;

    private String nickname;

    private Long createTime;

}
