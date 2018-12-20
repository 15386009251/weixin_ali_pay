package com.rumo.config.pay.commons.weixin.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;



/**
 * 请求微信生成扫一扫url
 * HttpsRequest<BR>
 * 创建人:小威 <BR>
 * 时间：2015年10月16日-下午5:05:36 <BR>
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class WeiXinRequest{
	
    public static String submitData(String url,Object xmlObj) throws ClientProtocolException, IOException{
    	DefaultHttpClient httpClient = new DefaultHttpClient();
        String result = null;
        HttpPost httpPost = new HttpPost(url);
        //解决XStream对出现双下划线的bug
        XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
        //将要提交给API的数据对象转换成XML格式数据Post给API
        String postDataXML = xStreamForRequestPostData.toXML(xmlObj);
        //得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
        StringEntity postEntity = new StringEntity(postDataXML, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.setEntity(postEntity);
        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        result = EntityUtils.toString(entity, "UTF-8");
        httpPost.abort();
        return result;
    }
}
