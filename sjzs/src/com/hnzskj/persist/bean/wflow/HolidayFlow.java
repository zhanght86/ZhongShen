/*
 * @项目名称: OA
 * @文件名称: HolidayFlow.java
 * @日期: 2012-7-12
 * @版权: 2012 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.persist.bean.wflow;

  /**        
 * 
 * 类名称：HolidayFlow
 * 类描述：<br/>
 * 创建人：郑辉  <br/>
 * 创建时间：2012-7-12 下午04:21:21 
 * 修改人：Administrator
 * 修改时间：
 * 修改备注：   
 * @version   1.0     
 */

public class HolidayFlow {
	private String hd_Id  ;
	private String  hd_Name;			//姓名
	private String   hd_OrgId ;			//所属部门
	private String hd_begin_time;		//请假开始时间
	private String hd_end_time;		//请假结束时间
	private String hd_end_days ;			//请假天数
	private String  hd_type ;			//请假类别   1，病假 2.婚假 3.产假 4.陪产假 5.事假 6.工伤假  7.丧假  8.公假  9.其他
	private String hd_reason ;			//请假事由
	private String hd_Sickleave_time ;			//销假时间 
	private String hd_Sickleave_days ;			//销假天数
	private String hd_timeout_time ;			//超（节）时时间  
	private String hd_timeout_days ;			//超节天数
	private String hd_desc ;					//描述
	private String hd_orderPerson ;				//备注
	private Integer instance_no; 			//实例号
	private Integer hd_sick;				//是否销假        //1 正在请假，2 请假通过正在假期期间，3开始销假，4.请假，度假，销假结束
	private Integer befor_instance_no;
	private Integer isScrap;				//是否是报废的请假流程
	private String nowMonth;		//按照月份查询
	private String nowYear;			//按照年份查询
	/**
	 * 
	 */
	public HolidayFlow() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the hd_Name
	 */
	public String getHd_Name() {
		return hd_Name;
	}
	/**
	 * @param hdName the hd_Name to set
	 */
	public void setHd_Name(String hdName) {
		hd_Name = hdName;
	}
	/**
	 * @return the hd_OrgId
	 */
	public String getHd_OrgId() {
		return hd_OrgId;
	}
	/**
	 * @param hdOrgId the hd_OrgId to set
	 */
	public void setHd_OrgId(String hdOrgId) {
		hd_OrgId = hdOrgId;
	}
	/**
	 * @return the hd_begin_time
	 */
	public String getHd_begin_time() {
		return hd_begin_time;
	}
	/**
	 * @param hdBeginTime the hd_begin_time to set
	 */
	public void setHd_begin_time(String hdBeginTime) {
		hd_begin_time = hdBeginTime;
	}
	/**
	 * @return the hd_end_time
	 */
	public String getHd_end_time() {
		return hd_end_time;
	}
	/**
	 * @param hdEndTime the hd_end_time to set
	 */
	public void setHd_end_time(String hdEndTime) {
		hd_end_time = hdEndTime;
	}
	/**
	 * @return the hd_end_days
	 */
	
	/**
	 * @return the hd_type
	 */
	public String getHd_type() {
		return hd_type;
	}
	/**
	 * @param hdType the hd_type to set
	 */
	public void setHd_type(String hdType) {
		hd_type = hdType;
	}
	/**
	 * @return the hd_reason
	 */
	public String getHd_reason() {
		return hd_reason;
	}
	/**
	 * @param hdReason the hd_reason to set
	 */
	public void setHd_reason(String hdReason) {
		hd_reason = hdReason;
	}
	/**
	 * @return the hd_Sickleave_time
	 */
	public String getHd_Sickleave_time() {
		return hd_Sickleave_time;
	}
	/**
	 * @param hdSickleaveTime the hd_Sickleave_time to set
	 */
	public void setHd_Sickleave_time(String hdSickleaveTime) {
		hd_Sickleave_time = hdSickleaveTime;
	}
	/**
	 * @return the hd_Sickleave_days
	 */
	public String getHd_Sickleave_days() {
		return hd_Sickleave_days;
	}
	/**
	 * @param hdSickleaveDays the hd_Sickleave_days to set
	 */
	public void setHd_Sickleave_days(String hdSickleaveDays) {
		hd_Sickleave_days = hdSickleaveDays;
	}
	/**
	 * @return the hd_timeout_time
	 */
	public String getHd_timeout_time() {
		return hd_timeout_time;
	}
	/**
	 * @param hdTimeoutTime the hd_timeout_time to set
	 */
	public void setHd_timeout_time(String hdTimeoutTime) {
		hd_timeout_time = hdTimeoutTime;
	}
	/**
	 * @return the hd_timeout_days
	 */
	public String getHd_timeout_days() {
		return hd_timeout_days;
	}
	/**
	 * @param hdTimeoutDays the hd_timeout_days to set
	 */
	public void setHd_timeout_days(String hdTimeoutDays) {
		hd_timeout_days = hdTimeoutDays;
	}
	/**
	 * @return the hd_desc
	 */
	public String getHd_desc() {
		return hd_desc;
	}
	/**
	 * @param hdDesc the hd_desc to set
	 */
	public void setHd_desc(String hdDesc) {
		hd_desc = hdDesc;
	}
	/**
	 * @return the hd_orderPerson
	 */
	public String getHd_orderPerson() {
		return hd_orderPerson;
	}
	/**
	 * @param hdOrderPerson the hd_orderPerson to set
	 */
	public void setHd_orderPerson(String hdOrderPerson) {
		hd_orderPerson = hdOrderPerson;
	}

	/**
	 * @return the hd_Id
	 */
	public String getHd_Id() {
		return hd_Id;
	}

	/**
	 * @param hdId the hd_Id to set
	 */
	public void setHd_Id(String hdId) {
		hd_Id = hdId;
	}

	/**
	 * @return the hd_end_days
	 */


	/**
	 * @return the instance_no
	 */
	public int getInstance_no() {
		return instance_no;
	}

	/**
	 * @return the hd_end_days
	 */
	public String getHd_end_days() {
		return hd_end_days;
	}

	/**
	 * @param hdEndDays the hd_end_days to set
	 */
	public void setHd_end_days(String hdEndDays) {
		hd_end_days = hdEndDays;
	}

	/**
	 * @param instanceNo the instance_no to set
	 */
	public void setInstance_no(int instanceNo) {
		instance_no = instanceNo;
	}

	/**
	 * @param instanceNo the instance_no to set
	 */
	public void setInstance_no(Integer instanceNo) {
		instance_no = instanceNo;
	}

	/**
	 * @return the hd_sick
	 */
	public Integer getHd_sick() {
		return hd_sick;
	}

	/**
	 * @param hdSick the hd_sick to set
	 */
	public void setHd_sick(Integer hdSick) {
		hd_sick = hdSick;
	}

	/**
	 * @return the befor_instance_no
	 */
	public Integer getBefor_instance_no() {
		return befor_instance_no;
	}

	/**
	 * @param beforInstanceNo the befor_instance_no to set
	 */
	public void setBefor_instance_no(Integer beforInstanceNo) {
		befor_instance_no = beforInstanceNo;
	}

	/**
	 * @return the isScrap
	 */
	public Integer getIsScrap() {
		return isScrap;
	}

	/**
	 * @param isScrap the isScrap to set
	 */
	public void setIsScrap(Integer isScrap) {
		this.isScrap = isScrap;
	}

	/**
	 * @return the nowMonth
	 */
	public String getNowMonth() {
		return nowMonth;
	}

	/**
	 * @param nowMonth the nowMonth to set
	 */
	public void setNowMonth(String nowMonth) {
		this.nowMonth = nowMonth;
	}

	/**
	 * @return the nowYear
	 */
	public String getNowYear() {
		return nowYear;
	}

	/**
	 * @param nowYear the nowYear to set
	 */
	public void setNowYear(String nowYear) {
		this.nowYear = nowYear;
	}
}
