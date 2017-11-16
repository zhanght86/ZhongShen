/*
 * @项目名称: htglxt
 * @文件名称: Employee.java
 * @日期: 2011-5-26
 * @版权: 2011 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.persist.bean.system;

import java.sql.Date;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



/**        
 * 
 * 类名称：Employee     <br/>
 * 类描述：用户信息Bean<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-5-26 上午10:03:20   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-5-26 上午10:03:20   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */
public class Employee {
	/**用户数据库标志id*/
	private String emplId;
	
	/**用户姓名*/
	private String emplName;
	
	/**用户性别*/
	private String emplSex;
	
	/**用户状态，未启用，正常，离职，停用*/
	private String emplStatus;
	
	/**用户身份证号*/
	private String emplIdCard;
	
	/**用户年龄*/
	private Integer emplAge;
	
	/**用户住址*/
	private String emplAddress;
	
	/**用户手机号*/
	private String emplMobile;
	
	
	/**用户办公电话*/
	private String emplOfficeTel;
	
	/**家庭电话*/
	private String emplHomeTel;
	
	/**用户Email*/
	private String emplEmail;
	
	/**用户qq*/
	private String emplqq;
	
	/**用户入职日期*/
	private Date emplRuZhi;
	
	/**用户所属机构编码*/
	private String orgId;
	
	/**用户所属部门*/
	private String orgName;
	
	/**用户职位*/
	private String emplPosition;
	
	/**用户登陆系统账号*/
	private String emplAccount;
	
	/**用户登陆系统账号密码*/
	private String emplPassword = "";
	
	/**用户所有角色编号*/
	private String rolesCode;
	
	/**用户所有权限信息编号*/
	private String powersCode;
	
	/**用户登陆IP*/
	private String loginIp;
	
	/**个人印章*/
	private String emplSignet;
	
	/**部门印章*/
	private String orgSignet;

	/**最后一次登录Ip*/
	private String lastLoginIp;

	/**最后一次登录时间*/
	private String lastLoginDate;
	
	/**标志字段用于区分前台用户，后台用户*/
	private String powerT;
	
	/**备用字段*/
	private String note2;
	
	/**添加时间*/
	private String timeOrder;
	
	/**排序字段－备用*/
	private String emplOrder;
	private int count;
	
	/**用户拥有的角色*/
	private Set<Role> roles = new HashSet<Role>();

	/**用户直接拥有的权限*/
	private Set<Power> powers = new HashSet<Power>();
	
	/**主管的部门*/
	private List<Organization> manageOrgs = new ArrayList<Organization>(); 
	
	private int colSort;
	
	
	private String userKey;
	
	public Employee() {}
	
	public Employee(String emplId, String orgId) {
		this.emplId = emplId;
		this.orgId = orgId;
	}
	
	/**
	 * @return the userKey
	 */
	public String getUserKey() {
		return userKey;
	}

	/**
	 * @param userKey the userKey to set
	 */
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	/**
	 * 数据库标志id
	 * @return the uid	
	 */
	public String getEmplId() {
		return emplId;
	}

	/**
	 * 设置数据库标志id
	 * @param uid the uid to set
	 */
	public void setEmplId(String emplId) {
		this.emplId = emplId;
	}

	/**
	 * 用户姓名
	 * @return the uname 
	 */
	public String getEmplName() {
		return emplName;
	}

	/**
	 * 用户姓名
	 * @param emplName the emplName to set
	 */
	public void setEmplName(String emplName) {
		this.emplName = emplName;
	}

	/**
	 * 用户性别
	 * @return the emplSex 
	 */
	public String getEmplSex() {
		return emplSex;
	}

	/**
	 *  用户性别
	 * @param usex the emplSex to set
	 */
	public void setEmplSex(String emplSex) {
		this.emplSex = emplSex;
	}

	/**
	 * 用户帐户状态 未启用，正常，离职	
	 * @return the ustatus
	 */
	public String getEmplStatus() {
		return emplStatus;
	}

	/**
	 * 设置用户帐户状态 未启用，正常，离职	
	 * @param emplStatus the emplStatus to set
	 */
	public void setEmplStatus(String emplStatus) {
		this.emplStatus = emplStatus;
	}

	/**
	 * 用户身份证号
	 * @return the emplIdCard
	 */
	public String getEmplIdCard() {
		return emplIdCard;
	}

	/**
	 * 设置用户身份证号
	 * @param emplIdCard the emplIdCard to set
	 */
	public void setEmplIdCard(String emplIdCard) {
		this.emplIdCard = emplIdCard;
	}

	/**
	 * 用户年龄
	 * @return the uage
	 */
	public Integer getUage() {
		return emplAge;
	}

	/**
	 * 设置用户年龄
	 * @param uage the emplAge to set
	 */
	public void setUage(Integer emplAge) {
		this.emplAge = emplAge;
	}

	/**
	 * 用户住址
	 * @return the emplAddress
	 */
	public String getEmplAddress() {
		return emplAddress;
	}

	/**
	 * 设置用户住址
	 * @param uaddress the emplAddress to set
	 */
	public void setEmplAddress(String emplAddress) {
		this.emplAddress = emplAddress;
	}

	/**
	 * 用户手机号
	 * @return the emplMobile
	 */
	public String getEmplMobile() {
		return emplMobile;
	}

	/**
	 * 设置用户手机号
	 * @param ucellphone the emplMobile to set
	 */
	public void setEmplMobile(String emplMobile) {
		this.emplMobile = emplMobile;
	}

	/**
	 * 用户办公电话
	 * @return the emplOfficeTel
	 */
	public String getEmplOfficeTel() {
		return emplOfficeTel;
	}

	/**
	 * 设置用户办公电话
	 * @param emplOfficeTel the emplOfficeTel to set
	 */
	public void setEmplOfficeTel(String emplOfficeTel) {
		this.emplOfficeTel = emplOfficeTel;
	}

	/**
	 * 用户Email
	 * @return the emplEmail
	 */
	public String getEmplEmail() {
		return emplEmail;
	}

	/**
	 * 设置用户Email
	 * @param uemail the emplEmail to set
	 */
	public void setEmplEmail(String emplEmail) {
		this.emplEmail = emplEmail;
	}

	/**
	 * 用户QQ或MSNS
	 * @return the emplqq
	 */
	public String getEmplqq() {
		return emplqq;
	}

	/**
	 * 设置用户QQ或MSN
	 * @param uqq the emplqq to set
	 */
	public void setEmplqq(String emplqq) {
		this.emplqq = emplqq;
	}

	/**
	 * 用户入职日期
	 * @return the emplRuZhi
	 */
	public Date getEmplRuZhi() {
		return emplRuZhi;
	}

	/**
	 * 设置用户入职日期
	 * @param emplRuZhi the emplRuZhi to set
	 */
	public void setEmplRuZhi(Date emplRuZhi) {
		this.emplRuZhi = emplRuZhi;
	}

	/**
	 * 设置用户所属机构
	 * @return the orgId
	 */
	public String getOrgId() {
		return orgId;
	}

	/**
	 * 用户所属机构
	 * @param ussjg the orgId to set
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * 设置用户职位
	 * @param uposition the uposition to set
	 */
	public void setEmplPosition(String emplPosition) {
		this.emplPosition = emplPosition;
	}

	/**
	 * 用户职位
	 * @return the uposition
	 */
	public String getEmplPosition() {
		return emplPosition;
	}

	/**
	 * 用户登陆系统帐号
	 * @return the emplAccount
	 */
	public String getEmplAccount() {
		return emplAccount;
	}

	/**
	 * 设置用户登陆系统帐号
	 * @param username the emplAccount to set
	 */
	public void setEmplAccount(String emplAccount) {
		this.emplAccount = emplAccount;
	}

	/**
	 * 用户登陆系统密码
	 * @return the upassword
	 */
	public String getEmplPassword() {
		return emplPassword;
	}

	/**
	 * 设置用户登陆系统密码
	 * @param upassword the upassword to set
	 */
	public void setEmplPassword(String empPassword) {
		this.emplPassword = empPassword;
	}

	/**
	 * 用户拥有的角色
	 * @return the roles
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * 设置用户拥有的角色
	 * @param roles the roles to set
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	/**
	 * 用户拥有权限
	 * @return the powers
	 */
	public Set<Power> getPowers() {
		return powers;
	}

	/**
	 * 设置用户拥有权限
	 * @param powers the powers to set
	 */
	public void setPowers(Set<Power> powers) {
		this.powers = powers;
	}

	/**
	 * 私用，分配角色时使用
	 * @return the rolesCode
	 */
	public String getRolesCode() {
		return rolesCode;
	}

	/**
	 * 私用，分配角色时使用
	 * @param rolesCode the rolesCode to set
	 */
	public void setRolesCode(String rolesCode) {
		this.rolesCode = rolesCode;
	}

	/**
	 * 私用，分配权限时使用
	 * @return the powersCode
	 */
	public String getPowersCode() {
		return powersCode;
	}

	/**
	 * 私用，分配权限时使用
	 * @param powersCode the powersCode to set
	 */
	public void setPowersCode(String powersCode) {
		this.powersCode = powersCode;
	}

	/**
	 * 用户登陆IP，登陆时设置
	 * @return the loginIp
	 */
	public String getLoginIp() {
		return loginIp;
	}

	/**
	 * 用户登陆IP，登陆时设置
	 * @param loginIp the loginIp to set
	 */
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	
	/**
	 * 个人电子签章
	 * @return the emplSignet
	 */
	public String getEmplSignet() {
		return emplSignet;
	}

	/**
	 * 个人电子签章
	 * @param emplSignet the emplSignet to set
	 */
	public void setEmplSignet(String emplSignet) {
		this.emplSignet = emplSignet;
	}

	/**
	 * 部门电子签章
	 * @return the orgSignet
	 */
	public String getOrgSignet() {
		return orgSignet;
	}

	/**
	 * 部门电子签章
	 * @param orgSignet the orgSignet to set
	 */
	public void setOrgSignet(String orgSignet) {
		this.orgSignet = orgSignet;
	}

	/**
	 * @return the emplAge
	 */
	public Integer getEmplAge() {
		return emplAge;
	}

	/**
	 * @param emplAge the emplAge to set
	 */
	public void setEmplAge(Integer emplAge) {
		this.emplAge = emplAge;
	}

	/**
	 * @return the emplHomeTel
	 */
	public String getEmplHomeTel() {
		return emplHomeTel;
	}

	/**
	 * @param emplHomeTel the emplHomeTel to set
	 */
	public void setEmplHomeTel(String emplHomeTel) {
		this.emplHomeTel = emplHomeTel;
	}

	/**
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * @param orgName the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * @return the lastLoginIp
	 */
	public String getLastLoginIp() {
		return lastLoginIp;
	}

	/**
	 * @param lastLoginIp the lastLoginIp to set
	 */
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	/**
	 * @return the lastLoginDate
	 */
	public String getLastLoginDate() {
		return lastLoginDate;
	}

	/**
	 * @param lastLoginDate the lastLoginDate to set
	 */
	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	
	

	public String getPowerT() {
		return powerT;
	}

	public void setPowerT(String powerT) {
		this.powerT = powerT;
	}

	/**
	 * @return the note2
	 */
	public String getNote2() {
		return note2;
	}

	/**
	 * @param note2 the note2 to set
	 */
	public void setNote2(String note2) {
		this.note2 = note2;
	}

	/**
	 * @return the timeOrder
	 */
	public String getTimeOrder() {
		return timeOrder;
	}

	/**
	 * @param timeOrder the timeOrder to set
	 */
	public void setTimeOrder(String timeOrder) {
		this.timeOrder = timeOrder;
	}

	/**
	 * @return the emplOrder
	 */
	public String getEmplOrder() {
		return emplOrder;
	}

	/**
	 * @param emplOrder the emplOrder to set
	 */
	public void setEmplOrder(String emplOrder) {
		this.emplOrder = emplOrder;
	}
	/**
	 * @return the manageOrgs
	 */
	public List<Organization> getManageOrgs() {
		return manageOrgs;
	}

	/**
	 * @param manageOrgs the manageOrgs to set
	 */
	public void setManageOrgs(List<Organization> manageOrgs) {
		this.manageOrgs = manageOrgs;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the colSort
	 */
	public int getColSort() {
		return colSort;
	}

	/**
	 * @param colSort the colSort to set
	 */
	public void setColSort(int colSort) {
		this.colSort = colSort;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((emplAccount == null) ? 0 : emplAccount.hashCode());
		result = prime * result + ((emplId == null) ? 0 : emplId.hashCode());
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
		Employee other = (Employee) obj;
		if (emplAccount == null) {
			if (other.emplAccount != null)
				return false;
		} else if (!emplAccount.equals(other.emplAccount))
			return false;
		if (emplId == null) {
			if (other.emplId != null)
				return false;
		} else if (!emplId.equals(other.emplId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Employee [emplAccount=" + emplAccount + ", emplAddress="
				+ emplAddress + ", emplAge=" + emplAge + ", emplEmail="
				+ emplEmail + ", emplHomeTel=" + emplHomeTel + ", emplId="
				+ emplId + ", emplIdCard=" + emplIdCard + ", emplMobile="
				+ emplMobile + ", emplName=" + emplName + ", emplOfficeTel="
				+ emplOfficeTel + ", emplOrder=" + emplOrder
				+ ", emplPassword=" + emplPassword + ", emplPosition="
				+ emplPosition + ", emplRuZhi=" + emplRuZhi + ", emplSex="
				+ emplSex + ", emplSignet=" + emplSignet + ", emplStatus="
				+ emplStatus + ", emplqq=" + emplqq + ", lastLoginDate="
				+ lastLoginDate + ", lastLoginIp=" + lastLoginIp + ", loginIp="
				+ loginIp + ", manageOrgs=" + manageOrgs + ", powerT=" + powerT
				+ ", note2=" + note2 + ", orgId=" + orgId + ", orgName="
				+ orgName + ", orgSignet=" + orgSignet + ", powers=" + powers
				+ ", powersCode=" + powersCode + ", roles=" + roles
				+ ", rolesCode=" + rolesCode + ", timeOrder=" + timeOrder + ", userKey=" + userKey +"]";
	}
}
