/**
 * flow
 *com.hnzskj.persist.bean.flow
 *2012-4-92012上午08:30:05
 *郑辉
 *
 */
package com.hnzskj.persist.bean.flow;

/**
 *创建人：郑辉
 *描述： 	流程实例集合
 *创建时间：2012-4-9 上午08:30:05
 *修改人：郑辉
 *修改时间：
 */
public class InstanceExceInfo {
	private int instance_id;	//流程编号
	private int task_id;		//任务号			0表示开始个任务，此后任务号累加 ，至到结束
	private int tache_id;		//当前环节代码
	private int before_tache_id; //上一环节代码
	private String task_state;	//E、表示已经执行完成，B、标志正在执行
	private int template_id;	//模板号
	private String overtime_flag;	//-Y、超时，N、未超时
	private String last_commitor;	//上一步提交人员
	private String task_reach_time;//任务到达时间
	private String task_commit_time;//任务提交时间
	private String task_commitor;	//提交人员列表
	private String tache_type;		//环节类型
	private String state;			//当前状态
	private String remark;			//注释
	private String department;			//部门
	private String tache_name;		//环节名称
	
	public InstanceExceInfo() {
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
	public String getTask_state() {
		return task_state;
	}
	public void setTask_state(String taskState) {
		task_state = taskState;
	}
	public int getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(int templateId) {
		template_id = templateId;
	}
	public String getOvertime_flag() {
		return overtime_flag;
	}
	public void setOvertime_flag(String overtimeFlag) {
		overtime_flag = overtimeFlag;
	}
	public String getLast_commitor() {
		return last_commitor;
	}
	public void setLast_commitor(String lastCommitor) {
		last_commitor = lastCommitor;
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
	public String getTask_commit_time() {
		if((task_commit_time!=null)&&(!"".equals(task_commit_time))){
			return task_commit_time.substring(0,19);
		}
		return task_commit_time;
	}
	public void setTask_commit_time(String taskCommitTime) {
		task_commit_time = taskCommitTime;
	}
	public String getTask_commitor() {
		return task_commitor;
	}
	public void setTask_commitor(String taskCommitor) {
		task_commitor = taskCommitor;
	}
	public String getTache_type() {
		return tache_type;
	}
	public void setTache_type(String tacheType) {
		tache_type = tacheType;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * @return the before_tache_id
	 */
	public int getBefore_tache_id() {
		return before_tache_id;
	}
	/**
	 * @param beforeTacheId the before_tache_id to set
	 */
	public void setBefore_tache_id(int beforeTacheId) {
		before_tache_id = beforeTacheId;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getTache_name() {
		return tache_name;
	}
	public void setTache_name(String tacheName) {
		tache_name = tacheName;
	}
	
}
