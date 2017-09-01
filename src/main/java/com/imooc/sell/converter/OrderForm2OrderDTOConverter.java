package com.imooc.sell.converter;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.entity.OrderDetail;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.web.form.OrderForm;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class OrderForm2OrderDTOConverter {
    public static OrderDTO converter(OrderForm orderForm){
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        //購物車JSON轉list對象
        try {
            List<OrderDetail> list = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
            orderDTO.setDetailList(list);
        }catch (Exception e){
            log.error("[對象轉換]購物車Json轉訂單詳情列表異常 String={}",orderForm.getItems());
            throw new SellException(ResultEnum.PAYSTATUS_ERROR);
        }
        return  orderDTO;
    }
}
