package com.imooc.sell.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.entity.OrderDetail;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderMasterServiceImplTest {

    @Autowired
    OrderService orderMasterService;

    final String orderId = "1503247869720776908";

    @Test
    public void findOrderMastersByBuyerNameLike() throws Exception {
        List<OrderDTO> list = orderMasterService.findOrderMastersByBuyerNameLike("tang");
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void findOrderMastersByBuyerPhoneEquals() throws Exception {
        List<OrderDTO> list = orderMasterService.findOrderMastersByBuyerPhoneEquals("17783253569");
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void saveOrUpdate() throws Exception {
        OrderDTO master = new OrderDTO();
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
    public void findList() throws Exception {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<OrderDTO> list = orderMasterService.findList("987654321", pageRequest);
        log.info("{}",list.getContent());
        Assert.assertNotEquals(0,list.getTotalElements());
    }

    @Test
    public void findOne() throws Exception {
        OrderDTO orderMaster = orderMasterService.findOne("1503247869720776908");
        log.info("单个订单详情列表:{}",orderMaster);
        Assert.assertNotNull(orderMaster);
    }

    @Test
    public void findAll() throws Exception {
        List<OrderDTO> list = orderMasterService.findAll();
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void findByPage() throws Exception {
        PageRequest pageRequest = new PageRequest(0, 10);
        Page<OrderDTO> page = orderMasterService.findByPage(pageRequest);
        Assert.assertNotEquals(0,page.getContent().size());
    }

    @Test
    public void create() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("唐嶺雲");
        orderDTO.setBuyerPhone("17783253569");
        orderDTO.setBuyerAddress("重慶市");
        orderDTO.setBuyerOpenid("987654321");
        ArrayList<OrderDetail> list = new ArrayList<>();
        OrderDetail detail = new OrderDetail();
        detail.setProductId("2");
        detail.setProductQuantity(1);
        list.add(detail);
        orderDTO.setDetailList(list);
        OrderDTO dto = orderMasterService.create(orderDTO);
        System.err.println(dto.toString());
    }

    @Test
    public void cancel(){
        OrderDTO orderDTO = orderMasterService.findOne(orderId);
        OrderDTO cancel = orderMasterService.cancel(orderDTO);
        System.err.println(cancel);
    }

    @Test
    public void finished(){
        OrderDTO orderDTO = orderMasterService.findOne("15032478697207769081");
        OrderDTO cancel = orderMasterService.finish(orderDTO);
        System.err.println(cancel);
    }

    @Test
    public void paid(){
        OrderDTO orderDTO = orderMasterService.findOne("15032478697207769081");
        OrderDTO cancel = orderMasterService.paid(orderDTO);
        System.err.println(cancel);
    }

}