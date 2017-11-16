package com.hnzskj.persist.dao.inti;


import com.hnzskj.persist.bean.init.ConfigBean;


/**
 * 系统参数获取模块业务处理接口
 * 
 * 状态：创建 日期：2010-6-8
 * @version v1.0
 * @author 常利召
 *
 */

public interface ParamGetManager {

	/**
	 * 获取系统参数
	 * 
	 * @param strXmlPath
	 *            系统配置文件存放路径
	 * @return ConfigBean类的对象
	 */
	public ConfigBean getParams(String strXmlPath);

	/**
	 * 把获取到系统参数存放到服务器内存
	 * 
	 * @param strXmlPath
	 *            系统配置文件存放路径
	 * @return
	 */
	public void saveParams(String strXmlPath);

}
