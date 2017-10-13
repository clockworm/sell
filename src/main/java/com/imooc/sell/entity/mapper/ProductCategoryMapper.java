package com.imooc.sell.entity.mapper;

import com.imooc.sell.entity.ProductCategory;
import org.apache.ibatis.annotations.Insert;

import java.util.Map;

public interface ProductCategoryMapper {
    @Insert("insert into product_category(category_name,category_type) values (#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER})")
    int insertByMap(Map<String,Object> map);


    @Insert("insert into product_category(category_name,category_type) values (#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER})")
    int insertByObject(ProductCategory productCategory);
}
