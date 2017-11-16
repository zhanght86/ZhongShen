/**
 * flow
 *com.hnzskj.persist.bean.flow
 *2012-4-72012下午07:09:44
 *郑辉
 *
 */
package com.hnzskj.persist.bean.flow;

/**
 *创建人：郑辉
 *描述：  流程实例对象
 *创建时间：2012-4-7 下午07:09:44
 *修改人：郑辉
 *修改时间：
 */
public class InstanceWF {
	
	private int instance_id;//实例号
	private String template_id;		//模板代码
	private String template_ids;
	private String state;			//状态   1、正在运行，2、流程结束，3、非正常结束'
	private String start_time;		//开始时间
	private String end_time;		//结束时间
	private String start_operator;	//流程启动
	private String start_operators;
	private String remark;			//流程说明
	private String department;
	private String templateUrl;
	public InstanceWF() {
		
		// TODO Auto-generated constructor stub
	}
	public int getInstance_id() {
		return instance_id;
	}
	public void setInstance_id(int instanceId) {
		instance_id = instanceId;
	}

	
	public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String templateId) {
		template_id = templateId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStart_time() {
		if((start_time!=null)&&(!"".equals(start_time))){
			if(start_time.length()<18) start_time+="";
			return start_time;
		}
		return start_time;
	}
	public void setStart_time(String startTime) {
		start_time = startTime;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String endTime) {
		end_time = endTime;
	}
	
	public String getStart_operator() {
		return start_operator;
	}
	public void setStart_operator(String startOperator) {
		start_operator = startOperator;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}		//流程启动者部门
	/**
	 * @return the template_ids
	 */
	public String getTemplate_ids() {
		return template_ids;
	}
	/**
	 * @param templateIds the template_ids to set
	 */
	public void setTemplate_ids(String templateIds) {
		template_ids = templateIds;
	}
	/**
	 * @return the start_operators
	 */
	public String getStart_operators() {
		return start_operators;
	}
	/**
	 * @param startOperators the start_operators to set
	 */
	public void setStart_operators(String startOperators) {
		start_operators = startOperators;
	}
	/**
	 * @return the templateUrl
	 */
	public String getTemplateUrl() {
		return templateUrl;
	}
	/**
	 * @param templateUrl the templateUrl to set
	 */
	public void setTemplateUrl(String templateUrl) {
		this.templateUrl = templateUrl;
	}
	
}
