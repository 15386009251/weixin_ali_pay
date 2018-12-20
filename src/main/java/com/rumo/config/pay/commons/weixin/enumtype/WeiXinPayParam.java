package com.rumo.config.pay.commons.weixin.enumtype;

/**
 * 微信支付参数
 * WeiXinPayParam<BR>
 * 创建人:小威 <BR>
 * 时间：2015年12月1日-下午4:41:02 <BR>
 * @version 1.0.0
 *
 */
public enum WeiXinPayParam {
   
	WEIXIN_PAY_DATA("data","微信支付数据"),
	WEIXIN_PAY_ID("id","缓存id"),
	WEIXIN_PAY_ORDERID("orderId","订单id"),
	WEIXIN_PAY_MONEY("money","支付价格");
	
	
   private String value;
   private String desc;
  
   WeiXinPayParam(String value,String desc) {
	 this.value = value;
	 this.desc = desc;
   }

	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
  
}
