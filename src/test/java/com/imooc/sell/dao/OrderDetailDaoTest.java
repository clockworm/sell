package com.imooc.sell.dao;

import com.imooc.sell.entity.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {

    @Autowired
    OrderDetailDao orderDetailDao;

    @Test
    public void findByOrderId() throws Exception {
        List<OrderDetail> list = orderDetailDao.findByOrderId("1");
        Assert.assertNotEquals(0, list.size());
    }

    @Test
    public  void save(){
        OrderDetail detail = new OrderDetail();
        detail.setOrderId("1");
        detail.setDetailId("2");
        detail.setProductIcon("/c/c");
        detail.setProductId("1");
        detail.setProductName("bingo");
        detail.setProductPrice(new BigDecimal(23.98));
        detail.setProductQuantity(3);
        detail = orderDetailDao.save(detail);
        Assert.assertNotNull(detail);
    }

}