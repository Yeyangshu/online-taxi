package com.yeyangshu.internalcommon.entity.apipassenger.datatransferobject;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 用户登录信息接收类
 *
 * @author yeyangshu
 */
@Data
public class UserAuthRequest {

    /**
     * 用户手机号
     */
    @NotBlank(message = "手机号不能为空")
    @Pattern(message = "手机号校验不正确", regexp = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$")
    private String passengerPhone;

    /**
     * 短信验证码
     */
    @NotBlank
    @Pattern(message = "验证码不正确", regexp = "^[0-9]{6}$")
    private String code;

}