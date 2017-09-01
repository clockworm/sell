package com.imooc.sell.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imooc.sell.entity.ProductCategory;

/**
 * 类目接口
 */
public interface ProductCategoryDao extends JpaRepository<ProductCategory,Integer> {

    ProductCategory findProductCategoryByCategoryTypeEquals(Integer categoryType);

    List<ProductCategory> findByCategoryTypeIn(List<Integer> types);
}
