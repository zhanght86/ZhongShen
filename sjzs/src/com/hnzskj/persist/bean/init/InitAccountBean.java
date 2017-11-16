package com.hnzskj.persist.bean.init;

/**
 * 系统配置文件中初始化账号相关参数类
 * 
 * 状态：创建 日期：2011-4-22
 * 
 * @version v1.0
 * @author 常利召
 * 
 */
public class InitAccountBean {
	/**
	 * 初始化页面登录账户
	 */
	private String strInitUser = null;
	/**
	 * 初始化页面登录密码
	 */
	private String strInitPass = null;

	public String getStrInitUser() {
		
		return strInitUser;
	}

	public void setStrInitUser(String strInitUser) {
		this.strInitUser = strInitUser;
	}

	public String getStrInitPass() {
		return strInitPass;
	}

	public void setStrInitPass(String strInitPass) {
		this.strInitPass = strInitPass;
	}

}
