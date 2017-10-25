package com.imooc.sell.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.imooc.sell.config.WechatAccountConfig;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

/**
 * 微信网页授权SDK配置
 */
@Component
public class WechatMpConfigBean {
    @Autowired
    WechatAccountConfig wechatAccountConfig;

    @Bean
    public WxMpService wxMpService() {
        WxMpServiceImpl wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }

    @Bean
    public WxMpConfigStorage wxMpConfigStorage() {
        WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
//        wxMpConfigStorage.setAppId(wechatAccountConfig.getMpAppId());
//        wxMpConfigStorage.setSecret(wxMpConfigStorage.getSecret());
        wxMpConfigStorage.setAppId("wx1fabbe95edaa7ed7");
        wxMpConfigStorage.setSecret("fb7915b63ba1140c619803789ee30e7c");
        return wxMpConfigStorage;
    }

}
