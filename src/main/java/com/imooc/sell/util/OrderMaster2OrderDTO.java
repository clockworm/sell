package com.imooc.sell.util;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 转换器
 */
public class OrderMaster2OrderDTO {

    public  static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return  orderDTO;
    }

    public  static List<OrderDTO> convert(List<OrderMaster> masters){
        return masters.stream().map(e -> convert(e)).collect(Collectors.toList());
    }
}
