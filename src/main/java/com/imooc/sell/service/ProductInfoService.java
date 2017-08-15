package com.imooc.sell.service;

import com.imooc.sell.dto.CartDTO;
import com.imooc.sell.entity.ProductInfo;
import com.imooc.sell.enums.CategoryStatusEnum;

import java.util.List;

/**
 * 商品逻辑层
 */
public interface ProductInfoService extends BaseService<ProductInfo> {

    List<ProductInfo> findProductInfoByProductStatus(CategoryStatusEnum statusEnum);

    List<ProductInfo> findProductInfoByCategoryType(Integer categoryType);

    //加庫存
    void increaseStock(List<CartDTO> cartDTOList);

    //減庫存
    void decreaseStock(List<CartDTO> cartDTOList);
}
