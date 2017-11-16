package com.hnzskj.persist.bean.fore;

import java.io.InputStream;

public class ClientUploadAttachDTO {
	private String attachId;
	private String attachName;
	private InputStream attachContentDoc;// 附件原格式流
	private InputStream attachContentSwf;// 转换后的流
	private String uploadDate;
	private String deleteFlag;
	private String note1;
	private String note2;
	private String note3;
	private String note4;
	private String note5;

	public String getAttachId() {
		return attachId;
	}

	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}

	public String getAttachName() {
		return attachName;
	}

	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}

	/**
	 * @return the attachContentDoc
	 */
	public InputStream getAttachContentDoc() {
		return attachContentDoc;
	}

	/**
	 * @param attachContentDoc
	 *            the attachContentDoc to set
	 */
	public void setAttachContentDoc(InputStream attachContentDoc) {
		this.attachContentDoc = attachContentDoc;
	}

	/**
	 * @return the attachContentSwf
	 */
	public InputStream getAttachContentSwf() {
		return attachContentSwf;
	}

	/**
	 * @param attachContentSwf
	 *            the attachContentSwf to set
	 */
	public void setAttachContentSwf(InputStream attachContentSwf) {
		this.attachContentSwf = attachContentSwf;
	}

	public String getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}

	/**
	 * @return the deleteFlag
	 */
	public String getDeleteFlag() {
		return deleteFlag;
	}

	/**
	 * @param deleteFlag
	 *            the deleteFlag to set
	 */
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
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
		return "ClientUploadAttachDTO [attachid=" + this.attachId
				+ ", attachName=" + this.attachName + 
				 ", deleteFlag=" + this.deleteFlag
				+ ", uploadDate=" + this.uploadDate + ", note1=" + this.note1
				+ ", note2=" + this.note2 + ", note3=" + note3 + ", note4="
				+ note4 + ", note5=" + note5 + "]";
	}
}
