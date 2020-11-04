package com.yeyangshu.serviceorderdispatch.mapper;

import com.yeyangshu.internalcommon.entity.serviceorder.dataobject.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface DispatchOrderMapper {

    /**
     * 删除
     * @param id 订单id
     * @return null
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @param record
     * @return
     */
    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    int countByBetweenTime(@Param("driverId") int driverId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    int countOrderByParam(@Param("driverId") int driverId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

}