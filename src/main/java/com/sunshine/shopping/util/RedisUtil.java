/**
 * Copyright © 2017郑州金色马甲电子商务有限公司. All rights reserved.
 *
 * @Title: RedisUtil
 * @Prject: shopping
 * @Package: com.sunshine.shopping.util
 * @Description: <功能详细描述>
 * @author: LiMG
 * @date: 2017/7/10 14:43
 * @version: V1.0
 */

package com.sunshine.shopping.util;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Title: RedisUtil
 * @Description: Redis工具类
 * @author LiMG
 * @date 2017/7/10 14:43
 * @see  [相关类/方法]
 * @since [产品/模块版本]
 */
public class RedisUtil {

    private static RedisTemplate<String, String> redisTemplate;

    /**
     * @Title: get
     * @Description: 获取指定key的value
     * @author LiMG
     * @date 2017/7/10 14:45 
     * @see [类、类#方法、类#成员]
     */
    public static String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * @Title: set
     * @Description: 设置指定key的value
     * @author LiMG
     * @date 2017/7/10 14:46
     * @see [类、类#方法、类#成员]
     */
    public static void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /** 
     * @Title: set
     * @Description: 设置指定有效期的key的value，单位秒
     * @author LiMG
     * @date 2017/7/10 14:47 
     * @see [类、类#方法、类#成员]
     */
    public static void set(String key, String value, long second) {
        redisTemplate.opsForValue().set(key, value, second, TimeUnit.SECONDS);
    }

    /**
     * @Title: del
     * @Description: 删除指定key的value
     * @author LiMG
     * @date 2017/7/10 14:47
     * @see [类、类#方法、类#成员]
     */
    public static void del(String key) {
        redisTemplate.delete(key);
    }

    /**
     * @Title: exists
     * @Description: 判断指定key是否存在
     * @author LiMG
     * @date 2017/7/10 14:48
     * @see [类、类#方法、类#成员]
     */
    public static Boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * @Title: expire
     * @Description: 给指定key设置有效期，单位秒
     * @author LiMG
     * @date 2017/7/10 14:48
     * @see [类、类#方法、类#成员]
     */
    public static Boolean expire(String key, long second) {
        return redisTemplate.expire(key, second, TimeUnit.SECONDS);
    }

    /**
     * @Title: expireAt
     * @Description: 设置Key有效期截止到具体date
     * @author LiMG
     * @date 2017/7/10 14:50
     * @see [类、类#方法、类#成员]
     */
    public static Boolean expireAt(String key, Date date) {
        return redisTemplate.expireAt(key, date);
    }

    /** 
     * @Title: increment
     * @Description: 增加值
     * @author LiMG
     * @date 2017/7/10 14:50 
     * @see [类、类#方法、类#成员]
     */
    public static Long increment(String key, long step) {
        return redisTemplate.opsForValue().increment(key, step);
    }

    /** 
     * @Title: increment
     * @Description: 增加值，步长为1
     * @author LiMG
     * @date 2017/7/10 14:51 
     * @see [类、类#方法、类#成员]
     */
    public static Long increment(String key) {
        return redisTemplate.opsForValue().increment(key, 1);
    }

    public static void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        RedisUtil.redisTemplate = redisTemplate;
    }

}
