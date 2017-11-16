package com.hnzskj.persist.bean.fore;

import java.sql.Date;

public class ClientUploadRelationDTO {
	/**
	 * 主键ID
	 */
	private String id;
	/**
	 * 用户ID
	 */
	private String clientId;
	/**
	 * 用户上传的记录ID
	 */
	private String infoId;
	/**
	 * 上传时间
	 */
	private Date uploadDate;
	/**
	 * 备用
	 */
	private String note1;

	private String note2;

	private String note3;

	private String note4;

	private String note5;

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
	 * @return the uploadDate
	 */
	public Date getUploadDate() {
		return uploadDate;
	}

	/**
	 * @param uploadDate
	 *            the uploadDate to set
	 */
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
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

	@Override
	public String toString() {
		return "ClientUploadRelationDTO [id=" + this.id + ", clientID="
				+ this.clientId + ", infoID=" + this.infoId + ", uploadDate="
				+ this.uploadDate + ", note1=" + this.note1 + ", note2="
				+ this.note2 + ", note3=" + note3 + ", note4=" + note4
				+ ", note5=" + note5 + "]";
	}
}
