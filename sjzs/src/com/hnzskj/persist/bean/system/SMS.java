/*
 * @项目名称: htglxt
 * @文件名称: SMS.java
 * @日期: 2011-9-7
 * @版权: 2011 河南中审科技有限公司 
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.persist.bean.system;

import java.sql.Timestamp;

import com.hnzskj.common.SystemException;

/**        
 * 
 * 类名称：SMS     <br/>
 * 类描述：<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-9-7 上午09:59:43   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-9-7 上午09:59:43   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */
public class SMS {
	/**数据库标志id*/
	private Integer sid;
	
	/**短信接收人*/
	private Integer uid;
	
	/**短信接收人手机号*/
	private String ucellphone;
	
	/**短信发送时间*/
	private Timestamp ssendTime;
	
	/**短信内容*/
	private String scontent;
	
	/**短信发送类型，如合同到期，项目到期*/
	private String stype;

	/**发送短信的信息的id*/
	private Integer sinfoId;

	/**短信是否发送成功*/
	private Boolean success;
	
	public SMS() {}
	
	/**
	 * 短信的构造器
	 * @param uid			短信接收人的uid
	 * @param ucellphone	短信接收人的手机号
	 * @param ssendTime		短信发送时间
	 * @param scontent		短信内容
	 * @param stype			短信类型
	 * @param sinfoId		发送短信的的信息id
	 */
	public SMS(Integer uid, String ucellphone, Timestamp ssendTime,
			String scontent, String stype, Integer sinfoId) {
		this.uid = uid;
		this.ucellphone = ucellphone;
		this.ssendTime = ssendTime;
		this.scontent = scontent;
		this.stype = stype;
		this.sinfoId = sinfoId;
	}
	
	/**
	 * 短信的构造器
	 * @param sid 			短信的数据库标志id
	 * @param uid			短信接收人的uid
	 * @param ucellphone	短信接收人的手机号
	 * @param ssendTime		短信发送时间
	 * @param scontent		短信内容
	 * @param stype			短信类型
	 * @param sinfoId		发送短信的的信息id
	 */
	public SMS(Integer sid, Integer uid, String ucellphone,
			Timestamp ssendTime, String scontent, String stype,
			Integer sinfoId, Boolean success) {
		this.sid = sid;
		this.uid = uid;
		this.ucellphone = ucellphone;
		this.ssendTime = ssendTime;
		this.scontent = scontent;
		this.stype = stype;
		this.sinfoId = sinfoId;
		this.success = success;
	}

	/**
	 * 获取数据库标志id
	 * @return the id
	 */
	public Integer getSid() {
		return sid;
	}

	/**
	 * 设置数据库标志id
	 * @param id the id to set
	 */
	public void setSid(Integer sid) {
		this.sid = sid;
	}

	/**
	 * 获取接收短信人的uid
	 * @return the uid
	 */
	public Integer getUid() {
		return uid;
	}

	/**
	 * 设置接收短信人的uid
	 * @param uid the uid to set
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	/**
	 * 获得短信发送时间
	 * @return the ssendTime
	 */
	public Timestamp getSsendTime() {
		return ssendTime;
	}

	/**
	 * 设置短信发送时间
	 * @param ssendTime the ssendTime to set
	 */
	public void setSsendTime(Timestamp ssendTime) {
		this.ssendTime = ssendTime;
	}

	/**
	 * 获取短信发送内容
	 * @return the content
	 */
	public String getScontent() {
		return scontent;
	}

	/**
	 * 设置短信发送内容
	 * @param content the content to set
	 */
	public void setScontent(String scontent) {
		this.scontent = scontent;
	}

	/**
	 * 获取短信发送类型
	 * @return the type
	 */
	public String getStype() {
		return stype;
	}

	/**
	 * 设置短信发送类型
	 * @param type the type to set
	 */
	public void setStype(String stype) {
		this.stype = stype;
	}

	/**
	 * 获取发送短信的信息的id
	 * @return the infoId
	 */
	public Integer getSinfoId() {
		return sinfoId;
	}

	/**
	 * 设置发送短信的信息的id
	 * @param infoId the infoId to set
	 */
	public void setSinfoId(Integer sinfoId) {
		this.sinfoId = sinfoId;
	}

	/**
	 * 获取短信接收人手机号
	 * @return the ucellphone
	 */
	public String getUcellphone() {
		return ucellphone;
	}

	/**
	 * 设置短信接收人手机号
	 * @param ucellphone the ucellphone to set
	 */
	public void setUcellphone(String ucellphone) {
		this.ucellphone = ucellphone;
	}
	
	/**
	 * 获得短信是否发送成功
	 * @return the isSuccess
	 */
	public Boolean isSuccess() {
		return success;
	}

	/**
	 * 设置短信的发送是否发送成功
	 * @param isSuccess the isSuccess to set
	 */
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(sid).append("hnzssms")
			.append(uid).append("hnzssms")
			.append(ucellphone).append("hnzssms")
			.append(ssendTime).append("hnzssms")
			.append(scontent).append("hnzssms")
			.append(stype).append("hnzssms")
			.append(sinfoId).append("hnzssms")
			.append(success);
		return sb.toString();
	}
	
	/**
	 * 方法描述：从字符串中获得SMS对象<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-9-16 下午04:11:33<br/>         
	 * @param smsStr
	 * @return
	 * @version   1.0  
	 */
	public static SMS valueOf(String smsStr) {
		SMS sms = null;
		String[] fields = smsStr.split("hnzssms");
		if(null==fields || fields.length!=8) {
			throw new SystemException("格式化短信实体出错");
		}
		Integer sid = null;
		Integer uid = null;
		String ucellphone = "";
		Timestamp ssendTime = null;
		String scontent = "";
		String stype = "";
		Integer sinfoId = null;
		Boolean success = false;
		
		try {
			sid = Integer.valueOf(fields[0]);
		} catch (NumberFormatException e) {
			sid = null;
		}
		
		try {
			uid = Integer.valueOf(fields[1]);
		} catch (NumberFormatException e) {
			uid = null;
		}
		
		ucellphone = fields[2];
		
		try {
			ssendTime = Timestamp.valueOf(fields[3]);
		} catch (Exception e) {
			ssendTime = null;
		}
		
		scontent = fields[4];
		
		stype = fields[5];
		
		try {
			sinfoId = Integer.valueOf(fields[6]);
		} catch (NumberFormatException e) {
			sinfoId = null;
		}
		
		try {
			success = Boolean.valueOf(fields[7]);
		} catch (Exception e) {
			success = false;
		}
		
		sms = new SMS(sid, uid, ucellphone, ssendTime, scontent, stype, sinfoId, success);
		
		return sms;
	}	

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ucellphone == null) ? 0 : ucellphone.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SMS other = (SMS) obj;
		if (ucellphone == null) {
			if (other.ucellphone != null)
				return false;
		} else if (!ucellphone.equals(other.ucellphone))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}

	
}
