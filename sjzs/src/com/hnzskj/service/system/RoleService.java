/*
 * @项目名称: htglxt
 * @文件名称: RoleService.java
 * @日期: 2011-5-27
 * @版权: 2011 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.service.system;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.system.Role;

/**        
 * 
 * 类名称：RoleService     <br/>
 * 类描述：<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-5-27 下午02:01:55   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-5-27 下午02:01:55   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */
public interface RoleService {
	/**
	 * 方法描述：添加一个角色信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午02:03:28<br/>         
	 * @param role
	 * @return
	 * @version   1.0  
	 */
	public boolean addRole(Role role);
	
	/**
	 * 方法描述：删除一个角色信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午02:03:55<br/>         
	 * @param role
	 * @return
	 * @version   1.0  
	 */
	public boolean deleteRole(Role role);
	
	/**
	 * 方法描述：更新一个角色信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午02:04:11<br/>         
	 * @param role
	 * @return
	 * @version   1.0  
	 */
	public boolean updateRole(Role role);
	
	/**
	 * 方法描述：根据角色编号查询一个角色<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午02:36:11<br/>         
	 * @param role
	 * @return
	 * @version   1.0  
	 */
	public Role findById( Role role );
	
	/**
	 * 方法描述：查询角色信息的指定字<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午02:05:20<br/>         
	 * @param fields
	 * @return
	 * @version   1.0  
	 */
	public Page<Role> searchRole(String fields);
	
	/**
	 * 方法描述：分页查询角色信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-27 上午11:48:14<br/>         
	 * @param fileds
	 * @param page
	 * @return
	 * @version   1.0  
	 */
	public Page<Role> searchRole(String fileds, Role role, Page<Role> page);
	
	/**
	 * 方法描述：给角色添加权限<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-30 下午04:39:39<br/>         
	 * @param role
	 * @param pcodes
	 * @return
	 * @version   1.0  
	 */
	public boolean authorize(Role role, String[] pcodes);
	
	
	/**
	 * 方法描述：根据角色代码查询出角色所拥有的权限值<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-30 下午05:34:02<br/>         
	 * @param rcode
	 * @return
	 * @version   1.0  
	 */
	public String[] getPcode(String rcode);
	
	/**
	 * 方法描述：根据id和角色名称判断除当前id外，角色名称是否和其他角色名称相同<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-24 上午08:36:25<br/>         
	 * @param roleId
	 * @param rname
	 * @return
	 * @version   1.0  
	 */
	public boolean isExsit(String roleId, String rname);
	
	/**
	 * 类描述：通过角色名验证是不是存在。
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-7-20 上午09:20:50 
	 * 修改人：
	 * 修改时间：2012-7-20 上午09:20:50  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public boolean checkRole(String roleName);
	
	/**
	 * 
	 * 方法描述：根据当前登录用户Id查询角色<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-8-15 下午05:11:51<br/>         
	 * @param emplId 当前登录用户Id<br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public String getRoleNames(String emplId);
}
