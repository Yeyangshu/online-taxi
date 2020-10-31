package com.yeyangshu.internalcommon.dto.apipassenger.request;

import lombok.Data;

/**
 * 获取token请求信息接受类
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/31 9:44
 */
@Data
public class TokenRequest {

    /** 用户手机号 */
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
