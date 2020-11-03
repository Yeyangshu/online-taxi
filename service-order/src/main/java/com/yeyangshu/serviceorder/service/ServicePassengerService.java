package com.yeyangshu.serviceorder.service;

import com.yeyangshu.internalcommon.entity.ResponseResult;

public interface ServicePassengerService {

    ResponseResult selectByPrimaryKey(Integer passengerId);
}