package com.imooc.sell.dao;

import com.imooc.sell.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 订单表逻辑层
 */
public interface OrderMasterDao  extends JpaRepository<OrderMaster,String>{

    @Query("select m from OrderMaster m where m.buyerName like %:buyerName%")
    List<OrderMaster> findOrderMastersByBuyerNameLike(@Param("buyerName") String buyerName);
    @Query("select m from OrderMaster m where m.buyerName like %?1%")
    List<OrderMaster> findOrderMastersByBuyerPhoneEquals(String buyerPhone);

    Page<OrderMaster> findByBuyerOpenid(String openId, Pageable pageable);
}

