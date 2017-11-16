package com.hnzskj.persist.bean.init;


public class InitGgbmBean {

	
//	<name>sqlserver</name><!-- 获得使用哪种数据库  sqlserver或mysql--> 
//	<htbh>豫A</htbh><!-- 合同编号首文字-->  
//	<scwj>D:/files/</scwj><!--上传文件根路径  -->
//	<sfxzlc>0</sfxzlc><!-- 是否选择流程   默认0为选择 1为不选择 -->
//	<sms>0</sms><!-- 是否启用短信功能   默认0， 0为启用 1为不启用 -->
	private String name;
	private String htbh;
	private String scwj;
	private String sfxzlc;
	private String sms;
	/**
	 * 项目编号位数
	 */
	private String xmbh;
	
	/**
	 * 项目编号位数
	 * @return xmbh
	 */
	public String getXmbh() {
		return xmbh;
	}
	
	/**
	 * 项目编号位数
	 * @param xmbh the xmbh to set
	 */
	public void setXmbh(String xmbh) {
		this.xmbh = xmbh;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHtbh() {
		return htbh;
	}
	public void setHtbh(String htbh) {
		this.htbh = htbh;
	}
	public String getScwj() {
		return scwj;
	}
	public void setScwj(String scwj) {
		this.scwj = scwj;
	}
	public String getSfxzlc() {
		return sfxzlc;
	}
	public void setSfxzlc(String sfxzlc) {
		this.sfxzlc = sfxzlc;
	}

	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}
}
