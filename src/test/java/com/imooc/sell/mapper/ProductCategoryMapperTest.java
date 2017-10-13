package com.imooc.sell.mapper;

import com.imooc.sell.entity.ProductCategory;
import com.imooc.sell.entity.mapper.ProductCategoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryMapperTest {

    @Autowired
    ProductCategoryMapper productCategoryMapper;

    @Test
    public void insertByMap() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("categoryName", "水壶");
        map.put("categoryType",7);
        int i = productCategoryMapper.insertByMap(map);
        System.err.println(i);
    }

    @Test
    public void insertByObject() throws Exception {
        ProductCategory category = new ProductCategory();
        category.setCategoryName("艺术");
        category.setCategoryType(8);
        int i = productCategoryMapper.insertByObject(category);
        System.err.println(i);
    }

}