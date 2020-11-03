package com.yeyangshu.serviceorder.service;

import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.dto.servicepassengeruser.PassengerInfo;

public interface ServicePassengerService {

    ResponseResult selectByPrimaryKey(Integer passengerId);
}