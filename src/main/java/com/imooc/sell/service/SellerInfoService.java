package com.imooc.sell.service;

import com.imooc.sell.entity.SellerInfo;

public interface SellerInfoService extends BaseService<SellerInfo> {

    SellerInfo findByOpenid(String openId);

    SellerInfo findByUsernameAndPassword(String userName, String passWord);
}
