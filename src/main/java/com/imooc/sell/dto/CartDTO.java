package com.imooc.sell.dto;


import lombok.Data;

/**
 * 購物車
 */
@Data
public class CartDTO {
    /** 商品ID*/
    private String productId;
    /** 數量*/
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
