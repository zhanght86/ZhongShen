/*
 * @项目名称: OA
 * @文件名称: LatentCuster.java
 * @日期: 2012-8-9 下午05:22:03  
 * @版权: 2012 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.persist.bean.system;

/**    
 * 项目名称：OA   <br/>
 * 类名称：LatentCuster.java   <br/>
 * 类描述： 潜在客户级别数据字典实体  <br/>
 * 创建人：王亲臣   <br/>
 * 创建时间：2012-8-9 下午05:22:03   <br/>
 * 修改人：王亲臣   <br/>
 * 修改时间：2012-8-9 下午05:22:03   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class LatentCuster {
	
	/**
	 * 唯一标识
	 */
	private String lcId;
	/**
	 * 显示内容
	 */
	private String lcText;
	/**
	 * 排序号
	 */
	private int lcOrder;
	/**
	 * 创建时间
	 */
	private String lcDateTime;
	/**
	 * 备注信息
	 */
	private String note;
	
	
	/**
	 * 唯一标识
	 * @return the lcId
	 */
	public String getLcId() {
		return lcId;
	}
	/**
	 * 唯一标识
	 * @param lcId the lcId to set
	 */
	public void setLcId(String lcId) {
		this.lcId = lcId;
	}
	/**
	 * 显示内容
	 * @return the lcText
	 */
	public String getLcText() {
		return lcText;
	}
	/**
	 * 显示内容
	 * @param lcText the lcText to set
	 */
	public void setLcText(String lcText) {
		this.lcText = lcText;
	}
	/**
	 * 排序号
	 * @return the lcOrder
	 */
	public int getLcOrder() {
		return lcOrder;
	}
	/**
	 * 排序号
	 * @param lcOrder the lcOrder to set
	 */
	public void setLcOrder(int lcOrder) {
		this.lcOrder = lcOrder;
	}
	/**
	 * 创建时间
	 * @return the lcDateTime
	 */
	public String getLcDateTime() {
		return lcDateTime;
	}
	/**
	 * 创建时间
	 * @param lcDateTime the lcDateTime to set
	 */
	public void setLcDateTime(String lcDateTime) {
		this.lcDateTime = lcDateTime;
	}
	/**
	 * 备注信息
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
	/**
	 * 备注信息
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
}
