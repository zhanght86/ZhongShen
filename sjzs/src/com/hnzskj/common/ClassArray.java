/*
 * @项目名称: OA
 * @文件名称: ArrayClass.java
 * @日期: 2012-7-31
 * @版权: 2012 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.common;

import java.util.ArrayList;
import java.util.List;

  /**        
 * 
 * 类名称：ArrayClass
 * 类描述：<br/>
 * 创建人：郑辉  <br/>
 * 创建时间：2012-7-31 上午11:15:27 
 * 修改人：Administrator
 * 修改时间：
 * 修改备注：   
 * @version   1.0     
 */

public class ClassArray<E> {
	private int classType ;  //0:默认 1:添加    2：修改  3：删除 	
	private List<Object> list=new ArrayList<Object>();

	/**
	 * 
	 */
	public ClassArray() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param classType
	 * @param list
	 */
	public ClassArray(int classType, List<Object> list) {
		super();
		this.classType = classType;
		this.list = list;
	}

	/**
	 * @return the classType
	 */
	public int getClassType() {
		return classType;
	}

	/**
	 * @param classType the classType to set
	 */
	public void setClassType(int classType) {
		this.classType = classType;
	}

	/**
	 * @return the list
	 */
	public List<Object> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<Object> list) {
		this.list = list;
	}
	

}
