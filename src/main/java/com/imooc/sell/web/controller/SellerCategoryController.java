package com.imooc.sell.web.controller;

import com.imooc.sell.entity.ProductCategory;
import com.imooc.sell.entity.ProductInfo;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.ProductCategoryService;
import com.imooc.sell.util.KeyUtil;
import com.imooc.sell.web.form.ProductCategoryForm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 类目
 */
@Controller
@RequestMapping("/seller/category")
@Slf4j
public class SellerCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("list")
    public ModelAndView list(Map<String,Object> map){
        List<ProductCategory> list = productCategoryService.findAll();
        map.put("data",list);
        return  new ModelAndView("category/list");
    }

    @GetMapping("index")
        public ModelAndView index(@RequestParam(value = "categoryId",required = false) Integer categoryId, Map<String,Object> map){
            ProductCategory productCategory = null;
            if(categoryId > 0){
                productCategory = productCategoryService.findOne(categoryId.toString());
            }
        map.put("data",productCategory);
        return  new ModelAndView("category/index");
    }

    @PostMapping("saveOrupdate")
    public ModelAndView saveOrupdate(@Valid ProductCategoryForm productCategoryForm, BindingResult bindingResult, Map<String,Object> map){
        map.put("url", "/sell/seller/category/list");
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }
        if(productCategoryForm.getCategoryType()==null){
            log.error("[商品类目修改或保存异常]:{}", ResultEnum.PARAM_ERROR.getMessage());
            map.put("msg", ResultEnum.PARAM_ERROR.getMessage());
            return new ModelAndView("common/error", map);
        }
        try {
            ProductCategory productCategory = new ProductCategory();
            if (productCategoryForm.getCategoryId()!=null) {
                productCategory = productCategoryService.findOne(productCategoryForm.getCategoryId().toString());
            }
            BeanUtils.copyProperties(productCategoryForm, productCategory,"productId");
            productCategoryService.saveOrUpdate(productCategory);
        } catch (SellException e) {
            log.error("[商品类目修改或保存异常]:{}", e);
            map.put("msg", e.getMessage());
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.SUCCESS.getMessage());
        return new ModelAndView("common/success", map);
    }
}
