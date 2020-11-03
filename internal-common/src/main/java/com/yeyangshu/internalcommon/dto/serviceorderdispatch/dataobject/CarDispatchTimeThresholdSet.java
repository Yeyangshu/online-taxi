package com.yeyangshu.internalcommon.dto.serviceorderdispatch.dataobject;

import lombok.Data;

import java.util.Date;

/**
 * 车辆调度时间阈值设置
 * A data object class directly models database table <tt>tbl_car_dispatch_time_threshold_set</tt>.
 */
@Data
public class CarDispatchTimeThresholdSet {

    private Integer id;

    private String cityCode;

    private Integer serviceTypeId;

    private Boolean timeThresholdType;

    private Integer timeThreshold;

    private Date createTime;

    private Date updateTime;

}