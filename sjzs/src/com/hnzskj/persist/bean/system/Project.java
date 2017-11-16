/*
 * @项目名称: OA
 * @文件名称: Project.java
 * @日期: 2012-8-9 上午10:32:01  
 * @版权: 2012 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.persist.bean.system;

/**    
 * 项目名称：OA   <br/>
 * 类名称：Project.java   <br/>
 * 类描述：公司项目系统维护   <br/>
 * 创建人：王亲臣   <br/>
 * 创建时间：2012-8-9 上午10:32:01   <br/>
 * 修改人：王亲臣   <br/>
 * 修改时间：2012-8-9 上午10:32:01   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class Project {
	/**
	 * 项目UUID
	 */
	private String proId;
	/**
	 * 项目编号
	 */
	private String proNo;
	/**
	 * 项目名称
	 */
	private String proName;
	/**
	 * 项目排序号
	 */
	private int proOrder;
	/**
	 * 项目录入时间
	 */
	private String proDateTime;
	
	/**
	 * 项目备注
	 */
	private String note;
	
	/**
	 * 项目备注
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
	/**
	 * 项目备注
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * 项目UUID
	 * @return the proId
	 */
	public String getProId() {
		return proId;
	}
	/**
	 * 项目UUID
	 * @param proId the proId to set
	 */
	public void setProId(String proId) {
		this.proId = proId;
	}
	/**
	 * 项目编号
	 * @return the proNo
	 */
	public String getProNo() {
		return proNo;
	}
	/**
	 * 项目编号
	 * @param proNo the proNo to set
	 */
	public void setProNo(String proNo) {
		this.proNo = proNo;
	}
	/**
	 * 项目名称
	 * @return the proName
	 */
	public String getProName() {
		return proName;
	}
	/**
	 * 项目名称
	 * @param proName the proName to set
	 */
	public void setProName(String proName) {
		this.proName = proName;
	}
	/**
	 * 项目排序号
	 * @return the proOrder
	 */
	public int getProOrder() {
		return proOrder;
	}
	/**
	 * 项目排序号
	 * @param proOrder the proOrder to set
	 */
	public void setProOrder(int proOrder) {
		this.proOrder = proOrder;
	}
	/**
	 * 项目录入时间
	 * @return the proDateTime
	 */
	public String getProDateTime() {
		return proDateTime;
	}
	/**
	 * 项目录入时间
	 * @param proDateTime the proDateTime to set
	 */
	public void setProDateTime(String proDateTime) {
		this.proDateTime = proDateTime;
	}
}
