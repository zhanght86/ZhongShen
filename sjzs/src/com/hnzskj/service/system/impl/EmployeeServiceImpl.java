/*
 * @项目名称: htglxt
 * @文件名称: EmployeeServiceImpl.java
 * @日期: 2011-5-27
 * @版权: 2011 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.service.system.impl;
import com.hnzskj.common.DataUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.hnzskj.common.Constant;
import com.hnzskj.common.Encrypt;
import com.hnzskj.common.Page;
import com.hnzskj.common.annotation.Description;
import com.hnzskj.common.init.SysParamUtil;
import com.hnzskj.persist.bean.init.DefaultPassword;
import com.hnzskj.persist.bean.system.Employee;
import com.hnzskj.persist.bean.system.Role;
import com.hnzskj.persist.dao.system.EmployeeDao;
import com.hnzskj.service.system.EmployeeService;

/**        
 * 
 * 类名称：EmployeeServiceImpl     <br/>
 * 类描述：<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-5-27 上午10:22:10   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-5-27 上午10:22:10   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */
public class EmployeeServiceImpl implements EmployeeService {
	private static Logger log = Logger.getLogger(EmployeeServiceImpl.class);
	private EmployeeDao employeeDao;


	/**
	 * @return the employeeDao
	 */
	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	/**
	 * @param employeeDao the employeeDao to set
	 */
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.EmployeeService#addEmployee(com.hnzskj.persist.bean.system.Employee)
	 */
	@Override
	@Description("添加员工信息")
	public boolean addEmployee(Employee employee) {
		int result = 0;
		//获得并设置的用户的初始化密码
		SysParamUtil sysp = new SysParamUtil();
		DefaultPassword defaultPass = sysp.getDefaultPassword();
		employee.setEmplPassword(Encrypt.digest(defaultPass.getPassword()));
		@SuppressWarnings("unused")
		int emplCount = 0;
		@SuppressWarnings("unused")
		int emplMaxCount = 0;
//		if (Constant.NORMAL.equals(employee.getUstatus())) {
//			String sqlCondition = " where ustatus = ?";
//			Object[] params = new Object[]{Constant.NORMAL};
//			//获得当前用户的信息在数据库中是第几条记录
//			emplCount = employeeDao.getEmplCount(sqlCondition, params);
//			//获得当前系统允许的最大用户数
//			emplMaxCount = OperatorET99.readCount();
//			//如果当前用户在数据库的行数超过通讯员允许的最大人数则不让用户继续登陆系统
//			if (emplCount > emplMaxCount) {
//				throw new SystemException("数据库中的在职员工人数已经达到最大值：" + emplMaxCount + ",不可以继续添加。");
//			}		
//		}
		result = this.employeeDao.addEmployee(employee);
		
		return result > 0 ? true : false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.EmployeeService#deleteEmployeeById(int)
	 */
	@Override
	@Description("删除员工信息")
	public boolean deleteEmployeeById(String emplId) {
		int result = 0;
		result = this.employeeDao.deleteEmployee(emplId);
		return result == 1 ? true : false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.EmployeeService#deleteEmployeeByIds(java.io.Serializable[])
	 */
	@Override
	@Description("批量删除员工信息")
	public boolean deleteEmployeeByIds(Serializable... ids) {
		int result = 0;
		result = this.employeeDao.deleteEmployee(ids);
		return result == ids.length ? true : false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.EmployeeService#updateEmployee(com.hnzskj.persist.bean.system.Employee)
	 */
	@Override
	@Description("更新员工信息")
	public boolean updateEmployee(Employee employee) {
		int result = 0;
//		int emplCount = 0;
//		int emplMaxCount = 0;
//		if (Constant.NORMAL.equals(employee.getUstatus())) {
//			String sqlCondition = " where ustatus = ? and uid != ?";
//			Object[] params = new Object[]{Constant.NORMAL, employee.getUid()};
//			//获得当前用户的信息在数据库中是第几条记录
//			emplCount = employeeDao.getEmplCount(sqlCondition, params);
//			//获得当前系统允许的最大用户数
//			emplMaxCount = OperatorET99.readCount();
//			//如果当前用户在数据库的行数超过通讯员允许的最大人数则不让用户继续登陆系统
//			if (emplCount > emplMaxCount) {
//				throw new SystemException("数据库中的在职员工人数已经达到最大值：" + emplMaxCount + ",不可以将当前员工修改为在职状态。");
//			}		
//		}
		result = this.employeeDao.updateEmployee(employee);
		
		return result == 1 ? true : false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.EmployeeService#updateEmployee(java.lang.String[], java.lang.Object[])
	 */
	@Override
	@Description("更新员工部分信息")
	public boolean updateEmployee(String[] fields, Object[] params) {
		int result = 0;
		result = this.employeeDao.updateEmployee(fields, params);
		return result == 1 ? true : false;
	}
	
	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.EmployeeService#resetPassword(com.hnzskj.persist.bean.system.Employee)
	 */
	@Override
	@Description("重置密码")
	public boolean resetPassword(Employee empl) {
		int result = 0;
		//获得并设置的用户的初始化密码
		SysParamUtil sysp = new SysParamUtil();
		DefaultPassword defaultPass = sysp.getDefaultPassword();
		String newPassword = Encrypt.digest(defaultPass.getPassword());
		String[] fields = {"emplpassword"};
		Object[] params = {newPassword,empl.getEmplId()};
		result = employeeDao.updateEmployee(fields, params);
		return result > 0 ? true : false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.EmployeeService#findEmployeeById(com.hnzskj.persist.bean.system.Employee)
	 */
	@Override
	@Description("根据uid查询员工信息")
	public Employee findEmployeeById(Employee employee) {
		employee = this.employeeDao.findEmployeeById(employee.getEmplId());
		//findEmployeeByIdAndStatus
		
		return employee;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.EmployeeService#searchEmplByOrg(com.hnzskj.persist.bean.system.Employee)
	 */
	@Override
	@Description("按部门查询员工信息")
	public Page<Employee> searchEmplByOrg(Employee empl, Page<Employee> page) {
		String fields = " emplId, emplName, emplSex, emplStatus, emplIdCard, emplAge, " +
				"emplAddress, emplMobile, emplOfficeTel, emplHomeTel, emplEmail, emplqq, emplRuZhi, " +
				"orgId, emplPosition, emplAccount, emplPassword, lastLoginIp, lastLoginDate, " +
				"timeOrder, emplOrder, note1, note2, dbo.getOrgNameById(orgId) as orgName ";

		StringBuffer sb = new StringBuffer(" where 1 = 1 ");
		List<Object> params = new ArrayList<Object>();
		if (!"".equals(empl.getEmplName()) && null != empl.getEmplName()) {
			sb.append(" and emplName like ? ");
			params.add("%" + empl.getEmplName() + "%");
		}
		if (!"".equals(empl.getOrgId()) && null != empl.getOrgId()) {
			sb.append(" and orgId = ? ");
			params.add(empl.getOrgId());
		}
		if (!"".equals(empl.getEmplStatus()) && null != empl.getEmplStatus()) {
			sb.append(" and emplStatus = ? ");
			params.add(empl.getEmplStatus());
		}
		
		Object [] queryParams= params.toArray();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("orgId", "desc");
		orderby.put("emplId", "desc");
		
		page = this.employeeDao.searchEmployee(null, fields , sb.toString(), queryParams);
		return page;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.EmployeeService#leaveOffice(com.hnzskj.persist.bean.system.Employee)
	 */
	@Override
	@Description("将员工标志为离职")
	public boolean leaveOffice(Employee empl) {
		int result = 0;
		result = this.employeeDao.updateEmployee(new String[]{"emplStatus"}, 
				new Object[]{Constant.DIMISSION,empl.getEmplId()});
		return result == 1 ? true : false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.EmployeeService#searchEmplByOrgAndStauts(com.hnzskj.persist.bean.system.Employee, com.hnzskj.common.Page)
	 */
	@Override
	@Description("根据员工所属部门和在职状态查询员工信息")
	public Page<Employee> searchEmplByOrgAndStauts(Employee empl,
			Page<Employee> page) {
		Object[] queryParams = null;
		String sqlCondition = null;
		queryParams = new Object[]{empl.getOrgId(), empl.getEmplStatus()};
		sqlCondition = " where orgId=? and emplStatus=?";
		String fields = " emplId, emplName, emplSex, emplStatus, emplIdCard, emplAge, " +
				"emplAddress, emplMobile, emplOfficeTel, emplHomeTel, emplEmail, emplqq, emplRuZhi, " +
				"orgId, emplPosition, emplAccount, emplPassword, lastLoginIp, lastLoginDate, " +
				"timeOrder, emplOrder, note1, note2, dbo.getOrgNameById(orgId) as orgName ";
		page = this.employeeDao.searchEmployee(page, fields , sqlCondition, queryParams);
		return page;
	}

	public Page<Employee> searchEmplAll(Page<Employee> page) {
		Object[] queryParams = null;
		String sqlCondition = null;
		queryParams = null;
		sqlCondition = "";
		String fields = " emplId, emplName, emplSex, emplStatus, emplIdCard, emplAge, " +
				"emplAddress, emplMobile, emplOfficeTel, emplHomeTel, emplEmail, emplqq, emplRuZhi, " +
				"orgId, emplPosition, emplAccount, emplPassword, lastLoginIp, lastLoginDate, " +
				"timeOrder, emplOrder, note1, note2, dbo.getOrgNameById(orgId) as orgName ";
		page = this.employeeDao.searchEmployee(page, fields , sqlCondition, queryParams);
		return page;
	}
	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.EmployeeService#addRole(com.hnzskj.persist.bean.system.Employee)
	 */
	@Override
	@Description("给员工分配角色信息")
	public boolean addRole(Employee empl) {
		int result = 0;
		result = this.employeeDao.addRoles(empl);
		return result > 0 ? true : false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.EmployeeService#getRoleCodes(com.hnzskj.persist.bean.system.Employee)
	 */
	@Override
	@Description("查询员工所有角色的编号")
	public String[] getRoleCodes(Employee empl) {
		String[] rcodes = null;
		List<Role> roleList = this.employeeDao.getRoles(empl);
		if (roleList.size() != 0) {
			rcodes = new String[roleList.size()];
			for (int i = 0; i < roleList.size(); i++) {
				rcodes[i] = roleList.get(i).getRoleId();
			}
		}
		return rcodes;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.EmployeeService#getEmplByIdAndStatus(int, java.lang.String)
	 */
	@Override
	@Description("根据员工uid和在职状态查询员工信息")
	public Employee getEmplByIdAndStatus(int uid, String ustatus) {
		Employee empl = null;
		empl = this.employeeDao.findEmployeeByIdAndStatus(uid, ustatus);
		return empl;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.EmployeeService#getAllEmplIdByOrg(java.lang.String)
	 */
	@Override
	@Description("查询某一部门的所有员工")
	public String getAllEmplIdByOrg(String jmcode) {
		List<Employee> empls = new ArrayList<Employee>();
		empls = this.employeeDao.getEmplsIdByOrg(jmcode);
		StringBuffer sb = new StringBuffer("");
		if ( null != empls && empls.size() > 0 ) {
			for (Employee empl : empls) {
				sb.append(empl.getEmplId()).append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.EmployeeService#login(com.hnzskj.persist.bean.system.Employee)
	 */
	@Override
	@Description("员工登陆系统")
	public Employee login(Employee empl) {
		String password = Encrypt.digest( empl.getEmplPassword());
		empl.setEmplPassword(password);
		empl = employeeDao.login(empl);
//		int rowCount = 0;
//		int emplMaxCount = 0;
//		if (null != empl) {
//			//获得当前用户的信息在数据库中是第几条记录
//			rowCount = employeeDao.getEmplRowCount(empl);
//			//获得当前系统允许的最大用户数
//			emplMaxCount = OperatorET99.readCount();
//			//如果当前用户在数据库的行数超过通讯员允许的最大人数则不让用户继续登陆系统
//			if (rowCount > emplMaxCount) {
//				throw new SystemException("当前系统只允许" + emplMaxCount + "名用户使用，您是第" + rowCount +"名用户。");
//			}		
//		}

		return empl;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.EmployeeService#checkAccount(int, java.lang.String)
	 */
	@Override
	@Description("验证当前登陆账户是否存在 ")
	public boolean isExsit(int id, String username) {
		boolean result = false;
		StringBuffer sqlCondition = new StringBuffer("");
		sqlCondition.append(" where emplId != ? and emplName = ? ");
		Object[] params = new Object[]{id, username};
		try {
			int count = employeeDao.getEmplCount(sqlCondition.toString(), params);
			result = count > 0 ? true : false;
		} catch (Exception e) {
			log.error(DataUtil.getStackTraceAsString(e));;
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.EmployeeService#searchEmpl(com.hnzskj.persist.bean.system.Employee, com.hnzskj.common.Page)
	 */
	@Override
	@Description("查询员工信息")
	public Page<Employee> searchEmpl(Employee empl, Page<Employee> page) {
		String fields = " emplId, emplName, emplSex, emplStatus, emplIdCard, emplAge, " +
				"emplAddress, emplMobile, emplOfficeTel, emplHomeTel, emplEmail, emplqq, " +
				"orgId, emplPosition, " +
				//"timeOrder, emplOrder, dbo.getOrgNameById(orgId) as orgName ";
				"timeOrder, emplOrder";
		StringBuffer sb = new StringBuffer(" where 1 = 1 ");
		List<Object> queryParams = new ArrayList<Object>();
		if (!"".equals(empl.getEmplName()) && null != empl.getEmplName()) {
			sb.append(" and emplName like ? ");
			queryParams.add("%" + empl.getEmplName() + "%");
		}
		if (!"".equals(empl.getOrgId()) && null != empl.getOrgId()) {
			sb.append(" and orgId = ? ");
			queryParams.add(empl.getOrgId());
		}
		if (!"".equals(empl.getEmplStatus()) && null != empl.getEmplStatus()) {
			sb.append(" and emplStatus = ? ");
			queryParams.add(empl.getEmplStatus());
		}
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("orgId", "desc");
		orderby.put("emplId", "desc");
		
		page = this.employeeDao.searchEmployee(page, fields , sb.toString(), queryParams.toArray(),orderby);
		return page;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.EmployeeService#searchEmplsByIds(java.lang.String)
	 */
	@Override
	@Description("根据指定的id字符串查询员工，并返回员工的姓名")
	public String searchEmplsByIds(String ids) {
		if (!ids.contains(",")) {
			ids = ids + ",";
		}
		String[] emplids = ids.split(",");
		Integer[] empls = new Integer[emplids.length];
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < empls.length; i++) {
			empls[i] = Integer.valueOf(emplids[i]);
			sb.append("?,");
		}
		if (sb.length() > 1) {
			sb.deleteCharAt(sb.length()-1);
		}
		String sqlCondition = " where emplId in(" + sb.toString() + ")";
		List<Employee> emplss = this.employeeDao.searchEmployee(null, "emplName" , sqlCondition, empls).getList();
		sb = new StringBuffer();
		for (Employee employee : emplss) {
			sb.append(employee.getEmplName()).append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.EmployeeService#getAllEmplsByOrgCode(java.lang.String)
	 */
	@Override
	@Description("根据部门编号查询出该部门及其子部门的所有员工id")
	public String getAllEmplsIdByOrgCode(String jmcode) {
//		String ordIds = organizationService.getAllChildOrg(jmcode);
//		String sqlCondition = " where orgId in (" + ordIds + ") ";
//		List<Employee> emplss = this.employeeDao.searchEmployee(null, "emplId", sqlCondition, null).getList();
		StringBuffer sb = new StringBuffer();
//		for (Employee employee : emplss) {
//			sb.append(",").append(employee.getEmplId());
//		}
		return sb.toString().replaceFirst(",", "");
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.EmployeeService#uploadSignet(com.hnzskj.persist.bean.system.Employee)
	 */
	@Override
	@Description("上传个人签名")
	public boolean uploadEmplSignet(Employee empl) {
		boolean result = false;
		String[] fields = new String[1];
		Object[] params = new Object[2];

		fields[0] = "emplSignet";
		params[0] = empl.getEmplSignet();
		params[1] = empl.getEmplId();
		
		result = updateEmployee(fields, params);
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.EmployeeService#uploadOrgSignet(com.hnzskj.persist.bean.system.Employee)
	 */
	@Override
	@Description("上传部门印章")
	public boolean uploadOrgSignet(Employee empl) {
		boolean result = false;
		String[] fields = new String[1];
		Object[] params = new Object[2];

		fields[0] = "orgSignet";
		params[0] = empl.getOrgSignet();
		params[1] = empl.getEmplId();
		
		result = updateEmployee(fields, params);
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.EmployeeService#deleteEmplSignet(com.hnzskj.persist.bean.system.Employee)
	 */
	@Override
	@Description("删除个人签名")
	public boolean deleteEmplSignet(Employee empl) {
		boolean result = false;
		String[] fields = new String[2];
		Object[] params = new Object[3];

		fields[0] = "emplSignet";
		fields[1] = "signet";
		params[0] = null;
		params[1] = null;
		params[2] = empl.getEmplId();
		
		result = updateEmployee(fields, params);
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.EmployeeService#deleteOrgSignet(com.hnzskj.persist.bean.system.Employee)
	 */
	@Override
	@Description("删除部门签章")
	public boolean deleteOrgSignet(Employee empl) {
		boolean result = false;
		String[] fields = new String[1];
		Object[] params = new Object[2];

		fields[0] = "orgSignet";
		params[0] = null;
		params[1] = empl.getEmplId();
		
		result = updateEmployee(fields, params);
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.EmployeeService#addSignet(int, java.io.File)
	 */
	@Override
	@Description("添加个人电子签名")
	public boolean addSignet(Employee empl,File file){
		int result = 0;
		try {
			InputStream inputStream = new FileInputStream(file);
			result = employeeDao.addSignet(empl, inputStream, file.length());
		} catch (FileNotFoundException e) {			
			log.error(DataUtil.getStackTraceAsString(e));;
		}
		return result==1?true:false;
	}

	@Override
	@Description("查询莫部门下的所有的人员信息")
	public List<Employee> searchEmplByOrgs(Employee employee) {
		List<Employee> es =employeeDao.searchEmplByOrgs(employee.getOrgId());
		if(es!=null&&es.size()>1){
			Collections.sort(es, new Comparator<Employee>() {
				public int compare(Employee s1, Employee s2) {
					Integer p1 = Integer.parseInt(s1.getEmplOrder());
					Integer p2 = Integer.parseInt(s2.getEmplOrder());
					return p1.compareTo(p2);
				}
			});
		}
		
		return es;
	}

	@Override
	public String getEmplIdsByName(String name) {
		// TODO Auto-generated method stub
		return employeeDao.getEmplIdsByName(name);
	}
	
	

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.EmployeeService#suspend(com.hnzskj.persist.bean.system.Employee)
	 */
	@Override
	@Description("停用员工帐号")
	public boolean suspend(Employee empl) {
		int result = 0;
		result = employeeDao.updateEmployee(new String[]{"emplStatus"}, 
				new Object[]{Constant.SUSPEND,empl.getEmplId()});
		return result == 1 ? true : false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.EmployeeService#enabledAccount(com.hnzskj.persist.bean.system.Employee)
	 */
	@Override
	@Description("启用员工帐号")
	public boolean enabledAccount(Employee empl) {
		int result = 0;
		result = this.employeeDao.updateEmployee(new String[]{"emplStatus"}, 
				new Object[]{Constant.NORMAL,empl.getEmplId()});
		return result == 1 ? true : false;
	}

	@Override
	@Description("根据员工姓名获取员工id")
	public String getIdByName(String name) {
		return employeeDao.getIdByName(name);
	}
	
	@Override
	@Description("根据员工编号获取员工角色")
	public Employee findEmployeeRolesById(String id)
	{
		return employeeDao.findEmployeeRolesById(id);
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.EmployeeService#isHaveEmpl(com.hnzskj.persist.bean.system.Employee)
	 */
	@Override
	public boolean isHaveEmpl(Employee empl) {
		if(employeeDao.getEmployee(empl) !=null){
			return true;
		}else {
			return false;
		}
	}
	
	
}
