package com.amx.material;

import com.amx.material.entity.Goods;
import com.amx.material.mapper.GoodsMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lin
 * @date 2023年04月06日 17:45
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsTest {

    @Autowired
    GoodsMapper goodsMapper;

    @Test
    public void goodsAdd(){
        //add test
        Goods goods = new Goods();
        goods.setGoodsName("口罩");
        goods.setGoodsInfo("防护物资");
        int insert = goodsMapper.insert(goods);
        System.out.println(insert);
    }

    @Test
    public void goodsUpdate(){
        //update test
        Goods goods = goodsMapper.selectById(1);
        goods.setGoodsName("一个物资");
        int updateById = goodsMapper.updateById(goods);
        System.out.println(updateById);
    }

    @Test
    public void goodsDel(){
        //del test
        int updateById = goodsMapper.deleteById(1);
        System.out.println(updateById);
    }



}
