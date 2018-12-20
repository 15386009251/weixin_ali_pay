package com.rumo.config.pay.commons.unionpay.enumtype;

/**
 * 银联支付相关枚举 
 * UnionPayEnum<BR>
 * 创建人:小威 <BR>
 * 时间：2015年11月27日-上午11:19:50 <BR>
 * @version 1.0.0
 *
 */
public enum UnionPayEnum {
	
	UNION_PAYMENT_MONEY("payMoney","支付金额"),
	UNION_RETURN_PARAMS("returnParams","回调参数"),
	UNION_OUT_TARDE_NO("out_tarde_no","交易订单号"),
	UNION_TARDE_TITLE("trade_title","交易title"),
	UNION_BG_URL("bg_url","优先回调地址"),
	UNION_PAGE_URL("page_url","bg_url调不通则调pageurl"),
    UNION_REQUEST_METHOD("post","请求方式"),
    UNION_REQUEST_INPUT("确认","提交按钮");
	
	private String value;
	private String desc;
	
	UnionPayEnum(String value,String desc){
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
