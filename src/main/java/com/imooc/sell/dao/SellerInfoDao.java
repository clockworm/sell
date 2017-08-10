package com.imooc.sell.dao;

import com.imooc.sell.entity.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 买家
 */
public interface SellerInfoDao extends JpaRepository<SellerInfo, String> {

    SellerInfo findByOpenid(String openId);

    SellerInfo findByUsernameAndPassword(String userName, String passWord);
}
