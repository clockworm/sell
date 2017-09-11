package com.imooc.sell.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.imooc.sell.dao.OrderDetailDao;
import com.imooc.sell.dao.OrderMasterDao;
import com.imooc.sell.dto.CartDTO;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.entity.OrderDetail;
import com.imooc.sell.entity.OrderMaster;
import com.imooc.sell.entity.ProductInfo;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.service.PayService;
import com.imooc.sell.service.ProductInfoService;
import com.imooc.sell.util.KeyUtil;
import com.imooc.sell.util.OrderMaster2OrderDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private PayService payService;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        //1.商品信息    3.  4.
        List<OrderDetail> detailList = orderDTO.getDetailList();
        for (OrderDetail orderDetail : detailList) {
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //(数量  价格 计算訂單总价)
            orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
            //写入订单数据库（OrderMaster ）
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailDao.save(orderDetail);
        }
        //写入订单詳情列表数据库OrderDetail
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        orderDTO.setOrderAmount(orderAmount);
        BeanUtils.copyProperties(orderDTO, orderMaster, "orderStatus", "payStatus");
        orderMasterDao.save(orderMaster);
        //扣库存
        List<CartDTO> cartDTOS = detailList.stream().map(
                e -> new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        productInfoService.decreaseStock(cartDTOS);
        return orderDTO;
    }

    @Override
    public OrderDTO saveOrUpdate(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO delete(String id) {
        return null;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterDao.findOne(orderId);
        if (orderMaster == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> details = orderDetailDao.findByOrderId(orderId);
        if (details.isEmpty()) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setDetailList(details);
        return orderDTO;
    }

    @Override
    public List<OrderDTO> findAll() {
        return null;
    }

    @Override
    public Page<OrderDTO> findByPage(Pageable page) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> list = orderMasterDao.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDTO> dtos = OrderMaster2OrderDTO.convert(list.getContent());
        PageImpl<OrderDTO> page = new PageImpl<>(dtos, pageable, list.getTotalElements());
        return page;
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        //判斷訂單的狀態
        if (!OrderStatusEnum.NEW.getCode().equals(orderDTO.getOrderStatus())) {
            log.error("【取消訂單】狀態不正確。orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDERSTATUS_ERROR);
        }
        //修改狀態 取消狀態
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster = orderMasterDao.save(orderMaster);
        if (orderMaster == null) {
            log.error("【取消訂單】 更新失敗：orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }
        //加庫存
        if (CollectionUtils.isEmpty(orderDTO.getDetailList())) {
            log.error("【取消訂單】 訂單中無商品詳情：orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOS = orderDTO.getDetailList().stream().map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productInfoService.increaseStock(cartDTOS);
        //如果支付完畢 需要退款
        if (PayStatusEnum.SUCCESS.getCode().equals(orderDTO.getPayStatus())) {
             payService.refund(orderDTO);
        }
        return orderDTO;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        //判斷訂單的狀態
        if (!OrderStatusEnum.NEW.getCode().equals(orderDTO.getOrderStatus())) {
            log.error("【完結訂單】 狀態不正確。orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDERSTATUS_ERROR);
        }
        //修改狀態 完結狀態
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster = orderMasterDao.save(orderMaster);
        if (orderMaster == null) {
            log.error("【完結訂單】 更新失敗：orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }
        //如果沒有支付 需要完成支付
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判斷訂單狀態
        if (!OrderStatusEnum.NEW.getCode().equals(orderDTO.getOrderStatus())) {
            log.error("【支付訂單】 訂單狀態不正確。orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDERSTATUS_ERROR);
        }
        //判斷支付狀態
        if (!PayStatusEnum.WAIT.getCode().equals(orderDTO.getPayStatus())) {
            log.error("【支付訂單】 支付狀態不正確。orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.PAYSTATUS_ERROR);
        }
        //修改支付狀態
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster = orderMasterDao.save(orderMaster);
        if (orderMaster == null) {
            log.error("【支付訂單】 更新失敗：orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }
        return orderDTO;
    }

    @Override
    public List<OrderDTO> findOrderMastersByBuyerNameLike(String tang) {
        return null;
    }

    @Override
    public List<OrderDTO> findOrderMastersByBuyerPhoneEquals(String s) {
        return null;
    }
}
