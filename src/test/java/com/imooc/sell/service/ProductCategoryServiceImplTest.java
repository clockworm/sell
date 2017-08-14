package com.imooc.sell.service;

import com.imooc.sell.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
    ProductCategoryService productCategoryService;

    @Test
    public void findProductCategoryByCategoryTypeEquals() throws Exception {
//        ProductCategory productCategory = productCategoryService.findProductCategoryByCategoryTypeEquals(1);
//        Assert.assertNotNull(productCategory);
    }

    @Test
    public void saveOrUpdate() throws Exception {
        ProductCategory category = new ProductCategory();
        category.setCategoryId(4);
        category.setCategoryName("drink4");
        category.setCategoryType(2);
        ProductCategory productCategory = productCategoryService.saveOrUpdate(category);
        Assert.assertNotNull(productCategory);
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void findOne() throws Exception {
        ProductCategory productCategory = productCategoryService.findOne("1");
        Assert.assertNotNull(productCategory);
    }

    @Test
    public void findAll() throws Exception {
        List<ProductCategory> list = productCategoryService.findAll();
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void findByPage() throws Exception {
        PageRequest pageRequest = new PageRequest(0, 10);
        Page<ProductCategory> page = productCategoryService.findByPage(pageRequest);
        Assert.assertNotEquals(0,page.getContent().size());
    }

}