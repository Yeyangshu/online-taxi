package com.yeyangshu.apipassenger.mapper;

import com.yeyangshu.internalcommon.entity.PassengerWallet;
import org.springframework.stereotype.Service;

/**
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/31 17:31
 */
public interface PassengerWalletMapper {

    int insertSelective(PassengerWallet record);

}
