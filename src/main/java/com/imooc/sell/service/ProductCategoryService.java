package com.imooc.sell.service;

import com.imooc.sell.Enum.CategoryStatusEnum;
import com.imooc.sell.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService extends BaseService<ProductCategory> {

    ProductCategory findProductCategoryByCategoryTypeEquals(Integer categoryType);

}
