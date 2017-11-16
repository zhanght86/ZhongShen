/*
 * @项目名称: htglxt
 * @文件名称: EmployeeDao.java
 * @日期: 2011-5-26
 * @版权: 2011 河南中审科技有限公司 
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.persist.dao.system;

import java.io.InputStream;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.system.Employee;
import com.hnzskj.persist.bean.system.Role;


/**        
 * 
 * 类名称：EmployeeDao<br/>
 * 类描述：<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-5-26 下午04:27:28<br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-5-26 下午04:27:28<br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */ 
public interface EmployeeDao {
	
	/**
	 * 
	 * 方法描述：添加一条记录到数据库<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-26 下午03:54:21<br/>         
	 * @param employee
	 * @return
	 * @version   1.0
	 */
	public int addEmployee(Employee employee);
	
	/**
	 * 
	 * 方法描述：更新数据库中的一条记录<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-26 下午03:54:44<br/>         
	 * @param employee
	 * @return
	 * @version   1.0
	 */
	public int updateEmployee(Employee employee);
	
	/**
	 * 
	 * 方法描述：根据id删除数据库的一条记录<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-26 下午03:55:05<br/>         
	 * @param id
	 * @return
	 * @version   1.0
	 */
	public int deleteEmployee(String id);
	
	/**
	 * 
	 * 方法描述：根据指定的id数组删除一组员工<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-26 下午03:55:30<br/>         
	 * @param ids
	 * @return
	 * @version   1.0
	 */
	public int deleteEmployee(Serializable...ids );
	
	/**
	 * 
	 * 方法描述：根据id查找一个员工<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-26 下午03:56:10<br/>         
	 * @param id
	 * @return
	 * @version   1.0
	 */
	public Employee findEmployeeById(String id);
	
	/**
	 * 
	 * 方法描述：根据员工的id和状态查询一个员工信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-7 下午06:17:47<br/>         
	 * @param id	员工id
	 * @param ustatus	员工状态
	 * 			Constant.UNINIT 未启用
	 * 			Constant.NORMAL 正常状态
	 * 			Constant.DIMISSION 离职
	 * @return
	 * @version   1.0
	 */
	public Employee findEmployeeByIdAndStatus(int id, String ustatus);
	
	/**
	 * 
	 * 方法描述：根据指定的字段更新员工信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 上午09:46:08<br/>         
	 * @param fields
	 * @param params
	 * @return
	 * @version   1.0
	 */
	public int updateEmployee(String[] fields, Object[] params);
	
	/**
	 * 
	 * 方法描述：无条件查询所有员工<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-26 下午03:56:24<br/>         
	 * @return
	 * @version   1.0
	 */
	public Page<Employee> searchEmployee(String fields);
	
	/**
	 * 
	 * 方法描述：无条件分布查询所有员工<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-26 下午04:10:59<br/>         
	 * @param page
	 * @return
	 * @version   1.0
	 */
	public Page<Employee> searchEmployee(Page<Employee> page,String fields);
	
	/**
	 * 
	 * 方法描述：根据查询条件分布查询员工<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-26 下午04:11:17<br/>         
	 * @param page
	 * @param sqlCondition
	 * @param queryParams
	 * @return
	 * @version   1.0
	 */
	public Page<Employee> searchEmployee(Page<Employee> page,String fields,
			String sqlCondition,Object[] queryParams);

	/**
	 * 
	 * 方法描述：无条件分页，按指定排序条件查询<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-26 下午04:11:39<br/>         
	 * @param page
	 * @param orderby
	 * @return
	 * @version   1.0
	 */
	public Page<Employee> searchEmployee(Page<Employee> page,String fields,
			LinkedHashMap<String, String> orderby);
	
	
	/**
	 * 方法描述：指定查询，排序条件分页查询<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-26 下午04:27:50<br/>         
	 * @param page
	 * @param sqlCondition
	 * @param queryParams
	 * @param orderby
	 * @return
	 * @version   1.0  
	 */
	public Page<Employee> searchEmployee(Page<Employee> page,String fields,
				String sqlCondition, Object[] queryParams,
				LinkedHashMap<String, String> orderby);

	/**
	 * 方法描述：给员工添加角色信息<br/>
	 * 实现原理：添加新角色信息前，先将用户先前的角色信息全部清除掉<br/>
	 * 然后再插入新的角色信息
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-3 下午02:19:27<br/>         
	 * @param empl
	 * @return
	 * @version   1.0  
	 */	
	public int addRoles(Employee empl);

	/**
	 * 方法描述：查询出该用户的所有角色代码<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-3 下午05:42:38<br/>
	 * @param empl
	 * @return
	 * @version   1.0  
	 */	
	public List<Role> getRoles(Employee empl);

	/**
	 * 方法描述：根据部门编号获得所有员工的id<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-18 下午01:48:33<br/>         
	 * @param jmcode
	 * @return
	 * @version   1.0
	 */	
	/*public List<Employee> getAllEmplId(String jmcode);*/
	
	/**
	 * 方法描述:根据员工的用户名和密码查询员工的信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-22 上午09:37:21<br/>         
	 * @param employee
	 * @return
	 * @version   1.0  
	 */
	public Employee login(Employee employee);

	/**
	 * 方法描述：根据部门编号查询出其所在部门的所有员工的id<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-7-18 上午07:58:26<br/>         
	 * @param jmcode
	 * @return
	 * @version   1.0  
	 */
	public List<Employee> getEmplsIdByOrg(String jmcode);
	
	/**
	 * 方法描述：根据指定的条件查询 <br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-8-9 下午02:46:12<br/>         
	 * @param sqlCondition
	 * @param params
	 * @return
	 * @version   1.0  
	 */
	public int getEmplCount(String sqlCondition, Object[] params);
	
	/**
	 * 方法描述：获得当前用户的信息在数据库中是第几条<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-8-9 下午05:09:51<br/>         
	 * @param empl
	 * @return
	 * @version   1.0  
	 */
	public int getEmplRowCount(Employee empl);

	/**
	 * 
	 * 方法描述：根据员工的id查询一个在职员工信息<br/>
	 * 创建人：柴茂森   <br/>
	 * @param id	员工id
	 * @param ustatus	员工状态
	 * 			Constant.UNINIT 未启用
	 * 			Constant.NORMAL 正常状态
	 * 			Constant.DIMISSION 离职
	 */
	public Employee findNomalEmployeeById(int id, String ustatus);
	
	/**
	 * 
	 * 方法描述：添加员工电子签名。<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-3-1 上午10:59:41<br/>         
	 * @param empl 用户信息<br/>
	 * @param file 二进制文件<br/>   
	 * @param fileLength 二进制大小<br/>      
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public int addSignet(Employee empl,InputStream file,long fileLength);

	/**
	 * 方法描述：查询出当前用户主管的部门，以部门编号形式返回，编号用单引号括起来，以逗号分隔<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2012-4-20 上午10:37:54<br/>         
	 * @param journalStaff
	 * @return
	 * @version   1.0  
	 */	
	public String getManageOrg(String journalStaff);
	/**
	 * 根据部门id查询此部门下的所有人信息
	 * @param orgId
	 * @return
	 */
	public List<Employee> searchEmplByOrgs(String orgId) ;

	/**
	 * @param name
	 * @return 用','分隔的id字符 根据用户名称获得用户id，主要完成根据创建人名称查询日志信息的功能
	 * 
	 */
	public String getEmplIdsByName(String name);

	/**
	 *毛俊玲
	 *方法描述：更具员工姓名获得员工id
	 *2012-5-9
	 * @param name
	 * @return
	 */
	public String getIdByName(String name);
	
	/**
	 *创建人：郑辉
	 *描述： 获取角色下的所有用户	
	 *创建时间：2012-6-11 下午04:08:09
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： List<Employee>
	 */
	public List<Employee> findEmpByRolesId(String roleId);

	/**
	 * 类描述：<br/>
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-8-15 下午02:28:33 
	 * 修改人：
	 * 修改时间：2012-8-15 下午02:28:33  
	 * 修改备注：   
	 * @version   1.0     
	 */
	Employee findEmployeeRolesById(String ids);

	/**
	 *方法描述：  <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-18下午06:14:50
	 *@return 
	 *@version 1.0
	 */
	public Employee getEmployee(Employee empl);
}
