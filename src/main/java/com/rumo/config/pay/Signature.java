package com.rumo.config.pay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.rumo.config.pay.commons.weixin.config.WeiXinConfig;
import com.rumo.config.pay.commons.weixin.sign.MD5;

/**
 * 签名算法
 * Signature<BR>
 * 创建人:小威 <BR>
 * 时间：2015年10月16日-下午2:12:44 <BR>
 * @version 1.0.0
 *
 */
public class Signature {
	/**
	 * 签名算法
	 * (这里用一句话描述这个方法的作用)<BR>
	 * 方法名：getSign<BR>
	 * 创建人：小威 <BR>
	 * 时间：2015年10月16日-下午2:12:55 <BR>
	 * @param o
	 * @return
	 * @throws IllegalAccessException String<BR>
	 * @exception <BR>
	 * @since  1.0.0
	 */
    public static String getSign(Map<String,String> map){
        ArrayList<String> list = new ArrayList<String>();
        for(Map.Entry<String,String> entry:map.entrySet()){
            if(entry.getValue()!=""){
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "key=" + WeiXinConfig.KEY;
        result = MD5.MD5Encode(result).toUpperCase();
        return result;
    }

    
    /**
     * 从API返回的XML数据里面重新计算一次签名
     * 方法名：getSignFromResponseString<BR>
     * 创建人：小威 <BR>
     * 时间：2015年10月16日-下午2:11:53 <BR>
     * @param responseString  API返回的XML数据
     * @return 
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException String<BR>
     * @exception <BR>
     * @since  1.0.0
     */
    public static String getSignFromResponseString(String responseString) throws IOException, SAXException, ParserConfigurationException {
        Map<String,String> map = XMLParser.getMapFromXML(responseString);
        //清掉返回数据对象里面的Sign数据（不能把这个数据也加进去进行签名），然后用签名算法进行签名
        map.put("sign","");
        //将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较
        return Signature.getSign(map);
    }
    /**
     * 检验API返回的数据里面的签名是否合法，避免数据在传输的过程中被第三方篡改
     * 方法名：checkIsSignValidFromResponseString<BR>
     * 创建人：小威 <BR>
     * 时间：2015年10月16日-下午2:12:19 <BR>
     * @param responseString API返回的XML数据字符串
     * @return API签名是否合法
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException boolean<BR>
     * @exception <BR>
     * @since  1.0.0
     */
    public static boolean checkIsSignValidFromResponseString(String responseString) throws ParserConfigurationException, IOException, SAXException {
        Map<String,String> map = XMLParser.getMapFromXML(responseString);
        String signFromAPIResponse = map.get("sign").toString();
        if(signFromAPIResponse=="" || signFromAPIResponse == null){
            return false;
        }
        //清掉返回数据对象里面的Sign数据（不能把这个数据也加进去进行签名），然后用签名算法进行签名
        map.put("sign","");
        //将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较
        String signForAPIResponse = Signature.getSign(map);

        if(!signForAPIResponse.equals(signFromAPIResponse)){
            //签名验不过，表示这个API返回的数据有可能已经被篡改了
            return false;
        }
        return true;
    }

}
