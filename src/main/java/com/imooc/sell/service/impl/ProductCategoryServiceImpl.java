package com.imooc.sell.service.impl;

import com.imooc.sell.dao.ProductCategoryDao;
import com.imooc.sell.entity.ProductCategory;
import com.imooc.sell.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    public ProductCategory findProductCategoryByCategoryTypeEquals(Integer categoryType) {
        return productCategoryDao.findProductCategoryByCategoryTypeEquals(categoryType);
    }

    @Override
    public ProductCategory saveOrUpdate(ProductCategory productCategory) {
        return productCategoryDao.save(productCategory);
    }

    @Override
    public ProductCategory delete(String id) {
        return null;
    }

    @Override
    public ProductCategory findOne(String id) {
        return productCategoryDao.findOne(Integer.parseInt(id));
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryDao.findAll();
    }

    @Override
    public Page<ProductCategory> findByPage(Pageable page) {
        return productCategoryDao.findAll(page);
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryType) {
        return productCategoryDao.findByCategoryTypeIn(categoryType);
    }
}
