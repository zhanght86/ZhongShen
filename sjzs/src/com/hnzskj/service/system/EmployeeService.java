/*
 * @项目名称: htglxt
 * @文件名称: EmployeeService.java
 * @日期: 2011-5-27
 * @版权: 2011 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */


package com.hnzskj.service.system;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.system.Employee;

/**        
 * 
 * 类名称：EmployeeService     <br/>
 * 类描述：<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-5-27 上午09:37:59   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-5-27 上午09:37:59   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */
public interface EmployeeService {
	
	
	/**
	 * 方法描述：添加一个员工信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 上午09:42:12<br/>         
	 * @param employee
	 * @return
	 * @version   1.0  
	 */
	public boolean addEmployee(Employee employee);
	
	/**
	 * 方法描述：删除一个员工信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 上午09:42:23<br/>         
	 * @param employee
	 * @return
	 * @version   1.0  
	 */
	public boolean deleteEmployeeById(String emplId);
	
	/**
	 * 方法描述：删除一组员工信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 上午09:42:27<br/>         
	 * @param ids
	 * @return
	 * @version   1.0  
	 */
	public boolean deleteEmployeeByIds(Serializable...ids);
	
	/**
	 * 方法描述：更新一个员工信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 上午09:42:30<br/>         
	 * @param employee
	 * @return
	 * @version   1.0  
	 */
	public boolean updateEmployee(Employee employee);
	
	/**
	 * 方法描述：更新员工信息的指定的字段<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-21 上午10:07:28<br/>         
	 * @param fields	指定更新的字段
	 * @param params	参数数组，数组最后一个参数是员工的id,参数数组的长度比fields多1
	 * @return
	 * @version   1.0  
	 */
	public boolean updateEmployee(String[] fields, Object[] params);
	
	/**
	 * 方法描述：根据员工id查询信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午02:54:30<br/>         
	 * @param employee
	 * @return
	 * @version   1.0  
	 */
	public Employee findEmployeeById(Employee employee);

	/**
	 * 方法描述：根据员工所在机构查询员工信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-2 上午10:59:40<br/>         
	 * @param empl
	 * @return
	 * @version   1.0  
	 */
	public Page<Employee> searchEmplByOrg(Employee empl, Page<Employee> page);

	/**
	 * 方法描述：员工标志为离职<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-2 下午02:31:39<br/>         
	 * @param empl
	 * @return
	 * @version   1.0  
	 */	
	public boolean leaveOffice(Employee empl);

	/**
	 * 方法描述：根据员工所属机构和状查询员工<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-3 上午09:24:40<br/>         
	 * @param empl
	 * @param page
	 * @return
	 * @version   1.0  
	 */	
	public Page<Employee> searchEmplByOrgAndStauts(Employee empl,
			Page<Employee> page);

	/**
	 *创建人：郑辉
	 *描述：用于获取所有的员工 	
	 *创建时间：2012-4-19 下午02:19:54
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： Page<Employee>
	 */
	public Page<Employee> searchEmplAll(Page<Employee> page);
	/**
	 * 方法描述：给用户添加角色信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-3 下午02:56:57<br/>         
	 * @param empl
	 * @return
	 * @version   1.0  
	 */	
	public boolean addRole(Employee empl);

	/**
	 * 方法描述：查询用户的角色代码数组<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-3 下午05:44:41<br/>         
	 * @param empl
	 * @return
	 * @version   1.0  
	 */	
	public String[] getRoleCodes(Employee empl);
	
	/**
	 * 方法描述：根据员工编号和员工状态查询出员工信息<br/>
	 * 查询出的员工信息包含了它所拥有的角色和权限信息
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-8 上午08:45:14<br/>
	 * @param 员工id
	 * @param 员工状态
	 * 			Constant.UNINIT 未启用
	 * 			Constant.NORMAL 正常状态
	 * 			Constant.DIMISSION 离职         
	 * @return	
	 * @version   1.0  
	 */
	public Employee getEmplByIdAndStatus(int uid, String ustatus);
	
	/**
	 * 方法描述：根据部门编号获得所有员工的id,所有id之间以逗号分隔<br/>
	 * 若没有员工则返回""
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-18 下午01:46:14<br/>         
	 * @param jmcode 部门编号
	 * @return
	 * @version   1.0  
	 */
	public String getAllEmplIdByOrg(String jmcode);

	/**
	 * 方法描述：员工登陆<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-22 上午09:33:55<br/>         
	 * @param empl
	 * @return
	 * @version   1.0  
	 */	
	public Employee login(Employee empl);
	
	/**
	 * 方法描述：验证当前账户是否存在<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-22 下午02:28:30<br/>         
	 * @param id	用户id
	 * @param username 用户账户
	 * @return
	 * @version   1.0  
	 */
	public boolean isExsit(int id, String username);

	/**
	 * 方法描述：查询员工信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-7-1 上午09:03:22<br/>         
	 * @param empl
	 * @param page
	 * @return
	 * @version   1.0  
	 */	
	public Page<Employee> searchEmpl(Employee empl, Page<Employee> page);
	
	/**
	 * 方法描述：根据指定的id字符串查询员工，并返回员工的姓名<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-7-6 下午02:29:05<br/>         
	 * @param ids
	 * @return
	 * @version   1.0  
	 */
	public String searchEmplsByIds(String ids);

	/**
	 * 方法描述：重置员工密码<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-7-14 上午09:08:26<br/>         
	 * @param empl
	 * @return
	 * @version   1.0  
	 */
	
	public boolean resetPassword(Employee empl);
	
	
	/**
	 * 方法描述：根据部门编号查询出该部门及其子部门的所有员工id<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-7-19 上午09:10:11<br/>         
	 * @param jmcode 
	 * @return 用户uid组成的字符，id之间以逗号分隔
	 * @version   1.0  
	 */
	public String getAllEmplsIdByOrgCode(String jmcode);

	/**
	 * 方法描述：上传用户电子签章<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-8-15 下午06:54:46<br/>         
	 * @param empl
	 * @param signetType
	 * @return
	 * @version   1.0  
	 */	
	public boolean uploadEmplSignet(Employee empl);
	
	/**
	 * 方法描述：上传部门电子签章<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-8-18 下午02:15:18<br/>         
	 * @param empl
	 * @return
	 * @version   1.0  
	 */
	public boolean uploadOrgSignet(Employee empl);

	/**
	 * 方法描述：删除个人签名<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-8-29 上午10:11:48<br/>         
	 * @param empl
	 * @version   1.0  
	 */
	
	public boolean deleteEmplSignet(Employee empl);

	/**
	 * 方法描述：删除部门签章<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-8-29 上午10:12:11<br/>         
	 * @param empl
	 * @version   1.0  
	 */
	
	public boolean deleteOrgSignet(Employee empl);

	/**
	 * 
	 * 方法描述：添加个人电子签名<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-3-1 上午11:09:06<br/>         
	 * @param empl 用户信息<br/>
	 * @param file 二进制文件<br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public boolean addSignet(Employee empl,File file);
	/**
	 * 查询莫部门下的所有的人员信息
	 * @param employee
	 * @return
	 */
	public List<Employee> searchEmplByOrgs(Employee employee);

	/**
	 * @param name
	 * @return 用','分隔的id字符 根据用户名称获得用户id，主要完成根据创建人名称查询日志信息的功能
	 * 
	 */
	public String getEmplIdsByName(String name);


	/**
	 * 方法描述：停用员工帐号<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2012-4-24 上午10:25:46<br/>         
	 * @param empl
	 * @return
	 * @version   1.0  
	 */
	public boolean suspend(Employee empl);

	/**
	 * 方法描述：启用员工帐号<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2012-4-24 上午10:59:39<br/>         
	 * @param empl
	 * @return
	 * @version   1.0  
	 */
	public boolean enabledAccount(Employee empl);
	/**
	 *毛俊玲
	 *方法描述：根据员工姓名获取员工id
	 *2012-5-9
	 * @param name
	 * @return
	 */
	public String getIdByName(String name);
	
	/**
	 * 类描述：获取员工角色
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-8-15 下午02:37:45 
	 * 修改人：
	 * 修改时间：2012-8-15 下午02:37:45  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public Employee findEmployeeRolesById(String id);
	
	
	/**
	 *方法描述：判断用户名是否已经存在  <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-18下午06:12:42
	 *@return 
	 *@version 1.0
	 */
	public boolean isHaveEmpl(Employee empl);
	
}
