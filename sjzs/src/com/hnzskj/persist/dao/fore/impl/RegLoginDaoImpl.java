/*@目名称：lwsj
 *@文件名：RegLoginDaoImpl.java
 *@期：下午02:47:58
 *@权：2011 河南中审科技有限公司
 *@开发公司或单位:河南中审科技有限公司
 */
package com.hnzskj.persist.dao.fore.impl;

import java.util.List;

import com.hnzskj.common.BaseDao;
import com.hnzskj.persist.bean.fore.ClientInfoDTO;
import com.hnzskj.persist.bean.system.Employee;
import com.hnzskj.persist.dao.fore.RegLoginDao;
import com.hnzskj.persist.dao.system.EmployeeDao;

/**
 *类名称:RegLoginDaoImpl 类描述：<br/>
 *创建人： 平西强<br/>
 *创建时间:2013-3-4下午04:17:14<br/>
 *修改人: Administrator<br/>
 *修改时间:2013-3-4下午04:17:14<br/>
 *修改备注: <br/>
 * 
 * @version 1.0
 *@param <E>
 */
public class RegLoginDaoImpl extends BaseDao implements RegLoginDao {

	private Employee employee = null;
	private EmployeeDao employeeDao = null;
	private List<Employee> employees = null;

	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @param employee
	 *            the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * @return the employeeDao
	 */
	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	/**
	 * @param employeeDao
	 *            the employeeDao to set
	 */
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	/**
	 * @return the employees
	 */
	public List<Employee> getEmployees() {
		return employees;
	}

	/**
	 * @param employees
	 *            the employees to set
	 */
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hnzskj.persist.dao.fore.RegLoginDao#addEmployee(com.hnzskj.persist
	 * .bean.system.Employee)
	 */
	public int addEmployee(Employee employee) {
		String sql = "insert into employee (emplId, emplName, "
				+ " emplAccount, emplPassword,  "
				+ "timeOrder,  userKeyId , powerT ) "
				+ "values (?, ?, ?, ?, ?, ?, 1 )";
		Object[] params = { employee.getEmplId(), employee.getEmplName(),
				 employee.getEmplAccount(),	employee.getEmplPassword(),
				employee.getTimeOrder(),employee.getUserKey() };
		int result = update(sql, params);// 将员工信息存入数据库
		return result;
	}

	public String getPassByName(String emplName) {
		String resuString = "";
		Object result = null;
		String sql = "select emplPassword from employee where emplAccount=? and powerT=1 ";
		Object[] params = { emplName };
		result = getSingleValue(sql, params);
		if (result == null) {
			resuString = "";
		} else {
			resuString = result.toString();
		}
		return resuString;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hnzskj.persist.dao.fore.RegLoginDao#loginEmployee(com.hnzskj.persist
	 * .bean.system.Employee)
	 */
	@Override
	public Employee loginEmployee(Employee employee, String loginIP) {
		Employee employe = null;
		String sql = "select * from employee where emplAccount = ? and emplPassword = ? and powerT=1 ";
		Object[] params = { employee.getEmplAccount(),
				employee.getEmplPassword() };
		employe = (Employee) get(sql, Employee.class, params);
		if (employe != null) {
			String date = (new java.sql.Date(new java.util.Date().getTime())
					.toString());
			String[] fields = new String[] { "lastLoginDate", "lastLoginIp" };
			Object[] paramsT = new Object[] { date, loginIP,
					employe.getEmplId() };
			String sqls = getUpdateSql("employee", fields, "emplId");
			int v = this.update(sqls, paramsT);
			if (v > 0) {
				return employe;
			} else {
				return null;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hnzskj.persist.dao.fore.RegLoginDao#updateEmployee(com.hnzskj.persist
	 * .bean.system.Employee)
	 */
	@Override
	public Employee updateEmployee(Employee employee) {
		String sql = "update employee set  emplSex=?,  emplIdCard=?, emplAge=?, "
				+ "emplAddress=?, emplMobile=?, emplOfficeTel=?, emplEmail=?, emplqq=?,  "
				+ "emplHomeTel=? where emplId = ?";
		Object[] params = { employee.getEmplSex(), employee.getEmplIdCard(),
				employee.getEmplAge(), employee.getEmplAddress(),
				employee.getEmplMobile(), employee.getEmplOfficeTel(),
				employee.getEmplEmail(), employee.getEmplqq(),
				employee.getEmplHomeTel(), employee.getEmplId() };
		int value = update(sql, params);
		/*if (value != 0 && employee.getOrgId()!=null) {
			String sqls = "update empl_org_relation set orgId=? where emplId=?";
			Object[] paramss = { employee.getOrgId(), employee.getEmplId() };
			update(sqls, paramss);
		}*/
		if (value > 0) {
			employee = (Employee)get("select * from employee where emplId =?", Employee.class, new Object[]{employee.getEmplId()});
			return employee;
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hnzskj.persist.dao.fore.RegLoginDao#updatePass(com.hnzskj.persist
	 * .bean.system.Employee, java.lang.String)
	 */
	@Override
	public Employee updatePass(Employee employee, String newPass) {
		int value = employeeDao.updateEmployee(new String[] { "emplPassword" },
				new Object[] { newPass, employee.getEmplId() });
		if (value > 0) {
			return employee;
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hnzskj.persist.dao.fore.RegLoginDao#checkEmplInfo(java.lang.String)
	 */
	@Override
	public Employee checkEmplInfo(String userKeyId) {
		String sql = "select * from employee where userkeyId = ?";
		Object[] params = new Object[] { userKeyId };
		employee = (Employee) get(sql, Employee.class, params);
		return employee;
	}

	@Override
	public int addClientInfo(ClientInfoDTO clientInfo) {
		String sql ="insert into sjzs_client_Info(id,clientId,title,rank) values(?,?,?,?)";
		Object[] params = new Object[]{getGUID(),clientInfo.getClientId(),clientInfo.getTitle(),clientInfo.getRank()};
		return this.update(sql, params);
	}
}
