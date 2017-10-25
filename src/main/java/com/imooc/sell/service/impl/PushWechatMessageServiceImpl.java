package com.imooc.sell.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.sell.config.WechatAccountConfig;
import com.imooc.sell.constant.WechatTemplateIdConstant;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.service.PushWechatMessageService;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

@Service
@Slf4j
public class PushWechatMessageServiceImpl implements PushWechatMessageService {

    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    @Override
    public void orderStatus(OrderDTO orderDTO) {
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setTemplateId(wechatAccountConfig.getTemplateId().get(WechatTemplateIdConstant.ORDER_STATUS));
        templateMessage.setToUser(orderDTO.getBuyerOpenid());
        List<WxMpTemplateData> data = Arrays.asList(new WxMpTemplateData("first", "亲,记得收货."),
                new WxMpTemplateData("keyword1", "Little-fairy 甜品"),
                new WxMpTemplateData("keyword2", "18888888888"),
                new WxMpTemplateData("keyword3", orderDTO.getOrderId()),
                new WxMpTemplateData("keyword4", orderDTO.getOrderStatusMsg()),
                new WxMpTemplateData("keyword5", "¥" + orderDTO.getOrderAmount().setScale(2,BigDecimal.ROUND_HALF_DOWN)),
                new WxMpTemplateData("remark", "欢迎再次光临,谢谢"));
        templateMessage.setData(data);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            log.error("[微信消息推送] 推送微信消息模板异常:{}", e);
        }
    }
}
