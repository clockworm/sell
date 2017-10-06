package com.imooc.sell.web.controller;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 卖家端订单
 */
@Slf4j
@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {

	@Autowired
	private OrderService orderService;

	/**
	 * 所有订单
	 * @param page 第几页开始 默认第一页开始 即为0
	 * @param size 每页多少条
	 * @return
	 */
	@GetMapping("list")
	public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
							   @RequestParam(value = "size", defaultValue = "10") Integer size,
							   Map<String, Object> map) {
		PageRequest pageRequest = new PageRequest(page - 1, size);
		Page<OrderDTO> list = orderService.findList(pageRequest);
		log.info("分页查询所有订单 出参:{}", JsonUtil.toJson(list.getContent()));
		map.put("currentPage",page);
		map.put("size",size);
		map.put("data", list);
		map.put("controller", "order");
		return new ModelAndView("order/list", map);
	}

	/** 取消订单*/
	@GetMapping("cancel")
	public ModelAndView cancel(String orderId,Map<String,Object> map){
		map.put("url","/sell/seller/order/list");
		try{
			OrderDTO orderDTO = orderService.findOne(orderId);
			orderService.cancel(orderDTO);
		}catch (SellException e){
			log.error("[卖家端取消订单] 发生异常:{}",e);
			map.put("msg", e.getMessage());
			return new ModelAndView("common/error",map);
		}
		map.put("msg",ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
		return  new ModelAndView("common/success");
	}

	/**订单详情*/
	@GetMapping("detail")
	public ModelAndView detail(String orderId,Map<String,Object> map){
		map.put("url","/sell/seller/order/list");
		try{
			OrderDTO dto = orderService.findOne(orderId);
			map.put("order",dto);
		}catch (SellException e){
			log.error("[卖家端查询订单详情异常] {}",e);
			map.put("msg", e.getMessage());
			return new ModelAndView("common/error",map);
		}
		return new ModelAndView("order/detail",map);
	}

	/**完结订单*/
	@GetMapping("finish")
	public ModelAndView finish(String orderId,Map<String,Object> map) {
		map.put("url", "/sell/seller/order/list");
		try {
			OrderDTO dto = orderService.findOne(orderId);
			orderService.finish(dto);
		} catch (SellException e) {
			log.error("[卖家端完结订单异常] {}", e);
			map.put("msg", e.getMessage());
			return new ModelAndView("common/error", map);
		}
		map.put("msg",ResultEnum.ORDER_OVER_SUCCESS.getMessage());
		return new ModelAndView("common/success",map);
	}
}
