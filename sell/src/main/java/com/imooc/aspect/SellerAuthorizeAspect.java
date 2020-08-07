package com.imooc.aspect;

import com.imooc.constant.CookieConstant;
import com.imooc.constant.RedisConstant;
import com.imooc.exception.SellException;
import com.imooc.exception.SellerAuthorizeException;
import com.imooc.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-08-01 21:19
 **/
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.imooc.controller.Seller*.*(..))" +
    "&& !execution(public * com.imooc.controller.SellerUserController.*(..))")
    public void verify(){}

    @Before("verify()")
    public void doVerify(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //Search Cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if(cookie == null){
            log.warn("[user identification] can not find token in the cookie");
            throw new SellerAuthorizeException();
        }

        //search the token in the redis
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if(StringUtils.isEmpty(tokenValue)){
            log.warn("[user identification] can not find token in the redis");
            throw new SellerAuthorizeException();
        }
    }
}
