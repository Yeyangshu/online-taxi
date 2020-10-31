package com.yeyangshu.apipassenger.service.impl;

import com.yeyangshu.apipassenger.dao.PassengerInfoDao;
import com.yeyangshu.apipassenger.dao.PassengerWalletDao;
import com.yeyangshu.internalcommon.dto.apipassenger.request.TokenRequest;
import com.yeyangshu.apipassenger.service.PassengerInfoService;
import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.entity.PassengerAddress;
import com.yeyangshu.internalcommon.entity.PassengerInfo;
import com.yeyangshu.internalcommon.entity.PassengerRegisterSource;
import com.yeyangshu.internalcommon.entity.PassengerWallet;
import com.yeyangshu.internalcommon.util.EncryptUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
    public PassengerInfo queryPassengerInfoByPhoneNum(String phoneNum) {
        return passengerInfoDao.queryPassengerInfoByPhoneNum(phoneNum);
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
