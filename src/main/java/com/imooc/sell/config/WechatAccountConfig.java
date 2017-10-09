package com.imooc.sell.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;

import java.util.Map;

/**
 * 微信配置
 */
@Data
@ConfigurationProperties(prefix = "wechat")
@Component
public class WechatAccountConfig {

    /** 公众平台ID*/
    private  String mpAppId;
    /** 公众平台密钥*/
    private String mpAppSecret;
    /** 开放平台号ID*/
    private  String openAppId;
    /** 开放平台密钥*/
    private String openAppSecret;
    /** 商户号*/
    private String mchId;
    /** 商户密钥*/
    private String mchKey;
    /** 商户证书地址*/
    private String keyPath;
    /** 微信支付异步通知地址*/
    private String notifyUrl;
    /** 微信消息模板推送*/
    private Map<String,String> templateId;
}
