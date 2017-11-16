package com.hnzskj.common.xml;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.hnzskj.persist.bean.flow.FlowRef;

public class ShowXml {
	private XmlDTO xmlDTO;
	private String[] message = new String[2];
	private String[] left = new String[2];
	private Map<String, FlowRef> center;
	private List<String[]> right;

	public String[] getMessage() {
		return message;
	}

	public void setMessage(String[] message) {
		this.message = message;
	}

	public XmlDTO getXmlDTO() {
		return this.xmlDTO;
	}

	public void setXmlDTO(XmlDTO xmlDTO) {
		this.xmlDTO = xmlDTO;
	}

	public XmlDTO getXmlDTO(String xmlId, String xml) {
		return new TransXml(xmlId).parseXML(xml);
	}

	public void setXmlDTO(String xmlId, String xml) {
		this.xmlDTO = new TransXml(xmlId).parseXML(xml);
	}

	public String[] getLeft() {
		return left;
	}

	public void setLeft(String[] left) {
		this.left = left;
	}

	public Map<String, FlowRef> getCenter() {
		return center;
	}

	public void setCenter(Map<String, FlowRef> center) {
		this.center = center;
	}

	public List<String[]> getRight() {
		return right;
	}

	public void setRight(List<String[]> right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "ShowXml [center=" + center + ", left=" + Arrays.toString(left)
				+ ", message=" + Arrays.toString(message) + ", right=" + right
				+ ", xmlDTO=" + xmlDTO + "]";
	}

}
