package com.yeyangshu.apipassenger.dao;

import com.yeyangshu.apipassenger.mapper.PassengerWalletMapper;
import com.yeyangshu.internalcommon.entity.PassengerWallet;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/31 13:31
 */
@Repository
@RequiredArgsConstructor
public class PassengerWalletDao {

    @NonNull
    private PassengerWalletMapper passengerWalletMapper;

    public int insertSelective(PassengerWallet record){
        return passengerWalletMapper.insertSelective(record);
    }

}
