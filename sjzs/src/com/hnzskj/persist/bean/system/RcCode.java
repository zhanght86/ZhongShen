/*
 * @项目名称: OA
 * @文件名称: rccode.java
 * @日期: 2012-8-21
 * @版权: 2012 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.persist.bean.system;

  /**        
 * 
 * 类名称：rccode
 * 类描述：<br/>
 * 创建人：郑辉  <br/>
 * 创建时间：2012-8-21 上午11:10:30 
 * 修改人：Administrator
 * 修改时间：
 * 修改备注：   
 * @version   1.0     
 */

public class RcCode {
	private String rc_Id;			//字典编号
	private String rc_no;			//父级字典
	private String rc_name;			//名称
	private String rc_key;			//键
	private String rc_value;		//值
	private Integer rc_order;		//排序
	private String rc_parent;		//父级菜单
	private Integer rc_type; 		//父级字典的类型  0 默认设置	1 从数据库查询
	private String rc_querySql;		// 查询其他表中数据，构成一个  key - value 的字典形式
	private String rc_Object;		//查询对应的实体对象
	private String rc_desc;
	private String codeTemp;
	private Integer classType=0;
	/**
	 * 
	 */
	public RcCode() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the rc_Id
	 */
	public String getRc_Id() {
		return rc_Id;
	}
	/**
	 * @param rcId the rc_Id to set
	 */
	public void setRc_Id(String rcId) {
		rc_Id = rcId;
	}
	/**
	 * @return the rc_name
	 */
	public String getRc_name() {
		return rc_name;
	}
	/**
	 * @param rcName the rc_name to set
	 */
	public void setRc_name(String rcName) {
		rc_name = rcName;
	}
	/**
	 * @return the rc_key
	 */
	public String getRc_key() {
		return rc_key;
	}
	/**
	 * @param rcKey the rc_key to set
	 */
	public void setRc_key(String rcKey) {
		rc_key = rcKey;
	}
	/**
	 * @return the rc_value
	 */
	public String getRc_value() {
		return rc_value;
	}
	/**
	 * @param rcValue the rc_value to set
	 */
	public void setRc_value(String rcValue) {
		rc_value = rcValue;
	}
	/**
	 * @return the rc_order
	 */
	public Integer getRc_order() {
		return rc_order;
	}
	/**
	 * @param rcOrder the rc_order to set
	 */
	public void setRc_order(Integer rcOrder) {
		rc_order = rcOrder;
	}
	/**
	 * @return the rc_parent
	 */
	public String getRc_parent() {
		return rc_parent;
	}
	/**
	 * @param rcParent the rc_parent to set
	 */
	public void setRc_parent(String rcParent) {
		rc_parent = rcParent;
	}
	/**
	 * @return the rc_desc
	 */
	public String getRc_desc() {
		return rc_desc;
	}
	/**
	 * @param rcDesc the rc_desc to set
	 */
	public void setRc_desc(String rcDesc) {
		rc_desc = rcDesc;
	}
	/**
	 * @return the codeTemp
	 */
	public String getCodeTemp() {
		return codeTemp;
	}
	/**
	 * @param codeTemp the codeTemp to set
	 */
	public void setCodeTemp(String codeTemp) {
		this.codeTemp = codeTemp;
	}
	/**
	 * @return the classType
	 */
	public Integer getClassType() {
		return classType;
	}
	/**
	 * @param classType the classType to set
	 */
	public void setClassType(Integer classType) {
		this.classType = classType;
	}
	/**
	 * @return the rc_no
	 */
	public String getRc_no() {
		return rc_no;
	}
	/**
	 * @param rcNo the rc_no to set
	 */
	public void setRc_no(String rcNo) {
		rc_no = rcNo;
	}
	/**
	 * @return the rc_type
	 */
	public Integer getRc_type() {
		return rc_type;
	}
	/**
	 * @param rcType the rc_type to set
	 */
	public void setRc_type(Integer rcType) {
		rc_type = rcType;
	}
	/**
	 * @return the rc_querySql
	 */
	public String getRc_querySql() {
		return rc_querySql;
	}
	/**
	 * @param rcQuerySql the rc_querySql to set
	 */
	public void setRc_querySql(String rcQuerySql) {
		rc_querySql = rcQuerySql;
	}
	/**
	 * @return the rc_Object
	 */
	public String getRc_Object() {
		return rc_Object;
	}
	/**
	 * @param rcObject the rc_Object to set
	 */
	public void setRc_Object(String rcObject) {
		rc_Object = rcObject;
	}
}
