package com.imooc.sell.web.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

@Data
public class ProductCategoryForm {

    private Integer categoryId;
    @NotEmpty(message = "类目名称不能为空")
    private String categoryName;
    @Range(min = 1,max = 999,message = "类目Type不正确")
    private Integer categoryType;
}
