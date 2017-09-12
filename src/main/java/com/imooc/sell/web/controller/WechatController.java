package com.imooc.sell.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.util.JsonUtil;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;

@Controller
@RequestMapping("wechat")
@Slf4j
public class WechatController {

    @Autowired
    WxMpService wxMpService;

    @GetMapping("authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) {
        //配置回调
        String url = "http://sell.mynatapp.cc/sell/wechat/userInfo";
        //调用
        String redirectUrl;
		try {
			redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAUTH2_SCOPE_USER_INFO, URLEncoder.encode(returnUrl, "UTF-8"));
            log.info("[微信网页授权] 获取code redirectUrl:{}", JsonUtil.toJson(redirectUrl));
        } catch (UnsupportedEncodingException e) {
            throw new SellException(ResultEnum.WX_MP_ERROR.getCode(), e.getMessage());
		}
        return "redirect:" + redirectUrl;
    }

    @GetMapping("userInfo")
    public String userInfo(@RequestParam("code") String code, @RequestParam("state") String returnUrl) {
        log.info("[微信网页授权]  获取微信code兑现accessToken识别码 code:{} state:{}", code, returnUrl);
        WxMpOAuth2AccessToken accessToken;
        try {
            accessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("[微信网页授权] 获取accessToken异常 入参 code:{} returnUrl:{}", code,returnUrl);
            throw new SellException(ResultEnum.WX_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
        String openId = accessToken.getOpenId();
        log.info("[微信网页授权] 获取用户openId:{}", openId);
        return "redirect:" + returnUrl + "?openid=" + openId;
    }


}

