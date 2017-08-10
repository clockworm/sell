package com.imooc.sell.service;

import com.imooc.sell.Enum.CategoryStatusEnum;
import com.imooc.sell.entity.ProductInfo;

import java.util.List;

/**
 * 商品逻辑层
 */
public interface ProductInfoService extends BaseService<ProductInfo> {

    List<ProductInfo> findProductInfoByProductStatus(CategoryStatusEnum statusEnum);

    List<ProductInfo> findProductInfoByCategoryType(Integer categoryType);
}
