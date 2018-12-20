package com.rumo.config.pay.commons.unionpay.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.rumo.config.pay.commons.unionpay.constants.UnionPayReturnParams;
import com.rumo.config.pay.commons.unionpay.enumtype.UnionPayEnum;
import com.rumo.config.pay.commons.unionpay.util.Pkipair;

public class UnionPaySubmit {
	private static final String UNIONPAY_GATEWAY_NEW = "https://www.99bill.com/gateway/recvMerchantInfoAction.htm";
	 /**
     * 建立请求，以表单HTML形式构造（默认）
     * @param sParaTemp 请求参数数组
     * @param strMethod 提交方式。两个值可选：post、get
     * @param strButtonName 确认按钮显示文字
     * @return 提交表单HTML文本
     */
    public static String buildRequest(Map<String, String> sPara, String strMethod,String strButtonName) {
        List<String> keys = new ArrayList<String>(sPara.keySet());

        StringBuffer sbHtml = new StringBuffer();

        sbHtml.append("<form  id='unionpaysubmit' name=\"kqPay\" action=\"" + UNIONPAY_GATEWAY_NEW
                       + "\" method=\"" + strMethod
                      + "\">");

        for (int i = 0; i < keys.size(); i++) {
            String name = (String) keys.get(i);
            String value = (String) sPara.get(name);
            sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
        }

        //submit按钮控件请不要含有name属性
        sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");
        sbHtml.append("<script>document.forms['unionpaysubmit'].submit();</script>");
        return sbHtml.toString();
    }
    
    
    /**
     * 组装支付请求参数
     * (这里用一句话描述这个方法的作用)<BR>
     * 方法名：assemblyParams<BR>
     * 创建人：小威 <BR>
     * 时间：2015年11月27日-下午2:27:56 <BR>
     * @param params
     * @return Map<String,String><BR>
     * @exception <BR>
     * @since  1.0.0
     */
    public static Map<String,String> assemblyPayParams(Map<String,String> params){
    	//订单提交时间，格式：yyyyMMddHHmmss，如：20071117020101，不能为空。
		String orderTime = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
		// signMsg 签名字符串 不可空，生成加密签名串
		String signMsgVal = "";
		Map<String, String> map = new LinkedHashMap<>();
		signMsgVal = appendParam(signMsgVal,UnionPayConstants.INPUTCHARSET, Config.INPUTCHARSET);
		map.put(UnionPayConstants.INPUTCHARSET, Config.INPUTCHARSET);
		signMsgVal = appendParam(signMsgVal,UnionPayConstants.PAGEURL,params.get(UnionPayEnum.UNION_PAGE_URL.getValue()));
		map.put(UnionPayConstants.PAGEURL,params.get(UnionPayEnum.UNION_PAGE_URL.getValue()));
		signMsgVal = appendParam(signMsgVal,UnionPayConstants.BGURL,params.get(UnionPayEnum.UNION_BG_URL.getValue()));
		map.put(UnionPayConstants.BGURL,params.get(UnionPayEnum.UNION_BG_URL.getValue()));
		signMsgVal = appendParam(signMsgVal,UnionPayConstants.VERSION, Config.VERSION);
		map.put(UnionPayConstants.VERSION,Config.VERSION);
		signMsgVal = appendParam(signMsgVal,UnionPayConstants.LANGUAGE, Config.LANGUAGE);
		map.put(UnionPayConstants.LANGUAGE,Config.LANGUAGE);
		signMsgVal = appendParam(signMsgVal,UnionPayConstants.SIGNTYPE, Config.SIGNTYPE);
		map.put(UnionPayConstants.SIGNTYPE,Config.SIGNTYPE);
		signMsgVal = appendParam(signMsgVal,UnionPayConstants.MERCHANTACCTID,Config.MERCHANTACCTID);
		map.put(UnionPayConstants.MERCHANTACCTID,Config.MERCHANTACCTID);
		signMsgVal = appendParam(signMsgVal,UnionPayConstants.ORDERID,params.get(UnionPayEnum.UNION_OUT_TARDE_NO.getValue()));
		map.put(UnionPayConstants.ORDERID,params.get(UnionPayEnum.UNION_OUT_TARDE_NO.getValue()));
		signMsgVal = appendParam(signMsgVal,UnionPayConstants.ORDERAMOUNT,params.get(UnionPayEnum.UNION_PAYMENT_MONEY.getValue()));
		map.put(UnionPayConstants.ORDERAMOUNT,params.get(UnionPayEnum.UNION_PAYMENT_MONEY.getValue()));
		signMsgVal = appendParam(signMsgVal,UnionPayConstants.ORDERTIME, orderTime);
		map.put(UnionPayConstants.ORDERTIME,orderTime);
		signMsgVal = appendParam(signMsgVal,UnionPayConstants.PRODUCTNAME,params.get(UnionPayEnum.UNION_TARDE_TITLE.getValue()));
		map.put(UnionPayConstants.PRODUCTNAME,params.get(UnionPayEnum.UNION_TARDE_TITLE.getValue()));
		signMsgVal = appendParam(signMsgVal,UnionPayConstants.PRODUCTDESC, params.get(UnionPayEnum.UNION_TARDE_TITLE.getValue()));
		map.put(UnionPayConstants.PRODUCTDESC,params.get(UnionPayEnum.UNION_TARDE_TITLE.getValue()));
		signMsgVal = appendParam(signMsgVal,UnionPayConstants.EXT1, params.get(UnionPayEnum.UNION_RETURN_PARAMS.getValue()));
		map.put(UnionPayConstants.EXT1,params.get(UnionPayEnum.UNION_RETURN_PARAMS.getValue()));
		signMsgVal = appendParam(signMsgVal,UnionPayConstants.PAYTYPE, Config.PAYTYPE);
		map.put(UnionPayConstants.PAYTYPE,Config.PAYTYPE);
		Pkipair pki = new Pkipair();
		String signMsg = pki.signMsg(signMsgVal);
		map.put("signMsg",signMsg);
		return map;
    }
    
    /**
     * 组装返回参数
     * (这里用一句话描述这个方法的作用)<BR>
     * 方法名：assemblyReturnParams<BR>
     * 创建人：小威 <BR>
     * 时间：2015年11月27日-下午2:34:46 <BR>
     * @param request
     * @return Map<String,String><BR>
     * @exception <BR>
     * @since  1.0.0
     */
	public static  Map<String, String> assemblyReturnParams(HttpServletRequest request){
		Map<String, String> map = new HashMap<String, String>();
		//人民币网关账号，该账号为11位人民币网关商户编号+01,该值与提交时相同。
		map.put(UnionPayReturnParams.MERCHANT_ACCT_ID, request.getParameter(UnionPayReturnParams.MERCHANT_ACCT_ID));
		//网关版本，固定值：v2.0,该值与提交时相同。
		map.put(UnionPayReturnParams.VERSION, request.getParameter(UnionPayReturnParams.VERSION));
		//语言种类，1代表中文显示，2代表英文显示。默认为1,该值与提交时相同。
		map.put(UnionPayReturnParams.LANGUAGE, request.getParameter(UnionPayReturnParams.LANGUAGE));
		//签名类型,该值为4，代表PKI加密方式,该值与提交时相同。
		map.put(UnionPayReturnParams.SIGN_TYPE, request.getParameter(UnionPayReturnParams.SIGN_TYPE));
		//支付方式，一般为00，代表所有的支付方式。如果是银行直连商户，该值为10,该值与提交时相同。
		map.put(UnionPayReturnParams.PAY_TYPE, request.getParameter(UnionPayReturnParams.PAY_TYPE));
		//银行代码，如果payType为00，该值为空；如果payType为10,该值与提交时相同。
		map.put(UnionPayReturnParams.BANK_ID, request.getParameter(UnionPayReturnParams.BANK_ID));
		//商户订单号，该值与提交时相同。
		map.put(UnionPayReturnParams.ORDER_ID, request.getParameter(UnionPayReturnParams.ORDER_ID));
		//订单提交时间，格式：yyyyMMddHHmmss，如：20071117020101,该值与提交时相同。
		map.put(UnionPayReturnParams.ORDER_TIME, request.getParameter(UnionPayReturnParams.ORDER_TIME));
		//订单金额，金额以“分”为单位，商户测试以1分测试即可，切勿以大金额测试,该值与支付时相同。
		map.put(UnionPayReturnParams.ORDER_AMOUNT, request.getParameter(UnionPayReturnParams.ORDER_AMOUNT));
		// 快钱交易号，商户每一笔交易都会在快钱生成一个交易号。
		map.put(UnionPayReturnParams.DEAL_ID, request.getParameter(UnionPayReturnParams.DEAL_ID));
		//银行交易号 ，快钱交易在银行支付时对应的交易号，如果不是通过银行卡支付，则为空
		map.put(UnionPayReturnParams.BANK_DEAL_ID, request.getParameter(UnionPayReturnParams.BANK_DEAL_ID));
		//快钱交易时间，快钱对交易进行处理的时间,格式：yyyyMMddHHmmss，如：20071117020101
		map.put(UnionPayReturnParams.DEAL_TIME, request.getParameter(UnionPayReturnParams.DEAL_TIME));
		//商户实际支付金额 以分为单位。比方10元，提交时金额应为1000。该金额代表商户快钱账户最终收到的金额。
		map.put(UnionPayReturnParams.PAY_AMOUNT, request.getParameter(UnionPayReturnParams.PAY_AMOUNT));
		//费用，快钱收取商户的手续费，单位为分。
		map.put(UnionPayReturnParams.FEE, request.getParameter(UnionPayReturnParams.FEE));
		//扩展字段，该值与提交时相同。
		map.put(UnionPayReturnParams.EXT1, request.getParameter(UnionPayReturnParams.EXT1));
		//扩展字段2，该值与提交时相同。
		map.put(UnionPayReturnParams.EXT2, request.getParameter(UnionPayReturnParams.EXT2));
		//处理结果， 10支付成功，11 支付失败，00订单申请成功，01 订单申请失败
		map.put(UnionPayReturnParams.PAY_RESULT,request.getParameter(UnionPayReturnParams.PAY_RESULT));
		//错误代码 ，请参照《人民币网关接口文档》最后部分的详细解释。
		map.put(UnionPayReturnParams.ERR_CODE, request.getParameter(UnionPayReturnParams.ERR_CODE));
		//签名字符串 
		map.put(UnionPayReturnParams.SIGN_MSG, request.getParameter(UnionPayReturnParams.SIGN_MSG));
		return map;
	}
	
	private static String appendParam(String returns, String paramId, String paramValue) {
		if (returns != "") {
			if (paramValue != "") {
				returns += "&" + paramId + "=" + paramValue;
			}
		} else {
			if (paramValue != "") {
				returns = paramId + "=" + paramValue;
			}
		}
		return returns;
	}

    
}
