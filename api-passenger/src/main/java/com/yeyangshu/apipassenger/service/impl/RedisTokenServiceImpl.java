package com.yeyangshu.apipassenger.service.impl;

import com.yeyangshu.apipassenger.service.RedisTokenService;
import com.yeyangshu.internalcommon.constant.RedisKeyPrefixConstant;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Redis Token存储服务实现类
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/31 14:43
 */
@Service
public class RedisTokenServiceImpl implements RedisTokenService {

    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> vOps;

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 添加
     *
     * @param phoneNum 电话号码
     * @param token    token
     * @param expHours 过期时间
     */
    @Override
    public void put(String phoneNum, String token, Integer expHours) {
        vOps.set(RedisKeyPrefixConstant.PASSENGER_LOGIN_TOKEN_APP_KEY_PRE + phoneNum, token, expHours, TimeUnit.HOURS);
    }

    /**
     * 查询
     *
     * @param phoneNum 手机号
     * @return token
     */
    @Override
    public String get(String phoneNum) {
        return vOps.get(RedisKeyPrefixConstant.PASSENGER_LOGIN_TOKEN_APP_KEY_PRE + phoneNum);
    }

    /**
     * 设置过期时间
     *
     * @param phoneNum
     * @param expHours
     * @return
     */
    @Override
    public Boolean expire(String phoneNum, Integer expHours) {
        return redisTemplate.expire(phoneNum, expHours, TimeUnit.HOURS);
    }

    /**
     * 删除
     *
     * @param phoneNum 手机号
     */
    @Override
    public void delete(String phoneNum) {
        redisTemplate.delete(RedisKeyPrefixConstant.PASSENGER_LOGIN_TOKEN_APP_KEY_PRE + phoneNum);
    }
}
