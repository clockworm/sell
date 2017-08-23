package com.imooc.sell.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    PARAM_ERROR(1,"參數不正確"),
    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"庫存不足"),
    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),
    ORDERSTATUS_ERROR(14,"订单狀態不正確"),
    ORDER_UPDATE_ERROR(15,"订单更新失敗"),
    ORDER_DETAIL_EMPTY(16,"订单中無商品詳情"),
    PAYSTATUS_ERROR(17,"支付狀態錯誤"),
    CART_EMPTY(18,"購物車為空"),
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
