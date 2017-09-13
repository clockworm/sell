package com.imooc.sell.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.imooc.sell.converter.Date2LongSerializer;
import com.imooc.sell.entity.OrderDetail;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import com.imooc.sell.util.EnumUtil;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@ToString
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    /** 订单ID*/
    @Id
    private String orderId;
    /** 买家姓名*/
    private String buyerName;
    /** 买家联系方式*/
    private String buyerPhone;
    /** 买家地址*/
    private String buyerAddress;
    /** 买家微信*/
    private String buyerOpenid;
    /** 总金额*/
    private BigDecimal orderAmount;
    /** 订单状态 0 新建 1完结 2取消*/
    private Integer orderStatus;
    /** 支付状态 0 等待支付 1 支付完成*/
    private Integer payStatus;
    /** 订单创建时间*/
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    /** 更新时间*/
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;
    /** 訂單詳情*/
    private List<OrderDetail> detailList;

    /** 订单状态信息 对象转JSON 忽略该属性或者方法*/
    @JsonIgnore
    public String getOrderStatusMsg(){
        return EnumUtil.getEnumByCode(orderStatus,OrderStatusEnum.class).getMessage();
    }
    /** 支付状态信息 对象转JSON 忽略该属性或者方法*/
    @JsonIgnore
    public String getPayStatusMsg(){
        return EnumUtil.getEnumByCode(orderStatus,PayStatusEnum.class).getMessage();
    }
}
