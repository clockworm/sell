package com.imooc.sell.web.controller;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.service.PayService;
import com.imooc.sell.util.JsonUtil;
import com.imooc.sell.util.KeyUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.BestPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 *微信支付控制器
 */
@Controller
@Slf4j
public class PayController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private BestPayService bestPayService;

    @Autowired
    private PayService payService;

    /** 账号借用*/
    @GetMapping("pay")
    public ModelAndView pay(String openid,Map<String,Object> map){
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(openid);
        payRequest.setOrderAmount(0.01d);
        payRequest.setOrderId("7235892789Ax9752");
        payRequest.setOrderName("借用账号支付");
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("[微信支付 账号借用] 发去支付 请求入参:{}", JsonUtil.toJson(payRequest));
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("[微信支付 账号借用] 发起支付 结果出参:{}", JsonUtil.toJson(payResponse));
        map.put("payResponse",payResponse);
        map.put("returnUrl","www.baidu.com");
        return  new ModelAndView("pay/create",map);
    }
    /**
     *创建预支付订单
     */
    @GetMapping("pay-weixin")
    public ModelAndView create(String orderId,String returnUrl,Map<String,Object> map){
        //查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //获得预支付订单参数
        PayResponse payResponse = payService.create(orderDTO);
        map.put("payResponse",payResponse);
        map.put("returnUrl",returnUrl);
        //发起支付 调用微信H5支付JSAPI
        return  new ModelAndView("pay/create",map);
    }

    /** 异步回调 */
    @PostMapping(value="notify")
    public ModelAndView notify(@RequestBody String notifyData) throws  Exception{
        payService.notify(notifyData);
        return new ModelAndView("pay/success");
    }
}
