package com.imooc.sell.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;

/**
 * 微信配置
 */
@Data
@ConfigurationProperties(prefix = "wechat")
@Component
public class WechatAccountConfig {

    /** 公众号ID*/
    private  String mpAppId;
    /** 公众号密钥*/
    private String mpAppSecret;
    /** 商户号*/
    private String mchId;
    /** 商户密钥*/
    private String mchKey;
    /** 商户证书地址*/
    private String keyPath;
    /** 微信支付异步通知地址*/
    private String notifyUrl;
}
