package com.yeyangshu.internalcommon.util;

import lombok.Data;

/**
 * jwt信息类
 *
 * @author yeyangshu
 */
@Data
public class JwtInfo {

    /**
     * 主题
     */
    String subject;

    /**
     * 签发时间
     */
    Long issueDate;

}