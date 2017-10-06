package com.imooc.sell.web.controller;

import com.imooc.sell.entity.ProductInfo;
import com.imooc.sell.enums.CategoryStatusEnum;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.ProductInfoService;
import com.imooc.sell.util.EnumUtil;
import com.imooc.sell.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 商品控制器
 */
@Slf4j
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @GetMapping("list")
    public ModelAndView findList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "size", defaultValue = "10") Integer size,
                                 Map<String, Object> map){
        PageRequest pageRequest = new PageRequest(page-1,size);
        Page<ProductInfo> products = productInfoService.findByPage(pageRequest);
        log.info("分页查询所有商品列表 出参:{}", JsonUtil.toJson(products.getContent()));
        map.put("currentPage",page);
        map.put("size",size);
        map.put("data", products);
        map.put("controller", "product");
        return new ModelAndView("/product/list",map);
    }

    @GetMapping("offOronSale")
    public ModelAndView onSale(String productId,Integer status,Map<String, Object> map){
        map.put("url", "/sell/seller/product/list");
        try{
            productInfoService.offOronSale(productId,status);
        }catch (SellException e){
            log.error("[商品上下架异常]:{}",e);
            map.put("msg", e.getMessage());
            return new ModelAndView("common/error",map);
        }
        map.put("msg", ResultEnum.SUCCESS.getMessage());
        return new ModelAndView("common/success",map);
    }

}
