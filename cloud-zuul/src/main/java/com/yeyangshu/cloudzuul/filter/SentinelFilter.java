package com.yeyangshu.cloudzuul.filter;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

/**
 * Sentinel
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/24 9:43
 */
public class SentinelFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return -10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 使用令牌逻辑
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        Entry entry = null;
        try {
            entry = SphU.entry("HelloWorld");
            // 业务逻辑
            System.out.println("正常请求");
        } catch (BlockException e) {
            System.out.println("阻塞住了");
        } finally {
            if (entry != null) {
                //必须存在
                entry.exit();
            }
        }
        return null;
    }
}
