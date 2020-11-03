package com.yeyangshu.internalcommon.dto.serviceorderdispatch.dataobject;

import lombok.Data;

import java.util.Date;

/**
 * A data object class directly models database table <tt>tbl_tag_info</tt>.
 */
@Data
public class TagInfo {

    /**
     * This field corresponds to the database column tbl_tag_info.id
     */
    private Integer id;

    /**
     * This field corresponds to the database column tbl_tag_info.tag_name
     */
    private String tagName;

    /**
     * This field corresponds to the database column tbl_tag_info.tag_img
     */
    private String tagImg;

    /**
     * This field corresponds to the database column tbl_tag_info.status
     */
    private Boolean status;

    /**
     * This field corresponds to the database column tbl_tag_info.operator_id
     */
    private Integer operatorId;

    /**
     * This field corresponds to the database column tbl_tag_info.create_time
     */
    private Date createTime;

    /**
     * This field corresponds to the database column tbl_tag_info.update_time
     */
    private Date updateTime;

}