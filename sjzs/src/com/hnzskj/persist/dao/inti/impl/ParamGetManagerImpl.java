package com.hnzskj.persist.dao.inti.impl;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;

import com.hnzskj.common.DataUtil;
import com.hnzskj.common.init.XmlReader;
import com.hnzskj.persist.bean.init.ConfigBean;
import com.hnzskj.persist.bean.init.Configure;
import com.hnzskj.persist.dao.inti.ParamGetManager;


public class ParamGetManagerImpl implements ParamGetManager {
	private static Logger log = Logger.getLogger(ParamGetManagerImpl.class);
	/**
	 * 获取系统参数
	 * 
	 * @param strXmlPath
	 *            系统配置文件存放路径
	 * @return ConfigBean类的对象
	 * @throws DocumentException 
	 */
	public ConfigBean getParams(String strXmlPath) {
		
		XmlReader xr = new XmlReader(strXmlPath);
		ConfigBean cb = null;
		try {
			cb = new ConfigBean();
			cb = xr.getSystemParams();
		} catch (DocumentException e) {
			log.error(DataUtil.getStackTraceAsString(e));;
		}
		return cb;
	}

	/**
	 * 把获取到系统参数存放到服务器内存
	 * 
	 * @param strXmlPath
	 *            系统配置文件存放路径
	 * @return
	 */
	public void saveParams(String strXmlPath) {
		ConfigBean cb = getParams(strXmlPath);
		Configure config = Configure.getInstance();
		config.setBean(cb);
	}

}
