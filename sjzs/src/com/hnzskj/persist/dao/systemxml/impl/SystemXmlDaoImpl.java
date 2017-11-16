package com.hnzskj.persist.dao.systemxml.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;

import com.hnzskj.common.Constant;
import com.hnzskj.common.init.SysParamUtil;
import com.hnzskj.common.init.XmlReader;
import com.hnzskj.persist.bean.init.ConfigBean;
import com.hnzskj.persist.bean.init.Configure;
import com.hnzskj.persist.bean.init.InitCodeTemp;
import com.hnzskj.persist.dao.systemxml.SystemXmlDao;
import com.hnzskj.common.DataUtil;
public class SystemXmlDaoImpl implements SystemXmlDao {
	private static Logger log = Logger.getLogger(SystemXmlDaoImpl.class);
	/**
	 * 读取system.xml中的信息.
	 * @throws DocumentException 
	 */
	@Override
	public List<Object> listSystemXml() throws DocumentException {
		// 系统参数保存对象
		XmlReader xmlReader = new XmlReader(Constant.config_parameter_path);
		ConfigBean configBean = xmlReader.getSystemParams();
		Configure configure = Configure.getInstance();
		configure.setBean(configBean);
		SysParamUtil sysParamUtil = new SysParamUtil();
		List<Object> list = new ArrayList<Object>();
		list.add(sysParamUtil.getInitCustLb());	// 添加客户类别
		list.add(sysParamUtil.getInitDxzt());	// 添加对象状态
		list.add(sysParamUtil.getInithtLb());	// 添加合同类别
		list.add(sysParamUtil.getInitHtwblb());	// 添加合同文本类别
		list.add(sysParamUtil.getInitJsfs());	// 添加结算方式
		list.add(sysParamUtil.getInitLclb());	// 添加流程类别
		list.add(sysParamUtil.getInitLcsp());	// 添加流程审批
		list.add(sysParamUtil.getInitLczt());	// 添加流程状态
		list.add(sysParamUtil.getLsList());		// 添加临时流状态
		list.add(sysParamUtil.getInitMblb());	// 添加模板类别
		list.add(sysParamUtil.getInitQbzl());	// 添加钱币种类
		list.add(sysParamUtil.getInitXmfjlb());	// 添加项附件类别
		list.add(sysParamUtil.getInitAccount());	// 添加初始化页面账户信息
		list.add(sysParamUtil.getInitCommon());	// 添加使用的数据库
		list.add(sysParamUtil.getInitHtzt());	//添加合同状态
		list.add(sysParamUtil.getInitKhzt());	// 添加客户状态
		list.add(sysParamUtil.getInitKhjb());	// 添加客户级别
		list.add(sysParamUtil.getInitKhxydj());	// 添加客户信用等级
		list.add(sysParamUtil.getInitGyslb());	// 添加供应商级别
		list.add(sysParamUtil.getET99Bean());	//添加加密信息
		//数据库，合同首字母，文件上传路径，是否选择流程
		list.add(sysParamUtil.getGgbmBean());
		list.add(sysParamUtil.getInitKhxydj());	//添加客户信用等级
		list.add(sysParamUtil.getInitGyslb());	//添加供应商类别
		list.add(sysParamUtil.getInitGysxydj());	//添加供应商信用等级
		list.add(sysParamUtil.getInitCodeTemp());	//项目编码生成规则
		list.add(sysParamUtil.getInitKuckfw());	//客户查看范围
		list.add(sysParamUtil.getInitGysckfw());	//供应商查看范围
		list.add(sysParamUtil.getInitXmckfw());	//项目查看范围
		list.add(sysParamUtil.getInitCtractQueryScope());
		return list;
	}

	/**
	 * 方法描述：添加一个节点.
	 * @param nodeName:父节点名称,name:节点的名称
	 * @author 田玉江
	 * 创建日期：2011-6-18
	 */
	@Override
	public boolean add(String nodeName, String name) {
		Document doc = getDoc();
		List<?> list = doc.selectNodes("//" + nodeName);
		Iterator<?> iter = list.iterator();
		int code = 0 ;
		while (iter.hasNext()) {
			Element element = (Element) iter.next();
			Element levelElement = element.addElement("level");
			List<?> l = element.selectNodes("level");
			for(int i = 0;i < l.size();i ++) {
				if(i == (l.size() - 2)) {
					Element e = (Element) l.get(i);
					code = Integer.parseInt(e.element("code").getText()) + 1;
				}
			}
			levelElement.addElement("name").setText(name);
			levelElement.addElement("code").setText((String.valueOf(code)));
		}

		if (saveXml(doc))
			return true;
		else
			return false;
		
	}

	/**
	 * 方法描述：根据code删除system.xml文件中一个节点
	 * @param nodeName:父节点名称,code:节点的code
	 * @author 田玉江
	 * @throws
	 * @return
	 * 创建日期：2011-6-18
	 */
	@Override
	public boolean del(String nodeName, String code) 
						throws DocumentException {
		Document doc = getDoc();
		List<?> list = doc.selectNodes("//" + nodeName);
		Iterator<?> iter = list.iterator();
		while (iter.hasNext()) {
			Element element = (Element) iter.next();
			Iterator<?> iterator = element.elementIterator("level");
			while (iterator.hasNext()) {
				Element e = (Element) iterator.next();
				Element et = e.element("code");

				if (et.getText().equals(code)) {
					e.getParent().remove(e);
					break;
				}
			}
		}
		if (saveXml(doc))
			return true;
		else
			return false;
	}
	
	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.systemxml.SystemXmlDao#delByCodeTemp(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean delByCodeTemp(String nodeName, String grade) throws DocumentException {
		Document doc = getDoc();
		List<?> list = doc.selectNodes("//" + nodeName);
		Iterator<?> iter = list.iterator();
		while (iter.hasNext()) {
			Element element = (Element) iter.next();
			Iterator<?> iterator = element.elementIterator("level");
			while (iterator.hasNext()) {
				Element e = (Element) iterator.next();
				Element et = e.element("grade");
				if (et.getText().equals(grade)) {
					e.getParent().remove(e);
					break;
				}
			}
		}
		if (saveXml(doc))
			return true;
		else
			return false;
	}

	/**
	 * 方法描述：保存xml文件
	 * @param doc
	 * @return
	 */
	private boolean saveXml(Document doc) {
		boolean rtn = false;
		org.dom4j.io.XMLWriter output;
		/* 格式化输出,类型IE浏览一样 */
		OutputFormat format = OutputFormat.createPrettyPrint();
		/* 指定XML编码 */
		format.setEncoding("utf-8");
		try { /* 将document中的内容写入文件中 */
			output = new org.dom4j.io.XMLWriter( 
                    new FileOutputStream(Constant.config_parameter_path), format); 

			output.write(doc);
			output.close();
			rtn = true;
		} catch (IOException e) {
			log.error(DataUtil.getStackTraceAsString(e));;
		}
		return rtn;
	}

	/**
	 * 方法描述：修改system.xml中一个节点的内容
	 * @param nodeName:父节点名称,code:节点的code,replaceName:替换后的内容
	 * @author 田玉江
	 * 创建日期：2011-6-18
	 * 
	 */
	@Override
	public boolean update(String nodeName, String code, String replaceName) {
		Document doc = getDoc();
		List<?> list = doc.selectNodes("//" + nodeName);
		Iterator<?> iter = list.iterator();
		while (iter.hasNext()) {
			Element element = (Element) iter.next();
			Iterator<?> iterator = element.elementIterator("level");
			if (!iterator.hasNext()) {
				Element e1 = element;//(Element) iter.next();
					e1.element(code).setText(replaceName);
					break;
			}
			while (iterator.hasNext()) {
				Element e = (Element) iterator.next();
				Element et = e.element("code");
				if (et.getText().equals(code)) {
					e.element("name").setText(replaceName);
					break;
				}
			}
			
			
		}
		
		if (saveXml(doc))
			return true;
		else
			return false;

	}
	
	@Override
	public boolean updateByCodeTemp(String nodeName, String code, InitCodeTemp codeTemp) {
		Document doc = getDoc();
		List<?> list = doc.selectNodes("//" + nodeName);
		Iterator<?> iter = list.iterator();
		while (iter.hasNext()) {
			Element element = (Element) iter.next();
			Iterator<?> iterator = element.elementIterator("level");
			if (!iterator.hasNext()) {
				Element e1 = element;//(Element) iter.next();
					e1.element("code").setText(codeTemp.getCode());
					e1.element("grade").setText(code);
					e1.element("name").setText(codeTemp.getName());
					e1.element("chose").setText(codeTemp.getChose());
					break;
			}
			while (iterator.hasNext()) {
				Element e = (Element) iterator.next();
				Element et = e.element("grade");
				if (et.getText().equals(code)) {
					e.element("code").setText(codeTemp.getCode());
					e.element("grade").setText(code);
					e.element("name").setText(codeTemp.getName());
					e.element("chose").setText(codeTemp.getChose());
					break;
				}
			}
			
			
		}
		
		if (saveXml(doc))
			return true;
		else
			return false;

	}
	
	/**
	 * 获得document
	 * @return
	 */
	private Document getDoc() {
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(new File(Constant.config_parameter_path));
			return doc;
		} catch (DocumentException e) {
			log.error(DataUtil.getStackTraceAsString(e));;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.systemxml.SystemXmlDao#isOpenSMS()
	 */
	@Override
	public boolean isOpenSMS() {
		String status = "";
    	Document doc = getDoc();		
		Node nodeysy = doc.selectSingleNode("//system/ggbm/sms");
		status = nodeysy.getText();
		if ("0".equals(status)) {
			return true;
		}
		return false;		
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.systemxml.SystemXmlDao#getCOM()
	 */
	@Override
	public String getCOM() {
		String COM = "";
    	Document doc = getDoc();
		Node nodeysy = doc.selectSingleNode("//system/ggbm/comnum");
		COM = nodeysy.getText();
		return COM;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.systemxml.SystemXmlDao#getModemCom()
	 */
	@Override
	public String getModemCom() {
		String modemcom = "";
    	Document doc = getDoc();
		Node nodeysy = doc.selectSingleNode("//system/ggbm/modemcom");
		modemcom = nodeysy.getText();
		return modemcom;
	}
	
	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.systemxml.SystemXmlDao#addCodeTemp(java.lang.String, com.hnzskj.persist.bean.init.InitCodeTemp)
	 */
	@Override
	public boolean addCodeTemp(String nodeName, InitCodeTemp codeTemp){
		Document doc = getDoc();
		List<?> list = doc.selectNodes("//" + nodeName);
		Iterator<?> iter = list.iterator();
		while (iter.hasNext()) {
			Element element = (Element) iter.next();
			Element levelElement = element.addElement("level");
			levelElement.addElement("name").setText(codeTemp.getName());
			levelElement.addElement("code").setText(codeTemp.getCode());
			levelElement.addElement("grade").setText(codeTemp.getGrade());
			levelElement.addElement("chose").setText(codeTemp.getChose());
		}

		if (saveXml(doc))
			return true;
		else
			return false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.systemxml.SystemXmlDao#updateScope()
	 */
	@Override
	public boolean updateScope(String nodeName, String code) {
		Document doc = getDoc();
		List<?> list = doc.selectNodes("//" + nodeName);
		Iterator<?> iter = list.iterator();
		while (iter.hasNext()) {
			Element element = (Element) iter.next();
			Iterator<?> iterator = element.elementIterator("level");
			while (iterator.hasNext()) {
				Element e = (Element) iterator.next();
				@SuppressWarnings("unused")
				Element et = e.element("code");
				e.element("code").setText(code);
			}
		}
		if (saveXml(doc))
			return true;
		else
			return false;
	}
}
