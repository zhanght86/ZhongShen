/**
 * flow
 *com.hnzskj.persist.bean
 *2012-4-52012上午10:29:24
 *郑辉
 *
 */
package com.hnzskj.persist.bean.flow;

/**
 *创建人：郑辉
 *描述： 	
 *创建时间：2012-4-5 上午10:29:24
 *修改人：郑辉
 *修改时间：
 */
public class Tache {
	private int template_id;    //模版
	private int tache_id;	//环节代码
	private String tache_name;//环节名称
	private int tache_type;
	private String tache_DESCRIPTION;//环节描述
	private String roles_id;//角色ID
	private String roles_name;//角色名称
	private int dept_id;//部门编号
	private String dept_name;//部门名称
	private String emp_names;//员工名称
	private String guid;//员工编号
	private String days ;//天
	private String hours;//是
	private String minutes;//分
	private String memo;//任务实例路径
	private int xposition;// x坐标
	private int yposition ;//y坐标
	private String is_back;//是否作废
	private int dotType;//节点类型
	private int nameModel;	//环节人物选择
	private String attach;
	public Tache() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(int templateId) {
		template_id = templateId;
	}
	public int getTache_id() {
		return tache_id;
	}
	public void setTache_id(int tacheId) {
		tache_id = tacheId;
	}
	public String getTache_name() {
		return tache_name;
	}
	public void setTache_name(String tacheName) {
		tache_name = tacheName;
	}
	
	public String getTache_DESCRIPTION() {
		return tache_DESCRIPTION;
	}
	
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public void setTache_DESCRIPTION(String tacheDESCRIPTION) {
		tache_DESCRIPTION = tacheDESCRIPTION;
	}
	public String getRoles_id() {
		return roles_id;
	}
	public void setRoles_id(String rolesId) {
		roles_id = rolesId;
	}
	public String getRoles_name() {
		return roles_name;
	}
	public void setRoles_name(String rolesName) {
		roles_name = rolesName;
	}
	public int getDept_id() {
		return dept_id;
	}
	public void setDept_id(int deptId) {
		dept_id = deptId;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String deptName) {
		dept_name = deptName;
	}
	
	public String getEmp_names() {
		return emp_names;
	}
	public void setEmp_names(String empNames) {
		emp_names = empNames;
	}
	
	
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	public String getMinutes() {
		return minutes;
	}
	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public int getXposition() {
		return xposition;
	}
	public void setXposition(int xposition) {
		this.xposition = xposition;
	}
	public int getYposition() {
		return yposition;
	}
	public void setYposition(int yposition) {
		this.yposition = yposition;
	}
	public String getIs_back() {
		return is_back;
	}
	public void setIs_back(String isBack) {
		is_back = isBack;
	}
	public int getTache_type() {
		return tache_type;
	}
	public void setTache_type(int tacheType) {
		tache_type = tacheType;
	}
	public int getDotType() {
		return dotType;
	}
	public void setDotType(int dotType) {
		this.dotType = dotType;
	}
	public int getNameModel() {
		return nameModel;
	}
	public void setNameModel(int nameModel) {
		this.nameModel = nameModel;
	}
}
