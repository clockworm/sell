package com.imooc.sell.service.impl;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.service.PayService;
import com.imooc.sell.util.JsonUtil;
import com.imooc.sell.util.MathUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PayServiceImpl implements PayService {
    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private OrderService orderService;

    private static final String ORDER_NAME = "微信商品订单";

    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("[微信支付] 发去支付 请求入参:{}", JsonUtil.toJson(payRequest));
        PayResponse pay = bestPayService.pay(payRequest);
        log.info("[微信支付] 发起支付 结果出参:{}", JsonUtil.toJson(pay));
        return pay;
    }

    @Override
    public PayResponse notify(String notifyData) {
        //验证签名(SDK 已经实现)   微信支付状态(SDK 已经实现)  支付金额
        log.info("[微信支付 异步回调] 回参notifyData文本={}", notifyData);
        PayResponse response = bestPayService.asyncNotify(notifyData);
        log.info("[微信支付 异步回调] request={}", JsonUtil.toJson(response));
        //修改订单支付状态
        OrderDTO orderDTO = orderService.findOne(response.getOrderId());
        if (orderDTO == null) throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        //效验支付金额
        if (!MathUtil.equals(orderDTO.getOrderAmount().doubleValue(),response.getOrderAmount())) {
            log.error("[微信支付 异步回调] 支付金额与订单金额不一致 订单号:{}支付金额:{} 订单金额:{}", orderDTO.getOrderId(),response.getOrderAmount(), orderDTO.getOrderAmount());
            throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
        }

        orderService.paid(orderDTO);
        return response;
    }

    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("[微信退款 发起退款订单入参 refundRequest:{}]",JsonUtil.toJson(refundRequest));
        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("[微信退款 发起退款订单 微信处理回参 refundResponse:{}]",JsonUtil.toJson(refundResponse));
        return  refundResponse;
    }
}
