package com.imooc.sell.service;

import com.imooc.sell.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

/**
 * 微信支付系统 交付业务层
 */
public interface PayService {

    /** 发起微信支付订单 并支付*/
    PayResponse create(OrderDTO orderDTO);

    /** 微信支付 回调*/
    PayResponse notify(String notifyData);

    /** 微信退款*/
    RefundResponse refund(OrderDTO orderDTO);
}
