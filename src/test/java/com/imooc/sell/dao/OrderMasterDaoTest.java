package com.imooc.sell.dao;

import com.imooc.sell.entity.OrderMaster;
import lombok.ToString;
import lombok.extern.java.Log;
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
@Log
public class OrderMasterDaoTest {

    @Autowired
    OrderMasterDao orderMasterDao;


    @Test
    public void save(){
        OrderMaster master = new OrderMaster();
        master.setOrderId("2");
        master.setBuyerName("tangbiao");
        master.setBuyerPhone("13036353072");
        master.setBuyerAddress("china");
        master.setBuyerOpenid("987456321");
        master.setOrderAmount(new BigDecimal(89.52*2));
        master.setOrderStatus(0);
        master.setPayStatus(0);
        master = orderMasterDao.save(master);
        Assert.assertNotNull(master);
    }

    @Test
    public void findOrderMastersByBuyerNameLike() throws Exception {
        List<OrderMaster> list = orderMasterDao.findOrderMastersByBuyerNameLike("tang");
        assertNotEquals(0,list.size());
        for (OrderMaster orderMaster : list){
            System.err.println(orderMaster.toString());
        }
    }

    @Test
    public void findOrderMastersByBuyerPhone() throws Exception {
        List<OrderMaster> list = orderMasterDao.findOrderMastersByBuyerPhoneEquals("13036353072");
        assertNotEquals(0,list.size());
        for (OrderMaster orderMaster : list){
            System.err.println(orderMaster.toString());
        }
    }

}