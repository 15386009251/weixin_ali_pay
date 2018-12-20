
 /**
 * itbooking系统平台<br/>
 * com.rumo.service<br/>
 * IOrderService.java<br/>
 * 创建人:mofeng <br/>
 * 时间：2018年12月12日-下午8:24:31 <br/>
 * 2018itbooking-版权所有<br/>
 */
package com.rumo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.rumo.mapper.OrderMapper;

/**
 * 
 * IOrderService<br/>
 * 创建人:mofeng<br/>
 * 时间：2018年12月12日-下午8:24:31 <br/>
 * @version 1.0.0<br/>
 * 
 */
@Service
public class OrderServiceImpl implements IOrderService{
	
	@Autowired
	private OrderMapper orderMapper;
	
	public List<Map<String,Object>> findOrders(){
		return orderMapper.findOrders();
	}
	
	public int getIsPay(Long orderId, Long userId) {
		return orderMapper.getIsPay(orderId,userId);
	}
}

