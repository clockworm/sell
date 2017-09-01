package com.imooc.sell.service;

import java.util.List;

import com.imooc.sell.entity.ProductCategory;

public interface ProductCategoryService extends BaseService<ProductCategory> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryType);

}
