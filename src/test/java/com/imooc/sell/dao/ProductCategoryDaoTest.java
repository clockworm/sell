package com.imooc.sell.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.imooc.sell.entity.ProductCategory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ProductCategoryDao productCategoryDao;

    @Test
    public void findProductCategoryByCategoryTypeEquals() throws Exception {
        ProductCategory productCategory = productCategoryDao.findProductCategoryByCategoryTypeEquals(1);
        log.info("{}",productCategory);
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