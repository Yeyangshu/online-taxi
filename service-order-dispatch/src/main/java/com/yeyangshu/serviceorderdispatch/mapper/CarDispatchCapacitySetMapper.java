package com.yeyangshu.serviceorderdispatch.mapper;


import com.yeyangshu.internalcommon.entity.serviceorderdispatch.dataobject.CarDispatchCapacitySet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CarDispatchCapacitySetMapper {

    /**
     * 查找车辆调度运力设置
     *
     * @param cityCode 城市编码
     * @param timeType 车辆服务时段，1：白天，2：夜晚
     * @return 具体车辆调度运力设置
     */
    CarDispatchCapacitySet getCarDispatchCapacitySet(@Param("cityCode") String cityCode, @Param("timeType") int timeType);

    /**
     * 查找车辆调度运力设置集合
     *
     * @param cityCode 城市编码
     * @return 所有车辆调度运力设置
     */
    List<CarDispatchCapacitySet> getCarDispatchCapacitySetList(@Param("cityCode") String cityCode);

}