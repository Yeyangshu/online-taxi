package com.yeyangshu.serviceorderdispatch.mapper;


import com.yeyangshu.internalcommon.dto.serviceorderdispatch.dataobject.DriverBaseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

@Mapper
public interface DriverBaseInfoMapper {

    DriverBaseInfo selectByPrimaryKey(Integer id);

}