package com.yeyangshu.internalcommon.entity.serviceorderdispatch.dataobject;

import lombok.Data;

import java.util.Date;

/**
 * 车辆调度特殊时段设置
 * A data object class directly models database table <tt>tbl_car_dispatch_special_period_set</tt>.
 */
@Data
public class CarDispatchSpecialPeriodSet {
    private Integer id;

    private String cityCode;

    private Integer serviceTypeId;

    private String timePeriod;

    private Boolean isDelete;

    private Date createTime;

    private Date updateTime;

}