package com.amx.material;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lin
 * @date 2023年03月30日 15:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Qualifier("defRedisTemplate")
    @Autowired
    StringRedisTemplate defRedisTemplate;

    @Test
    public void TestAdd(){
        defRedisTemplate.opsForValue().set("id","007");
    }
}
