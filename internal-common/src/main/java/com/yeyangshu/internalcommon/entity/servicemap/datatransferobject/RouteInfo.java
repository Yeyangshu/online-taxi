package com.yeyangshu.internalcommon.entity.servicemap.datatransferobject;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 距离测量结果
 *
 */
@Data
@AllArgsConstructor
public class RouteInfo {

    /**
     * 行驶距离（米）
     */
    private Double distance;

    /**
     * 行驶时间（秒）
     */
    private Double duration;
}
