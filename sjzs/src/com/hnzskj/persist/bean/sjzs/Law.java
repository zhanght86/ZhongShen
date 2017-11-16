/*
 * @项目名称: lwsj_cj
 * @文件名称: Law.java
 * 
 * @日期: 2011-12-19
 * @版权: 2011 河南中审科技有限公司 
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.persist.bean.sjzs;

/**        
 * 
 * 类名称：Law    <br/>
 * 类描述：建设项目法规制度库<br/>
 * 创建人：马旭阳   <br/>
 * 创建时间：2011-12-8 下午03:26:00   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-12-8 下午03:26:00   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */

import java.sql.Date;

import com.hnzskj.persist.bean.attach.Attach;

public class Law {

	// 法规ID
	private String lawId;
	// 法规名称
	private String lawName;
	// 法规编号
	private String lawNumber;
	// 法规机构
	private String lawOrg;
	// 法规所属行业
	private String lawTrade;

	// 法规内容
	private String lawContent;
	// 法规日期
	private Date lawDate;
	// 法规等级
	private String lawGrade;

	// 法规分类
	private String lawCategory;

	/**
	 * 法规类别
	 */
	private String lawSort;

	/**
	 * 法规类别名称
	 */
	private String lawSortName;

	/**
	 * 附件ID
	 */
	private String attachId;

	private Attach attach;
	
	private Integer uploadFlag;
	
	private String note1;
	
	private String note2;
	
	public Law() {
	}
	
	public Law(String lawId, String lawName, String lawNumber, String lawOrg,
			String lawTrade, String lawContent, Date lawDate, String lawGrade,
			String lawCategory, String lawSort, String lawSortName,
			String attachId, Integer uploadFlag) {
		super();
		this.lawId = lawId;
		this.lawName = lawName;
		this.lawNumber = lawNumber;
		this.lawOrg = lawOrg;
		this.lawTrade = lawTrade;
		this.lawContent = lawContent;
		this.lawDate = lawDate;
		this.lawGrade = lawGrade;
		this.lawCategory = lawCategory;
		this.lawSort = lawSort;
		this.lawSortName = lawSortName;
		this.attachId = attachId;
		this.uploadFlag = uploadFlag;
	}

	/**
	 * @return the attach
	 */
	public Attach getAttach() {
		return attach;
	}

	/**
	 * @param attach the attach to set
	 */
	public void setAttach(Attach attach) {
		this.attach = attach;
	}

	/**
	 * @return the lawSortName
	 */
	public String getLawSortName() {
		return lawSortName;
	}

	/**
	 * @param lawSortName
	 *            the lawSortName to set
	 */
	public void setLawSortName(String lawSortName) {
		this.lawSortName = lawSortName;
	}

	/**
	 * @return the lawSort
	 */
	public String getLawSort() {
		return lawSort;
	}

	/**
	 * @param lawSort
	 *            the lawSort to set
	 */
	public void setLawSort(String lawSort) {
		this.lawSort = lawSort;
	}

	public String getLawCategory() {
		return lawCategory;
	}

	public void setLawCategory(String lawCategory) {
		this.lawCategory = lawCategory;
	}

	public String getLawId() {
		return lawId;
	}

	public void setLawId(String lawId) {
		this.lawId = lawId;
	}

	public String getLawName() {
		return lawName;
	}

	public void setLawName(String lawName) {
		this.lawName = lawName;
	}

	public String getLawNumber() {
		return lawNumber;
	}

	public void setLawNumber(String lawNumber) {
		this.lawNumber = lawNumber;
	}

	public String getLawOrg() {
		return lawOrg;
	}

	public void setLawOrg(String lawOrg) {
		this.lawOrg = lawOrg;
	}

	public String getLawTrade() {
		return lawTrade;
	}

	public void setLawTrade(String lawTrade) {
		this.lawTrade = lawTrade;
	}

	public String getLawContent() {
		return lawContent;
	}

	public void setLawContent(String lawContent) {
		this.lawContent = lawContent;
	}

	public Date getLawDate() {
		return lawDate;
	}

	public void setLawDate(Date lawDate) {
		this.lawDate = lawDate;
	}

	public String getLawGrade() {
		return lawGrade;
	}

	public void setLawGrade(String lawGrade) {
		this.lawGrade = lawGrade;
	}

	/**
	 * @return the attachId
	 */
	public String getAttachId() {
		return attachId;
	}

	/**
	 * @param attachId
	 *            the attachId to set
	 */
	public void setAttachId(String attachId) {
		this.attachId = attachId;
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

	public Integer getUploadFlag() {
		return uploadFlag;
	}

	public void setUploadFlag(Integer uploadFlag) {
		this.uploadFlag = uploadFlag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Law [lawCategory=" + lawCategory + ", lawContent=" + lawContent
				+ ", lawDate=" + lawDate + ", lawGrade=" + lawGrade
				+ ", lawId=" + lawId + ", lawName=" + lawName + ", lawNumber="
				+ lawNumber + ", lawOrg=" + lawOrg + ", lawTrade=" + lawTrade
				+ ", attachId=" + attachId + "]";
	}

}
