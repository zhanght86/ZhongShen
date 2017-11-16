package com.hnzskj.persist.bean.attach;

import java.io.InputStream;

public class Attach {
	private String attachId;
	private String attachName;
	private String attachType;
	private InputStream attachContentDoc;//附件原格式流
	private InputStream attachContentSwf;//转换后的流
//	private String journalId;
	private String uploadDate;
	private String pathString ;
	
	public Attach() {
	}
	
	public Attach(String attachId, String attachName,
			InputStream attachContentDoc, InputStream attachContentSwf) {
		super();
		this.attachId = attachId;
		this.attachName = attachName;
		this.attachContentDoc = attachContentDoc;
		this.attachContentSwf = attachContentSwf;
	}

	/**
	 * @return the pathString
	 */
	public String getPathString() {
		return pathString;
	}

	/**
	 * @param pathString the pathString to set
	 */
	public void setPathString(String pathString) {
		this.pathString = pathString;
	}

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

	public String getAttachType() {
		return attachType;
	}

	public void setAttachType(String attachType) {
		this.attachType = attachType;
	}



//	public String getJournalId() {
//		return journalId;
//	}
//
//	public void setJournalId(String journalId) {
//		this.journalId = journalId;
//	}

	/**
	 * @return the attachContentDoc
	 */
	public InputStream getAttachContentDoc() {
		return attachContentDoc;
	}

	/**
	 * @param attachContentDoc the attachContentDoc to set
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
	 * @param attachContentSwf the attachContentSwf to set
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
}
