package com.yeyangshu.apipassenger.service;

/**
 * Redis Token存储服务接口
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/31 14:42
 */
public interface RedisTokenService {

    void put(String phoneNum, String token, Integer expHours);

    String get(String phoneNum);

    Boolean expire(String phoneNum, Integer expHours);

    void delete(String phoneNum);
}
