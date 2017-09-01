package com.imooc.sell.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imooc.sell.entity.SellerInfo;

/**
 * 买家
 */
public interface SellerInfoDao extends JpaRepository<SellerInfo, String> {

    SellerInfo findByOpenid(String openId);

    SellerInfo findByUsernameAndPassword(String userName, String passWord);
}
