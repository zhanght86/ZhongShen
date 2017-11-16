/*
 * @项目名称: htglxt
 * @文件名称: PowerService.java
 * @日期: 2011-5-27
 * @版权: 2011 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.service.system;

import java.io.Serializable;
import java.util.List;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.system.Power;
import com.hnzskj.persist.bean.system.Role;

/**        
 * 
 * 类名称：PowerService     <br/>
 * 类描述：<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-5-27 下午02:01:55   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-5-27 下午02:01:55   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */
public interface PowerService {
	/**
	 * 方法描述：添加一个权限信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午02:03:28<br/>         
	 * @param role
	 * @return
	 * @version   1.0  
	 */
	public boolean addPower(Power power);
	
	/**
	 * 方法描述：删除一个权限信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午02:03:55<br/>         
	 * @param power
	 * @return
	 * @version   1.0  
	 */
	public boolean deletePowers(Serializable...pcodes);
	
	/**
	 * 方法描述：更新一个权限信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午02:04:11<br/>         
	 * @param power
	 * @return
	 * @version   1.0  
	 */
	public boolean updatePower(Power power);
	
	/**
	 * 方法描述：根据权限编号查询权限<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午02:46:34<br/>         
	 * @param power
	 * @return
	 * @version   1.0  
	 */
	public Power findById(Power power);
	
	/**
	 * 方法描述：查询权限信息的指定字<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午02:05:20<br/>         
	 * @param fields
	 * @return
	 * @version   1.0  
	 */
	public Page<Power> searchPower(String fields);
	
	/**
	 * 方法描述：根据条件查询所有的权限<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-7-2 下午05:29:11<br/>         
	 * @param fields
	 * @param power
	 * @param page
	 * @return
	 * @version   1.0  
	 */
	public Page<Power> searchPower(String fields, Power power, Page<Power> page);
	
	/**
	 * 方法描述：查询当前权限的所有直接子节点<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-10 下午02:34:48<br/>         
	 * @param power 当前节点
	 * @param fields 查询的字段
	 * @return
	 * @version   1.0  
	 */
	public Page<Power> findChildren(Power power, String fields);
	
	/**
	 * 方法描述：构建针对角色的权限XML文档<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-10 下午06:33:17<br/>         
	 * @param role
	 * @return
	 * @version   1.0  
	 */
	public String buildXMLForRole( Role role );
			
	/**
	 * 方法描述：根据功能编号查询父功能编号<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-7-1 上午08:46:46<br/>         
	 * @param pcode
	 * @return
	 * @version   1.0  
	 */	
	public String findParentCode(String pcode);

	/**
	 * 方法描述：为一个角色构建其所拥有的权限树<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-7-14 下午04:29:49<br/>         
	 * @param role
	 * @return
	 * @version   1.0  
	 */	
	public String buildXMLForOneRole(Role role);
	
	/**
	 * 方法描述：查询当前节点的直接子节点无分页<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-12-29 下午06:02:24<br/>         
	 * @param power
	 * @return
	 * @version   1.0  
	 */
	public List<Power> findDirectChild(Power power);
	
	/**
	 * 方法描述：组织功能菜单字符串<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-12-29 下午07:03:58<br/>         
	 * @param power
	 * @return
	 * @version   1.0  
	 */
	public String getPowerStr(Power power, List<Power> powers);
}
