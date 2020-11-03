package com.yeyangshu.serviceorderdispatch.mapper;

import com.yeyangshu.internalcommon.dto.serviceorderdispatch.dataobject.TagInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

@Mapper
public interface TagInfoMapper {
    /**
     * This method corresponds to the database table tbl_tag_info
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method corresponds to the database table tbl_tag_info
     */
    int insert(TagInfo record);

    /**
     * This method corresponds to the database table tbl_tag_info
     */
    int insertSelective(TagInfo record);

    /**
     * This method corresponds to the database table tbl_tag_info
     */
    TagInfo selectByPrimaryKey(Integer id);

    /**
     * This method corresponds to the database table tbl_tag_info
     */
    int updateByPrimaryKeySelective(TagInfo record);

    /**
     * This method corresponds to the database table tbl_tag_info
     */
    int updateByPrimaryKey(TagInfo record);
}