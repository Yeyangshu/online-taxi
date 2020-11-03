package com.yeyangshu.internalcommon.entity.servicesms.datatransferobject;

import lombok.Data;

/**
 *
 */
@Data
public class SmsRequest {

    /**
     * 模板ID
     */
    private String templateId;

    /**
     * 手机号
     */
    private String[] phones;

    /**
     * 替换内容
     */
    private String[] content;

}