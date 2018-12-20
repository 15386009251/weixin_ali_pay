package com.rumo.config.pay.commons.weixin.enumtype;

/**
 * 微信支付交易状态
 * WeiXinPayTradeStatus<BR>
 * 创建人:小威 <BR>
 * 时间：2015年12月1日-下午4:37:55 <BR>
 * @version 1.0.0
 *
 */
public enum WeiXinPayTradeStatus {
	SUCCESS("SUCCESS", "交易成功"),
	FAIL("FAIL", "交易失败");
	private String value;
	private String desc;

	WeiXinPayTradeStatus(String value, String desc) {
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
