package com.imooc.sell.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imooc.sell.entity.ProductCategory;
import com.imooc.sell.entity.ProductInfo;
import com.imooc.sell.enums.CategoryStatusEnum;
import com.imooc.sell.service.ProductCategoryService;
import com.imooc.sell.service.ProductInfoService;
import com.imooc.sell.util.ResultVOUtil;
import com.imooc.sell.vo.ProductInfoVO;
import com.imooc.sell.vo.ProductVO;
import com.imooc.sell.vo.ResultVO;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ResultVO<?> list(){
        List<ProductInfo> productInfos = productInfoService.findProductInfoByProductStatus(CategoryStatusEnum.UP);
        List<Integer> typeList = productInfos.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> categoryList = productCategoryService.findByCategoryTypeIn(typeList);
        List<ProductVO> productVOS = new ArrayList<>();
        for (ProductCategory productCategory:categoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOS = new ArrayList<>();
            for (ProductInfo productInfo:productInfos) {
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO infoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,infoVO);
                    productInfoVOS.add(infoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOS);
            productVOS.add(productVO);
        }
        return ResultVOUtil.success(productVOS);
    }

}
