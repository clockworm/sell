package com.imooc.sell.service;

import java.util.List;

import com.imooc.sell.dto.CartDTO;
import com.imooc.sell.entity.ProductInfo;
import com.imooc.sell.enums.CategoryStatusEnum;

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

    /**上架:1 /下架:0*/
    void offOronSale(String productId,Integer status);
}
