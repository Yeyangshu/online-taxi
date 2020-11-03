package com.yeyangshu.servicesms.controller;

import com.yeyangshu.internalcommon.entity.ResponseResult;
import com.yeyangshu.internalcommon.entity.servicesms.datatransferobject.SmsSendRequest;
import com.yeyangshu.servicesms.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 第三方短信服务
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/13 21:39
 */
@RestController
@RequestMapping("/send")
@Slf4j
public class SendSmsController {

    @Autowired
    private SmsService smsService;

    @PostMapping("/sms-template")
    public ResponseResult send(@RequestBody SmsSendRequest smsSendRequest) {
        JSONObject param =JSONObject.fromObject(smsSendRequest);
        log.info("/send/sms-template, request param is " + param.toString());
        return smsService.sendSms(smsSendRequest);
    }
}
