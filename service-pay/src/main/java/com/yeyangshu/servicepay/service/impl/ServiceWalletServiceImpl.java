package com.yeyangshu.servicepay.service.impl;

import com.yeyangshu.internalcommon.constant.PayConst;
import com.yeyangshu.internalcommon.entity.servicepassengeruser.dataobject.PassengerWallet;
import com.yeyangshu.internalcommon.entity.servicepay.dataobject.PassengerWalletRecord;
import com.yeyangshu.internalcommon.util.BigDecimalUtil;
import com.yeyangshu.servicepay.service.ServiceWalletService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/7 8:50
 */
@Service
public class ServiceWalletServiceImpl implements ServiceWalletService {


    @Override
    public int alterPassengerWalletPrice(Integer passengerInfoId, Double payCapital, Double payGiveFee, int changeStatus) {
        return 0;
    }

    @Override
    public PassengerWalletRecord createWalletRecord(Integer passengerInfoId, Double payCapital, Double payGiveFee,
                                                    Integer payType, Integer tradeType, String description,
                                                    Integer orderId, Integer payStatus, String createUser) {
        Date date = new Date();
        PassengerWalletRecord passengerWalletRecord = new PassengerWalletRecord();
        passengerWalletRecord.setPassengerInfoId(passengerInfoId);
        passengerWalletRecord.setPayTime(date);
        passengerWalletRecord.setPayCapital(payCapital);
        passengerWalletRecord.setPayGiveFee(payGiveFee);

        // 计算支付总费用
        Double sumMoney = BigDecimalUtil.add(payCapital.toString(), payGiveFee.toString());
        Double discount = 0D;

        if (sumMoney.compareTo(PayConst.ZERO) > 0) {
            // 计算充值折扣
            discount = BigDecimalUtil.div(payGiveFee.toString(), sumMoney.toString(), 2);
        }
        passengerWalletRecord.setRechargeDiscount(discount);
        passengerWalletRecord.setPayType(payType);
        passengerWalletRecord.setCreateTime(date);
        passengerWalletRecord.setPayStatus(payStatus);
        passengerWalletRecord.setTradeType(tradeType);
        passengerWalletRecord.setDescription(description);
        passengerWalletRecord.setOrderId(orderId);
        passengerWalletRecord.setCreateUser(createUser);
//        walletRecordDao.insertSelective(passengerWalletRecord);
        return passengerWalletRecord;

    }

    @Override
    public int handleCallBack(int tradeType, Integer rechargeId, String tradeNo) {
        return 0;
    }

    @Override
    public PassengerWallet initPassengerWallet(Integer passengerInfoId, Double payCapital, Double payGiveFee) {
        return null;
    }

    @Override
    public int unfreezeWallet(Integer passengerInfoId, Double freezeCapital, Double freezeGiveFee) {
        return 0;
    }
}
