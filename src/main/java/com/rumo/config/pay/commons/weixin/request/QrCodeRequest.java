package com.rumo.config.pay.commons.weixin.request;

import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.rumo.config.pay.commons.weixin.ReportReqData;
import com.rumo.config.pay.commons.weixin.config.WeiXinConfig;
import com.rumo.config.pay.commons.weixin.config.WeiXinConstants;
import com.rumo.config.pay.commons.weixin.sign.Signature;
import com.rumo.config.pay.commons.weixin.util.QrCode;
import com.rumo.config.pay.commons.weixin.util.RandomStringGenerator;
import com.rumo.config.pay.commons.weixin.util.WeiXinRequest;
import com.rumo.config.pay.commons.weixin.util.XMLParser;
import com.rumo.controller.JsonUtil;


/**
 * 数据提交并且生成二维码
 * WeixinSubmit<BR>
 * 创建人:小威 <BR>
 * 时间：2015年10月16日-下午2:14:47 <BR>
 * @version 1.0.0
 *
 */
public class QrCodeRequest {
	
	
	
	
	/**
	 * 生成二维码
	 * (这里用一句话描述这个方法的作用)<BR>
	 * 方法名：submitCode<BR>
	 * 创建人：小威 <BR>
	 * 时间：2015年10月16日-下午5:23:27 <BR>
	 * @param data
	 * @return BufferedImage<BR>
	 * @exception <BR>
	 * @since  1.0.0
	   data.setTrade_type("JSAPI");//移动端和小程序 --openid scretkey
	 */
	public static BufferedImage submitCode(ReportReqData data){
		
		data.setAppid(WeiXinConfig.APPID);//公众账号ID
		data.setMch_id(WeiXinConfig.MCH_ID);//商户号
		data.setNonce_str(RandomStringGenerator.getRandomStringByLength(32));//随机字符
		data.setTrade_type("NATIVE");//扫一扫支付方式---二维码支付
		data.setSign(Signature.getSign(data.toMap()));//签名字符
		BufferedImage buff = null ;
		//请求提交
		try {
			String returnData = WeiXinRequest.submitData(WeiXinConstants.URL, data);
			if(returnData != null && !"".equals(returnData)){
				Map<String,String> map = XMLParser.getMapFromXML(returnData);//解析返回的字符串 并且组成map集合
				if(null != map && !map.isEmpty()){
					String resultCode = (String) map.get(WeiXinConstants.RESULT);
					if("SUCCESS".equals(resultCode)){//链接生成成功 ，支付地址weixin://wxpay/bizpayurl?pr=fFc506i
						buff = QrCode.QrcodeImage(WeiXinConstants.WIDTH,WeiXinConstants.HEIGHT,String.valueOf(map.get(WeiXinConstants.CODE_URL)));
					}
				}
			}
			return buff;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * 微信支付成功信息配置
	 * 方法名：submitWeixinMessage<br/>
	 * 创建人：mofeng <br/>
	 * 时间：2018年11月28日-下午2:06:06 <br/>
	 * 手机:1564545646464<br/>
	 * @param data
	 * @return Map<String,String><br/>
	 * @exception <br/>
	 * @since  1.0.0<br/>
	 */
	public static Map<String,String> submitWeixinMessage(ReportReqData data){
		Map<String,String> nmap = new HashMap<>();
		try {
			data.setAppid(WeiXinConfig.APPID);//公众账号ID
			data.setMch_id(WeiXinConfig.MCH_ID);//商户号
			data.setKey(WeiXinConfig.KEY);//商户号
			data.setNonce_str(RandomStringGenerator.getRandomStringByLength(32));//随机字符
			data.setTrade_type("JSAPI");//扫一扫支付方式 NATIVE
			data.setSign(Signature.getSign(data.toMap()));//签名字符
			//log.info("=======支付信息是：{}",JsonUtil.obj2StringPretty(data));
			String returnData = WeiXinRequest.submitData(WeiXinConstants.URL, data);
			if(returnData != null && !"".equals(returnData)){
				Map<String,String> map = XMLParser.getMapFromXML(returnData);//解析返回的字符串 并且组成map集合
				if(null != map && !map.isEmpty()){
					String resultCode = (String) map.get(WeiXinConstants.RESULT);
					if("SUCCESS".equals(resultCode)){//链接生成成功
						//nmap.put("appId",user.getAppid().trim());
						nmap.put("timeStamp",new Date().getTime()+"");
						nmap.put("signType","MD5");
						nmap.put("key", data.getKey());
						nmap.put("nonceStr",data.getNonce_str());
						nmap.put("package","prepay_id="+map.get("prepay_id"));
						nmap.put("paySign",Signature.getSign(nmap));
						return nmap;
					}
				}
			}else {
				nmap.put("message", returnData);
			}
			return nmap;
		} catch (Exception e) {
			e.printStackTrace();
			nmap.put("message", e.getMessage());
			return nmap;
		}
	}
	
	
	
	/**
	 * 检查订单是否已经支付 
	 * (这里用一句话描述这个方法的作用)<BR>
	 * 方法名：checkPay<BR>
	 * 创建人：小威 <BR>
	 * 时间：2015年10月17日-上午10:54:02 <BR>
	 * @param data
	 * @return boolean<BR> true 已经支付   false没有支付
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public static boolean checkPay(ReportReqData data){
		data.setAppid(WeiXinConfig.APPID);//公众账号ID
		data.setMch_id(WeiXinConfig.MCH_ID);//商户号
		data.setNonce_str(RandomStringGenerator.getRandomStringByLength(32));//随机字符
		data.setTrade_type("NATIVE");//扫一扫支付方式
		data.setSign(Signature.getSign(data.toMap()));//签名字符
		try {
			String returnData = WeiXinRequest.submitData(WeiXinConstants.URL, data);
			if(returnData != null && !"".equals(returnData)){
				Map<String,String> map = XMLParser.getMapFromXML(returnData);//解析返回的字符串 并且组成map集合
				if(null != map && !map.isEmpty()){
					String resultCode = (String) map.get(WeiXinConstants.RESULT);
					if("FAIL".equals(resultCode) && "ORDERPAID".equals(map.get(WeiXinConstants.ERROR_CODE))){//该订单已经支付
						return true;
					}
				}
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
