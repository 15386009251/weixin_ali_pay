package com.rumo.config.pay.commons.weixin;

/**
 * 支付回调完成之后 返回给微信的数据 
 * ReturnUrlData<BR>
 * 创建人:小威 <BR>
 * 时间：2015年10月19日-下午3:52:06 <BR>
 * @version 1.0.0
 *
 */
public class ReturnUrlData {
   private String return_code;
   private String return_msg;
  
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	public String getReturn_msg() {
		return return_msg;
	}
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
}
