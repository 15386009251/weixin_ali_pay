
 /**
 * itbooking系统平台<br/>
 * com.rumo.mapper<br/>
 * OrderMapper.java<br/>
 * 创建人:mofeng <br/>
 * 时间：2018年12月12日-下午8:18:37 <br/>
 * 2018itbooking-版权所有<br/>
 */
package com.rumo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 
 * OrderMapper<br/>
 * 创建人:mofeng<br/>
 * 时间：2018年12月12日-下午8:18:37 <br/>
 * @version 1.0.0<br/>
 * 
 */
public interface OrderMapper {

	public List<Map<String,Object>> findOrders();
	
	public Map<String,Object> getOrder(@Param("orderId")Long orderId,@Param("userId")Long userId);

	/**
	 * (这里用一句话描述这个方法的作用)<br/>
	 * 方法名：getIsPay<br/>
	 * 创建人：mofeng <br/>
	 * 时间：2018年12月12日-下午9:34:23 <br/>
	 * 手机:1564545646464<br/>
	 * @param orderId
	 * @param userId
	 * @return int<br/>
	 * @exception <br/>
	 * @since  1.0.0<br/>
	*/
	public int getIsPay(Long orderId, Long userId);
	public int updateOrderIsPay(Long orderId, Long userId);
}
