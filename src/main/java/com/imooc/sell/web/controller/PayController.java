package com.imooc.sell.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.service.PayService;
import com.lly835.bestpay.model.PayResponse;

/**
 *微信支付控制器
 */
@Controller
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    /**
     *创建预支付订单
     */
    @GetMapping("pay")
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
