package com.yeyangshu.internalcommon.dto.serviceorderdispatch.datatransferobject;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 极光推送信息类
 */
@Data
@NoArgsConstructor
public class PushRequest {

    private String sendId;

    private Integer sendIdentity;

    private int acceptIdentity ;

    private String acceptId;

    private int messageType;

    private String title;

    private String messageBody;

    private Integer messageChannel;

    private String businessMessage;

    private Integer businessType;

}