package com.yeyangshu.serviceorder.mapper;

import com.yeyangshu.serviceorder.entity.TblOrderEvent;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/26 22:22
 */
@Mapper
public interface TblOrderEventDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TblOrderEvent record);

    int insertSelective(TblOrderEvent record);

    TblOrderEvent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TblOrderEvent record);

    int updateByPrimaryKey(TblOrderEvent record);

    List<TblOrderEvent> selectByOrderType(String orderType);

    int updateEvent(String orderType);
}
