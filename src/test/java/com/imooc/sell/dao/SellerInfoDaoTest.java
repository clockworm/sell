package com.imooc.sell.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.imooc.sell.entity.SellerInfo;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoDaoTest {

    @Autowired
    SellerInfoDao sellerInfoDao;

    @Test
    public void save() {
        SellerInfo info = new SellerInfo();
        info.setId("1");
        info.setUsername("tangbiao");
        info.setPassword("123456");
        info.setOpenid("987654321");
        info = sellerInfoDao.save(info);
        Assert.assertNotNull(info);
    }

    @Test
    public void findAll() {
        List<SellerInfo> list = sellerInfoDao.findAll();
        Assert.assertNotEquals(0, list.size());
    }

    @Test
    public void findOne() {
        SellerInfo info = sellerInfoDao.findOne("1");
        Assert.assertNotNull(info);
    }

    @Test
    public void findByOpenid() throws Exception {
        SellerInfo sellerInfo = sellerInfoDao.findByOpenid("oTgZpwfEBevx0K010OoB4tzXue6o");
        Assert.assertNotNull(sellerInfo);
    }

    @Test
    public void findByUsernameAndPassword() throws Exception {
        SellerInfo info = sellerInfoDao.findByUsernameAndPassword("tanglingyun", "123456");
        Assert.assertNotNull(info);
    }

}