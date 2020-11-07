package com.yeyangshu.servicepay.mapper;

import com.yeyangshu.internalcommon.entity.servicepay.dataobject.OrderEvent;
import java.util.List;

/**
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/7 19:47
 */
public interface PayEventMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(OrderEvent record);

    int insertSelective(OrderEvent record);

    OrderEvent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderEvent record);

    int updateByPrimaryKey(OrderEvent record);

    List<OrderEvent> selectByOrderType(String orderType);

    int updateEvent(String orderType);
}
