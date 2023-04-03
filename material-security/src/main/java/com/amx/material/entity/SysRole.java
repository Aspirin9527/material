package com.amx.material.entity;

import lombok.Data;

/**
 * @author lin
 * @date 2023年04月03日 14:24
 */
@Data
public class SysRole {

    private Integer id;

    private String roleName;

    private String description;

    private int superAdmin;

    private long createTime;
}
