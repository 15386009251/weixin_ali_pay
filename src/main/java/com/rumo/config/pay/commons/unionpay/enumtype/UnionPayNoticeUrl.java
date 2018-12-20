package com.rumo.config.pay.commons.unionpay.enumtype;

/**
 * 块钱回调通知url枚举 
 * UnionPayNoticeUrl<BR>
 * 创建人:小威 <BR>
 * 时间：2015年12月4日-下午2:20:23 <BR>
 * @version 1.0.0
 *
 */
public enum UnionPayNoticeUrl {
    SUCCESS("<result>1</result><redirecturl>http://www.tanzhouedu.com/mall/pay/paySuccess</redirecturl>","回调处理成功"),
    FAIL("<result>0</result><redirecturl>http://www.tanzhouedu.com/mall/pay/payFail</redirecturl>","回调处理失败");
	
	private String value;
	private String desc;
	
	UnionPayNoticeUrl(String value,String desc) {
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
