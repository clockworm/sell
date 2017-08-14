package com.imooc.sell.service;

import com.imooc.sell.entity.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterServiceImplTest {

    @Autowired
    OrderService orderMasterService;

    @Test
    public void findOrderMastersByBuyerNameLike() throws Exception {
        List<OrderMaster> list = orderMasterService.findOrderMastersByBuyerNameLike("tang");
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void findOrderMastersByBuyerPhoneEquals() throws Exception {
        List<OrderMaster> list = orderMasterService.findOrderMastersByBuyerPhoneEquals("17783253569");
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void saveOrUpdate() throws Exception {
        OrderMaster master = new OrderMaster();
        master.setOrderId("3");
        master.setBuyerName("tangbiao");
        master.setBuyerPhone("13036353072");
        master.setBuyerAddress("china");
        master.setBuyerOpenid("987456321");
        master.setOrderAmount(new BigDecimal(89.52*4));
        master.setOrderStatus(0);
        master.setPayStatus(0);
        master = orderMasterService.saveOrUpdate(master);
        Assert.assertNotNull(master);
    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void findOne() throws Exception {
        OrderMaster orderMaster = orderMasterService.findOne("3");
        Assert.assertNotNull(orderMaster);
    }

    @Test
    public void findAll() throws Exception {
        List<OrderMaster> list = orderMasterService.findAll();
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void findByPage() throws Exception {
        PageRequest pageRequest = new PageRequest(0, 10);
        Page<OrderMaster> page = orderMasterService.findByPage(pageRequest);
        Assert.assertNotEquals(0,page.getContent().size());
    }

}