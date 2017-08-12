package com.imooc.sell.web.controller;

import com.imooc.sell.VO.ProductInfoVO;
import com.imooc.sell.VO.ProductVO;
import com.imooc.sell.VO.ResultVO;
import com.imooc.sell.entity.ProductInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @GetMapping("/list")
    public ResultVO list(){
        ResultVO vo = new ResultVO();
        vo.setCode(0);
        vo.setMessage("成功");
        ProductInfoVO infoVO = new ProductInfoVO();
        ArrayList<ProductInfoVO> list = new ArrayList<>();
        list.add(infoVO);
        ProductVO productVO = new ProductVO();
        productVO.setProductInfoVOList(list);
        vo.setData(productVO);
        return vo;
    }

}
