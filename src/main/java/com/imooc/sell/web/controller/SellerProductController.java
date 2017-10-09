package com.imooc.sell.web.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.imooc.sell.entity.ProductCategory;
import com.imooc.sell.entity.ProductInfo;
import com.imooc.sell.enums.CategoryStatusEnum;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.ProductCategoryService;
import com.imooc.sell.service.ProductInfoService;
import com.imooc.sell.util.JsonUtil;
import com.imooc.sell.util.KeyUtil;
import com.imooc.sell.web.form.ProductInfoForm;

import lombok.extern.slf4j.Slf4j;

/**
 * 商品控制器
 */
@Slf4j
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private ProductCategoryService productCategoryService;


    @GetMapping("list")
    public ModelAndView findList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "size", defaultValue = "10") Integer size,
                                 Map<String, Object> map) {
        PageRequest pageRequest = new PageRequest(page - 1, size);
        Page<ProductInfo> products = productInfoService.findByPage(pageRequest);
        log.info("分页查询所有商品列表 出参:{}", JsonUtil.toJson(products.getContent()));
        map.put("currentPage", page);
        map.put("size", size);
        map.put("data", products);
        map.put("controller", "product");
        return new ModelAndView("/product/list", map);
    }

    @GetMapping("offOronSale")
    public ModelAndView onSale(String productId, Integer status, Map<String, Object> map) {
        map.put("url", "/sell/seller/product/list");
        try {
            productInfoService.offOronSale(productId, status);
        } catch (SellException e) {
            log.error("[商品上下架异常]:{}", e);
            map.put("msg", e.getMessage());
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.SUCCESS.getMessage());
        return new ModelAndView("common/success", map);
    }

    @GetMapping("index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId, Map<String, Object> map) {
        if (StringUtils.isNotEmpty(productId)) {
            ProductInfo info = productInfoService.findOne(productId);
            map.put("data", info);
        }
        //查询所有的类目
        List<ProductCategory> list = productCategoryService.findAll();
        map.put("productCategorys", list);
        map.put("productStatusEnum", CategoryStatusEnum.values());
        return new ModelAndView("product/index");
    }

    @PostMapping("saveOrupdate")
    public ModelAndView saveOrupdate(@Valid ProductInfoForm productInfoForm, BindingResult bindingResult, Map<String, Object> map) {
        map.put("url", "/sell/seller/product/list");
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }
        try {
            ProductInfo productInfo = new ProductInfo();
            if (StringUtils.isNotEmpty(productInfoForm.getProductId())) {
                productInfo = productInfoService.findOne(productInfoForm.getProductId());
            } else {
                productInfo.setProductId(KeyUtil.genUniqueKey());
            }
            BeanUtils.copyProperties(productInfoForm, productInfo,"productId");
            productInfoService.saveOrUpdate(productInfo);
        } catch (SellException e) {
            log.error("[商品修改或保存异常]:{}", e);
            map.put("msg", e.getMessage());
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.SUCCESS.getMessage());
        return new ModelAndView("common/success", map);
    }

}
