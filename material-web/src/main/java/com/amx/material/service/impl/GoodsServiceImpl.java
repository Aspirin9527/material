package com.amx.material.service.impl;

import com.amx.material.entity.Goods;
import com.amx.material.mapper.GoodsMapper;
import com.amx.material.service.GoodsService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lin
 * @date 2023年04月06日 18:17
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public int addGoodsInfo(Goods goods) {
        return goodsMapper.insert(goods);
    }

    @Override
    public PageInfo<Goods> getGoodsList() {

        return null;
    }
}
