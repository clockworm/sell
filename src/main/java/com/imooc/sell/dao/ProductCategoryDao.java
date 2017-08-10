package com.imooc.sell.dao;

import com.imooc.sell.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 类目接口
 */
public interface ProductCategoryDao extends JpaRepository<ProductCategory,Integer> {
    ProductCategory findProductCategoryByCategoryTypeEquals(Integer categoryType);
}
