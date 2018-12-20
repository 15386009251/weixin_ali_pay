
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

/**
 * 
 * IOrderService<br/>
 * 创建人:mofeng<br/>
 * 时间：2018年12月12日-下午8:24:31 <br/>
 * @version 1.0.0<br/>
 * 
 */
public interface IOrderService {
	
	public List<Map<String,Object>> findOrders();

	/**
	 * (这里用一句话描述这个方法的作用)<br/>
	 * 方法名：getIsPay<br/>
	 * 创建人：mofeng <br/>
	 * 时间：2018年12月12日-下午9:33:53 <br/>
	 * 手机:1564545646464<br/>
	 * @param orderId
	 * @param userId
	 * @return int<br/>
	 * @exception <br/>
	 * @since  1.0.0<br/>
	*/
	public int getIsPay(Long orderId, Long userId);
}
