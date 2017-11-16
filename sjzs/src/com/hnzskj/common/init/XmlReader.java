package com.hnzskj.common.init;

import java.io.File;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.hnzskj.persist.bean.init.ConfigBean;
import com.hnzskj.persist.bean.init.DefaultPassword;
import com.hnzskj.persist.bean.init.InitAccountBean;
import com.hnzskj.persist.bean.init.InitET99Bean;

/**
 * 读取xml文档类
 * 
 * 状态：创建 日期：2011-2-25
 * 
 * @version v1.0
 * @author 常利召
 * 
 */
public class XmlReader {
	/**
	 * xml文档的路径
	 */
	String strXMLPath = null;

	/**
	 * 构造函数，设置strXMLPath属性的值。
	 * 
	 * @param strXMLPath
	 */

	public XmlReader(String strXMLPath) {
		this.strXMLPath = strXMLPath;
	}

	/**
	 * 读取system.xml里的配置信息
	 * 
	 * @return ConfigBean类的对象
	 * @throws DocumentException
	 */
	public ConfigBean getSystemParams() throws DocumentException {
		ConfigBean cb = new ConfigBean();
		File file = new File(strXMLPath);
		SAXReader reader = new SAXReader();
		Document doc = reader.read(file);
		// 初始化信息
		getInitAccount(doc, cb);
		//用户的初始密码
		getDefaultPassword(doc, cb);
		//加密
		getPassword(doc, cb);
		return cb;
	}

	/**
	 * 方法描述：获得加密信息
	 * 创建人：丁艳伟   <br/>
	 * 创建时间：2011年8月9日15:56:25
	 * @param doc
	 * @param cb
	 * @version   1.0
	 */
	private void getPassword(Document doc, ConfigBean cb) {
		InitET99Bean addPassWord = new InitET99Bean(); 
		Node pid = doc.selectSingleNode("//system/ET99/pid");
		Node word = doc.selectSingleNode("//system/ET99/userpin");
		addPassWord.setPid(pid.getText());
		addPassWord.setUserpin(word.getText());
		cb.setAddPassWordBean(addPassWord);
	}
	/**
	 * 获取初始化页面登录账号信息
	 * 
	 * @param doc
	 *            xml文档对应Document类的对象
	 * @param cb
	 *            ConfigBean对象
	 */
	private void getInitAccount(Document doc, ConfigBean cb) {
		InitAccountBean initAccount = new InitAccountBean();
		Node nodeUser = doc.selectSingleNode("//system/init/user");
		initAccount.setStrInitUser(nodeUser.getText());
		Node nodePass = doc.selectSingleNode("//system/init/password");
		initAccount.setStrInitPass(nodePass.getText());
		cb.setInitAccount(initAccount);
	}
	
	/**
	 * 
	 * 方法描述：获得默认的密码<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-7-17 下午02:57:00<br/>         
	 * @param doc
	 * @param cb
	 * @version   1.0
	 */
	private void getDefaultPassword(Document doc, ConfigBean cb) {
		DefaultPassword defaultPassword = new DefaultPassword();
		Node password = doc.selectSingleNode("//system/default/password");
		defaultPassword.setPassword(password.getText());
		cb.setDefaultPassword(defaultPassword);
	}

	
}
