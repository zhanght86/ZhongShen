package com.hnzskj.persist.bean.sjzs;

/*
 * @(#)DataDicDTO.java	2013-3-25
 * @项目名称: sjzs
 * @版权: 2013 河南中审科技有限公司 
 * @开发公司或单位：河南中审科技有限公司开发部
 */

/**
 * 类名称：DataDic<br/>.
 * 类描述：审计事项实体<br/>
 * 创建人：wenxuanzhen   <br/>
 * 创建时间：2013-3-25 下午01:39:59<br/>
 * @version   1.0
 */
public class DataDicDTO {
	
	/**审计事项Id*/
	private String dicId;
	
	/**审计事项名称*/
	private String dicName;;
	
	/**审计事项父级*/
    private String dicParentId;
    
    /**审计事项父级名称*/
    private String dicParentName;
    
    /**审计事项说明*/
    private String dicMemo;
    
    /**审计事项排序*/
    private Integer dicOrder;
    
    /**审计事项业务分类*/
    private String industry;
    
    /**删除标记*/
    private Integer isDel;
    
    private String note1;
    
    private String note2;
    
    private Integer deleteflag;
    
    private String updateDate;

	/**
	 * @return the dicId
	 */
	public String getDicId() {
		return dicId;
	}

	/**
	 * @param dicId the dicId to set
	 */
	public void setDicId(String dicId) {
		this.dicId = dicId;
	}

	/**
	 * @return the dicName
	 */
	public String getDicName() {
		return dicName;
	}

	/**
	 * @param dicName the dicName to set
	 */
	public void setDicName(String dicName) {
		this.dicName = dicName;
	}

	/**
	 * @return the dicParentId
	 */
	public String getDicParentId() {
		return dicParentId;
	}

	/**
	 * @param dicParentId the dicParentId to set
	 */
	public void setDicParentId(String dicParentId) {
		this.dicParentId = dicParentId;
	}

	/**
	 * @return the dicMemo
	 */
	public String getDicMemo() {
		return dicMemo;
	}

	/**
	 * @param dicMemo the dicMemo to set
	 */
	public void setDicMemo(String dicMemo) {
		this.dicMemo = dicMemo;
	}

	/**
	 * @return the dicOrder
	 */
	public Integer getDicOrder() {
		return dicOrder;
	}

	/**
	 * @param dicOrder the dicOrder to set
	 */
	public void setDicOrder(Integer dicOrder) {
		this.dicOrder = dicOrder;
	}

	/**
	 * @return the isDel
	 */
	public Integer getIsDel() {
		return isDel;
	}

	/**
	 * @param isDel the isDel to set
	 */
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	/**
	 * @return the dicParentName
	 */
	public String getDicParentName() {
		return dicParentName;
	}

	/**
	 * @param dicParentName the dicParentName to set
	 */
	public void setDicParentName(String dicParentName) {
		this.dicParentName = dicParentName;
	}

	
	/**
	 * @return the industry
	 */
	public String getIndustry() {
		return industry;
	}

	/**
	 * @param industry the industry to set
	 */
	public void setIndustry(String industry) {
		this.industry = industry;
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
	
	
	
	public Integer getDeleteflag() {
		return deleteflag;
	}

	public void setDeleteflag(Integer deleteflag) {
		this.deleteflag = deleteflag;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "DataDicDTO [deleteflag=" + deleteflag + ", dicId=" + dicId
				+ ", dicMemo=" + dicMemo + ", dicName=" + dicName
				+ ", dicOrder=" + dicOrder + ", dicParentId=" + dicParentId
				+ ", dicParentName=" + dicParentName + ", industry=" + industry
				+ ", isDel=" + isDel + ", note1=" + note1 + ", note2=" + note2
				+ ", updateDate=" + updateDate + "]";
	}

	
}
