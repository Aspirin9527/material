package com.amx.material.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author lin
 * @date 2023年04月06日 17:27
 */
@Data
@TableName("tb_goods")
public class Goods {

    @TableId("goods_id")
    private Integer goodsId;
    private String goodsName;
    private String goodsInfo;
}
