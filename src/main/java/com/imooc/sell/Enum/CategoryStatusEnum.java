package com.imooc.sell.Enum;

import lombok.Data;
import lombok.Getter;

/**
 * 商品状态
 */
@Getter
public enum CategoryStatusEnum {
    UP(1, "上架"),
    DOWN(0, "下架");

    private Integer code;
    private String message;

    CategoryStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
