package com.yeyangshu.servicepassengeruser.service.impl;

import com.yeyangshu.internalcommon.constant.IdentityEnum;
import com.yeyangshu.internalcommon.constant.RedisKeyPrefixConstant;
import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.dto.apipassenger.request.TokenRequest;
import com.yeyangshu.internalcommon.entity.PassengerInfo;
import com.yeyangshu.internalcommon.entity.PassengerRegisterSource;
import com.yeyangshu.internalcommon.entity.PassengerWallet;
import com.yeyangshu.internalcommon.util.EncryptUtil;
import com.yeyangshu.internalcommon.util.JwtUtil;
import com.yeyangshu.servicepassengeruser.dao.PassengerInfoDao;
import com.yeyangshu.servicepassengeruser.dao.PassengerWalletDao;
import com.yeyangshu.servicepassengeruser.service.PassengerInfoService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * 乘客信息服务实现类
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/31 13:53
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PassengerInfoServiceImpl implements PassengerInfoService {

    @NonNull
    private PassengerInfoDao passengerInfoDao;

    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> vOps;

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 新用户
     */
    private static final int NEW_PASSENGER = 0;

    /**
     * 老用户
     */
    private static final int OLD_PASSENGER = 1;

    /**
     * 验证码登录方式
     */
    private static final byte METHOD = 1;

    /**
     * token有效期 1天
     */
    private static final Integer EXP_HOURS = 24;

//    @NonNull
//    private PassengerAddressDao passengerAddressDao;

    @NonNull
    private PassengerWalletDao passengerWalletDao;

    @Override
    public PassengerInfo queryPassengerInfoById(Integer passengerId) {
        return passengerInfoDao.queryPassengerInfoById(passengerId);

    }

    @Override
    public int initPassengerWallet(Integer passengerId) {
        PassengerWallet record = new PassengerWallet();
        record.setPassengerInfoId(passengerId);
        record.setFreezeGiveFee(0d);
        record.setFreezeCapital(0d);
        record.setGiveFee(0d);
        record.setCapital(0d);
        record.setCreateTime(new Date());

        return passengerWalletDao.insertSelective(record);
    }

    @Override
    public ResponseResult queryPassengerInfoByTokenRequest(TokenRequest request) {
        String phoneNumber = request.getPhoneNumber();
        String encryptPhoneNumber = EncryptUtil.toHexString(EncryptUtil.encrypt(phoneNumber));
        log.info("乘客加密后的手机号：" + encryptPhoneNumber);
        PassengerInfo passengerInfo = passengerInfoDao.queryPassengerInfoByPhoneNum(encryptPhoneNumber);
        log.info("根据手机号查询，乘客信息。" + passengerInfo);
        PassengerInfo passengerInfoTemp = new PassengerInfo();
        int passengerId;
        int isNewPassenger = OLD_PASSENGER;
        // 若查询不到乘客信息，记录乘客信息
        if (null == passengerInfo) {
            isNewPassenger = NEW_PASSENGER;
            Date date = new Date();
            passengerInfoTemp.setBirthday(date);
            passengerInfoTemp.setLastLoginTime(date);
            passengerInfoTemp.setLastLoginMethod(METHOD);
            passengerInfoTemp.setPhone(encryptPhoneNumber);
            passengerInfoTemp.setBalance(new BigDecimal(0));
            passengerInfoTemp.setRegisterTime(date);
            passengerInfoTemp.setCreateTime(date);
            passengerInfoTemp.setUpdateTime(date);
            log.info("新增乘客手机号：" + encryptPhoneNumber);
            passengerInfoDao.insertSelective(passengerInfoTemp);
            passengerId = passengerInfoTemp.getId();
            // 新增注册来源
            try {
                PassengerRegisterSource passengerRegisterSource = new PassengerRegisterSource();
                passengerRegisterSource.setPassengerInfoId(passengerId);
                String registerSource = request.getRegisterSource();
                passengerRegisterSource.setRegisterSource(registerSource);
                passengerRegisterSource.setMarketChannel(request.getMarketChannel());
                passengerRegisterSource.setCreateTime(new Date());
                passengerInfoDao.insertPassengerRegisterSource(passengerRegisterSource);
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.info("乘客注册或登录 - " + encryptPhoneNumber + " - 校验注册状态 - 用户未注册，已插入新用户记录");
            // 初始化乘客钱包
            initPassengerWallet(passengerId);
        } else {
            log.info("乘客注册或登录 - " + encryptPhoneNumber + " - 校验注册状态 - 用户已注册");
            // 若乘客登录或者注册过了，更新登录时间
            passengerId = passengerInfo.getId();
            passengerInfoDao.updatePassengerInfoLoginTime(passengerId);
        }
        // 乘客登录，生成jwtStr
        String subject = IdentityEnum.PASSENGER.getCode() + "_" + phoneNumber + "_" + passengerId;
        log.info("token subject:" + subject);
        String jwtStr = JwtUtil.createJavaWebToken(subject, new Date());
        vOps.set(RedisKeyPrefixConstant.PASSENGER_LOGIN_TOKEN_APP_KEY_PRE + subject, jwtStr, EXP_HOURS, TimeUnit.HOURS);
        log.info("乘客注册或登录用户-" + phoneNumber + "- access_token:" + jwtStr);
        // 多终端互踢
        passengerInfo = passengerInfoDao.queryPassengerInfoByPhoneNum(encryptPhoneNumber);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("passengerInfo", passengerInfo);
        jsonObject.put("token", jwtStr);
        jsonObject.put("isNewPassenger", isNewPassenger);
        return ResponseResult.success(jsonObject);
    }

    @Override
    public void updatePassengerInfoLoginTime(Integer passengerId) {
        passengerInfoDao.updatePassengerInfoLoginTime(passengerId);
    }


    @Override
    public void insertPassengerInfo(PassengerInfo passengerInfo) {
        passengerInfoDao.insertSelective(passengerInfo);
    }

    /**
     * 获取乘客信息
     *
     * @param tokenRequest 对象
     * @return HashMap<String, Object>
     */
    @Override
    public HashMap<String, Object> getPassengerInfoView(TokenRequest tokenRequest) {
        HashMap<String, Object> view = new HashMap<>(16);
//        PassengerInfo passengerInfo = passengerInfoDao.selectByPrimaryKey(tokenRequest.getId());
//        PassengerAddress passengerAddress = new PassengerAddress();
//        passengerAddress.setPassengerInfoId(tokenRequest.getId());
//        List<PassengerAddress> passengerAddressList = new ArrayList<>();
//        if (null != tokenRequest.getType()) {
//            passengerAddress.setType(tokenRequest.getType());
//            passengerAddress = passengerAddressDao.selectByAddPassengerInfoId(passengerAddress);
//        } else {
//            passengerAddressList = passengerAddressDao.selectPassengerAddressList(tokenRequest.getId());
//        }
//
//        if (null != passengerInfo) {
//            String decrypt;
//            if (!StringUtils.isEmpty(passengerInfo.getPhone())) {
//                decrypt = EncryptUtil.decrypt(passengerInfo.getPhone());
//                passengerInfo.setPhone(decrypt);
//            }
//            view.put("passengerInfo", passengerInfo);
//        }
//        if (null != passengerAddressList && 0 != passengerAddressList.size()) {
//            view.put("passengerAddressList", passengerAddressList);
//        }
//        if (null != passengerAddress && null != tokenRequest.getType()) {
//            view.put("passengerAddress", passengerAddress);
//        }

        return view;
    }

    /**
     * 修改乘客地址
     *
     * @param passengerInfo 对象
     * @return ResponseResult实例
     */
    @Override
    public ResponseResult updatePassengerInfo(PassengerInfo passengerInfo) {
//        //乘客信息
//        if (null != passengerInfo) {
//            if (!StringUtils.isEmpty(passengerInfo.getPhone())) {
//                String decrypt = EncryptUtil.encryptionPhoneNumber(passengerInfo.getPhone());
//                passengerInfo.setPhone(decrypt);
//            }
//        }
//        int updateOrInsert;
//        if (null != passengerInfo && null != passengerInfo.getId()) {
//            updateOrInsert = passengerInfoDao.updateByPrimaryKeySelective(passengerInfo);
//        } else {
//            if (null != passengerInfo) {
//                passengerInfo.setCreateTime(new Date());
//            }
//            updateOrInsert = passengerInfoDao.insertSelective(passengerInfo);
//        }
//        if (0 == updateOrInsert) {
//            return ResponseResult.fail(BusinessInterfaceStatus.FAIL.getCode(), "修改或添加乘客信息失败!");
//        } else {
//            return ResponseResult.success("");
//        }
        return ResponseResult.success("");
    }

    @Override
    public int insertPassengerRegisterSource(PassengerRegisterSource passengerRegisterSource) {
        return passengerInfoDao.insertPassengerRegisterSource(passengerRegisterSource);
    }

    @Override
    public int updatePassengerInfoById(PassengerInfo passengerInfo) {
        return passengerInfoDao.updateByPrimaryKeySelective(passengerInfo);
    }
}
