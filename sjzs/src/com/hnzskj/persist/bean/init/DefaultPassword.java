/*
 * @项目名称: htglxt
 * @文件名称: DefaultPassword.java
 * @日期: 2011-7-17
 * @版权: 2011 河南中审科技有限公司 
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.persist.bean.init;

/**        
 * 
 * 类名称：DefaultPassword     <br/>
 * 类描述：<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-7-17 下午03:00:12   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-7-17 下午03:00:12   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */
public class DefaultPassword {
	/**用户的初始密码*/
	private String password;

	/**
	 * 用户的初始密码
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 用户的初始密码
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
