package com.yeyangshu.internalcommon.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeyangshu.internalcommon.dto.ResponseResult;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;


/**
 * RestTemplate帮助类
 */
@Configuration
public class ResponseResultHelper {

    /**
     * 将ResponseResult解析为指定的类
     *
     * @param result ResponseResult
     * @param clazz  指定的类
     * @param <T>    指定的类类型
     * @return 指定的类的实例
     * @throws Exception 异常
     */
    public static <T> T parse(@Nullable ResponseResult result, Class<T> clazz) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(mapper.writeValueAsString(result != null ? result.getData() : null), clazz);
    }
}