/*
 * @项目名称: OA
 * @文件名称: RepairFlow.java
 * @日期: 2012-7-23
 * @版权: 2012 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.persist.bean.wflow;

  /**        
 * 
 * 类名称：RepairFlow
 * 类描述：<br/>
 * 创建人：郑辉  <br/>
 * 创建时间：2012-7-23 下午03:19:16 
 * 修改人：Administrator
 * 修改时间：
 * 修改备注：   
 * @version   1.0     
 */

public class RepairFlow {
	  private String rp_id ;	
	  private String rp_orgId ;		//保修部门
	  private String rp_person ;	//保修人
	  private String rp_date ;		//保修时间
	  private String rp_desc ;		//保修原因
	  private String rp_items ;		//保修物品
	  private Integer rp_type=0;
	  private Integer instance_no ;
	  
	/**
	 * 
	 */
	public RepairFlow() {

	}
	/**
	 * @return the rp_id
	 */
	public String getRp_id() {
		return rp_id;
	}
	/**
	 * @param rpId the rp_id to set
	 */
	public void setRp_id(String rpId) {
		rp_id = rpId;
	}
	/**
	 * @return the rp_orgId
	 */
	public String getRp_orgId() {
		return rp_orgId;
	}
	/**
	 * @param rpOrgId the rp_orgId to set
	 */
	public void setRp_orgId(String rpOrgId) {
		rp_orgId = rpOrgId;
	}
	/**
	 * @return the rp_person
	 */
	public String getRp_person() {
		return rp_person;
	}
	/**
	 * @param rpPerson the rp_person to set
	 */
	public void setRp_person(String rpPerson) {
		rp_person = rpPerson;
	}
	/**
	 * @return the rp_date
	 */
	public String getRp_date() {
		return rp_date;
	}
	/**
	 * @param rpDate the rp_date to set
	 */
	public void setRp_date(String rpDate) {
		rp_date = rpDate;
	}
	/**
	 * @return the rp_desc
	 */
	public String getRp_desc() {
		return rp_desc;
	}
	/**
	 * @param rpDesc the rp_desc to set
	 */
	public void setRp_desc(String rpDesc) {
		rp_desc = rpDesc;
	}
	/**
	 * @return the rp_items
	 */
	public String getRp_items() {
		return rp_items;
	}
	/**
	 * @param rpItems the rp_items to set
	 */
	public void setRp_items(String rpItems) {
		rp_items = rpItems;
	}
	/**
	 * @return the instance_no
	 */
	public Integer getInstance_no() {
		return instance_no;
	}
	/**
	 * @param instanceNo the instance_no to set
	 */
	public void setInstance_no(Integer instanceNo) {
		instance_no = instanceNo;
	}
	/**
	 * @return the rp_type
	 */
	public Integer getRp_type() {
		return rp_type;
	}
	/**
	 * @param rpType the rp_type to set
	 */
	public void setRp_type(Integer rpType) {
		rp_type = rpType;
	}
}
