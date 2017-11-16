/*
 * @项目名称: htglxt
 * @文件名称: InitCodeTemp.java
 * @日期: 2012-3-19 下午02:40:05  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.persist.bean.init;

import java.io.Serializable;

/**    
 * 项目名称：htglxt   <br/>
 * 类名称：InitCodeTemp.java   <br/>
 * 类描述：   项目编号生成规则模板<br/>
 * 创建人：王亲臣   <br/>
 * 创建时间：2012-3-19 下午02:40:05   <br/>
 * 修改人：王亲臣   <br/>
 * 修改时间：2012-3-19 下午02:40:05   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class InitCodeTemp implements Serializable{
	private static final long serialVersionUID = 5376873185495673258L;
	/**
	 * 名称。一级部门、二级部门......
	 */
	private String name;
	/**
	 * 项目编码
	 */
	private String code;
	/**
	 * 部门级别
	 */
	private String grade;
	/**
	 * 是否选择启用。0 未启用 1启用
	 */
	private String chose;
	
	/**
	 * 级别名称
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 级别名称
	 * @param name the name to set 
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 项目编码
	 * @return code 
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * 项目编码
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * 部门级别
	 * @return grade
	 */
	public String getGrade() {
		return grade;
	}
	
	/**
	 * 部门级别
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**
	 * 是否选择启用
	 * @return chose 
	 */
	public String getChose() {
		return chose;
	}
	
	/**
	 * 是否选择启用
	 * @param chose the chose to set
	 */
	public void setChose(String chose) {
		this.chose = chose;
	}
	
	
	
}
