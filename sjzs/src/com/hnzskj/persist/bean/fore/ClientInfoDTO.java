package com.hnzskj.persist.bean.fore;

import java.io.InputStream;

public class ClientInfoDTO {
	/**
	 * 主键ID
	 */
	private String id;

	/**
	 * 用户ID
	 */
	private String clientId;
	/**
	 * 用户积分
	 */
	private int integral = 0;
	/**
	 * 上传量
	 */
	private long uploadNum = 0;
	/**
	 * 下载量
	 */
	private long downNum = 0;
	/**
	 * 审核通过量
	 */
	private long checkNum = 0;
	/**
	 * 头衔
	 */
	private String title;
	/**
	 * 等级
	 */
	private String rank;
	/**
	 * 头像
	 */
	private InputStream headIcon;
	/**
	 * 头像路径
	 */
	private String headIconUrl;
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

	public ClientInfoDTO() {
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
	 * @return the integral
	 */
	public int getIntegral() {
		return integral;
	}

	/**
	 * @param integral
	 *            the integral to set
	 */
	public void setIntegral(int integral) {
		this.integral = integral;
	}

	/**
	 * @return the uploadNum
	 */
	public long getUploadNum() {
		return uploadNum;
	}

	/**
	 * @param uploadNum
	 *            the uploadNum to set
	 */
	public void setUploadNum(long uploadNum) {
		this.uploadNum = uploadNum;
	}
	
	/**

	 * @return the downLoadNum
	 */
	public long getDownNum() {
		return downNum;
	}

	/**
	 * @param downLoadNum
	 *            the downLoadNum to set
	 */
	public void setDownNum(long downNum) {
		this.downNum = downNum;
	}


	
	
	/**
	 * @return the checkNum
	 */
	public long getCheckNum() {
		return checkNum;
	}

	/**
	 * @param checkNum the checkNum to set
	 */
	public void setCheckNum(long checkNum) {
		this.checkNum = checkNum;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @return the rank
	 */
	public String getRank() {
		return rank;
	}

	/**
	 * @param rank
	 *            the rank to set
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}

	/**
	 * @return the headIcon
	 */
	public InputStream getHeadIcon() {
		return headIcon;
	}

	/**
	 * @param headIcon
	 *            the headIcon to set
	 */
	public void setHeadIcon(InputStream headIcon) {
		this.headIcon = headIcon;
	}

	/**
	 * @return the headIconUrl
	 */
	public String getHeadIconUrl() {
		return headIconUrl;
	}

	/**
	 * @param headIconUrl
	 *            the headIconUrl to set
	 */
	public void setHeadIconUrl(String headIconUrl) {
		this.headIconUrl = headIconUrl;
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
		return "ClientUploadDTO [id=" + this.id + ", clientId=" + this.clientId
				+ ", downNum=" + this.downNum + ", headIconUrl="
				+ this.headIconUrl + ", integral=" + this.integral + ", rank="
				+ this.rank + ", uploadNum=" + this.uploadNum + ", title="
				+ this.title + ", note1=" + this.note1 + ", note2="
				+ this.note2 + ", note3=" + note3 + ", note4=" + note4
				+ ", note5=" + note5 + "]";
	}

}
