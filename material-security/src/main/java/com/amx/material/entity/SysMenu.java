package com.amx.material.entity;

import lombok.Data;

/**
 * @author lin
 * @date 2023年04月04日 17:38
 */
@Data
public class SysMenu {

    private Integer id;
    private Integer parentId;
    private String menuName;
    private Integer type;
    private Integer sort;
    private String css;
    private String href;
    private String permission;
}
