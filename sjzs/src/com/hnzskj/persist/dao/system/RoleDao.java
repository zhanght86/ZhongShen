/*
 * @项目名称: htglxt
 * @文件名称: RoleDao.java
 * @日期: 2011-5-27
 * @版权: 2011 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.persist.dao.system;

import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.system.Power;
import com.hnzskj.persist.bean.system.Role;

/**        
 * 
 * 类名称：RoleDao     <br/>
 * 类描述：<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-5-27 上午10:24:01   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-5-27 上午10:24:01   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */
public interface RoleDao {
	/**
	 * 方法描述：保存一个角色信息到数据库<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 上午10:29:49<br/>         
	 * @param role
	 * @return
	 * @version   1.0  
	 */
	public int addRole(Role role);
	
	/**
	 * 方法描述：根据角色编号删除一个角色<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 上午10:30:10<br/>         
	 * @param rcode
	 * @return
	 * @version   1.0  
	 */
	public int deleteRoleById(String rcode) ;
	
	/**
	 * 方法描述：更新一个角色信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 上午10:30:48<br/>         
	 * @param role
	 * @return
	 * @version   1.0  
	 */
	public int updateRole(Role role);
	
	/**
	 * 方法描述：根据角色编号查询一个角色<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午02:32:48<br/>         
	 * @param rcode
	 * @return
	 * @version   1.0  
	 */
	public Role findById(String rcode);
	/**
	 * 方法描述：根据多个角色编号查询多个角色<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午02:32:48<br/>         
	 * @param rcode
	 * @return
	 * @version   1.0  
	 */
	public List<Role> findByRcodes(String rcode);
	
	/**
	 * 方法描述：无条件查询所有角色<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 上午10:31:06<br/>
	 * @param 查询字段
	 * @return
	 * @version   1.0  
	 */
	public Page<Role> searchRole(String fields);
	
	/**
	 * 方法描述：无条件分页查询所有角色信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 上午10:31:23<br/>         
	 * @param page
	 * @param fields 查询的字段
	 * @return
	 * @version   1.0  
	 */
	public Page<Role> searchRole(Page<Role> page, String fields);
	
	/**
	 * 方法描述：根据指定条件查询分页查询角色信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 上午10:34:06<br/>         
	 * @param page
	 * @param fields	查询字段
	 * @param sqlCondition 查询条件
	 * @param queryParams 查询语句参数
	 * @return
	 * @version   1.0  
	 */
	public Page<Role> searchRole(Page<Role> page, String fields,
			String sqlCondition, Object[] queryParams);
	
	/**
	 * 方法描述：根据指定排序条件分页查询角色信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 上午10:35:31<br/>         
	 * @param page
	 * @param fields	查询字段
	 * @param orderby	排序条件
	 * @return
	 * @version   1.0  
	 */
	public Page<Role> searchRole(Page<Role> page, String fields,
			LinkedHashMap<String, String> orderby);
	
	/**
	 * 方法描述：根据指定查询条件，排序条件分页查询角色信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 上午10:36:38<br/>         
	 * @param page	
	 * @param fields	查询字段
	 * @param sqlCondition	查询条件
	 * @param queryParams	查询语句参数
	 * @param orderby	排序条件
	 * @return
	 * @version   1.0  
	 */
	public Page<Role> searchRole(Page<Role> page, String fields,
			String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby);
	
	/**
	 * 方法描述：将角色拥有的权限信息添加到数据库中<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-30 下午04:45:52<br/>         
	 * @param role
	 * @param pcodes
	 * @return
	 * @version   1.0  
	 */
	public int saveRolePower(Role role, String[] pcodes);
	
	/**
	 * 方法描述：根据角色代码从角色权限表查询出所有权限的代码<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-30 下午05:32:26<br/>         
	 * @param rcode
	 * @return
	 * @version   1.0  
	 */
	public List<Power> getPowersCode(String rcode);
	
	/**
	 * 方法描述：根据角色代码查询出角色所拥有的所有权限<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-11 上午08:15:27<br/>         
	 * @param rcode
	 * @return
	 * @version   1.0  
	 */
	public List<Power> getPowers(String rcode);
	
	/**
	 * 方法描述：根据指定的sql语句查询角色信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-13 上午10:32:04<br/>         
	 * @param sql
	 * @param params
	 * @return
	 * @version   1.0  
	 */
	public List<Role> searchRoleBySQL(String sql, Object[] params);

	/**
	 * 方法描述：<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-24 上午08:40:10<br/>         
	 * @param roleId
	 * @param rname
	 * @return
	 * @version   1.0  
	 */	
	public int getRoleCountByIdAndName(String roleId, String rname);
	/**
	 * 方法描述:添加节点时选择审批角色<br/>
	 * 创建人：丁艳伟   <br/>
	 * 创建时间：2012年2月28日8:49:18<br/>         
	 * @return
	 * @version   1.0  
	 */
	public List<Role> getRoleList();
	
	/**
	 * 方法描述：根据登录用户id查询用户拥有的所有权限
	 * 创建人：丁艳伟
	 * 创建日期：2012年3月9日10:56:42
	 * @return
	 */
	public List<Role> getRoleByUid(int uid);	
	
	/**
	 * 
	 * 方法描述：根据当前登录用户Id查询角色<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-8-15 下午05:11:51<br/>         
	 * @param emplId 当前登录用户Id<br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public List<Role> getRoleNameByUid(String emplId);	
}
