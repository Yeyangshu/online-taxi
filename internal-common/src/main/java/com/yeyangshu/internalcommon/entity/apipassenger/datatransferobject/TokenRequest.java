package com.yeyangshu.internalcommon.entity.apipassenger.datatransferobject;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 获取token请求信息接受类
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/31 9:44
 */
@Data
public class TokenRequest {

    /**
     * 用户手机号
     */
    @NotNull(message = "用户手机号不能为空")
    @Pattern(message = "用户手机号校验不正确", regexp = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$")
    private String phoneNumber;

    /** 短信验证码 */
    private String verifyCode;

    /**  */
    private Integer id;

    /**  */
    private Integer type;

    /**  */
    private Integer identityStatus;

    /**  */
    private String equipType;

    /** 精度 */
    private Double longitude;

    /** 纬度 */
    private Double latitude;

    /** 速度 */
    private Double speed;

    /**  */
    private Double accuracy;

    /**  */
    private Double direction;

    /** 高度 */
    private Double height;

    /** 城市 */
    private String city;

    /** token */
    private String token;

    /** 注册来源 */
    private String registerSource;

    /** 市场渠道 */
    private String marketChannel;
}
