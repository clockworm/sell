package com.imooc.sell.bean;

import com.imooc.sell.config.WechatAccountConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;

/**
 * 微信支付配置
 */
@Component
public class WechatPayConfig {

    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    @Bean
    public WxPayH5Config wxPayH5Config(){
        WxPayH5Config config = new WxPayH5Config();
        config.setAppId(wechatAccountConfig.getMpAppId());
        config.setAppSecret(wechatAccountConfig.getMpAppSecret());
        config.setMchId(wechatAccountConfig.getMchId());
        config.setMchKey(wechatAccountConfig.getMchKey());
        config.setKeyPath(wechatAccountConfig.getKeyPath());
        config.setNotifyUrl(wechatAccountConfig.getNotifyUrl());
        return config;
    }

    @Bean
    public BestPayServiceImpl bestPayService(WxPayH5Config wxPayH5Config){
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config);
        return bestPayService;
    }
}
