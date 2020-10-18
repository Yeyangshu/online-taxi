package com.yeyangshu.internalcommon.dto.servicepassengeruser.request;

import lombok.Data;

/**
 * 用户登录消息接收类
 *
 * @author yeyangshu
 */
@Data
public class LoginRequest {

    /**
     * 用户手机号
     */
    private String passengerPhone;

}