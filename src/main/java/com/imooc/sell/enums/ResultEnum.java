package com.imooc.sell.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    SUCCESS(0,"成功"),
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
    ORDER_OWNER_ERROR(19,"该订单不属于当前用户"),
    WX_MP_ERROR(20,"微信网页授权 微信公众账号错误"),
    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(21,"微信支付异步通知 金额效验不通过"),
    ORDER_CANCEL_SUCCESS(22,"订单取消成功"),
    ORDER_OVER_SUCCESS(23,"订单完成成功"),
    WX_OPEN_ERROR(24,"微信开放平台授权 微信公众账号错误"),
    WX_LOGIN_FAIL(25,"微信开放平台授权 该用户没有关注平台 登录失败 "),
    WX_LOGOUT_SUCCESS(26,"登出成功"),
    WX_LOGOUT_FAIL(27,"登出失败"),
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
