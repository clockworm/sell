package com.imooc.sell.dao;

import com.imooc.sell.entity.ProductCategory;
import com.imooc.sell.entity.ProductInfo;
import com.imooc.sell.enums.CategoryStatusEnum;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ProductInfoDao productInfoDao;

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1");
        productInfo.setCategoryType(1);
        productInfo.setProductDescription("i am go to s");
        productInfo.setProductIcon("/u/c.jpg");
        productInfo.setProductName("coke");
        productInfo.setProductPrice(new BigDecimal(13.63));
        productInfo.setProductStatus(CategoryStatusEnum.UP.getCode());
        productInfo.setProductStock(100);
        productInfo = productInfoDao.save(productInfo);
        Assert.assertNotNull(productInfo);
    }

    @Test
    public void findOne() {
        ProductInfo productInfo = productInfoDao.findOne("1");
        log.info(productInfo.toString());
    }
}