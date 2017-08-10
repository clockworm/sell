package com.imooc.sell.service;

import com.imooc.sell.entity.OrderMaster;

import java.util.List;

public interface OrderMasterService extends BaseService<OrderMaster> {

    List<OrderMaster> findOrderMastersByBuyerNameLike(String buyerName);

    List<OrderMaster> findOrderMastersByBuyerPhoneEquals(String buyerPhone);

}
