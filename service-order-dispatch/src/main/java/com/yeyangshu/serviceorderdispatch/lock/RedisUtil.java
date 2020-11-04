package com.yeyangshu.serviceorderdispatch.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/3 23:12
 */
@Component
public class RedisUtil {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void set(String key, String value, long expireTime) {
        redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
        // redisTemplate.opsForValue().set
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public boolean setnx(String key, String value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    public String getSet(String key, String value) {
        String v = redisTemplate.opsForValue().getAndSet(key, value);
        return v;
    }

    public boolean setnx(String key, String value, int second) {
        boolean b = redisTemplate.opsForValue().setIfAbsent(key, value);
        if (b) {
            redisTemplate.expire(key, second, TimeUnit.SECONDS);
        }
        return b;
    }
}
