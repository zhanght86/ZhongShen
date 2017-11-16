/**
*com.hnzskj.persist.bean.info
 * 2012-10-16
*auditLaw.java
*tzsj
*毛俊玲
 */
package com.hnzskj.persist.bean.sjzs;

/**
 * @author 毛俊玲
 *2012-10-16下午01:57:32
 *审计法律法规
 */
public class DxyjLaw {
	private String id ;
	private String caption;  //标题
	private String isShow;   //是否显示0否 1是
	private String isRead;	//是否只读 0否  1是
	private Integer nodeClass;     //节点等级
	private Integer nodeType;   //节点类型 0目录 1信息
	private String parentID ;   //对应的父节点
	private String department;  //单位
	private String lawNo ;      //法规编号
	private String tiao;    //条
	private String kuan;   //款
	private String lawContent;  //法规内容
    private String writeDate;
    private String type;
    private String note1;
    private String note2;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	public String getIsRead() {
		return isRead;
	}
	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
	public Integer getNodeClass() {
		return nodeClass;
	}
	public void setNodeClass(Integer nodeClass) {
		this.nodeClass = nodeClass;
	}
	public Integer getNodeType() {
		return nodeType;
	}
	public void setNodeType(Integer nodeType) {
		this.nodeType = nodeType;
	}
	public String getParentID() {
		return parentID;
	}
	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getLawNo() {
		return lawNo;
	}
	public void setLawNo(String lawNo) {
		this.lawNo = lawNo;
	}
	public String getTiao() {
		return tiao;
	}
	public void setTiao(String tiao) {
		this.tiao = tiao;
	}
	public String getKuan() {
		return kuan;
	}
	public void setKuan(String kuan) {
		this.kuan = kuan;
	}
	public String getLawContent() {
		return lawContent;
	}
	public void setLawContent(String lawContent) {
		this.lawContent = lawContent;
	}
	/**
	 * @return the writeDate
	 */
	public String getWriteDate() {
		return writeDate;
	}
	/**
	 * @param writeDate the writeDate to set
	 */
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
	
	
}
