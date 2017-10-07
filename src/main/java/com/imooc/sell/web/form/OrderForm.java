package com.imooc.sell.web.form;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 订单相关表单
 */
@Data
@ToString
public class OrderForm {
    /** 买家*/
    @NotEmpty(message = "姓名不能為空")
    private String name;
    /** 买家手机号*/
    @NotEmpty(message = "手機號不能為空")
    private String phone;
    /** 买家地址*/
    @NotEmpty(message = "地址不能為空")
    private String address;
    /** 买家微信唯一标识*/
    @NotEmpty(message = "openid不能為空")
    private String openid;
    /** 购物车JSON格式*/
    @NotEmpty(message = "購物車不能為空")
    private String items;

}
