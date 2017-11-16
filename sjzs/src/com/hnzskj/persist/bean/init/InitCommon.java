package com.hnzskj.persist.bean.init;
/**
 * 系统配置文件中获得公共类别类
 * 
 * 状态：创建 日期：2011-4-22
 * 
 * @version v1.0
 * @author 常利召
 * 
 */
public class InitCommon {
	/**
	 * 数据库类别
	 */
	private String strInitDB = null;
	/**
	 * 合同编号
	 */
	private String contractcode;
	/**
	 * 上传文件根路径
	 */
	private String filePath;
	/**
	 * 是否选择流程
	 */
	private String sfxzlc;
	/**
	 * 是否选择流程
	 */
	public String getSfxzlc() {
		return sfxzlc;
	}
	/**
	 *是否选择流程
	 */
	public void setSfxzlc(String sfxzlc) {
		this.sfxzlc = sfxzlc;
	}
	/**
	 * 数据库类别
	 */
	public String getStrInitDB() {
		return strInitDB;
	}
	/**
	 * 数据库类别
	 */
	public void setStrInitDB(String strInitDB) {
		this.strInitDB = strInitDB;
	}
	/**
	 * 合同编号
	 */
	public String getContractcode() {
		return contractcode;
	}
	/**
	 * 合同编号
	 */
	public void setContractcode(String contractcode) {
		this.contractcode = contractcode;
	}
	/**
	 * 上传文件根路径
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * 上传文件根路径
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}
