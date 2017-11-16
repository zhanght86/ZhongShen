/*
 * @项目名称: OA
 * @文件名称: SoftWareFlow.java
 * @日期: 2012-7-16
 * @版权: 2012 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.persist.bean.wflow;

import java.util.ArrayList;
import java.util.List;

  /**        
 * 
 * 类名称：SoftWareFlow
 * 类描述：<br/>
 * 创建人：郑辉  <br/>
 * 创建时间：2012-7-16 下午02:19:10 
 * 修改人：Administrator
 * 修改时间：
 * 修改备注：   
 * @version   1.0     
 */

public class SoftWareFlow {
	private String sf_id;
	private String sf_date;
	private String sf_person;
	private String sf_use;
	private Integer instance_no;
	private String tep;
	private String sf_OrgId;
	private List<SoftWareItems> items=new ArrayList<SoftWareItems>();
	/**
	 * 
	 */
	public SoftWareFlow() {

	}
	/**
	 * @return the sf_id
	 */
	public String getSf_id() {
		return sf_id;
	}
	/**
	 * @param sfId the sf_id to set
	 */
	public void setSf_id(String sfId) {
		sf_id = sfId;
	}
	/**
	 * @return the sf_person
	 */
	public String getSf_person() {
		return sf_person;
	}
	/**
	 * @param sfPerson the sf_person to set
	 */
	public void setSf_person(String sfPerson) {
		sf_person = sfPerson;
	}
	/**
	 * @return the sf_date
	 */
	public String getSf_date() {
		return sf_date;
	}
	/**
	 * @param sfDate the sf_date to set
	 */
	public void setSf_date(String sfDate) {
		sf_date = sfDate;
	}
	/**
	 * @return the sf_use
	 */
	public String getSf_use() {
		return sf_use;
	}
	/**
	 * @param sfUse the sf_use to set
	 */
	public void setSf_use(String sfUse) {
		sf_use = sfUse;
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
	 * @return the tep
	 */
	public String getTep() {
		return tep;
	}
	/**
	 * @param tep the tep to set
	 */
	public void setTep(String tep) {
		this.tep = tep;
	}
	/**
	 * @return the items
	 */
	public List<SoftWareItems> getItems() {
		return items;
	}
	/**
	 * @param items the items to set
	 */
	public void setItems(List<SoftWareItems> items) {
		this.items = items;
	}
	/**
	 * @return the sf_OrgId
	 */
	public String getSf_OrgId() {
		return sf_OrgId;
	}
	/**
	 * @param sfOrgId the sf_OrgId to set
	 */
	public void setSf_OrgId(String sfOrgId) {
		sf_OrgId = sfOrgId;
	}
}
