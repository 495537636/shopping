/**
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: RedisConfig
 * @Prject: shopping
 * @Package: com.sunshine.shopping.config
 * @Description: <功能详细描述>
 * @author: LiMG
 * @date: 2017/7/10 15:03
 * @version: V1.0
 */

package com.sunshine.shopping.config;

import com.sunshine.shopping.util.RedisUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Title: RedisConfig
 * @Description: Redis配置类
 * @author LiMG
 * @date 2017/7/10 15:03
 * @see  [相关类/方法]
 * @since [产品/模块版本]
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisUtil setRedis() {
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.setRedisTemplate(getRedisTemplate());
        return redisUtil;
    }

    @Bean(name = "poolConfig")
    public JedisPoolConfig getJedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(300);
        config.setMinIdle(10);
        return config;
    }

    @Bean(name = "connectionFactory")
    public JedisConnectionFactory getJedisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName("127.0.0.1");
        factory.setPort(6379);
        factory.setPassword("");
        factory.setPoolConfig(getJedisPoolConfig());
        return factory;
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate getRedisTemplate() {
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(getJedisConnectionFactory());
        // 将key、value、hashKey、hashValue序列化，解决key和value前面出现类似转义字符内容的问题
        // 这种问题：\xAC\xED\x00\x05t\x00\x09checkCode
        StringRedisSerializer serializer = new StringRedisSerializer();
        template.setKeySerializer(serializer);
        template.setValueSerializer(serializer);
        template.setHashKeySerializer(serializer);
        template.setHashValueSerializer(serializer);
        return template;
    }

}
