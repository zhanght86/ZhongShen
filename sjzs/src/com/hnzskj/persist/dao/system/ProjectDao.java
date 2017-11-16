/*
 * @项目名称: OA
 * @文件名称: ProjectDao.java
 * @日期: 2012-8-9 上午10:36:00  
 * @版权: 2012 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.persist.dao.system;

import java.util.LinkedHashMap;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.system.Project;

/**    
 * 项目名称：OA   <br/>
 * 类名称：ProjectDao.java   <br/>
 * 类描述： 项目信息维护数据库访问层接口  <br/>
 * 创建人：王亲臣   <br/>
 * 创建时间：2012-8-9 上午10:36:00   <br/>
 * 修改人：王亲臣   <br/>
 * 修改时间：2012-8-9 上午10:36:00   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public interface ProjectDao {

	/**
	 * 
	 * 方法描述：添加一条项目信息<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-8-9 上午11:08:51<br/>         
	 * @param project 项目实体<br/>   
	 * @return int 1成功0失败<br/>
	 * @version   1.0<br/>
	 */
	public int addProject(Project project);
	
	/**
	 * 
	 * 方法描述：删除一条项目信息<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-8-9 上午11:09:08<br/>         
	 * @param proId 项目UUID<br/>   
	 * @return int 1成功0失败<br/>   
	 * @version   1.0<br/>
	 */
	public int delProject(String proId);
	
	/**
	 * 
	 * 方法描述：查询项目信息列表<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-8-9 上午11:11:37<br/>         
	 * @param page 分页<br/>
	 * @param fields 查询字段<br/>
	 * @param sqlCondition 查询条件<br/>
	 * @param queryParams 条件参数<br/>
	 * @param orderby 排序字段<br/>      
	 * @return Page<Project> 项目信息分页<br/>   
	 * @version   1.0<br/>
	 */
	public Page<Project> searchProject(Page<Project> page,String fields,
			String sqlCondition,Object[] queryParams,
			LinkedHashMap<String, String> orderby);
	
	/**
	 * 
	 * 方法描述：修改一条项目信息<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-8-9 上午11:14:00<br/>         
	 * @param project 项目实体<br/>   
	 * @return int 1成功0失败<br/>
	 * @version   1.0<br/>
	 */
	public int updProject(Project project);
	
	/**
	 * 
	 * 方法描述：获取一条项目信息<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-8-9 上午11:15:36<br/>         
	 * @param proId 项目Id<br/>   
	 * @return Project 项目实体<br/>   
	 * @version   1.0<br/>
	 */
	public Project getProject(String proId);
}
