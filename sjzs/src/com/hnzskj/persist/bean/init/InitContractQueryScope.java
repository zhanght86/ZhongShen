/*
 * @项目名称: htglxt
 * @文件名称: InitContractQueryScope.java
 * @日期: 2012-4-16
 * @版权: 2012 河南中审科技有限公司 
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.persist.bean.init;

/**        
 * 
 * 类名称：InitContractQueryScope     <br/>
 * 类描述：合同年度查询－查询范围<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2012-4-16 上午11:28:29   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2012-4-16 上午11:28:29   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */
public class InitContractQueryScope {
	private String name;
	
	private String scope;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the scope
	 */
	public String getScope() {
		return scope;
	}

	/**
	 * @param scope the scope to set
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}
	
	
}
