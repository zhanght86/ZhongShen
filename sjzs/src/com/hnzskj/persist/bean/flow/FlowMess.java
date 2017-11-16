/**
 * flow
 *com.hnzskj.persist.bean.flow
 *2012-4-92012上午08:43:53
 *郑辉
 *
 */
package com.hnzskj.persist.bean.flow;

/**
 *创建人：郑辉
 *描述： 	
 *创建时间：2012-4-9 上午08:43:53
 *修改人：郑辉
 *修改时间：
 */
public class FlowMess {
	private int id;				//主键
	private int instance_id;	//实例号
	private String dept_id;		//审批部门编码
	private String dept_name;	//部门名称
	private String person_id;	//审批人员
	private String person_name;	//审批人员名称
	private String body;		//审批内容
	private String shenpi_time;	//审批时间
	private String label;		//标志
	public FlowMess() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getInstance_id() {
		return instance_id;
	}
	public void setInstance_id(int instanceId) {
		instance_id = instanceId;
	}
	
	public String getDept_id() {
		return dept_id;
	}
	public void setDept_id(String deptId) {
		dept_id = deptId;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String deptName) {
		dept_name = deptName;
	}
	public String getPerson_id() {
		return person_id;
	}
	public void setPerson_id(String personId) {
		person_id = personId;
	}
	public String getPerson_name() {
		return person_name;
	}
	public void setPerson_name(String personName) {
		person_name = personName;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getShenpi_time() {
		return shenpi_time;
	}
	public void setShenpi_time(String shenpiTime) {
		shenpi_time = shenpiTime;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	
}
