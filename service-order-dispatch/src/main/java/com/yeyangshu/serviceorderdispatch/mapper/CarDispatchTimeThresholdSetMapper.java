package com.yeyangshu.serviceorderdispatch.mapper;

import com.yeyangshu.internalcommon.dto.serviceorderdispatch.dataobject.CarDispatchTimeThresholdSet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 */
@Mapper
public interface CarDispatchTimeThresholdSetMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(CarDispatchTimeThresholdSet record);

    int insertSelective(CarDispatchTimeThresholdSet record);

    CarDispatchTimeThresholdSet selectByPrimaryKey(Integer id);

    /**
     * 查询车辆调度
     * @param cityCode 城市编码
     * @param type 时间阈值类型 1：开启立即用车派单逻辑，2：预约用车待派订单开启强派模式
     * @return
     */
    CarDispatchTimeThresholdSet selectByCityAndServiceType(@Param("cityCode") String cityCode, @Param("type") int type);

    int updateByPrimaryKeySelective(CarDispatchTimeThresholdSet record);

    int updateByPrimaryKey(CarDispatchTimeThresholdSet record);
}