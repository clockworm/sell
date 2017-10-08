package com.imooc.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "projectUrl")
@Component
public class ProjectUrlConfig {
    /**微信公众平台Url*/
    private String wechatMpAuthorize;
    /**微信开放平台Url*/
    private String wechatOpenAuthorize;
    /**点餐系统项目路径*/
    private String sell;
}
