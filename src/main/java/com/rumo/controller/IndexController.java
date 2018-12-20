
 /**
 * itbooking系统平台<br/>
 * com.rumo.controller<br/>
 * WeixinPayController.java<br/>
 * 创建人:mofeng <br/>
 * 时间：2018年12月12日-下午8:06:09 <br/>
 * 2018itbooking-版权所有<br/>
 */
package com.rumo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rumo.service.IOrderService;

/**
 * 
 * WeixinPayController<br/>
 * 创建人:mofeng<br/>
 * 时间：2018年12月12日-下午8:06:09 <br/>
 * @version 1.0.0<br/>
 * 
 */
@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	private IOrderService orderService;
	
	
	@GetMapping("/index")
	public String index(ModelMap modelMap) {
		modelMap.addAttribute("orders", orderService.findOrders());
		return "index";
	}
	
	@ResponseBody
	@RequestMapping("/listernpay")
	public int listernispay(@RequestParam("orderId")Long orderId) {
		Long userId = 9L;
		return orderService.getIsPay(orderId,userId);
	}
	
	
}
