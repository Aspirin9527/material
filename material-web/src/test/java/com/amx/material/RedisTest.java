package com.amx.material;


import com.amx.material.utils.RedisCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lin
 * @date 2023年03月30日 15:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    RedisCache redisCache;

    @Test
    public void TestAdd(){
        Object cacheObject = redisCache.getCacheObject("1");
        System.out.println(cacheObject);
    }
}
