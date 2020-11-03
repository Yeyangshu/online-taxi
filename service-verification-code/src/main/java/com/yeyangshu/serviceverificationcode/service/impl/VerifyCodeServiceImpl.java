package com.yeyangshu.serviceverificationcode.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yeyangshu.serviceverificationcode.service.VerifyCodeService;
import com.yeyangshu.internalcommon.constant.CommonStatusEnum;
import com.yeyangshu.internalcommon.constant.IdentityConstant;
import com.yeyangshu.internalcommon.constant.RedisKeyPrefixConstant;
import com.yeyangshu.internalcommon.entity.ResponseResult;
import com.yeyangshu.internalcommon.entity.serviceverificationcode.datatransferobject.VerifyCodeResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 验证码服务实现类
 *
 * @author yeyangshu
 */
@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    /**
     * 根据身份和手机号生成验证码并存入Redis缓存，时长2分钟
     *
     * @param identity
     * @param phoneNumber
     * @return
     */
    @Override
    public ResponseResult<VerifyCodeResponse> generateCode(int identity, String phoneNumber) {
        // 校验三档验证。乌云安全监测，也无妨控制，不能无限制发短信
        // Redis 1分钟发了3次后，不能发送，1小时发了10次，24小时不能发送
        String code = String.valueOf((int) ((Math.random() * 9 + 1) * Math.pow(10, 5)));

        // 生成Redis key
        String keyPre = generateKeyPreByIdentity(identity);
        String key = keyPre + phoneNumber;
        BoundValueOperations<String, String> codeRedis = redisTemplate.boundValueOps(key);
        // 将code存入Redis，2分钟后过期
        codeRedis.set(code, 2, TimeUnit.MINUTES);

        VerifyCodeResponse data = new VerifyCodeResponse();
        data.setCode(code);
        String jsonString = JSONObject.toJSONString(data);
        return ResponseResult.success(jsonString);
    }

    /**
     * 校验验证码
     *
     * @param identity
     * @param phoneNumber
     * @param code
     * @return
     */
    @Override
    public ResponseResult verifyCode(int identity, String phoneNumber, String code) {
        // 三档验证
        // 生成Redis key
        String keyPre = generateKeyPreByIdentity(identity);
        String key = keyPre + phoneNumber;
        BoundValueOperations<String, String> codeRedis = redisTemplate.boundValueOps(key);
        String redisCode = codeRedis.get();
        if (StringUtils.isNotBlank(code) && StringUtils.isNotBlank(redisCode) && code.trim().equals(redisCode.trim())) {
            return ResponseResult.success("");
        } else {
            return ResponseResult.fail(CommonStatusEnum.VERIFY_CODE_ERROR.getCode(), CommonStatusEnum.VERIFY_CODE_ERROR.getValue(), "");
        }
    }

    /**
     * 根据身份类型生成对应的Redis缓存key
     *
     * @param identity
     * @return
     */
    private String generateKeyPreByIdentity(int identity) {
        String keyPre = "";
        if (identity == IdentityConstant.PASSENGER) {
            keyPre = RedisKeyPrefixConstant.PASSENGER_LOGIN_CODE_KEY_PRE;
        } else if (identity == IdentityConstant.DRIVER) {
            keyPre = RedisKeyPrefixConstant.DRIVER_LOGIN_CODE_KEY_PRE;
        }
        return keyPre;
    }
}