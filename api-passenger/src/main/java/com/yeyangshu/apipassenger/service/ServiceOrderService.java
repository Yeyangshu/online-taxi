package com.yeyangshu.apipassenger.service;

import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.dto.apipassenger.request.OrderRequest;

/**
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/2 20:26
 */
public interface ServiceOrderService {

    ResponseResult queryEstimateFee(OrderRequest request);
}
