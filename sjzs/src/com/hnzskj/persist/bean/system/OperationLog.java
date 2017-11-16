package com.hnzskj.persist.bean.system;


import java.util.Date;


/**
 * 
 * 
 * 类名称：OperationLog     <br/>
 * 类描述：用户操作日志类，记录用户在什么时间执行了什么操作<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-5-17 下午02:47:45   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-5-17 下午02:47:45   <br/>  
 * 修改备注：     <br/>
 * @version   1.0
 */
public class OperationLog {
	//数据库操作标志字段
	private String oid;
	//执行操作的用户
	private String operator;
	//操作时间
	private Date oprtime;
	//执行什么操作
	private String operation;
	//登录的IP地址
	private String loginIp;
	
	public OperationLog() {
	}
	
	public OperationLog(String operator, String operation, String loginIp) {
		this.operator = operator;
		this.operation = operation;
		this.loginIp = loginIp;
	}

	/**
	 * @return the oid
	 */
	public String getOid() {
		return oid;
	}

	/**
	 * @param oid the oid to set
	 */
	public void setOid(String oid) {
		this.oid = oid;
	}

	/**
	 * 执行操作的用户
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * 执行操作的用户
	 * @param empl the operator to set
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * 操作时间
	 * @return the oprtime
	 */
	public Date getOprtime() {
		return oprtime;
	}

	/**
	 * 操作时间
	 * @param oprtime the oprtime to set
	 */
	public void setOprtime(Date oprtime) {
		this.oprtime = oprtime;
	}

	/**
	 * 用户执行的操作
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * 用户执行的操作
	 * @param operation the operation to set
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

	/**
	 * 登录IP
	 * @return the loginIp
	 */
	public String getLoginIp() {
		return loginIp;
	}

	/**
	 * 登录IP
	 * @param loginIp the loginIp to set
	 */
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OperationLog [loginIp=" + loginIp + ", oid=" + oid
				+ ", operation=" + operation + ", operator=" + operator
				+ ", oprtime=" + oprtime + "]";
	}
	
	
}
