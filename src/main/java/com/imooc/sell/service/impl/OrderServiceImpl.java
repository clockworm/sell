package com.imooc.sell.service.impl;

import com.imooc.sell.dao.OrderDetailDao;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.entity.OrderDetail;
import com.imooc.sell.entity.ProductInfo;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private ProductInfoService productInfoService;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        List<OrderDetail> detailList = orderDTO.getDetailList();
        for (OrderDetail  orderDetail:detailList) {
            productInfoService.findOne(orderDetail.getDetailId());
        }
        return null;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finsh(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
