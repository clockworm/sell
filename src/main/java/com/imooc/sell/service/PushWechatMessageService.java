package com.imooc.sell.service;

import com.imooc.sell.dto.OrderDTO;

/** 推送微信消息服务接口*/
public interface PushWechatMessageService {

    /** 订单状态变更消息*/
    void orderStatus(OrderDTO orderDTO);
}
