package com.imooc.sell.service.impl;

import com.imooc.sell.Enum.CategoryStatusEnum;
import com.imooc.sell.dao.ProductInfoDao;
import com.imooc.sell.entity.ProductInfo;
import com.imooc.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Override
    public List<ProductInfo> findProductInfoByProductStatus(CategoryStatusEnum status) {
        return productInfoDao.findProductInfoByProductStatus(status.getCode());
    }

    @Override
    public List<ProductInfo> findProductInfoByCategoryType(Integer categoryType) {
        return productInfoDao.findProductInfoByCategoryType(categoryType);
    }

    @Override
    public ProductInfo saveOrUpdate(ProductInfo productInfo) {
        return productInfoDao.save(productInfo);
    }

    @Override
    public ProductInfo delete(String id) {
        return null;
    }

    @Override
    public ProductInfo findOne(String id) {
        return productInfoDao.findOne(id);
    }

    @Override
    public List<ProductInfo> findAll() {
        return productInfoDao.findAll();
    }

    @Override
    public Page<ProductInfo> findByPage(Pageable page) {
        return productInfoDao.findAll(page);
    }
}
