package com.rumo.config.pay.commons.unionpay.core;

public class Config {
    public static final String INPUTCHARSET = "1";//编码方式，1代表 UTF-8; 2 代表 GBK; 3代表 GB2312 默认为1,该参数必填。
    public static final String VERSION = "v2.0";//网关版本，固定值：v2.0,该参数必填。 
    public static final String LANGUAGE = "1";//语言种类，1代表中文显示，2代表英文显示。默认为1,该参数必填。
    public static final String SIGNTYPE = "4";//签名类型,该值为4，代表PKI加密方式,该参数必填。
    public static final String MERCHANTACCTID = "1002467188301";//人民币网关账号，该账号为11位人民币网关商户编号+01,该参数必填。1002459617001
    public static final String PAYTYPE = "00";//支付方式，一般为00，代表所有的支付方式。如果是银行直连商户，该值为10，必填。
    
}
