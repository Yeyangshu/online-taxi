package com.yeyangshu.servicepassengeruser.service.impl;

import com.yeyangshu.internalcommon.constant.RedisKeyPrefixConstant;
import com.yeyangshu.internalcommon.entity.ResponseResult;
import com.yeyangshu.internalcommon.util.JwtUtil;
import com.yeyangshu.servicepassengeruser.dao.ServicePassengerUserInfoCustomDao;
import com.yeyangshu.servicepassengeruser.service.PassengerUserService;
import com.yeyangshu.servicepassengeruser.entity.ServicePassengerUserInfoDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 用户登录实现类
 *
 * @author yeyangshu
 */
public class PassengerUserServiceImpl implements PassengerUserService {

    @Autowired
    ServicePassengerUserInfoCustomDao servicePassengerUserInfoCustomDao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 用户登录
     *
     * @param passengerPhone
     * @return
     */
    @Override
    public ResponseResult login(String passengerPhone) {
        ServicePassengerUserInfoDo passengerUserInfo = new ServicePassengerUserInfoDo();
        // 方法一：insertPassenger(passengerUserInfo)
        servicePassengerUserInfoCustomDao.insertSelective(passengerUserInfo);

        Long passengerId = passengerUserInfo.getId();
        // 生成token并存到redis中
        String token = generateToken(passengerId);
        return ResponseResult.success(token);
    }

    @Override
    public ResponseResult logout(String token) {
        return null;
    }

    /**
     * 生成token，并放入redis缓存，5min
     *
     * @param passengerId
     * @return
     */
    private String generateToken(Long passengerId) {
        String token = JwtUtil.createJavaWebToken(passengerId + "", new Date());
        BoundValueOperations<String, String> operations = redisTemplate.boundValueOps(
                RedisKeyPrefixConstant.PASSENGER_LOGIN_TOKEN_APP_KEY_PRE + passengerId);
        operations.set(token, 5, TimeUnit.MINUTES);
        return token;
    }
}