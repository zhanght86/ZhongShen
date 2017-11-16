/*
 * @项目名称: htglxt
 * @文件名称: Power.java
 * @日期: 2011-5-26
 * @版权: 2011 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.persist.bean.system;

/**        
 * 
 * 类名称：Power     <br/>
 * 类描述：<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-5-26 下午02:36:35   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-5-26 下午02:36:35   <br/>  
 * 修改备注：<br/>
 * @version   1.0     
 */
public class Power implements Comparable<Power>{
	
	/**权限数据库标志id*/
	private String powerId;
	
	/**权限名称*/
	private String powerName;  

	/**权限URL*/
	private String powerUrl;
	
	/**当前功能是模块,还是操作*/
	private boolean powerModule;
	
	/**当前权限的父节点编号*/
	private String powerParent;
	
	/**当前权限的父节点*/
	private Power parentNode;
	
	/**当前权限是否是超级权限*/
	private boolean powerSuperPower;
	private String powerType;
	
	/**
	 * 权限图标
	 */
	private String powerImg;
	
	/**
	 * 排序字段
	 */
	private Integer powerOrder;

	public Power() {}

	public Power(String powerId, String powerName, String powerParent) {
		super();
		this.powerId = powerId;
		this.powerName = powerName;
		this.powerParent = powerParent;
	}



	/**
	 * 权限数据库标志i
	 * @return the pid
	 */
	public String getPowerId() {
		return powerId;
	}

	/**
	 * 权限数据库标志i
	 * @param pid the pid to set
	 */
	public void setPowerId(String powerId) {
		this.powerId = powerId;
	}

	/**
	 * 权限名称
	 * @return the powerName
	 */
	public String getPowerName() {
		return powerName;
	}

	/**
	 * 权限名称
	 * @param pname the pname to set
	 */
	public void setPowerName(String powerName) {
		this.powerName = powerName;
	}

	/**
	 * 权限URL
	 * @return the purl
	 */
	public String getPowerUrl() {
		return powerUrl;
	}

	/**
	 * 权限URL
	 * @param purl the purl to set
	 */
	public void setPowerUrl(String powerUrl) {
		this.powerUrl = powerUrl;
	}
	/**
	 * 当前权限的父节点编号
	 * @return the powerParent
	 */
	public String getPowerParent() {
		return powerParent;
	}

	/**
	 * 当前权限的父节点
	 * @return the parentNode
	 */
	public Power getParentNode() {
		return parentNode;
	}

	/**
	 * 当前权限的父节点
	 * @param parentNode the parentNode to set
	 */
	public void setParentNode(Power parentNode) {
		this.parentNode = parentNode;
	}

	/**
	 * 是否是模块
	 * @return the powerModule
	 */
	public boolean isPowerModule() {
		return powerModule;
	}

	/**
	 * 是否是模块
	 * @param powerModule the powerModule to set
	 */
	public void setPowerModule(boolean powerModule) {
		this.powerModule = powerModule;
	}

	/**
	 * 是否是超级权限
	 * @return the powerSuperPower
	 */
	public boolean isPowerSuperPower() {
		return powerSuperPower;
	}

	/**
	 * 是否是超级权限
	 * @param powerSuperPower the powerSuperPower to set
	 */
	public void setPowerSuperPower(boolean powerSuperPower) {
		this.powerSuperPower = powerSuperPower;
	}

	/**
	 * 父级菜单Id
	 * @param powerParent the powerParent to set
	 */
	public void setPowerParent(String powerParent) {
		this.powerParent = powerParent;
	}

	/**
	 * 排序字段
	 * @return the powerOrder
	 */
	public Integer getPowerOrder() {
		return powerOrder;
	}

	/**
	 * 排序字段
	 * @param powerOrder the powerOrder to set
	 */
	public void setPowerOrder(Integer powerOrder) {
		this.powerOrder = powerOrder;
	}

	/**
	 * @return the powerImg
	 */
	public String getPowerImg() {
		return powerImg;
	}

	/**
	 * 权限类型
	 * @return the powerType
	 */
	public String getPowerType() {
		return powerType;
	}

	/**
	 * 权限类型
	 * @param powerType the powerType to set
	 */
	public void setPowerType(String powerType) {
		this.powerType = powerType;
	}

	/**
	 * @param powerImg the powerImg to set
	 */
	public void setPowerImg(String powerImg) {
		this.powerImg = powerImg;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((powerId == null) ? 0 : powerId.hashCode());
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
		Power other = (Power) obj;
		if (powerId == null) {
			if (other.powerId != null)
				return false;
		} else if (!powerId.equals(other.powerId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Power temp) {
		if ( powerOrder > temp.getPowerOrder() ) {
			return 1;
		} else if (powerOrder == temp.getPowerOrder()) {
			return 0;
		} else {
			return -1;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Power [parentNode=" + parentNode + ", powerId=" + powerId
				+ ", powerImg=" + powerImg + ", powerModule=" + powerModule
				+ ", powerName=" + powerName + ", powerOrder=" + powerOrder
				+ ", powerParent=" + powerParent + ", powerSuperPower="
				+ powerSuperPower + ", powerUrl=" + powerUrl + "]";
	}
	
}