package com.yeyangshu.cloudzuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.yeyangshu.internalcommon.constant.RedisKeyPrefixConstant;
import com.yeyangshu.internalcommon.util.JwtInfo;
import com.yeyangshu.internalcommon.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * 登陆鉴权
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/24 9:38
 */
@Component
@Slf4j
public class AuthFilter extends ZuulFilter {

    /**
     * token有效期 1天
     */
    private static final Integer EXP_HOURS = 24;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> vOps;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("auth intercept, start authentication");
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorization:" + token);
        if (StringUtils.isNotBlank(token)) {
            JwtInfo tokenJwtInfo = JwtUtil.parseToken(token);
            if(null != tokenJwtInfo) {
                String subject = tokenJwtInfo.getSubject();
                Long tokenIssueDate = tokenJwtInfo.getIssueDate();
                String redisToken = vOps.get(RedisKeyPrefixConstant.PASSENGER_LOGIN_TOKEN_APP_KEY_PRE + subject);
                log.info("缓存中的认证key:" + subject + ", value:" + redisToken);
                if (StringUtils.isNotEmpty(redisToken) && StringUtils.equals(token, redisToken)) {
                    log.info("验证Token是否失效 - Token仍有效 - 延长token有效期，phone: " + subject + " token:" + redisToken);
                    vOps.set(RedisKeyPrefixConstant.PASSENGER_LOGIN_TOKEN_APP_KEY_PRE + subject, token, EXP_HOURS, TimeUnit.HOURS);
                    return null;
                }
            }
        }
        // 不往下走，还走剩下的过滤器，但是不向后面的服务转发。
        requestContext.setSendZuulResponse(false);
        requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        requestContext.setResponseBody("auth fail");
        return null;
    }
}
