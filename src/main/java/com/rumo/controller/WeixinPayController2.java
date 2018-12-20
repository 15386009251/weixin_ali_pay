
 /**
 * itbooking系统平台<br/>
 * com.rumo.controller<br/>
 * WeixinPayController.java<br/>
 * 创建人:mofeng <br/>
 * 时间：2018年12月12日-下午8:06:09 <br/>
 * 2018itbooking-版权所有<br/>
 */
package com.rumo.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rumo.config.pay.Signature;
import com.rumo.config.pay.XMLParser;
import com.rumo.config.pay.commons.weixin.ReportReqData;
import com.rumo.config.pay.commons.weixin.ReturnUrlData;
import com.rumo.config.pay.commons.weixin.config.WeiXinConstants;
import com.rumo.config.pay.commons.weixin.enumtype.WeiXinPayTradeStatus;
import com.rumo.config.pay.commons.weixin.request.QrCodeRequest;
import com.rumo.mapper.OrderMapper;
import com.rumo.pojo.AdminCenter;
import com.rumo.pojo.Order;
import com.rumo.util.ip.TmIpUtil;

import net.sf.json.JSONObject;


/**
 * 
 * WeixinPayController<br/>
 * 创建人:mofeng<br/>
 * 时间：2018年12月12日-下午8:06:09 <br/>
 * @version 1.0.0<br/>
 * 
 */
@Controller
@RequestMapping("/admin/weixinxiao")
public class WeixinPayController2 {

	
	private static Logger log = LoggerFactory.getLogger(WeixinPayController2.class);
	
	@Autowired
	private OrderMapper orderMapper;
	
	/**
	 * 生成微信支付码
	 * (这里用一句话描述这个方法的作用)<BR>
	 * 
	 setScale(1)表示保留一位小数，默认用四舍五入方式 
	 setScale(1,BigDecimal.ROUND_DOWN)直接删除多余的小数位，如2.35会变成2.3 
	 setScale(1,BigDecimal.ROUND_UP)进位处理，2.35变成2.4 
	 setScale(1,BigDecimal.ROUND_HALF_UP)四舍五入，2.35变成2.4
	 setScaler(1,BigDecimal.ROUND_HALF_DOWN)四舍五入，2.35变成2.3，如果是5则向下舍
	 * 方法名：gainQrCode<BR>
	 * 创建人：小威 <BR>
	 * 时间：2015年10月19日-下午2:48:05 <BR>
	 * @return
	 * @throws Exception String<BR>
	 * @exception <BR>
	 * @since  1.0.0
	 */
	@ResponseBody
	@PostMapping
	public Map<String,String> pay(
			@RequestParam("orderId")Long orderId,
			@RequestParam("orderNumber")String orderNumber2,
			@RequestParam("userId")Long userId,
			@RequestParam("openId")String openId,
			HttpServletRequest request,HttpSession session) throws IOException{
		try {
			log.info("微信支付进来了...............centerid:{}");
			log.info("1------------微信支付进来.....................");
			String orderIdstr = request.getParameter("orderId");
			if(orderIdstr==null)return null;
			Map<String, Object> orderData = orderMapper.getOrder(Long.parseLong(orderIdstr), userId);
			//订单编号
			String orderNumber= String.valueOf(orderData.get("orderNumber"));
			String weixinOrderNumber= getOrderNo(userId+"");
			//设置给回调函数的参数，属于附加参数值
			JSONObject json = new JSONObject();
			json.put("paytype", 1);
			json.put("userId", userId);
			json.put("orderNumber", orderNumber);
			json.put("orderId", orderIdstr);
			String params = json.toString().replace("\"", "'");
			//设置微信支付的参数，关于你自己购买的产品信息(课程，包年月业务，订单)
			ReportReqData data = new ReportReqData();
			data.setAttach(params);
			data.setOpenid(openId);//小程序一定要传递
			data.setBody("濡沫课程");//商品名称
			data.setNotify_url("http://www.itbooking.net/admin/weixin/notifyUrl");//异步通知URL
			data.setOut_trade_no(weixinOrderNumber);//给微信订单号
			data.setProduct_id(orderIdstr);//商品ID
			data.setSpbill_create_ip("127.0.0.1");//ip地址
			data.setTotal_fee(getMoney(String.valueOf(orderData.get("payment"))));//金额
			return QrCodeRequest.submitWeixinMessage(data);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("微信支付返回失败...............",e);
			return null;
		}
	}
	
	
	/**
	 * 
	 * wx:request("/decodeUserInfo",function(res){
	 * })
	 *
	 *let orderId = 1;
	 *let userId = 9;	
	 * wx:request({
		  	url:"/decodeUserInfo",
		  	data:{},
		  	method:"get",
		    success:function(res){
		   		console.log(res)//openId  sessionKey
		   	  	wx:request({
		   	  	   url:"/admin/weixinxiao",
		   	  	   data:{opendId:res.openId,orderId,userId}
		   	  	   success:function(res){
		   	  	   		wx.requestPayment({
						  timeStamp: '',
						  nonceStr: '',
						  package: '',
						  signType: 'MD5',
						  paySign: '',
						  success(res) {
						  	//updateispay
						  },
						  fail(res) {
						   
						  }
						})
		   	  	   }
		   	  	})
		    }
	 * });
     * decoding encrypted data to get openid
     * @param iv
     * @param encryptedData
     * @param code
     * @return
     */
    @RequestMapping(value = "/decodeUserInfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String,String> decodeUserInfo(String code) {
        Map<String,String> map = new HashMap<>();
        // login code can not be null
        if (code == null || code.length() == 0) {
            map.put("status", "0");
            map.put("msg", "code 不能为空");
            return map;
        }
        // mini-Program's AppID
        String wechatAppId = "wxfd4dbd43681b73a1";
        // mini-Program's session-key
        String wechatSecretKey = "b5eb9ac10500fd931a717fda09b3f550";
        String grantType = "authorization_code";
        // using login code to get sessionId and openId
        String params = "appid=" + wechatAppId + "&secret=" + wechatSecretKey + "&js_code=" + code + "&grant_type=" + grantType;
        // sending request
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
        // analysis request content
        JSONObject json = JSONObject.fromObject(sr);
        log.info("返回的数据是：.",json.toString());
        // getting session_key
        String sessionKey = json.get("session_key").toString();
        // getting open_id
        String openId = json.get("openid").toString();
        map.put("sessionKey", sessionKey);
        map.put("openId", openId);
        log.info("******************返回的数据是：.",JsonUtil.obj2String(map));
        return map;
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
	 * 1.03
     * 元转换成分
     * @param amount
     * @return
     */
    public static String getMoney(String amount) {
        if(amount==null){
            return "";
        }
        // 金额转化为分为单位
        // 处理包含, ￥ 或者$的金额
        String currency =  amount.replaceAll("\\$|\\￥|\\,", "");
        int index = currency.indexOf(".");
        int length = currency.length();
        Long amLong = 0l;
        if(index == -1){
            amLong = Long.valueOf(currency+"00");
        }else if(length - index >= 3){
            amLong = Long.valueOf((currency.substring(0, index+3)).replace(".", ""));
        }else if(length - index == 2){
            amLong = Long.valueOf((currency.substring(0, index+2)).replace(".", "")+0);
        }else{
            amLong = Long.valueOf((currency.substring(0, index+1)).replace(".", "")+"00");
        }
        return amLong.toString();
    }

	
	/**
	 * 异步通知
	 * 方法名：notifyUrl<BR>
	 * 创建人：小威 <BR>
	 * 时间：2015年10月17日-上午11:21:00 <BR>
	 * @return String<BR>
	 * @throws Exception 
	 * @exception <BR>
	 * @since  1.0.0
	 */
	@ResponseBody
	@RequestMapping("notifyUrl")
	public String notifyUrl(HttpSession session,HttpServletRequest request) {
		try {
			log.info("1------------微信支付异步进来.....................");
			String inputLine;
			String notityXml = "";
			ReturnUrlData returnData = new ReturnUrlData();
			while ((inputLine = request.getReader().readLine()) != null) {
				notityXml += inputLine;
			}
			if("".equals(notityXml)){
				returnData.setReturn_code("FAIL");
				return XMLParser.printString(returnData);
			}
			//进行签名校验
			boolean boo = Signature.checkIsSignValidFromResponseString(notityXml);
			log.info("2------------微信支付异步进来.....................{}",boo);
			if(boo){
				//处理业务逻辑
				Map<String,String> map = XMLParser.getMapFromXML(notityXml);
				//商户订单号
			 	String out_trade_no = map.get(WeiXinConstants.OUT_TRADE_NO);
				//公共参数
				String attach = map.get(WeiXinConstants.ATTACH);
				//微信订单号
				String transaction_id = map.get(WeiXinConstants.TRANSACTION_ID);
				JSONObject jsonObject = JSONObject.fromObject(attach);
				String orderNumber = "";
				String orderId = "";
				if(jsonObject.containsKey("orderNumber")){//订单ID
					orderNumber = jsonObject.getString("orderNumber");
				}
				
				if(jsonObject.containsKey("orderId")){//订单ID
					orderId = jsonObject.getString("orderId");
				}
				String userId = jsonObject.getString("userId");
				String total_fee = map.get(WeiXinConstants.TOTAL_FEE);
				//根据订单id和userId把支付状态修改为1
				int count = orderMapper.updateOrderIsPay(Long.parseLong(orderId),Long.parseLong(userId));
				log.info("3--------支付状态修改成功....orderId:{}...userId:{}...count:{}..........",orderId,userId,count);
				returnData.setReturn_code(WeiXinPayTradeStatus.SUCCESS.getValue());
				return XMLParser.printString(returnData);
			}else{
				log.info("4------签名失败.........{}",boo);
				returnData.setReturn_code(WeiXinPayTradeStatus.FAIL.getValue());
				return XMLParser.printString(returnData);
			}
		} catch (Exception e) {
			log.info("45-----------支付失败....{}",e.getMessage());
			e.printStackTrace();
			return "error";
		}
	}
}
