package com.hnzskj.persist.bean.fore;

import java.util.Date;


public class ClientUploadDTO {
	
	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 标题
	 */
	private String caption;
	
	/**
	 * 简介
	 */
	private String content;
	
	/**
	 * 标分类ID
	 */
	private String parentId;
	
	/**
	 * 审核标志0：待审核 1：审核中 2，审核通过 3：打回
	 */
	private int checkFlag = 0;
	
	/**
	 * 上传时间
	 */
	private Date uploadDate;
	
	/**
	 * 时间段查询时开始时间
	 */
	private String beginDate;
	
	/**
	 * 时间段查询时结束时间
	 */
	private String endDate;
	
	/**
	 * 积分
	 * */
	private int integral;
	
	/**
	 * FG-审计法规 YJ-定性依据 AL-审计方法 DH-导航 SX审计事项 EI实施方案' ,
	 */
	private String type;
	
	/**
	 * 是否公开0：不公开 1：公开
	 */
	private int isOpen = 1;

	/**
	 * 上传用户ID
	 */
	private String clientId;
	
	/**
	 * 上传用户名称
	 */
	private String clientName;
	
	/**
	 * 上传附件ID
	 */
	private String attachId;
	
	/**
	 * 正在审核人员ID
	 */
	private String curCheckUserId;
	
	/**
	 * 正在审核人员名称
	 */
	private String curCheckUserName;
	
	/**
	 * 数据是否已迁移  0-未迁移   1-已迁移
	 */
	private Integer isTransport = 0;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public int getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(int checkFlag) {
		this.checkFlag = checkFlag;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public int getIntegral() {
		return integral;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(int isOpen) {
		this.isOpen = isOpen;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
		
	
	public String getAttachId() {
		return attachId;
	}

	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}

	public String getCurCheckUserId() {
		return curCheckUserId;
	}

	public void setCurCheckUserId(String curCheckUserId) {
		this.curCheckUserId = curCheckUserId;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCurCheckUserName() {
		return curCheckUserName;
	}

	public void setCurCheckUserName(String curCheckUserName) {
		this.curCheckUserName = curCheckUserName;
	}

	public Integer getIsTransport() {
		return isTransport;
	}

	public void setIsTransport(Integer isTransport) {
		this.isTransport = isTransport;
	}

	@Override
	public String toString() {
		return "ClientUploadDTO [attachId=" + attachId + ", caption=" + caption
				+ ", checkFlag=" + checkFlag + ", clientId=" + clientId
				+ ", clientName=" + clientName + ", content=" + content
				+ ", curCheckUserId=" + curCheckUserId + ", id=" + id
				+ ", integral=" + integral + ", isOpen=" + isOpen
				+ ", parentId=" + parentId + ", type=" + type + ", uploadDate="
				+ uploadDate + "]";
	}

		
}
