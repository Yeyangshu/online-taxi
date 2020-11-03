package com.yeyangshu.internalcommon.entity.servicepassengeruser.dataobject;

import lombok.Data;

import java.util.Date;

/**
 * 乘客钱包
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/31 15:16
 */
@Data
public class PassengerWallet {

    private Integer id;

    private Integer passengerInfoId;

    private Double capital;

    private Double giveFee;

    private Double freezeCapital;

    private Double freezeGiveFee;

    private Date createTime;

    private Date updateTime;
}
