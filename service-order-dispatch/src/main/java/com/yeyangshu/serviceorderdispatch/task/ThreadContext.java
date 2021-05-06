package com.yeyangshu.serviceorderdispatch.task;

import java.util.Map;

/**
 * @author yeyangshu
 * @version 1.0
 * @date 2020/12/19 0:49
 */
public interface ThreadContext {

    /**
     * 添加属性
     *
     * @param key
     * @param object
     */
    void add(String key, Object object);

    /**
     * 获得属性
     *
     * @param key
     * @return
     */
    Object get(String key);

    Map<String, Object> getAll();
}
