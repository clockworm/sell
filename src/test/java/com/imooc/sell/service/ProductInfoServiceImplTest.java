package com.imooc.sell.service;

import com.imooc.sell.Enum.CategoryStatusEnum;
import com.imooc.sell.entity.ProductInfo;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
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

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    ProductInfoService productInfoService;

    @Test
    public void findProductInfoByProductStatus() throws Exception {
        List<ProductInfo> list = productInfoService.findProductInfoByProductStatus(CategoryStatusEnum.UP);
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void findProductInfoByCategoryType() throws Exception {
        List<ProductInfo> list = productInfoService.findProductInfoByCategoryType(1);
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void saveOrUpdate() throws Exception {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("2");
        productInfo.setCategoryType(1);
        productInfo.setProductDescription("i am go to s");
        productInfo.setProductIcon("/u/c.jpg");
        productInfo.setProductName("coke2");
        productInfo.setProductPrice(new BigDecimal(13.63));
        productInfo.setProductStatus(CategoryStatusEnum.UP.getCode());
        productInfo.setProductStock(19);
        ProductInfo info = productInfoService.saveOrUpdate(productInfo);
        Assert.assertNotNull(info);
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void findOne() throws Exception {
        ProductInfo info = productInfoService.findOne("2");
        Assert.assertNotNull(info);
    }

    @Test
    public void findAll() throws Exception {
        List<ProductInfo> list = productInfoService.findAll();
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void findByPage() throws Exception {
        PageRequest pageRequest = new PageRequest(0, 10);
        Page<ProductInfo> page = productInfoService.findByPage(pageRequest);
        assertNotEquals(0,page.getTotalPages());
        List<ProductInfo> list = page.getContent();
        for (ProductInfo  productInfos : list) {
            System.err.println(productInfos.toString());
        }
    }

}