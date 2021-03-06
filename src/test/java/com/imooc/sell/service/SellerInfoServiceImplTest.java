package com.imooc.sell.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.imooc.sell.entity.SellerInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoServiceImplTest {

    @Autowired
    SellerInfoService sellerInfoService;

    @Test
    public void findByOpenid() throws Exception {
        SellerInfo sellerInfo = sellerInfoService.findByOpenid("oxDczwPj_zNdDL7fNG_RCMJh88fQ");
        Assert.assertNotNull(sellerInfo);
    }

    @Test
    public void findByUsernameAndPassword() throws Exception {
        SellerInfo info = sellerInfoService.findByUsernameAndPassword("tanglingyun", "123456");
        Assert.assertNotNull(info);
    }

    @Test
    public void saveOrUpdate() throws Exception {
        SellerInfo info = new SellerInfo();
        info.setId("2");
        info.setUsername("tanglingyun");
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
       /* List<String> collect = list.stream().map(e -> e.getUsername()).collect(Collectors.toList());
        for (String string : collect) {
			System.err.println(string);
		}*/
        for (SellerInfo sellerInfo : list) {
			System.err.println(sellerInfo.getUsername());
		}
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void findByPage() throws Exception {
        PageRequest pageRequest = new PageRequest(0, 10);
        Page<SellerInfo> page = sellerInfoService.findByPage(pageRequest);
        Assert.assertNotEquals(0,page.getContent().size());
    }

}