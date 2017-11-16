package com.hnzskj.common.xml;

/**
 * 
 * @author 申雅各
 * 解析xml文件属性
 */


import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XmlDTOParseLine {
	

	public XmlDTOParseLine() {

	}

	/**
	 * xmlfromDB 数据库中保存的xml文件 转义字符串
	 * 
	 * @param xmlfromDB
	 * @return String
	 */

	private String escapeString(String xmlfromDB) {

		if (null != xmlfromDB && "" != xmlfromDB) {
			xmlfromDB = xmlfromDB.replaceAll("&amp;", "&");
			xmlfromDB = xmlfromDB.replaceAll("&apos;", "'");
			xmlfromDB = xmlfromDB.replaceAll("&quot;", "\"");
			xmlfromDB = xmlfromDB.replaceAll("&gt;", ">");
			xmlfromDB = xmlfromDB.replaceAll("&lt;", "<");
		}
		return xmlfromDB;
	}
/**
 * 解析xml文件的line属性，属性wbasemode和xbasemode必须存在且有值
 * @param protocolXML
 * @return
 */
	@SuppressWarnings("unchecked")
	public boolean parseXML(String protocolXML) {
		System.out.println(protocolXML + "************");
	
		Document doc = null;
		try {
			doc = (Document) DocumentHelper.parseText(escapeString(protocolXML));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root = doc.getRootElement();

		List<Element> childList = root.elements();
		int temp = 0;
		int linesize = 0;
		for (Element element : childList) {

			if ("line".equals(element.getName().trim())) {
				linesize++;
				List<Attribute> attrList = element.attributes();

				for (Attribute attribute : attrList) {

					if (attribute.getName().equalsIgnoreCase("wBaseMode")) {
						if(null!=attribute.getValue()&&!"".endsWith(attribute.getValue())){
						temp++;
					}}
					if (attribute.getName().equalsIgnoreCase("xBaseMode")) {
						if(null!=attribute.getValue()&&!"".endsWith(attribute.getValue())){
							temp++;
						}}
				}
			}
		}
		if (temp != 2 * linesize) {
			return false;
		}else{
			return  true;
		}

	}

}
