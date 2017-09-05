package com.imooc.sell.vo;

import lombok.Data;
import lombok.ToString;

/**
 * HTTP 請求最外層對象
 */
@Data
@ToString
public class ResultVO<T> {
    /** 状态码 0成功 1异常*/
    private Integer code;
    /** 返回的信息*/
    private String message;
    /** 返回的数据*/
    private T data;

}
