package com.hnzskj.persist.bean.init;

/**
 * 系统配置文件中参数存放类
 * 
 * 状态：创建 日期：2010-6-8
 * 
 * @version v1.0
 * @author 常利召
 * 
 */
public final class Configure {
	/**
	 * 存储系统参数类
	 */
	private static Configure config = new Configure();
	/**
	 * 系统参数
	 */
	private ConfigBean cf = new ConfigBean();

	private Configure() {

	}

	public static Configure getInstance() {
		if (null != config) {
			return config;
		} else {
			config = new Configure();
			return config;
		}
	}

	public void setBean(ConfigBean cf) {
		this.cf = cf;
	}

	public ConfigBean getBean() {
		return this.cf;
	}
}
