package com.imooc.sell.web.controller;

import java.util.Map;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.imooc.sell.util.KeyUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.lly835.bestpay.utils.JsonUtil;

import lombok.extern.slf4j.Slf4j;

/**
 *微信支付控制器
 */
@Controller
@Slf4j
@RequestMapping(value="pay")
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private PayService payService;

    /**
     *创建订单
     */
    @GetMapping("create")
    public ModelAndView create(String orderId,String returnUrl,Map<String,Object> map){
        //查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //获得预支付订单参数
        PayResponse payResponse = payService.create(orderDTO);
        map.put("payResponse",payResponse);
        //发起支付 调用微信H5支付JSPI
        return  new ModelAndView("pay",map);
    }


    /** 支付*/
    @GetMapping(value="pay")
    public ModelAndView pay(@RequestParam("openid") String openid, Map<String,Object> map){
        PayRequest request = new PayRequest();

        //支付请求参数
        request.setOpenid(openid);
        request.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        request.setOrderId(KeyUtil.genUniqueKey());
        request.setOrderAmount(0.01);
        request.setOrderName("最好的支付");
        log.info("[发起支付] request={}", JsonUtil.toJson(request));

        PayResponse payResponse = bestPayService.pay(request);
        map.put("payResponse",payResponse);
        return new ModelAndView("pay/create",map);
    }


    /** 异步回调 */
    @PostMapping(value="notify")
    public ModelAndView notify(@RequestBody String notifyData) throws  Exception{
        log.info("[异步回调] request={}",notifyData);
        PayResponse response = bestPayService.asyncNotify(notifyData);
        log.info("[异步回调] request={}",JsonUtil.toJson(response));
        return new ModelAndView("pay/success");
    }
}
