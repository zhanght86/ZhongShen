package com.hnzskj.persist.bean.sjzs;

import java.sql.Date;
import java.sql.Timestamp;

public class UpdateDataLog {
	private String fileName;
	private String oldVersion;
	private String newVersion;
	private int type;
	private Timestamp uploadDate;


	public UpdateDataLog() {
	}
	
	/**
	 * @param fileName
	 * @param oldVersion
	 * @param newVersion
	 * @param type
	 * @param uploadDate
	 */
	public UpdateDataLog(String fileName, String oldVersion, String newVersion,
			int type) {
		super();
		this.fileName = fileName;
		this.oldVersion = oldVersion;
		this.newVersion = newVersion;
		this.type = type;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the oldVersion
	 */
	public String getOldVersion() {
		return oldVersion;
	}

	/**
	 * @param oldVersion
	 *            the oldVersion to set
	 */
	public void setOldVersion(String oldVersion) {
		this.oldVersion = oldVersion;
	}

	/**
	 * @return the newVersion
	 */
	public String getNewVersion() {
		return newVersion;
	}

	/**
	 * @param newVersion
	 *            the newVersion to set
	 */
	public void setNewVersion(String newVersion) {
		this.newVersion = newVersion;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	public Timestamp getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Timestamp uploadDate) {
		this.uploadDate = uploadDate;
	}



}
