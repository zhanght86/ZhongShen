/*
 * @项目名称: htglxt
 * @文件名称: Role.java
 * @日期: 2011-5-26
 * @版权: 2011 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.persist.bean.system;

/**        
 * 
 * 类名称：UserRole     <br/>
 * 类描述：<br/>
 * 创建人：丁艳伟   <br/>
 * 创建时间：2012年3月9日9:42:57   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */
public class UserRole {
	
	/**用户Id*/
	private Integer uid;
	
	/**角色代码*/
	private String rcode;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getRcode() {
		return rcode;
	}

	public void setRcode(String rcode) {
		this.rcode = rcode;
	}

	@Override
	public String toString() {
		return "UserRole [rcode=" + rcode + ", uid=" + uid + ", getRcode()="
				+ getRcode() + ", getUid()=" + getUid() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
	
	
}
