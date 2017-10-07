package com.imooc.sell.web.form;

import com.imooc.sell.enums.CategoryStatusEnum;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigDecimal;

/**
 * 商品
 */
@Data
@ToString
public class ProductInfoForm {
    /** 商品ID不能为空 修改使用*/
    private String productId;
    /** 商品名称*/
    @NotEmpty(message = "商品名称不能为空")
    private String productName;
    /** 商品价格*/
    private BigDecimal productPrice;
    /**库存*/
    private Integer productStock;
    /** 商品描述*/
    @NotEmpty(message = "商品描述不能為空")
    private String productDescription;
    /** 商品简图*/
    private String productIcon;
    /** 商品状态*/
    private Integer productStatus = CategoryStatusEnum.UP.getCode();
    /** 商品类目*/
    private Integer categoryType;

}
