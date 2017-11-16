package com.hnzskj.persist.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.hnzskj.common.Constant;

public class PropertiesUtil {
	private static final Logger LOGGER = Logger.getLogger(PropertiesUtil.class);

	public Properties prop = new Properties();

	public PropertiesUtil(String pathString) {
		String pathTemp = Constant.webRoot + pathString;// "WEB-INF\\config\\moduleNames.properties";
		try {
			InputStream is = new FileInputStream(pathTemp);
			prop.load(is);

		} catch (Exception e) {
			System.err.print("模块名称配置文件读取错误！");
			System.err.println("moduleNames.properties == " + pathTemp);
			// e.printStackTrace();
			LOGGER.error("模块名称配置文件读取错误！");
		}
	}
}
