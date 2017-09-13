package com.imooc.sell.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.imooc.sell.dto.OrderDTO;

public interface OrderService extends BaseService<OrderDTO>{

    /**创建订单*/
    OrderDTO create(OrderDTO orderDTO);
    /**查询某买家订单列表*/
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);
    /**查询所有订单列表*/
    Page<OrderDTO> findList(Pageable pageable);
    /**取消订单*/
    OrderDTO cancel(OrderDTO orderDTO);
    /**完结订单*/
    OrderDTO finish(OrderDTO orderDTO);
    /**支付订单*/
    OrderDTO paid(OrderDTO orderDTO);

    List<OrderDTO> findOrderMastersByBuyerNameLike(String tang);

    List<OrderDTO> findOrderMastersByBuyerPhoneEquals(String s);
}
