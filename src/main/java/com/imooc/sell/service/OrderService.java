package com.imooc.sell.service;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.entity.OrderMaster;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService{

    OrderDTO create(OrderDTO orderDTO);

    OrderDTO findOne(String orderId);

    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    OrderDTO cancel(OrderDTO orderDTO);

    OrderDTO finsh(OrderDTO orderDTO);

    OrderDTO paid(OrderDTO orderDTO);

}
