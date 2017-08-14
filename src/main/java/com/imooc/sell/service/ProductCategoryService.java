package com.imooc.sell.service;

import com.imooc.sell.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService extends BaseService<ProductCategory> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryType);

}
