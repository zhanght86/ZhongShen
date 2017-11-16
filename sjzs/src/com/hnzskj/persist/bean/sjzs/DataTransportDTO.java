package com.hnzskj.persist.bean.sjzs;

import java.util.Date;

public class DataTransportDTO {

	/**
	 * 主键ID
	 */
	private String id;
	/**
	 * 迁移记录ID
	 */
	private String infoId;
	/**
	 * 迁移记录对应的附件ID
	 */
	private String attachId;
	/**
	 *用户ID
	 */
	private String clientId;
	/**
	 * 用户
	 */
	private String clientName;
	/**
	 * 迁移人ID
	 */
	private String transUserId;
	/**
	 * 迁移人
	 */
	private String transUserName;
	/**
	 * 迁移类型 审计法规 审计方法 实施方案
	 */
	private String type;
	/**
	 * 迁移状态 0：迁移 1 回迁
	 */
	private int flag = 0;
	/**
	 * 迁移日期
	 */
	private Date transDate = new Date();
	/**
	 * 备用
	 */
	private String note1;
	/**
	 * 备用
	 */
	private String note2;
	/**
	 * 备用
	 */
	private String note3;
	/**
	 * 备用
	 */
	private String note4;
	/**
	 * 备用
	 */
	private String note5;
	
	public DataTransportDTO() {
	}
	
	public DataTransportDTO(String infoId, String attachId,
			String clientId, String clientName, String transUserId,
			String transUserName, String type, int flag) {
		super();
		this.infoId = infoId;
		this.attachId = attachId;
		this.clientId = clientId;
		this.clientName = clientName;
		this.transUserId = transUserId;
		this.transUserName = transUserName;
		this.type = type;
		this.flag = flag;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the infoId
	 */
	public String getInfoId() {
		return infoId;
	}

	/**
	 * @param infoId
	 *            the infoId to set
	 */
	public void setInfoId(String infoId) {
		this.infoId = infoId;
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
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * @param clientId
	 *            the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the clientName
	 */
	public String getClientName() {
		return clientName;
	}

	/**
	 * @param clientName
	 *            the clientName to set
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	/**
	 * @return the transUserId
	 */
	public String getTransUserId() {
		return transUserId;
	}

	/**
	 * @param transUserId
	 *            the transUserId to set
	 */
	public void setTransUserId(String transUserId) {
		this.transUserId = transUserId;
	}

	/**
	 * @return the transUserName
	 */
	public String getTransUserName() {
		return transUserName;
	}

	/**
	 * @param transUserName
	 *            the transUserName to set
	 */
	public void setTransUserName(String transUserName) {
		this.transUserName = transUserName;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the flag
	 */
	public int getFlag() {
		return flag;
	}

	/**
	 * @param flag
	 *            the flag to set
	 */
	public void setFlag(int flag) {
		this.flag = flag;
	}

	/**
	 * @return the transDate
	 */
	public Date getTransDate() {
		return transDate;
	}

	/**
	 * @param transDate
	 *            the transDate to set
	 */
	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	/**
	 * @return the note1
	 */
	public String getNote1() {
		return note1;
	}

	/**
	 * @param note1
	 *            the note1 to set
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
	 * @param note2
	 *            the note2 to set
	 */
	public void setNote2(String note2) {
		this.note2 = note2;
	}

	/**
	 * @return the note3
	 */
	public String getNote3() {
		return note3;
	}

	/**
	 * @param note3
	 *            the note3 to set
	 */
	public void setNote3(String note3) {
		this.note3 = note3;
	}

	/**
	 * @return the note4
	 */
	public String getNote4() {
		return note4;
	}

	/**
	 * @param note4
	 *            the note4 to set
	 */
	public void setNote4(String note4) {
		this.note4 = note4;
	}

	/**
	 * @return the note5
	 */
	public String getNote5() {
		return note5;
	}

	/**
	 * @param note5
	 *            the note5 to set
	 */
	public void setNote5(String note5) {
		this.note5 = note5;
	}

	@Override
	public String toString() {
		return "DataTransportDTO [id=" + id + ", infoID=" + infoId
				+ ", attachID=" + attachId + ", clientId=" + clientId
				+ ",clientName" + clientName + ",transUserId" + transUserId
				+ ",transUserName" + transUserName + ",type" + type + ",flag"
				+ flag + ", note1=" + note1 + ", note2=" + note2 + ", note3="
				+ note3 + ", note4=" + note4 + ", note5=" + note5
				+ ", toString()=" + super.toString() + "]";
	}

}
