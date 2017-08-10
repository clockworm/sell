package com.imooc.sell.dao;

import com.imooc.sell.entity.ProductCategory;
import lombok.extern.java.Log;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class ProductCategoryDaoTest {

    @Autowired
    ProductCategoryDao productCategoryDao;

    @Test
    public void findProductCategoryByCategoryTypeEquals() throws Exception {
        ProductCategory productCategory = productCategoryDao.findProductCategoryByCategoryTypeEquals(1);
        log.info("对象:" + productCategory);
        Assert.assertNotNull(productCategory);
    }

    @Test
    public void save() {
        ProductCategory category = new ProductCategory();
        category.setCategoryId(2);
        category.setCategoryName("drink2");
        category.setCategoryType(2);
        ProductCategory productCategory = productCategoryDao.save(category);
        Assert.assertNotNull(productCategory);
    }
}