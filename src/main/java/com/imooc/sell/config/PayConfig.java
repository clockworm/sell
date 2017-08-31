package com.imooc.sell.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class PayConfig {

    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    @Bean
    public WxPayH5Config wxPayH5Config(){
        WxPayH5Config config = new WxPayH5Config();
        config.setAppId(wechatAccountConfig.getMpAppId());
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
