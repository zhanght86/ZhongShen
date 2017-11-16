/*@目名称：lwsj
 *@文件名：RegLoginServiceImpl.java
 *@期：下午02:43:27
 *@权：2011 河南中审科技有限公司
 *@开发公司或单位:河南中审科技有限公司
 */
package com.hnzskj.service.fore.impl;

import com.hnzskj.persist.bean.fore.ClientInfoDTO;
import com.hnzskj.persist.bean.system.Employee;
import com.hnzskj.persist.dao.fore.RegLoginDao;
import com.hnzskj.service.fore.RegLoginService;

/**
 *类名称:RegLoginServiceImpl 类描述：<br/>
 *创建人： 平西强<br/>
 *创建时间:2013-3-4下午04:18:10<br/>
 *修改人: Administrator<br/>
 *修改时间:2013-3-4下午04:18:10<br/>
 *修改备注: <br/>
 * 
 * @version 1.0
 *@param <E>
 */
public class RegLoginServiceImpl implements RegLoginService {
	private RegLoginDao regLoginDao = null;
	private Employee employee = null;

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
	 * @return the regLoginDao
	 */
	public RegLoginDao getRegLoginDao() {
		return regLoginDao;
	}

	/**
	 * @param regLoginDao
	 *            the regLoginDao to set
	 */
	public void setRegLoginDao(RegLoginDao regLoginDao) {
		this.regLoginDao = regLoginDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hnzskj.service.fore.RegLoginService#addEmployee(com.hnzskj.persist
	 * .bean.system.Employee)
	 */
	@Override
	public boolean addEmployee(Employee employee) {
		int result = 0;
		if (employee.getUserKey() != null
				&& regLoginDao.checkEmplInfo(employee.getUserKey()) != null) {
			return false;
		}
		if ((employee.getEmplAccount() != null)
				&& (!"".equals(regLoginDao.getPassByName(
						employee.getEmplAccount()).trim()))
				&& (!"0".equals(regLoginDao.getPassByName(
						employee.getEmplAccount()).trim()))) {
			return false;
		}
		employee.setPowerT("1");
		result = regLoginDao.addEmployee(employee);
		ClientInfoDTO clientInfo =new ClientInfoDTO();
		clientInfo.setClientId(employee.getEmplId());
		clientInfo.setTitle("审计初级学徒");
		clientInfo.setRank("一级");
		regLoginDao.addClientInfo(clientInfo);
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hnzskj.service.fore.RegLoginService#loginEmployee(com.hnzskj.persist
	 * .bean.system.Employee)
	 */
	@Override
	public Employee loginEmployee(Employee employee, String loginIp) {
		return regLoginDao.loginEmployee(employee, loginIp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hnzskj.service.fore.RegLoginService#updateEmployee(com.hnzskj.persist
	 * .bean.system.Employee)
	 */
	@Override
	public Employee updateEmployee(Employee employee) {
		return regLoginDao.updateEmployee(employee);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hnzskj.service.fore.RegLoginService#updatePass(com.hnzskj.persist
	 * .bean.system.Employee, java.lang.String)
	 */
	@Override
	public Employee updatePass(Employee employee, String newPass) {
		return regLoginDao.updatePass(employee, newPass);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hnzskj.service.fore.RegLoginService#checkEmplInfo(java.lang.String)
	 */
	@Override
	public Employee checkEmplInfo(String userKeyId) {
		return regLoginDao.checkEmplInfo(userKeyId);
	}

}
