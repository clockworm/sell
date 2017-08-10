package com.imooc.sell.service.impl;

import com.imooc.sell.dao.OrderMasterDao;
import com.imooc.sell.entity.OrderMaster;
import com.imooc.sell.service.OrderMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderMasterServiceImpl implements OrderMasterService {

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Override
    public List<OrderMaster> findOrderMastersByBuyerNameLike(String buyerName) {
        return orderMasterDao.findOrderMastersByBuyerNameLike(buyerName);
    }

    @Override
    public List<OrderMaster> findOrderMastersByBuyerPhoneEquals(String buyerPhone) {
        return orderMasterDao.findOrderMastersByBuyerPhoneEquals(buyerPhone);
    }

    @Override
    public OrderMaster saveOrUpdate(OrderMaster orderMaster) {
        return orderMasterDao.save(orderMaster);
    }

    @Override
    public OrderMaster delete(String id) {
        return null;
    }

    @Override
    public OrderMaster findOne(String id) {
        return orderMasterDao.findOne(id);
    }

    @Override
    public List<OrderMaster> findAll() {
        return orderMasterDao.findAll();
    }

    @Override
    public Page<OrderMaster> findByPage(Pageable page) {
        return orderMasterDao.findAll(page);
    }
}
