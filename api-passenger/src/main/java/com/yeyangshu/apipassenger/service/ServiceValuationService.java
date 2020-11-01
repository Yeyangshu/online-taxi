package com.yeyangshu.apipassenger.service;

import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.dto.apipassenger.request.EstimateRequest;

/**
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/1 22:51
 */
public interface ServiceValuationService {

    ResponseResult queryEstimatePrice(EstimateRequest request);
}
