package com.imooc.sell.service;

import com.imooc.sell.entity.SellerInfo;
import lombok.extern.java.Log;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class SellerInfoServiceImplTest {

    @Autowired
    SellerInfoService sellerInfoService;

    @Test
    public void findByOpenid() throws Exception {
        SellerInfo sellerInfo = sellerInfoService.findByOpenid("987654321");
        Assert.assertNotNull(sellerInfo);
    }

    @Test
    public void findByUsernameAndPassword() throws Exception {
        SellerInfo info = sellerInfoService.findByUsernameAndPassword("tangbiao", "123456");
        Assert.assertNotNull(info);
    }

    @Test
    public void saveOrUpdate() throws Exception {
        SellerInfo info = new SellerInfo();
        info.setId("2");
        info.setUsername("tangbiao");
        info.setPassword("123456");
        info.setOpenid("987654321");
        info = sellerInfoService.saveOrUpdate(info);
        Assert.assertNotNull(info);
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void findOne() throws Exception {
        SellerInfo sellerInfo = sellerInfoService.findOne("2");
        Assert.assertNotNull(sellerInfo);
    }

    @Test
    public void findAll() throws Exception {
        List<SellerInfo> list = sellerInfoService.findAll();
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void findByPage() throws Exception {
        PageRequest pageRequest = new PageRequest(0, 10);
        Page<SellerInfo> page = sellerInfoService.findByPage(pageRequest);
        Assert.assertNotEquals(0,page.getContent().size());
    }

}