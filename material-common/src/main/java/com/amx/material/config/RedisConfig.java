package com.amx.material.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author lin
 * @date 2023年03月30日 15:07
 */
@Configuration
@Slf4j
public class RedisConfig {

    @Value("${redis.host}")
    private String hostName;
    @Value("${redis.port}")
    private int port;
    @Value("${redis.password}")
    private String passWord;
    @Value("${redis.maxIdle}")
    private int maxIdl;
    @Value("${redis.minIdle}")
    private int minIdl;
    @Value("${redis.timeout}")
    private int timeout;


    @Bean
    public RedisTemplate<Object, Object> redisTemplate(){
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory(jedisPoolConfig(), 15));
        FastJsonRedisSerializer<Object> serializer = new FastJsonRedisSerializer<>(Object.class);

        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(serializer);
        template.setValueSerializer(serializer);

        // Hash的key也采用StringRedisSerializer的序列化方式
        template.setHashKeySerializer(serializer);
        template.setHashValueSerializer(serializer);

        return template;
    }

    /**
     * 连接池配置信息
     *
     * @return
     */
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 最大连接数
        poolConfig.setMaxIdle(maxIdl);
        // 最小空闲连接数
        poolConfig.setMinIdle(minIdl);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setNumTestsPerEvictionRun(10);
        // ------其他属性根据需要自行添加-------------
        return poolConfig;
    }

    /**
     * jedis连接工厂
     *
     * @param jedisPoolConfig
     * @return
     */
    public RedisConnectionFactory redisConnectionFactory(JedisPoolConfig jedisPoolConfig, int db) {
        // 单机版jedis
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        // 设置redis服务器的host或者ip地址
        redisStandaloneConfiguration.setHostName(hostName);
        // 设置默认使用的数据库
        redisStandaloneConfiguration.setDatabase(db);
        // 设置密码
        redisStandaloneConfiguration.setPassword(RedisPassword.of(passWord));
        // 设置redis的服务的端口号
        redisStandaloneConfiguration.setPort(port);

        // 获得默认的连接池构造器(怎么设计的，为什么不抽象出单独类，供用户使用呢)
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcb = (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration
                .builder();
        // 指定jedisPoolConifig来修改默认的连接池构造器（真麻烦，滥用设计模式！）
        jpcb.poolConfig(jedisPoolConfig);
        // 通过构造器来构造jedis客户端配置
        JedisClientConfiguration jedisClientConfiguration = jpcb.build();
        // 单机配置 + 客户端配置 = jedis连接工厂
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
    }



}
