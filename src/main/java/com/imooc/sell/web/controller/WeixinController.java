package com.imooc.sell.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("weixin")
@Slf4j
public class WeixinController {

    @GetMapping("auth")
    public void auth(@RequestParam("code") String code) {
        log.info("[微信认证]:{}", code);
        String token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx1fabbe95edaa7ed7&secret=fb7915b63ba1140c619803789ee30e7c&code=" + code + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(token_url, String.class);
        log.info("token_code:{}",response);

    }
}
