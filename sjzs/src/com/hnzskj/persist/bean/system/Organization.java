/*
 * @项目名称: htglxt
 * @文件名称: Organization.java
 * @日期: 2011-5-30
 * @版权: 2011 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.persist.bean.system;


/**        
 * 
 * 类名称：Organization     <br/>
 * 类描述：<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-5-30 上午08:38:55   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-5-30 上午08:38:55   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */
public class Organization {
	
	/**机构guid*/
	private String orgId;
	
	
	/**机构名称*/	
	private String orgName;
	

	/**上级机构*/
	private String orgParent;
	
	/**
	 * 上级机构名称
	 */
	private String orgParentName;
	
	/**
	 * 机构排序
	 */
	private String orgOrder;
	
	/**
	 * 机构类型
	 */
	private Integer orgType = Integer.valueOf("0");
	
	/**
	 * 电子
	 */
	private String signetFileName;
	
	/**
	 * 备用字段1
	 */
	private String note1;
	
	/**
	 * 备用字段1
	 */
	private String note2;
	
	/**当前部门子部门的个数-部门授权模块使用*/
	private Integer count;
	
	public Organization() {}
	
	public Organization(String orgId, String orgName) {
		this.orgId = orgId;
		this.orgName = orgName;
	}
	
	public Organization(String orgId, String orgName, String orgParent, Integer orgType) {
		this.orgId = orgId;
		this.orgName = orgName;
		this.orgParent = orgParent;
		this.orgType = orgType;
	}


	/**
	 * @return the orgId
	 */
	public String getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * @param orgName the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * @return the orgParent
	 */
	public String getOrgParent() {
		return orgParent;
	}

	/**
	 * @param orgParent the orgParent to set
	 */
	public void setOrgParent(String orgParent) {
		this.orgParent = orgParent;
	}

	/**
	 * @return the orgParentName
	 */
	public String getOrgParentName() {
		return orgParentName;
	}

	/**
	 * @param orgParentName the orgParentName to set
	 */
	public void setOrgParentName(String orgParentName) {
		this.orgParentName = orgParentName;
	}

	/**
	 * @return the orgOrder
	 */
	public String getOrgOrder() {
		return orgOrder;
	}

	/**
	 * @param orgOrder the orgOrder to set
	 */
	public void setOrgOrder(String orgOrder) {
		this.orgOrder = orgOrder;
	}

	/**
	 * @return the orgType
	 */
	public Integer getOrgType() {
		return orgType;
	}

	/**
	 * @param orgType the orgType to set
	 */
	public void setOrgType(Integer orgType) {
		this.orgType = orgType;
	}

	/**
	 * @return the signetFileName
	 */
	public String getSignetFileName() {
		return signetFileName;
	}

	/**
	 * @param signetFileName the signetFileName to set
	 */
	public void setSignetFileName(String signetFileName) {
		this.signetFileName = signetFileName;
	}

	/**
	 * @return the note1
	 */
	public String getNote1() {
		return note1;
	}

	/**
	 * @param note1 the note1 to set
	 */
	public void setNote1(String note1) {
		this.note1 = note1;
	}

	/**
	 * @return the note2
	 */
	public String getNote2() {
		return note2;
	}

	/**
	 * @param note2 the note2 to set
	 */
	public void setNote2(String note2) {
		this.note2 = note2;
	}

	/**
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Organization [note1=" + note1 + ", note2=" + note2 + ", orgId="
				+ orgId + ", orgName=" + orgName + ", orgOrder=" + orgOrder
				+ ", orgParent=" + orgParent + ", orgParentName="
				+ orgParentName + ", orgType=" + orgType + ", signetFileName="
				+ signetFileName + "]";
	}
}
