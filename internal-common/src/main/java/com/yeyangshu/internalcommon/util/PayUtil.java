package com.yeyangshu.internalcommon.util;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 支付工具类
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/7 9:41
 */
public class PayUtil {

    /**
     * 支付宝回调数据转化为map
     *
     * @param request 支付宝回调请求
     * @return map
     */
    public static Map<String, String> convertRequestParamsToMap(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        Set<Map.Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();
        for (Map.Entry<String, String[]> entry : entrySet) {
            String name = entry.getKey();
            String[] values = entry.getValue();
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        return params;
    }
}
