package com.amx.material.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author lin
 * @date 2023年04月06日 17:30
 */
@Data
@TableName("tb_purchase")
public class Purchase {

    @TableId("purchase_id")
    private Long purchaseId;
    private Integer goodsId;
    private Double purchasePrice;
    private Integer purchaseNum;
    private Double purchaseMoney;
    private Date purchaseDate;
}
