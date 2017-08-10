package com.imooc.sell.service.impl;

import com.imooc.sell.dao.SellerInfoDao;
import com.imooc.sell.entity.SellerInfo;
import com.imooc.sell.service.SellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerInfoServiceImpl implements SellerInfoService {

    @Autowired
    private SellerInfoDao sellerInfoDao;

    @Override
    public SellerInfo findByOpenid(String openId) {
        return sellerInfoDao.findByOpenid(openId);
    }

    @Override
    public SellerInfo findByUsernameAndPassword(String userName, String passWord) {
        return sellerInfoDao.findByUsernameAndPassword(userName, passWord);
    }

    @Override
    public SellerInfo saveOrUpdate(SellerInfo sellerInfo) {
        return sellerInfoDao.save(sellerInfo);
    }

    @Override
    public SellerInfo delete(String id) {
        return null;
    }

    @Override
    public SellerInfo findOne(String id) {
        return sellerInfoDao.findOne(id);
    }

    @Override
    public List<SellerInfo> findAll() {
        return sellerInfoDao.findAll();
    }

    @Override
    public Page<SellerInfo> findByPage(Pageable page) {
        return sellerInfoDao.findAll(page);
    }
}
