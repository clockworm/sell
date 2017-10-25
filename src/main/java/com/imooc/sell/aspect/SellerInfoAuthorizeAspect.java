package com.imooc.sell.aspect;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.imooc.sell.constant.CookieConstant;
import com.imooc.sell.constant.RedisConstant;
import com.imooc.sell.exception.SellerAuthorizeException;
import com.imooc.sell.util.CookieUtil;

import lombok.extern.slf4j.Slf4j;
/**
 * AOP登录效验
 */
@Aspect
@Component
@Slf4j
public class SellerInfoAuthorizeAspect {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Pointcut("execution(public  * com.imooc.sell.web.controller.Seller*.*(..))" + "&& !execution(public  * com.imooc.sell.web.controller.LoginController.*(..))")
    public void verify() {
    }

    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //查询Cookie
        Cookie cookie = CookieUtil.getCookie(request, CookieConstant.TOKEN_KEY);
        if(cookie==null){
            log.warn("[登录效验] Cookie未找到token");
            throw new SellerAuthorizeException();
        }
        String token = stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if(StringUtils.isBlank(token)){
            log.warn("[登录效验] Redis未找到token");
            throw new SellerAuthorizeException();
        }
    }
}
