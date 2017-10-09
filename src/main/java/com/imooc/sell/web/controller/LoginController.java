package com.imooc.sell.web.controller;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.imooc.sell.config.ProjectUrlConfig;
import com.imooc.sell.constant.CookieConstant;
import com.imooc.sell.constant.RedisConstant;
import com.imooc.sell.entity.SellerInfo;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.service.SellerInfoService;
import com.imooc.sell.util.CookieUtil;

/**
 * 卖家登录
 */
@Controller
@RequestMapping("user")
public class LoginController {

    @Autowired
    private SellerInfoService sellerInfoService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("login")
    public ModelAndView login(@RequestParam(value = "openId", required = true) String openId, HttpServletResponse response, Map<String, Object> map) {
        //效验数据库OpenId
        SellerInfo info = sellerInfoService.findByOpenid(openId);
        if (info == null) {
            map.put("msg", ResultEnum.WX_LOGIN_FAIL.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        //创建token
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        //设置token放入redis
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), openId, expire, TimeUnit.SECONDS);
        //设置token至cookie
        CookieUtil.setCookie(response, CookieConstant.TOKEN_KEY, token, expire);
        return new ModelAndView("redirect:" + projectUrlConfig.getSell() + "/sell/seller/order/list");
    }

    @GetMapping("logout")
    public ModelAndView logout(HttpServletRequest httpServletRequest, HttpServletResponse response, Map<String, Object> map) {
        //查询Cookie
        Cookie cookie = CookieUtil.getCookie(httpServletRequest, CookieConstant.TOKEN_KEY);
        if(cookie!=null){
            //清除Redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
            //清除Cookie
            CookieUtil.setCookie(response,CookieConstant.TOKEN_KEY,null,0);
            map.put("url","/sell/seller/order/list");
            map.put("msg",ResultEnum.WX_LOGOUT_SUCCESS.getMessage());
            return new ModelAndView("common/success",map);
        }
        map.put("url","/sell/seller/order/list");
        map.put("msg",ResultEnum.WX_LOGOUT_FAIL.getMessage());
        return new ModelAndView("common/error",map);
    }
}
