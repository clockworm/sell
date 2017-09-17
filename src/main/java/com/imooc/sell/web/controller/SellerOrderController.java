package com.imooc.sell.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.util.JsonUtil;

import lombok.extern.slf4j.Slf4j;

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
	 * 
	 * @param page
	 *            第几页开始 默认第一页开始 即为0
	 * @param size
	 *            每页多少条
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
		map.put("orders", list);
		return new ModelAndView("order/list", map);
	}

}
