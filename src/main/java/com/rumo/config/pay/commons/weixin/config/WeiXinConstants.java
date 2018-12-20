package com.rumo.config.pay.commons.weixin.config;

/**
 * 微信支付常量
 * WeiXinConstants<BR>
 * 创建人:小威 <BR>
 * 时间：2015年10月19日-下午7:14:15 <BR>
 * @version 1.0.0
 *
 */
public class WeiXinConstants {
	
	
	   public static final String URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";//统一下单url
	   
	   public static final int WIDTH = 200;//二维码宽度
	   
	   public static final int HEIGHT = 200;//二维码高度
	   
	   public static final String RESULT = "result_code";//返回结果
	   
	   public static final String ORDER_PAID = "orderPaid";//已经支付
	   
	   public static final String WEIXINPAY = "weixinPay";//可以支付 
	   
	   public static final String CODE_URL = "code_url";//微信支付url
	   
	   public static final String ERROR_CODE = "err_code";//错误码
	   
	   public static final String ATTACH = "attach";//商家数据包
	   
	   public static final String BODY = "body";//商品名称
	   
	   public static final String NOTIFY_URL = "notify_url";//异步回调地址
	   
	   public static final String OUT_TRADE_NO = "out_trade_no";//商家订单号
	   
	   public static final String TRANSACTION_ID = "transaction_id";//微信订单号
	   
	   public static final String PRODUCT_ID = "product_id";//商品ID
	   
	   public static final String SPBILL_CREATE_IP = "spbill_create_ip";//支付用户IP地址
	   
	   public static final String TOTAL_FEE = "total_fee";//支付金额 
}
