package com.amx.material.mapper;

import com.amx.material.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author lin
 * @date 2023年04月04日 17:41
 */
public interface MenuMapper extends BaseMapper<SysMenu> {

    List<String> getPermissionByUserId(Integer id);
}
