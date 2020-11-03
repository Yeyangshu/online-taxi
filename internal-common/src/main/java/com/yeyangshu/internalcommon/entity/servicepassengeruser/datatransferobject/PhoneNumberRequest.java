package com.yeyangshu.internalcommon.entity.servicepassengeruser.datatransferobject;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 手机号码信息接收类
 *
 * @author yeyangshu
 */
@Data
public class PhoneNumberRequest {

    /**
     * 用户手机号
     */
    @NotNull(message = "用户手机号不能为空")
    @Pattern(message = "用户手机号校验不正确", regexp = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$")
    private String phoneNumber;
}