package com.yeyangshu.internalcommon.entity.servicesms.datatransferobject;

import lombok.Data;

import java.util.List;

/**
 * 第三方短信发送信息接受类
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/13 21:42
 */
@Data
public class SmsSendRequest {

    /**
     * 短信接收者
     */
    private String[] receivers;

    /**
     * 短信模板
     */
    private List<SmsTemplateDto> data;

}
