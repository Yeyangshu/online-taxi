package com.yeyangshu.servicevaluation.service;

import com.yeyangshu.internalcommon.entity.ResponseResult;
import com.yeyangshu.internalcommon.entity.apipassenger.datatransferobject.EstimateRequest;

/**
 * 地图服务
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/1 19:42
 */
public interface ServiceMapService {

    /**
     * 获取距离测量结果
     *
     * @param request 请求信息
     * @return Route结果
     */
    ResponseResult getRoute(EstimateRequest request);
}
