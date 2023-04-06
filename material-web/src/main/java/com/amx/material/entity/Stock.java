package com.amx.material.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author lin
 * @date 2023年04月06日 17:34
 */
@Data
@TableName("tb_stock")
public class Stock {

    @TableId("goods_id")
    private Integer goodsId;
    private Integer stockNum;
}
