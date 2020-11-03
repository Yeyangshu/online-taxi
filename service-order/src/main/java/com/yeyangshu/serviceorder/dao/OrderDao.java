package com.yeyangshu.serviceorder.dao;

import com.yeyangshu.internalcommon.entity.serviceorder.dataobject.Order;
import com.yeyangshu.serviceorder.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDao {

    @Autowired
    OrderMapper orderMapper;

    public Integer insertOrder(Order order) {
        return orderMapper.insertSelective(order);
    }
}