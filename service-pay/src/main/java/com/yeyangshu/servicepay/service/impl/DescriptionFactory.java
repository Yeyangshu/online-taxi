package com.yeyangshu.servicepay.service.impl;

import com.yeyangshu.internalcommon.constant.PayConst;

/**
 * 描述信息工厂类
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/7 9:04
 */
public class DescriptionFactory {

    public static String createDescription(Double payCapital, Double payGiveFee, String prefix) {
        String description = "";

        if (payCapital.compareTo(PayConst.ZERO) > 0 && payGiveFee.compareTo(PayConst.ZERO) > 0) {
            description = prefix + "（本金+赠额）";
        } else if (payCapital.compareTo(PayConst.ZERO) > 0) {
            description = prefix + "（本金）";
        } else if (payGiveFee.compareTo(PayConst.ZERO) > 0) {
            description = prefix + "（赠额）";
        }
        return description;
    }
}
