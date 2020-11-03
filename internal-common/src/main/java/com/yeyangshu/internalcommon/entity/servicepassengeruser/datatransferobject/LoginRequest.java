package com.yeyangshu.internalcommon.entity.servicepassengeruser.datatransferobject;

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