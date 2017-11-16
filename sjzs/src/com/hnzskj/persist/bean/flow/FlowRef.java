package com.hnzskj.persist.bean.flow;

import java.sql.Timestamp;

public class FlowRef {
	private String id;
	private String sjdhId;
	private String xmlId;
	private String modeId;
	private String attachId;
	private String fangFaId;
	private int deleteflag;
	private Timestamp updatedate;
	private String fangFaUrl;
	private String attachUrl;
	private String fangFaName;
	private String attachName;
	
	public FlowRef() {
	}
	
	public FlowRef(String sjdhId, String xmlId, String modeId, String fangFaId, String fangFaName,
			String fangFaUrl, String attachId, String attachName, String attachUrl) {
		super();
		this.sjdhId = sjdhId;
		this.xmlId = xmlId;
		this.modeId = modeId;
		this.attachId = attachId;
		this.fangFaId = fangFaId;
		this.fangFaUrl = fangFaUrl;
		this.attachUrl = attachUrl;
		this.fangFaName = fangFaName;
		this.attachName = attachName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSjdhId() {
		return sjdhId;
	}

	public void setSjdhId(String sjdhId) {
		this.sjdhId = sjdhId;
	}

	public String getXmlId() {
		return xmlId;
	}

	public void setXmlId(String xmlId) {
		this.xmlId = xmlId;
	}

	public String getModeId() {
		return modeId;
	}

	public void setModeId(String modeId) {
		this.modeId = modeId;
	}

	public String getAttachId() {
		return attachId;
	}

	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}

	public String getFangFaId() {
		return fangFaId;
	}

	public void setFangFaId(String fangFaId) {
		this.fangFaId = fangFaId;
	}

	public int getDeleteflag() {
		return deleteflag;
	}

	public void setDeleteflag(int deleteflag) {
		this.deleteflag = deleteflag;
	}

	public Timestamp getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Timestamp updatedate) {
		this.updatedate = updatedate;
	}

	public String getFangFaUrl() {
		return fangFaUrl;
	}

	public void setFangFaUrl(String fangFaUrl) {
		this.fangFaUrl = fangFaUrl;
	}

	public String getAttachUrl() {
		return attachUrl;
	}

	public void setAttachUrl(String attachUrl) {
		this.attachUrl = attachUrl;
	}

	public String getFangFaName() {
		return fangFaName;
	}

	public void setFangFaName(String fangFaName) {
		this.fangFaName = fangFaName;
	}

	public String getAttachName() {
		return attachName;
	}

	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((modeId == null) ? 0 : modeId.hashCode());
		result = prime * result + ((xmlId == null) ? 0 : xmlId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlowRef other = (FlowRef) obj;
		if (modeId == null) {
			if (other.modeId != null)
				return false;
		} else if (!modeId.equals(other.modeId))
			return false;
		if (xmlId == null) {
			if (other.xmlId != null)
				return false;
		} else if (!xmlId.equals(other.xmlId))
			return false;
		return true;
	}

	
}
