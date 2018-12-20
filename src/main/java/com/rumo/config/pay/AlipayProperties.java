package com.rumo.config.pay;

import org.springframework.boot.context.properties.ConfigurationProperties;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */
public class AlipayProperties {
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者APPID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088121467069613";
	// 收款支付宝账号
	public static String seller_email = "472186195@qq.com";
	// 商户的私钥 MD5加密的key RSA
	public static String key = "w6nzrhv72zxdx3iylnhf90hwojc7orhg";
	
	
	// 合作身份者APPID，以2088开头由16位纯数字组成的字符串
	//public static String partner = "2088121467069613";
	// 收款支付宝账号
	//public static String seller_email = "472186195@qq.com";
	// 商户的私钥 MD5加密的key RSA
	//public static String RASkey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCp3D3uPmJKi2Stxs4vmyhb+Y/j8f98nr1hRlGUEPuv1BUHDWSC+04OwmaRvWr0cy/7mbjqGaimLx35xSugy9FpgtYTbRLjGvYwwk0XwKAmlkXV8Z57UVrnhjA73EZQE+dxxcbHnxQEoT5icd7Lvec8pCvv2/mrv38IXsZp3HwsXwIDAQAB+Y/j8f98nr1hRlGUEPuv1BUHDWSC+04OwmaRvWr0cy/7mbjqGaimLx35xSugy9FpgtYTbRLjGvYwwk0XwKAmlkXV8Z57UVrnhjA73EZQE+dxxcbHnxQEoT5icd7Lvec8pCvv2/mrv38IXsZp3HwsXwIDAQAB";
	//public static String ALIPAYkey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
	
	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "C:/alipaylog/";
	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	// 签名方式 不需修改
	public static String sign_type = "MD5";
}
