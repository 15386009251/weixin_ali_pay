package com.rumo.config.pay.commons.tenpay.core;

public class Config {
	//收款方
	public static final  String  SPANAME = "Seven-IT男孩咨询有限公司";  

	//商户号
	public static final  String PARENER = "1254797001";

	//密钥
	public static final  String KEY = "5cd588875867635cc500cca855a3f956";
	
	//支付网关
	public static final String GATEURL  = "https://gw.tenpay.com/gateway/pay.htm";
	
	//异步通知 xml文件地址
	public static final String NOTIFY_GATEURL = "https://gw.tenpay.com/gateway/simpleverifynotifyid.xml";
	
	//签名类型,默认：MD5
	public static final String MD5 = "MD5";
	
	//版本号，默认为1.0
	public static final String VERSION = "1.0";

	//字符编码
	public static final String GBK = "GBK";
	
	//密钥序号
	public static final String SIGN_INDEX = "1";
	
	//交易模式
	public static final String TRADE_MODE = "1";//1即时到账(默认)，2中介担保，3后台选择
}
