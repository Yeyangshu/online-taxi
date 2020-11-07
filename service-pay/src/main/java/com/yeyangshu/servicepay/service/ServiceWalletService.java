package com.yeyangshu.servicepay.service;

import com.yeyangshu.internalcommon.entity.servicepassengeruser.dataobject.PassengerWallet;
import com.yeyangshu.internalcommon.entity.servicepay.dataobject.PassengerWalletRecord;

/**
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/7 8:49
 */
public interface ServiceWalletService {

    /**
     * 用户的余额变更
     *
     * @param passengerInfoId
     * @param payCapital
     * @param payGiveFee
     * @param changeStatus    -1：减，1：加
     * @return
     */
    int alterPassengerWalletPrice(Integer passengerInfoId, Double payCapital, Double payGiveFee, int changeStatus);

    /**
     * 生成钱包记录
     *
     * @param passengerInfoId
     * @param payCapital
     * @param payGiveFee
     * @param payType
     * @param tradeType
     * @param description
     * @param orderId
     * @return
     */
    PassengerWalletRecord createWalletRecord(Integer passengerInfoId, Double payCapital, Double payGiveFee,
                                             Integer payType, Integer tradeType, String description,
                                             Integer orderId, Integer payStatus, String createUser);

    /**
     * 支付完成处理逻辑
     *
     * @param tradeType
     * @param rechargeId
     * @return
     */
    int handleCallBack(int tradeType, Integer rechargeId, String tradeNo);

    /**
     * 初始化乘客钱包
     *
     * @param passengerInfoId
     * @param payCapital
     * @param payGiveFee
     * @return
     */
    PassengerWallet initPassengerWallet(Integer passengerInfoId, Double payCapital, Double payGiveFee);

    /**
     * 冻结乘客钱包
     *
     * @param passengerInfoId
     * @param freezeCapital
     * @param freezeGiveFee
     * @return
     */
    public int unfreezeWallet(Integer passengerInfoId, Double freezeCapital, Double freezeGiveFee);
}
