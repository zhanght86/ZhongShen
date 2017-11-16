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
 * 类名称：Role     <br/>
 * 类描述：<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-5-26 下午02:36:19   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-5-26 下午02:36:19   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */
public class Role {
	
	/**角色数据库标志id*/
	private String roleId;
	
	/**角色名称*/
	private String roleName;
	
	/**角色说明*/
	private String roleRemark;
	
	/**
	 * 角色类型
	 */
	private String roleType;

	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the roleRemark
	 */
	public String getRoleRemark() {
		return roleRemark;
	}

	/**
	 * @param roleRemark the roleRemark to set
	 */
	public void setRoleRemark(String roleRemark) {
		this.roleRemark = roleRemark;
	}

	/**
	 * @return the roleType
	 */
	public String getRoleType() {
		return roleType;
	}

	/**
	 * @param roleType the roleType to set
	 */
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName
				+ ", roleRemark=" + roleRemark + ", roleType=" + roleType + "]";
	}
	
}
