package com.amx.material.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author lin
 * @date 2023年04月06日 17:32
 */
@Data
@TableName("tb_sale")
public class Sale {

    @TableId("sale_id")
    private Long saleId;
    private Integer goodsId;
    private Integer userId;
    private Double salePrice;
    private Integer saleNum;
    private Double saleSum;
    private Date saleDate;
    private Long saleTime;

}
