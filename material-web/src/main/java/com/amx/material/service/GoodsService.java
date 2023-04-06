package com.amx.material.service;

import com.amx.material.entity.Goods;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author lin
 * @date 2023年04月06日 18:16
 */
public interface GoodsService {

    /**
     * 添加商品信息
     * @param goods
     */
    int addGoodsInfo(Goods goods);

    PageInfo<Goods> getGoodsList();


}
