package com.yeyangshu.serviceorder.mapper;

import com.yeyangshu.internalcommon.entity.servicepay.dataobject.OrderEvent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/26 22:22
 */
@Mapper
public interface TblOrderEventDao {

    int deleteByPrimaryKey(Integer id);

    int insert(OrderEvent record);

    int insertSelective(OrderEvent record);

    OrderEvent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderEvent record);

    int updateByPrimaryKey(OrderEvent record);

    List<OrderEvent> selectByOrderType(String orderType);

    int updateEvent(String orderType);
}
