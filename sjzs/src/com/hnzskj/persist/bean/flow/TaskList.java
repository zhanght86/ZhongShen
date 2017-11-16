/**
 * flow
 *com.hnzskj.persist.bean.flow
 *2012-4-92012上午08:37:10
 *郑辉
 *
 */
package com.hnzskj.persist.bean.flow;

/**
 *创建人：郑辉
 *描述： 	
 *创建时间：2012-4-9 上午08:37:10
 *修改人：郑辉
 *修改时间：
 */
public class TaskList {
	  private int instance_id;			//流程代码
	  private int task_id;				//任务号
	  private int tache_id;				//环节代码
	  private String enti_id;			//处理实体代码
	  private String enti_ids;
	  private String enti_type;			//处理实体类型
	  private int template_id;			//	模板号
	  private String task_state;		//任务状态
	  private String last_commitor;		//最后提交人员
	  private String last_commitors;
	  private String overtime_flag;		//超时标志
	  private String tache_type;		//环节类型
	  private String task_reach_time;	//任务到达时间
	  private String start_operator;	//启动人员
	  private String start_operators;
	  private int department;			//部门
	  private int role_code;			//较色
	  private String state;				//当前状态
	  private String tache_name;		//环节名称
	  private String template_name;
	  private String template_url;
	public TaskList() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getInstance_id() {
		return instance_id;
	}
	public void setInstance_id(int instanceId) {
		instance_id = instanceId;
	}
	public int getTask_id() {
		return task_id;
	}
	public void setTask_id(int taskId) {
		task_id = taskId;
	}
	public int getTache_id() {
		return tache_id;
	}
	public void setTache_id(int tacheId) {
		tache_id = tacheId;
	}
	public String getOvertime_flag() {
		return overtime_flag;
	}
	public void setOvertime_flag(String overtimeFlag) {
		overtime_flag = overtimeFlag;
	}
	public String getEnti_id() {
		return enti_id;
	}
	public void setEnti_id(String entiId) {
		enti_id = entiId;
	}
	public String getEnti_type() {
		return enti_type;
	}
	public void setEnti_type(String entiType) {
		enti_type = entiType;
	}
	public int getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(int templateId) {
		template_id = templateId;
	}
	public String getTask_state() {
		return task_state;
	}
	public void setTask_state(String taskState) {
		task_state = taskState;
	}
	public String getLast_commitor() {
		return last_commitor;
	}
	public void setLast_commitor(String lastCommitor) {
		last_commitor = lastCommitor;
	}
	public String getTache_type() {
		return tache_type;
	}
	public void setTache_type(String tacheType) {
		tache_type = tacheType;
	}
	public String getTask_reach_time() {
		if((task_reach_time!=null)&&(!"".equals(task_reach_time))){
			return task_reach_time.substring(0,19);
		}
		return task_reach_time;
	}
	public void setTask_reach_time(String taskReachTime) {
		task_reach_time = taskReachTime;
	}
	public String getStart_operator() {
		return start_operator;
	}
	public void setStart_operator(String startOperator) {
		start_operator = startOperator;
	}
	public int getDepartment() {
		return department;
	}
	public void setDepartment(int department) {
		this.department = department;
	}
	public int getRole_code() {
		return role_code;
	}
	public void setRole_code(int roleCode) {
		role_code = roleCode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTache_name() {
		return tache_name;
	}
	public void setTache_name(String tacheName) {
		tache_name = tacheName;
	}
	/**
	 * @return the enti_ids
	 */
	public String getEnti_ids() {
		return enti_ids;
	}
	/**
	 * @param entiIds the enti_ids to set
	 */
	public void setEnti_ids(String entiIds) {
		enti_ids = entiIds;
	}
	/**
	 * @return the last_commitors
	 */
	public String getLast_commitors() {
		return last_commitors;
	}
	/**
	 * @param lastCommitors the last_commitors to set
	 */
	public void setLast_commitors(String lastCommitors) {
		last_commitors = lastCommitors;
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
	 * @return the template_name
	 */
	public String getTemplate_name() {
		return template_name;
	}
	/**
	 * @param templateName the template_name to set
	 */
	public void setTemplate_name(String templateName) {
		template_name = templateName;
	}
	/**
	 * @return the template_url
	 */
	public String getTemplate_url() {
		return template_url;
	}
	/**
	 * @param templateUrl the template_url to set
	 */
	public void setTemplate_url(String templateUrl) {
		template_url = templateUrl;
	}
	  
}
