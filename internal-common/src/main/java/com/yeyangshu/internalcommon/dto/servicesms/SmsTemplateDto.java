package com.yeyangshu.internalcommon.dto.servicesms;

import lombok.Data;

import java.util.Map;

/**
 * 短信模板信息接受类
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/13 21:44
 */
@Data
public class SmsTemplateDto {

    /**
     * 短信模板id
     */
    private String id;

    /**
     * 参数、占位符
     */
    private Map<String, Object> templateMap;
}
