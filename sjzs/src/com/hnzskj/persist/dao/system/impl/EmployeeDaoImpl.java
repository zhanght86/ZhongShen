/*
 * @项目名称: htglxt
 * @文件名称: EmployeeDaoImpl.java
 * @日期: 2011-5-26
 * @版权: 2011 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.persist.dao.system.impl;
import com.hnzskj.common.DataUtil;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.Constant;
import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.system.Employee;
import com.hnzskj.persist.bean.system.Organization;
import com.hnzskj.persist.bean.system.Power;
import com.hnzskj.persist.bean.system.Role;
import com.hnzskj.persist.dao.system.EmployeeDao;
import com.hnzskj.persist.dao.system.PowerDao;
import com.hnzskj.persist.dao.system.RoleDao;

/**        
 * 
 * 类名称：EmployeeDaoImpl<br/>
 * 类描述：员工管理数据访问层实现<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-5-26 下午04:30:57   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-5-26 下午04:30:57   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */
public class EmployeeDaoImpl extends BaseDao implements EmployeeDao {
	
	private static final Logger LOGGER = Logger.getLogger(EmployeeDaoImpl.class);
	
	private PowerDao powerDao;
	
	private RoleDao roleDao;
	
	/**
	 * @return the roleDao
	 */
	public RoleDao getRoleDao() {
		return roleDao;
	}

	/**
	 * @param roleDao the roleDao to set
	 */
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	/**
	 * @return the powerDao
	 */
	public PowerDao getPowerDao() {
		return powerDao;
	}

	/**
	 * @param powerDao the powerDao to set
	 */
	public void setPowerDao(PowerDao powerDao) {
		this.powerDao = powerDao;
	}


	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.employee.EmployeeDao#addEmployee(com.hnzskj.persist.bean.employee.Employee)
	 */
	@Override
	public int addEmployee(Employee employee) {
		System.out.println(employee+"*-*-*");
		String sql = "insert into employee (emplId,emplName, emplSex, emplStatus, emplIdCard, emplAge, " +
				"emplAddress, emplMobile, emplOfficeTel, emplHomeTel, emplEmail, emplqq, emplRuZhi, " +
				"orgId, emplPosition, emplAccount, emplPassword, lastLoginIp, lastLoginDate, " +
				"timeOrder, emplOrder, powerT, note2) " +
				"values (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] params = {
				this.getGUID(),
			employee.getEmplName(),
			employee.getEmplSex(),
			employee.getEmplStatus(),
			employee.getEmplIdCard(),
			employee.getEmplAge(),
			employee.getEmplAddress(),
			employee.getEmplMobile(),
			employee.getEmplOfficeTel(),
			employee.getEmplHomeTel(),
			employee.getEmplEmail(),
			employee.getEmplqq(),
			employee.getEmplRuZhi(),
			employee.getOrgId(),
			employee.getEmplPosition(),
			employee.getEmplAccount(),
			employee.getEmplPassword(),
			employee.getLastLoginIp(),
			employee.getLastLoginDate(),
			new java.sql.Date(new java.util.Date().getTime()).toString(),
			employee.getEmplOrder(),
			employee.getPowerT(),
			employee.getNote2()
		};
		int result = update(sql, params);//将员工信息存入数据库		
		return result;
	}
	
	@Override
	public int addRoles(Employee empl) {
		//step1 用户没有选择任务角色信息分配当前用户，则直接执行删除员工角色信息操作
		if ( null == empl.getRolesCode() ) {
			String sql = "delete from user_role where emplId=?";
			this.update(sql, new Object[]{empl.getEmplId()});
			return 1;
		} else {
			//step2 用户选择了角色信息，删除先前的员工角色信息，然后再插入新的员工角色信息
			String[] rcode = empl.getRolesCode().split(", ");
			Object[][] params1 = new Object[rcode.length + 1][];
			
			String[] sqls = new String[rcode.length + 1];
			sqls[0] = "delete from user_role where emplId=?";
			params1[0] = new Object[]{empl.getEmplId()};
			
			//生成sql语句
			for (int i = 1; i < sqls.length; i++) {
				sqls[i] = "insert into user_role (emplId, roleId) values (?, ?)";
			}
			//为相应的sql语句设置参数
			for (int i = 1; i < params1.length; i++) {
				params1[i] = new Object[]{empl.getEmplId(),rcode[i-1]};
			}
			//执行更新操作
			int result = this.update(sqls, params1);
			return result;
		}
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.employee.EmployeeDao#deleteEmployee(int)
	 */
	@Override
	public int deleteEmployee(String id) {
		String sql = "delete from employee where emplId=?";
		return this.update(sql, new Object[]{id});
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.employee.EmployeeDao#deleteEmployee(java.io.Serializable[])
	 */
	@Override
	public int deleteEmployee(Serializable... ids) {
		StringBuffer idStr = new StringBuffer("");
		if (ids != null && ids.length > 0) {
			for (Object id : ids ) {
				idStr.append("'").append((String)id).append("',");
			}
			idStr.deleteCharAt(idStr.length() - 1);
		}
		String sql = "delete from employee where emplId in ( " + idStr.toString() + ")";
		return this.update(sql, null);
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.employee.EmployeeDao#findEmployeeById(int)
	 */
	@Override
	public Employee findEmployeeById(String id) {
		String sql = "select emplId, emplName, emplSex, emplStatus, emplIdCard, emplAge, " +
				"emplAddress, emplMobile, emplOfficeTel, emplHomeTel, emplEmail, emplqq, emplRuZhi, " +
				"orgId, emplPosition, emplAccount, emplPassword, lastLoginIp, lastLoginDate, " +
				"timeOrder, emplOrder, powerT, note2 " +
				"from employee where emplId=?";
		Employee employee = (Employee) this.get(sql, Employee.class, new Object[]{id});
		return employee;
	}
	
	
	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.EmployeeDao#findEmployeeByIdAndStatus(int, java.lang.String)
	 */
	@Override
	public Employee findEmployeeByIdAndStatus(int id, String ustatus) {
		Employee employee = null;
		//查询出员工信息
		String sql = " emplId, emplName, emplSex, emplStatus, emplIdCard, emplAge, " +
					"emplAddress, emplMobile, emplOfficeTel, emplHomeTel, emplEmail, emplqq, emplRuZhi, " +
					"orgId, emplPosition, emplAccount, emplPassword, lastLoginIp, lastLoginDate, " +
					"timeOrder, emplOrder, powerT, note2 " +
					"from employee where emplId=? and ustatus=?";
		employee = this.queryBySQL(sql, new Object[]{id, ustatus});
		return employee;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.EmployeeDao#login(com.hnzskj.persist.bean.system.Employee)
	 */
	
	public Employee login(Employee employee) {
		//查询出员工信息
		String sql = "select emplId, emplName, emplSex, emplStatus, emplIdCard, emplAge, " +
					"emplAddress, emplMobile, emplOfficeTel, emplHomeTel, emplEmail, emplqq, emplRuZhi, " +
					"orgId, emplPosition, emplAccount, emplPassword, lastLoginIp, lastLoginDate, " +
					"timeOrder, emplOrder, powerT, note2 " +
					"from employee where emplAccount=? and emplPassword=? and powerT=0 ";
		Object[] params = new Object[]{
				employee.getEmplAccount(),
				employee.getEmplPassword()
			};
		employee = this.queryBySQL(sql, params);
		return employee;
	}
	
	private Employee queryBySQL(String sql, Object[] params ) {
		Employee employee = null;
		employee = (Employee) get(sql, Employee.class, params);
		//如果员工存在则查询出员工的角色和权限信息
		if ( null != employee) {
			getEmplRoles(employee);
			getPowers(employee);
//			getManageOrgs(employee);
		}
		return employee;
	}
	
	/**
	 * 方法描述：查询当前用户拥有的角色<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2012-4-18 下午03:11:01<br/>         
	 * @param employee
	 * @version   1.0
	 */
	private void getEmplRoles(Employee employee) {
		//从员工角色中查询出员工的角色信息
		String roleSql = "select roleId,roleName,roleType from role where roleId in (select roleId from user_role where emplId=?)";
		List<Role> roles = roleDao.searchRoleBySQL(roleSql, new Object[]{employee.getEmplId()});
		employee.getRoles().addAll(roles);
	}
	
	/**
	 *创建人：郑辉
	 *描述： 获取角色下所有的用户	
	 *创建时间：2012-6-11 下午04:07:32
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： List<Employee>
	 */
	@SuppressWarnings("unchecked")
	public List<Employee> findEmpByRolesId(String roleId){
		String roleSql ="select emplId,emplName,orgId from employee where emplId in(select emplId from user_role where roleId=?)";
		Object [] params={roleId};
		List<Employee> empls =this.query(roleSql, Employee.class, params);
		if(empls!=null&&empls .size()!=0){
			return empls;
		}
		return null;
	}
	
	/**
	 * 方法描述：查询当前用户拥有的权限<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2012-4-18 下午03:10:41<br/>         
	 * @param employee
	 * @version   1.0
	 */
	private void getPowers(Employee employee) {
		//从角色权限表中查询出权限信息
		String rcodes = "";
		for (Role role : employee.getRoles()) {
			rcodes = rcodes + ",'" +role.getRoleId() + "'";
		}
		rcodes = rcodes.replaceFirst(",", "");
		if ("".equals(rcodes.trim())) {//如果没有角色信息
			rcodes = "''";
		}

		//查询出员工拥有的权限信息
		String rolePower = "select powerId, powerName, powerUrl, powerParent, powerImg, powerModule, powerOrder from power where powerId in " +
						"(select powerId from role_power where roleId in ("+rcodes+") ) order by powerOrder asc";
		List<Power> powers = powerDao.searchPowerBySQL(rolePower, null);
		
		employee.getPowers().addAll(powers);
	}
	
	/**
	 * 方法描述：获得主管部门<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2012-4-18 下午03:09:58<br/>         
	 * @param employee
	 * @version   1.0
	 */
	@SuppressWarnings("unchecked")
	/*private void getManageOrgs(Employee employee) {
		String sql = "select orgId, orgName, orgParent, orgOrder, orgType from org where orgId in (select orgId from empl_org_relation where emplId = ?) order by orgType asc, orgOrder asc";
		List<Organization> orgs = query(sql, Organization.class, new Object[]{employee.getEmplId()});
		employee.setManageOrgs(orgs);
	}*/

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.EmployeeDao#updateEmployee(java.lang.String[], java.lang.Object[])
	 */
	@Override
	public int updateEmployee(String[] fields, Object[] params) {
		String sql = getUpdateSql("employee", fields, "emplId");
		return this.update(sql, params);
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.employee.EmployeeDao#updateEmployee(com.hnzskj.persist.bean.employee.Employee)
	 */
	@Override
	public int updateEmployee(Employee employee) {
		/*
		 * emplName, emplSex, emplStatus, emplIdCard, emplAge, " +
				"emplAddress, emplMobile, emplOfficeTel, emplEmail, emplqq, emplRuZhi, " +
				"orgId, emplPosition, emplAccount
		 * */
		String sql = "update employee set emplName=?, emplSex=?, emplStatus=?, emplIdCard=?, emplAge=?, " +
			"emplAddress=?, emplMobile=?, emplOfficeTel=?, emplEmail=?, emplqq=?, emplRuZhi=?, " +
			"orgId=?, emplPosition=?, emplAccount=?,emplOrder=? where emplId = ?";
		Object[] params = {
			employee.getEmplName(),
			employee.getEmplSex(),
			employee.getEmplStatus(),
			employee.getEmplIdCard(),
			employee.getEmplAge(),
			employee.getEmplAddress(),
			employee.getEmplMobile(),
			employee.getEmplOfficeTel(),
			employee.getEmplEmail(),
			employee.getEmplqq(),
			employee.getEmplRuZhi(),
			employee.getOrgId(),
			employee.getEmplPosition(),
			employee.getEmplAccount(),
			employee.getEmplOrder(),
			employee.getEmplId()
		};
		int result = update(sql, params);	
//		if(result!=0){
//			String sqls ="update empl_org_relation set orgId=? where emplId=?";
//			Object[] paramss = {employee.getOrgId(),employee.getEmplId()};
//			update(sqls, paramss);
//		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.employee.EmployeeDao#searchEmployee()
	 */
	@Override
	public Page<Employee> searchEmployee(String fields) {
		return this.searchEmployee(null, fields, null, null, null);
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.employee.EmployeeDao#searchEmployee(com.hnzskj.common.Page)
	 */
	@Override
	public Page<Employee> searchEmployee(Page<Employee> page,String fields) {
		return this.searchEmployee(page, fields, null, null, null);
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.employee.EmployeeDao#searchEmployee(com.hnzskj.common.Page, java.lang.String, java.lang.Object[])
	 */
	@Override
	public Page<Employee> searchEmployee(Page<Employee> page,String fields,
			String sqlCondition, Object[] queryParams) {
		return this.searchEmployee(page, fields, sqlCondition, queryParams, null);
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.employee.EmployeeDao#searchEmployee(com.hnzskj.common.Page, java.util.LinkedHashMap)
	 */
	@Override
	public Page<Employee> searchEmployee(Page<Employee> page,String fields,
			LinkedHashMap<String, String> orderby) {
		return this.searchEmployee(page, fields, null, null, orderby);
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.employee.EmployeeDao#searchEmployee(com.hnzskj.common.Page, java.lang.String, java.lang.Object[], java.util.LinkedHashMap)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Page<Employee> searchEmployee(Page<Employee> page, String fields,
			String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {
		List<Employee> empls = new ArrayList<Employee>();//封装查询结果集
		Integer totalRecords = 0;//记录查询的总记录数
		String sql = "";//查询的sql语句
		String countSql = "";//查询总记录数的sql语句
		Page<Employee> epage = new Page<Employee>();//临时变量，如果在page为null的情况下使用
		
		sqlCondition =  ("".equals(sqlCondition) || null == sqlCondition) ? " " : sqlCondition;
		countSql = "select count(emplId) from employee " + sqlCondition;
		//查询总记录数
		totalRecords = Integer.valueOf(getSingleValue(countSql, queryParams).toString());
		
		if ( page != null) {//如果需要分页
//			if ( null != queryParams && queryParams.length > 0) {
//				//将数组的长度扩充为原来的2倍,并使得数组的第n个元素和第n+数组长度的元素的值相等,如此是为了分页查询时参数设置的需要
//				Object[] newParamsArray = Arrays.copyOf(queryParams, queryParams.length*2);
//				for (int i = 0; i < queryParams.length; i++) {
//					//newParamsArray[queryParams.length + i] = queryParams[i];
//				}
//				queryParams = newParamsArray;
//			}
			//如果不执行模糊查询则构建where语句使用DBUtil.buildOrderBy（）的方法
			//如果执行模糊查询则构建where语句使用PolicyDao的buildSQLWhereFuzzy（）方法
			sql = "select " + fields + "  from employee " +sqlCondition + BaseDao.buildOrderBy(orderby) +
				"limit "+(page.getCurPage() - 1) * page.getMaxResult()+","+page.getMaxResult() ;
			
		
			epage = page;
			
			empls = query(sql, Employee.class, queryParams);
		} else {//如果不需要分页
			sql = "select " +fields + " from employee " + sqlCondition + buildOrderBy(orderby);
			//查询结果集
			empls = query(sql, Employee.class, queryParams);
		}
		//设置总记录数
		epage.setList(empls);
		//设置结果集
		epage.setTotalRecords(totalRecords);

		return epage;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRoles(Employee empl) {
		//从用户角色表查询出角色信息
		List<Role> roleList = new ArrayList<Role>();
		String sql = "select roleId from user_role where emplId=?";
		roleList = query(sql, Role.class, new Object[]{empl.getEmplId()});
		return roleList;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.EmployeeDao#getEmplsIdByOrg(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getEmplsIdByOrg(String jmcode) {
		List<Employee> empls = new ArrayList<Employee>();
		String sql = "select emplId from employee where orgId = ?";
		empls = query(sql, Employee.class, new Object[]{jmcode});
		return empls;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.EmployeeDao#getEmplCount(java.lang.String, java.lang.Object[])
	 */
	@Override
	public int getEmplCount(String sqlCondition, Object[] params) {
		Integer result = 0;
		String sql = "";
		
		if ( null == sqlCondition || "".equals(sqlCondition.trim())) {
			sqlCondition = " ";
		}
		sql = "select count(emplId) from employee " + sqlCondition;
		result = Integer.valueOf( getSingleValue(sql, params).toString());
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.EmployeeDao#getEmplRowCount(com.hnzskj.persist.bean.system.Employee)
	 */
	@Override
	public int getEmplRowCount(Employee empl) {
		Integer result = 0;
		String sql = "select count(emplId) from employee where " +
				"emplId <= (select emplId from employee where emplAccount=? " +
				"and emplPassword=?) and emplStatus=?";
		result = Integer.valueOf( getSingleValue(sql, new Object[]{
							empl.getEmplAccount(),
							empl.getEmplPassword(),
							Constant.NORMAL}).toString());
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.EmployeeDao#login(com.hnzskj.persist.bean.system.Employee)
	 */
	
	@Override
	public Employee findNomalEmployeeById(int id, String ustatus) {
		Employee employee = null;
		//查询出员工信息
		String sql = "select emplId, emplName, emplSex, emplStatus, emplIdCard, emplAge, " +
					"emplAddress, emplMobile, emplOfficeTel, emplHomeTel, emplEmail, emplqq, emplRuZhi, " +
					"orgId, emplPosition, emplAccount, emplPassword, lastLoginIp, lastLoginDate, " +
					"timeOrder, emplOrder, powerT, note2 " +
					"from employee where emplId=? and emplStatus!=?";
		employee = this.queryBySQL(sql, new Object[]{id, ustatus});
		return employee;
	}
	
	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.EmployeeDao#addSignet(int, java.io.InputStream, long)
	 */
	@Override
	public int addSignet(Employee empl,InputStream file,long fileLength){
		String sql="update employee set signet = ? where emplId = ?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		int result=0;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setBinaryStream(1, file, (int)fileLength);
			pstmt.setString(2, empl.getEmplId());
			LOGGER.info("执行SQL语句成功：" + sql);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			LOGGER.error("执行sql语句出错："+sql);
			log.error(DataUtil.getStackTraceAsString(e));;
		}finally{
			try {
				if(null!=file){
					file.close();
				}
			} catch (Exception e2) {
				LOGGER.error("关闭文件流错误");
				e2.printStackTrace();
			}
			BaseDao.close(null, pstmt, conn);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.EmployeeDao#getManageOrg(java.lang.String)
	 */
	@Override
	public String getManageOrg(String emplId) {
		String sql = "select dbo.getAllManageEmpl('"+emplId+"') as orgIds";
		String orgIds = (String) getSingleValue(sql, null);
		return orgIds;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> searchEmplByOrgs(String orgId) {
		// TODO Auto-generated method stub
		String sql = "select * from employee where orgId=? and emplstatus=1";
		List<Employee> list = query(sql, Employee.class, new Object[] { orgId });
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getEmplIdsByName(String name) {
		// TODO Auto-generated method stub
		String sql = "select emplId from employee where emplName like '%"
				+ name + "%'";
		StringBuffer emplId = new StringBuffer("");
		List<Employee> list = new ArrayList<Employee>();
		list = query(sql, Employee.class, null);
		for (Employee employee : list) {
			emplId.append(",'").append(employee.getEmplId()).append("'");
		}
		return emplId.toString().replaceFirst(",", "");
	}

	@Override
	public String getIdByName(String name) {
		String sql = "select emplId from employee where emplName = ? limit 1";
		String id = (String)getSingleValue(sql, new Object[]{name});
		return id;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.EmployeeDao#findEmployeeRolesById(java.lang.String, java.lang.String)
	 */
	@Override
	public Employee findEmployeeRolesById(String id) {
		// TODO Auto-generated method stub
		Employee employee = null;
		//查询出员工信息
		String sql = "select emplId, emplName, emplSex, emplStatus, emplIdCard, emplAge, " +
					"emplAddress, emplMobile, emplOfficeTel, emplHomeTel, emplEmail, emplqq, emplRuZhi, " +
					"orgId, emplPosition, emplAccount, emplPassword, lastLoginIp, lastLoginDate, " +
					"timeOrder, emplOrder, powerT, note2 " +
					"from employee where emplId=?";
		employee = this.queryBySQL(sql, new Object[]{id});
		return employee;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.EmployeeDao#getEmployee(com.hnzskj.persist.bean.system.Employee)
	 */
	@Override
	public Employee getEmployee(Employee empl) {
		return (Employee)get("select * from employee where emplAccount = ?", Employee.class, new Object[]{empl.getEmplAccount()});
		
	}
	
	
}
