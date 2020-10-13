package com.yeyangshu.servicesms.service.impl;

import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.dto.servicesms.SmsTemplateDto;
import com.yeyangshu.internalcommon.dto.servicesms.request.SmsSendRequest;
import com.yeyangshu.servicesms.constant.SmsStatusEnum;
import com.yeyangshu.servicesms.dao.ServiceSmsRecordDao;
import com.yeyangshu.servicesms.dao.ServiceSmsTemplateCustomDao;
import com.yeyangshu.servicesms.entity.ServiceSmsRecord;
import com.yeyangshu.servicesms.entity.ServiceSmsTemplate;
import com.yeyangshu.servicesms.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/13 21:54
 */
@Service
@Slf4j
public class SmsServiceImpl implements SmsService {

    /**
     * 缓存用于替换内容的模板
     */
    private Map<String, String> templateMaps = new HashMap<String, String>();

    @Autowired
    ServiceSmsTemplateCustomDao serviceSmsTemplateCustomDao;

    @Autowired
    private ServiceSmsRecordDao serviceSmsRecordDao;

    @Override
    public ResponseResult sendSms(SmsSendRequest request) {
        log.info("sms request = " + request.toString());
        for (String phoneNumber : request.getReceivers()) {
            List<SmsTemplateDto> templates = request.getData();

            ServiceSmsRecord smsRecord = new ServiceSmsRecord();
            // 若内存中没有模板信息，从数据库中加载模板
            for (SmsTemplateDto template : templates) {
                loadTemplateFromDB(templateMaps, template);
                String content = "";
                content = replacePlaceholder(template);
                try {
                    int result = send(phoneNumber, template.getId(), template.getTemplateMap());

                    // 组装短信记录smsRecord对象
                    smsRecord.setSendTime(new Date());
                    smsRecord.setOperatorName("");
                    smsRecord.setSendFlag(1);
                    smsRecord.setSendNumber(0);
                    smsRecord.setSmsContent(content);

                    if (result != SmsStatusEnum.SEND_SUCCESS.getCode()) {
                        throw new Exception("短信发送失败");
                    }
                } catch (Exception e) {
                    smsRecord.setSendFlag(0);
                    smsRecord.setSendNumber(1);
                    log.error("发送短信（" + template.getId() + "）失败：" + phoneNumber, e);
                } finally {
                    smsRecord.setCreateTime(new Date());
                    serviceSmsRecordDao.insert(smsRecord);
                }
            }
        }
        return ResponseResult.success("");
    }

    /**
     * 从数据库中加载模板
     *
     * @param templateMaps
     * @param template
     */
    private void loadTemplateFromDB(Map<String, String> templateMaps, SmsTemplateDto template) {
        if (!templateMaps.containsKey(template.getId())) {
            log.info("load template from db, template id is " + template.getId());
            ServiceSmsTemplate serviceSmsTemplate = serviceSmsTemplateCustomDao.selectByTemplateId(template.getId());
            templateMaps.put(template.getId(), serviceSmsTemplate.getTemplateContent());
        }
    }

    /**
     * 替换占位符
     *
     * @param template
     * @return
     */
    private String replacePlaceholder(SmsTemplateDto template) {
        String content = templateMaps.get(template.getId());
        for (Map.Entry<String, Object> entry : template.getTemplateMap().entrySet()) {
            content = StringUtils.replace(content, "${" + entry.getKey() + "}", "" + entry.getValue());
        }
        return content;
    }

    /**
     * 发送短信
     * @param phoneNumber
     * @param templateId
     * @param map
     * @return
     * @throws Exception
     */
    private int send(String phoneNumber, String templateId, Map<String, ?> map) throws Exception {
        JSONObject param = new JSONObject();
        param.putAll(map);
        return sendMsg(phoneNumber, templateId, param.toString());
    }

    /**
     * 供应商发送短信
     * @param phoneNumber
     * @param templateCode
     * @param param
     * @return
     */
    private int sendMsg(String phoneNumber, String templateCode, String param) {
        return SmsStatusEnum.SEND_SUCCESS.getCode();
    }
}
