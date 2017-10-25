package com.imooc.sell.service;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.imooc.sell.dto.OrderDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PushWechatMessageServiceImplTest {
    @Autowired
    PushWechatMessageService pushWechatMessageService;


    @Test
    public void  orderStatus(){
        OrderDTO dto = new OrderDTO();
        dto.setOrderId("2017109220516");
        dto.setOrderStatus(0);
        dto.setOrderAmount(new BigDecimal(13.21));
        pushWechatMessageService.orderStatus(dto);
    }

}
