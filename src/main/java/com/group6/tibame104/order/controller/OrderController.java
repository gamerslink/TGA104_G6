package com.group6.tibame104.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group6.tibame104.order.model.OrderService;
import com.group6.tibame104.order.model.OrderVO;
import com.group6.tibame104.orderlist.model.OrderlistService;
import com.group6.tibame104.orderlist.model.OrderlistVO;

@Controller
@RequestMapping("/front-end/order")
public class OrderController {
	@Autowired
	OrderService orderSvc;
	@Autowired
	OrderlistService orderlistSvc;
	
	
//	@PostMapping("/select_by_OrderID")
//    public String selectByOrderID(Model model, @RequestParam("orderID") String str) {
//		Integer orderID = Integer.valueOf(str);
//		
//		
//		OrderVO orderVO_orderID = orderSvc.getOrder(orderID);
//		
//		model.addAttribute("orderVO_orderID", orderVO_orderID); // 資料庫取出的empVO物件,存入req
//		model.addAttribute("orderID", orderID); 
//		return "front-end/order/listOrder_One";
//		
//	}
	
	@PostMapping("/select_Order")
	public String selectOrder(Model model, @RequestParam Map<String,String> map
			) {
		
		Map<OrderVO,List<OrderlistVO>> map_list = new HashMap<OrderVO,List<OrderlistVO>>();
		List<OrderVO> list_OrderVO  = orderSvc.getAllOrderByComposite(map);
		
		for(OrderVO orderVO : list_OrderVO) {
			List<OrderlistVO> list_OrderlistVO = orderlistSvc.searchOrderlist(orderVO.getOrderID());
			map_list.put(orderVO, list_OrderlistVO);
		}
		
		model.addAttribute("map_list", map_list);
	
				return "front-end/order/listOrder";
		
	}
}
