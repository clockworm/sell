package com.imooc.sell.web.controller;

import com.imooc.sell.VO.ResultVO;
import com.imooc.sell.converter.OrderForm2OrderDTOConverter;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.entity.OrderDetail;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.util.ResultVOUtil;
import com.imooc.sell.web.form.OrderForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 买家控制器
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    //创建订单
    @PostMapping("create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){
        HashMap<String, String> map = new HashMap<>();
        if(bindingResult.hasErrors()){
            log.error("[創建訂單] 參數不正確,orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO dto = OrderForm2OrderDTOConverter.converter(orderForm);
        if(dto.getDetailList().isEmpty()){
            log.error("[創建訂單] 購物車不能為空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        dto = orderService.create(dto);
        map.put("orderId",dto.getOrderId());
        return ResultVOUtil.success(map);
    }
    //订单列表
    @GetMapping("list")
    public  ResultVO<List<OrderDTO>> list(@RequestParam(value = "openId") String openId,
                                                   @RequestParam(value = "page",defaultValue = "0") Integer page,
                                                   @RequestParam(value = "size",defaultValue = "10") Integer size){
        if(StringUtils.isEmpty(openId)){
            log.error("[查询订单列表] openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest request = new PageRequest(page, size);
        Page<OrderDTO> list = orderService.findList(openId, request);
        return  ResultVOUtil.success(list);
    }
    //订单详情
    @GetMapping("detail")
    public ResultVO<OrderDetail> findOrderDetail(@RequestParam(value = "openId") String openId,
                                                             @RequestParam(value = "orderId") String orderId){
        //TODO
        OrderDTO orderDTO = orderService.findOne(orderId);
        return ResultVOUtil.success(orderDTO);
    }
    //取消订单
    @PostMapping("cancelOrder")
    public  ResultVO cancelOrder(@RequestParam(value = "openId") String openId,
                                 @RequestParam(value = "orderId") String orderId){
        //TODO
        OrderDTO orderDTO = orderService.findOne(orderId);
        orderService.cancel(orderDTO);
        return ResultVOUtil.success();
    }
}
