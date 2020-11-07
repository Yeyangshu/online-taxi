package com.yeyangshu.servicepay.dao;

import com.yeyangshu.internalcommon.entity.servicepay.dataobject.OrderEvent;
import com.yeyangshu.servicepay.mapper.PayEventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 消息事件dao
 */
@Repository
public class PayEventDao {

    @Autowired
    PayEventMapper payEventMapper;

    public int deleteByPrimaryKey(Integer id) {
        return payEventMapper.deleteByPrimaryKey(id);
    }

    public int insert(OrderEvent record) {
        return payEventMapper.insertSelective(record);
    }

    public OrderEvent selectByOrderEventId(Integer id) {
        return payEventMapper.selectByPrimaryKey(id);
    }

    public int updateOrderEvent(OrderEvent record) {
        return payEventMapper.updateByPrimaryKeySelective(record);
    }

    public List<OrderEvent> listByOrderType(String orderType) {
        return payEventMapper.selectByOrderType(orderType);
    }

    public int updateEvent(String orderType) {
        return payEventMapper.updateEvent(orderType);
    }

}