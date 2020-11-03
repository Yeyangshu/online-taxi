package com.yeyangshu.serviceorderdispatch.mapper;


import com.yeyangshu.internalcommon.entity.serviceorderdispatch.dataobject.DriverBaseInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DriverBaseInfoMapper {

    DriverBaseInfo selectByPrimaryKey(Integer id);

}