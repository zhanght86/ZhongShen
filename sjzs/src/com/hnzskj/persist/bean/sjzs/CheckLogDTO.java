package com.hnzskj.persist.bean.sjzs;

public class CheckLogDTO {

	/**
	 * 主键ID
	 */
	private String id;
	/**
	 * 审核人ID
	 */
	private String checkUserId;
	/**
	 * 审核人名
	 */
	private String checkUserName;
	/**
	 * 被审核的用户ID
	 */
	private String clientId;
	/**
	 * 用户名
	 */
	private String clientName;
	/**
	 * 审核的记录ID
	 */
	private String infoId;
	/**
	 * 审核记录名称
	 */
	private String infoName;
	/**
	 * 附件ID
	 */
	private String attachId;
	/**
	 * 审核时间
	 */
	private String updateDate;
	/**
	 * 审核意见
	 */
	private String checkLog;
	/**
	 * 审核状态
	 */
	private int checkResult = 0;
	/**
	 * 数据迁移状态
	 */
	private int isTransport = 0;
	/**
	 * 备用
	 */
	private String note1;

	private String note2;

	private String note3;

	private String note4;

	private String note5;

	public CheckLogDTO() {
	}
	
	
	public CheckLogDTO(String checkUserId, String checkUserName,
			String clientId, String clientName, String infoId, String infoName, String attachId,
			String checkLog, int checkResult) {
		super();
		this.checkUserId = checkUserId;
		this.checkUserName = checkUserName;
		this.clientId = clientId;
		this.clientName = clientName;
		this.infoId = infoId;
		this.attachId = attachId;
		this.checkLog = checkLog;
		this.checkResult = checkResult;
		this.infoName = infoName;
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
	 * @return the checkUserId
	 */
	public String getCheckUserId() {
		return checkUserId;
	}

	/**
	 * @param checkUserId
	 *            the checkUserId to set
	 */
	public void setCheckUserId(String checkUserId) {
		this.checkUserId = checkUserId;
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
	 * @return the updateDate
	 */
	public String getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate
	 *            the updateDate to set
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return the checkLog
	 */
	public String getCheckLog() {
		return checkLog;
	}

	/**
	 * @param checkLog
	 *            the checkLog to set
	 */
	public void setCheckLog(String checkLog) {
		this.checkLog = checkLog;
	}

	public int getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(int checkResult) {
		this.checkResult = checkResult;
	}

	/**
	 * @return the isTransport
	 */
	public int getIsTransport() {
		return isTransport;
	}

	/**
	 * @param isTransport
	 *            the isTransport to set
	 */
	public void setIsTransport(int isTransport) {
		this.isTransport = isTransport;
	}

	public String getInfoName() {
		return infoName;
	}

	public void setInfoName(String infoName) {
		this.infoName = infoName;
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

	public String getCheckUserName() {
		return checkUserName;
	}

	public void setCheckUserName(String checkUserName) {
		this.checkUserName = checkUserName;
	}

	@Override
	public String toString() {
		return "CheckLogDTO [id=" + this.id + ", clientId=" + this.clientId
				+ ", attachId=" + this.attachId + ", checkLog=" + this.checkLog
				+ ", checkRresult=" + this.checkResult + ", checkUserId="
				+ this.checkUserId + ", clientName=" + this.clientName
				+ ", isTransport=" + this.isTransport + ", infoId="
				+ this.infoId + ", note1=" + this.note1 + ", note2="
				+ this.note2 + ", note3=" + note3 + ", note4=" + note4
				+ ", note5=" + note5 + "]";
	}
}
