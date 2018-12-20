package com.rumo.config.pay.commons.weixin.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.rumo.config.pay.commons.weixin.ReturnUrlData;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * XML解析 
 * XMLParser<BR>
 * 创建人:小威 <BR>
 * 时间：2015年10月16日-下午2:11:08 <BR>
 * @version 1.0.0
 *
 */
@SuppressWarnings("deprecation")
public class XMLParser {
    public static Map<String,String> getMapFromXML(String xmlString) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream is =  getStringStream(xmlString);
        Document document = builder.parse(is);

        //获取到document里面的全部结点
        NodeList allNodes = document.getFirstChild().getChildNodes();
        Node node;
        Map<String, String> map = new HashMap<String, String>();
        int i=0;
        while (i < allNodes.getLength()) {
            node = allNodes.item(i);
            if(node instanceof Element){
                map.put(node.getNodeName(),node.getTextContent());
            }
            i++;
        }
        return map;
    }
    
    public static InputStream getStringStream(String sInputString) throws UnsupportedEncodingException {
        ByteArrayInputStream tInputStringStream = null;
        if (sInputString != null && !sInputString.trim().equals("")) {
            tInputStringStream = new ByteArrayInputStream(sInputString.getBytes("UTF-8"));
        }
        return tInputStringStream;
    }
    
    public static String printString(Object obj){
		XStream xStream = new XStream(new XppDriver(new  
                XmlFriendlyReplacer("_-", "_"))); 
		xStream.alias("xml",ReturnUrlData.class);
    	return xStream.toXML(obj);
    }
}