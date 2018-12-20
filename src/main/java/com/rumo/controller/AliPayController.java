package com.rumo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rumo.config.pay.AlipayProperties;
import com.rumo.config.pay.util.AlipaySubmit;
import com.rumo.mapper.OrderMapper;

import net.sf.json.JSONObject;

/**
 * 
 * I
 * 创建人:xuchengfei 
 * 时间：2015年12月12日-下午2:26:11 
 * @version 1.0.0
 * 
 */
@Controller
@RequestMapping("/admin/alipay")
public class AliPayController {
	
	private static Logger log = LoggerFactory.getLogger(AliPayController.class);
	
	@Autowired
	private OrderMapper orderMapper;

	@ResponseBody
	@RequestMapping(method=RequestMethod.POST)
	public String pay(HttpServletRequest request,HttpSession session){
		try{
			log.info("1------------阿里支付进来.....................");
			String orderIdstr = request.getParameter("orderId");
			if(orderIdstr==null)return null;
			Long userId = 9L;//这里userId从session中获取
			Map<String, Object> orderData = orderMapper.getOrder(Long.parseLong(orderIdstr), userId);
			if(orderData==null) {
				return "参数不正确..";
			}
			//订单编号
			String orderNumber= String.valueOf(orderData.get("orderNumber"));
			////////////////////////////////////请求参数//////////////////////////////////////
			//支付类型 1:商品购买 2：服务购买  3：网络拍卖  4捐赠
			String payment_type = "1";
			JSONObject json = new JSONObject();
			json.put("paytype", "1");
			json.put("mark", "year");
			json.put("userId", userId);
			json.put("orderNumber", orderNumber);
			//设置订单
			String params = json.toString().replace("\"", "'");
			//必填，不能修改
			//服务器异步通知页面路径
			String notify_url = "http://www.itbooking.net/admin/alipay/notifyUrl";
			//需http://格式的完整路径，不能加?id=123这类自定义参数
			//页面跳转同步通知页面路径
			String return_url ="http://localhost:8080/admin/alipay/returnUrl";
			//需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成https://www.mengkedu.com/
			//商户订单号
			String out_trade_no = String.valueOf(orderNumber);
			//商户网站订单系统中唯一订单号，必填
			//订单名称
			String subject =String.valueOf("濡沫学院阿里支付");//一般放入你的商品的标题，业务的标题
			//必填
			//付款金额
			String total_fee =String.valueOf(orderData.get("payment"));
			//必填
			//订单描述 
			String body = String.valueOf(orderData.get("payment"));
			//商品展示地址
			String show_url = new String("http://www.itbooking.net".getBytes("ISO-8859-1"),"UTF-8");
			//需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html
			//防钓鱼时间戳
			String anti_phishing_key = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			//若要使用请调用类文件submit中的query_timestamp函数
			//客户端的IP地址
			String exter_invoke_ip = getIpAddr(request);
			//非局域网的外网IP地址，如：221.0.0.1
			//////////////////////////////////////////////////////////////////////////////////
			//把请求参数打包成数组
			Map<String, String> sParaTemp = new HashMap<String, String>();
			// 即时到账交易接口    服务名称：create_direct_pay_by_user
			sParaTemp.put("service", "create_direct_pay_by_user");
			
			//合作者身份ID 签约的支付宝账号对应的支付宝唯一用户号。  以2088开头的16位纯数字组成。 
			sParaTemp.put("partner", AlipayProperties.partner); 
			
			//卖家支付宝账号 卖家信息优先级： seller_id>seller_account_name>seller_email。  seller_id，seller_account_name和seller_email不能全部为空，至少有一项不为空。
			sParaTemp.put("seller_email", AlipayProperties.seller_email);
			
			//商户网站使用的编码格式，如utf-8、gbk、gb2312等。
			sParaTemp.put("_input_charset", AlipayProperties.input_charset);
			
			//支付类型 1:商品购买 2：服务购买  3：网络拍卖  4捐赠
			sParaTemp.put("payment_type", payment_type);
			
			//服务器异步通知页面路径 支付宝服务器主动通知商户网站里指定的页面http路径
			sParaTemp.put("notify_url", notify_url);
			
			//页面跳转同步通知页面路径  支付宝处理完请求后，当前页面自动跳转到商户网站里指定页面的http路径。
			sParaTemp.put("return_url", return_url);
			
			//商户网站唯一订单号 支付宝合作商户网站唯一订单号（确保在合作伙伴系统中唯一）。
			sParaTemp.put("out_trade_no", out_trade_no);
			
			//商品名称 商品的标题/交易标题/订单标题/订单关键字等。  该参数最长为128个汉字。
			sParaTemp.put("subject", subject);
			//支付宝公共参数
			sParaTemp.put("extra_common_param", params);
			//交易金额  该笔订单的资金总额，单位为RMB-Yuan。取值范围为[0.01，100000000.00]，精确到小数点后两位。
//			sParaTemp.put("total_fee", "0.01");
			sParaTemp.put("total_fee", total_fee);
			
			//商品描述  对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。
			sParaTemp.put("body", body);
			//商品展示网址 收银台页面上，商品展示的超链接。
			sParaTemp.put("show_url", show_url);
			//防钓鱼时间戳 通过时间戳查询接口获取的加密支付宝系统时间戳。  如果已申请开通防钓鱼时间戳验证，则此字段必填。
			sParaTemp.put("anti_phishing_key", anti_phishing_key);
			//客户端IP 用户在创建交易时，该用户当前所使用机器的IP。  如果商户申请后台开通防钓鱼IP地址检查选项，此字段必填，校验用。
			sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
			//建立请求
			String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"post","确认");
			return sHtmlText;
		}catch(Exception ex){
			return "error";
		}
	}
	
	@RequestMapping("/payloading")
	public String payloading() {
		return "payloading";
	}
	
	
	// 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip.equals("0:0:0:0:0:0:0:1"))
			return "127.0.0.1";
		return ip;
	}
	
	/**
	 * 生成订单编号
	 * 
	 * @return
	 */
	static AtomicLong atomicLong = new AtomicLong();
	public static synchronized String getOrderNo(String opid) {
		String str = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
		String date = null;
		long orderNum = 0L;
		if (date == null || !date.equals(str)) {
			date = str;
			orderNum = 0l;
		}
		orderNum++;
		long orderNo = Long.parseLong((date)) * 10000;
		orderNo += orderNum;
		return "mk_" + orderNo + opid + atomicLong.incrementAndGet();
	}
	
	
	/**
	 * 同步通知
	 */
	@RequestMapping("/returnUrl")
	public String return_url(HttpSession session,HttpServletRequest request) throws Exception {
		log.info("阿里支付同步通知进来了.......................");
		return "success";
		/*boolean result = alipayCallback(true,session,request);
		if(result){
			return "redirect:/"; // 请不要修改或删除
		}else {
			// 验证失败
			return "fail";
		}*/
	}
	
	/**
	 * 异步通知
	 */
	@ResponseBody
	@RequestMapping("/notifyUrl")
	public String notify_url(HttpSession session,HttpServletRequest request) throws Exception {
		log.info("阿里支付异步通知进来了.......................");
		return "success";
		//boolean result = alipayCallback(false,session,request);
		//if(result){
		//	return "success"; // 请不要修改或删除
		//}else {
		//	// 验证失败
		//	return "fail";
		//}
	}
	
//	/**
//	 * 支付宝回调
//	 * @param flag
//	 * @return
//	 * @throws Exception
//	 */
//	private boolean alipayCallback(boolean flag,HttpSession session,HttpServletRequest request)throws Exception{
//		// 获取支付宝GET过来反馈信息
//		Map<String, String> paramss = new HashMap<String, String>();
//		Map requestParams = request.getParameterMap();
//		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
//			String name = (String) iter.next();
//			String[] values = (String[]) requestParams.get(name);
//			String valueStr = "";
//			for (int i = 0; i < values.length; i++) {
//				valueStr = (i == values.length - 1) ? valueStr + values[i]
//						: valueStr + values[i] + ",";
//			}
//			if(flag){
//				paramss.put(name, new String (valueStr.getBytes("ISO-8859-1"),"UTF-8"));
//			}else{
//				paramss.put(name,valueStr);
//			}
//		}
//		// 计算得出通知验证结果
//		boolean verify_result = AlipayNotify.verify(paramss);
//		// 返回公共参数
//		String extra_common_param = new String(request.getParameter("extra_common_param").getBytes("ISO-8859-1"), "UTF-8");
//		JSONObject jsonObject = JSONObject.fromObject(extra_common_param);
//		String orderNumber = "";
//		
//		if(jsonObject.containsKey("orderNumber")){//订单ID
//			orderNumber = jsonObject.getString("orderNumber");
//		}
//		
//		//交易完成
//        if(verify_result){
//        	try {
//        		String userId = jsonObject.getString("userId");
//				String total_fee = new String(request.getParameter("total_fee").getBytes("ISO-8859-1"), "UTF-8");
//    			OrderYear orderYear = new OrderYear();
//    			String psettingid = jsonObject.getString("psettingid");
//    			orderYear.setPrice(Float.parseFloat(total_fee));
//    			orderYear.setIp(TmIpUtil.getIpAddr(request));
//    			orderYear.setIpaddress(TmIpUtil.ipLocation(request));
//    			orderYear.setType(2);//支付宝
//    			orderYear.setStatus(1);
//    			orderYear.setPaysettingid(Integer.parseInt(psettingid));
//    			orderYear.setOrdernumber(orderNumber);
//    			orderYear.setUserId(Long.parseLong(userId));
//    			orderYear.setDescription("《IT书》VIP会员");
//    			orderMapper.saveOrderYear(orderYear);
//    			
//    			//保存用户的购买信息
//    			OrderYearUser orderYearUser = orderYearUserMapper.getOrderYearUserById(Long.parseLong(userId));
//    			if(orderYearUser==null){
//    				orderYearUser =new OrderYearUser();
//    				orderYearUser.setStatus(1);
//    				orderYearUser.setUserId(Long.parseLong(userId));
//    				if(psettingid.equals("1"))orderYearUser.setPeriodTime(TmDateUtil.addYear(new Date(), 1));
//    				if(psettingid.equals("2"))orderYearUser.setPeriodTime(TmDateUtil.addMonth(new Date(), 7));
//    				if(psettingid.equals("3"))orderYearUser.setPeriodTime(TmDateUtil.addMonth(new Date(), 4));
//    				if(psettingid.equals("4"))orderYearUser.setPeriodTime(TmDateUtil.addMonth(new Date(), 1));
//    				if(psettingid.equals("5"))orderYearUser.setPeriodTime(TmDateUtil.addDay(new Date(), 7));
//    				orderYearUserMapper.saveOrderYearUser(orderYearUser);
//    			}else {
//    				orderYearUser.setStatus(1);
//        			if(psettingid.equals("1"))orderYearUser.setPeriodTime(TmDateUtil.addYear(orderYearUser.getPeriodTime(), 1));
//        			if(psettingid.equals("2"))orderYearUser.setPeriodTime(TmDateUtil.addMonth(orderYearUser.getPeriodTime(), 7));
//        			if(psettingid.equals("3"))orderYearUser.setPeriodTime(TmDateUtil.addMonth(orderYearUser.getPeriodTime(), 4));
//        			if(psettingid.equals("4"))orderYearUser.setPeriodTime(TmDateUtil.addMonth(orderYearUser.getPeriodTime(), 1));
//        			if(psettingid.equals("5"))orderYearUser.setPeriodTime(TmDateUtil.addDay(orderYearUser.getPeriodTime(), 7));
//        			orderYearUserMapper.updateOrderYearUser(orderYearUser);
//				}
//    			
//    			//修改学生的标识
//    			User user = userMapper.getUserById(Long.parseLong(userId));
//    			if(user.getType()==1) {
//    				user.setType(2);
//    			}else if(user.getType()==2) {
//    				user.setType(3);
//    			}
//    			userMapper.updateUser(user);
//    			//更新session缓存
//    			session.setAttribute(TmConstant.SESSION_USER_KEY, user);
//        	} catch (Exception e) {
//			     e.printStackTrace();
//			}
//			return true;
//        }else{
//        	// 交易状态    因为异步通知比同步快
//			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
//			if (trade_status.equals("TRADE_FINISHED")|| trade_status.equals("TRADE_SUCCESS")) {
//				//执行订单后续的操作
//				try {
//					String userId = jsonObject.getString("userId");
//					String total_fee = new String(request.getParameter("total_fee").getBytes("ISO-8859-1"), "UTF-8");
//        			OrderYear orderYear = new OrderYear();
//        			String psettingid = jsonObject.getString("psettingid");
//        			orderYear.setPrice(Float.parseFloat(total_fee));
//        			orderYear.setIp(TmIpUtil.getIpAddr(request));
//        			orderYear.setIpaddress(TmIpUtil.ipLocation(request));
//        			orderYear.setType(2);//支付宝
//        			orderYear.setStatus(1);
//        			orderYear.setPaysettingid(Integer.parseInt(psettingid));
//        			orderYear.setOrdernumber(orderNumber);
//        			orderYear.setUserId(Long.parseLong(userId));
//        			orderYear.setDescription("《IT书》VIP会员");
//        			orderMapper.saveOrderYear(orderYear);
//        			
//        			//保存用户的购买信息
//        			OrderYearUser orderYearUser = orderYearUserMapper.getOrderYearUserById(Long.parseLong(userId));
//        			if(orderYearUser==null){
//        				orderYearUser =new OrderYearUser();
//        				orderYearUser.setStatus(1);
//        				orderYearUser.setUserId(Long.parseLong(userId));
//        				if(psettingid.equals("1"))orderYearUser.setPeriodTime(TmDateUtil.addYear(new Date(), 1));
//        				if(psettingid.equals("2"))orderYearUser.setPeriodTime(TmDateUtil.addMonth(new Date(), 7));
//        				if(psettingid.equals("3"))orderYearUser.setPeriodTime(TmDateUtil.addMonth(new Date(), 4));
//        				if(psettingid.equals("4"))orderYearUser.setPeriodTime(TmDateUtil.addMonth(new Date(), 1));
//        				if(psettingid.equals("5"))orderYearUser.setPeriodTime(TmDateUtil.addDay(new Date(), 7));
//        				orderYearUserMapper.saveOrderYearUser(orderYearUser);
//        			}else {
//        				orderYearUser.setStatus(1);
//	        			if(psettingid.equals("1"))orderYearUser.setPeriodTime(TmDateUtil.addYear(orderYearUser.getPeriodTime(), 1));
//	        			if(psettingid.equals("2"))orderYearUser.setPeriodTime(TmDateUtil.addMonth(orderYearUser.getPeriodTime(), 7));
//	        			if(psettingid.equals("3"))orderYearUser.setPeriodTime(TmDateUtil.addMonth(orderYearUser.getPeriodTime(), 4));
//	        			if(psettingid.equals("4"))orderYearUser.setPeriodTime(TmDateUtil.addMonth(orderYearUser.getPeriodTime(), 1));
//	        			if(psettingid.equals("5"))orderYearUser.setPeriodTime(TmDateUtil.addDay(orderYearUser.getPeriodTime(), 7));
//	        			orderYearUserMapper.updateOrderYearUser(orderYearUser);
//					}
//        			
//        			//修改学生的标识
//        			User user = userMapper.getUserById(Long.parseLong(userId));
//        			if(user.getType()==1) {
//        				user.setType(2);
//        			}else if(user.getType()==2) {
//        				user.setType(3);
//        			}
//        			userMapper.updateUser(user);
//        			//更新session缓存
//        			session.setAttribute(TmConstant.SESSION_USER_KEY, user);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				return true;
//			}
//        }      
//       return false;
//	}
}