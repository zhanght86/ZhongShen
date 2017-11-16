/*
 * @项目名称: OA
 * @文件名称: LatentCusterService.java
 * @日期: 2012-8-9 下午05:56:52  
 * @版权: 2012 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.service.system;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.system.LatentCuster;

/**    
 * 项目名称：OA   <br/>
 * 类名称：LatentCusterService.java   <br/>
 * 类描述：潜在客户级别字典表业务层接口   <br/>
 * 创建人：王亲臣   <br/>
 * 创建时间：2012-8-9 下午05:56:52   <br/>
 * 修改人：王亲臣   <br/>
 * 修改时间：2012-8-9 下午05:56:52   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public interface LatentCusterService {

	/**
	 * 
	 * 方法描述：添加一条信息<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-8-9 下午05:27:26<br/>         
	 * @param latentCuster 实体信息<br/>   
	 * @return boolean true成功false失败<br/>   
	 * @version   1.0<br/>
	 */
	public boolean addLatentCuster(LatentCuster latentCuster);
	
	/**
	 * 
	 * 方法描述：删除一条信息<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-8-9 下午05:28:27<br/>         
	 * @param lcId 标识id<br/>   
	 * @return boolean true成功false失败<br/>
	 * @version   1.0<br/>
	 */
	public boolean delLatentCuster(String lcId); 
	
	/**
	 * 
	 * 方法描述：修改一条信息<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-8-9 下午05:36:54<br/>         
	 * @param latentCuster 实体信息<br/>   
	 * @return boolean true成功false失败<br/>
	 * @version   1.0<br/>
	 */
	public boolean updLatentCuster(LatentCuster latentCuster);
	
	/**
	 * 
	 * 方法描述：查询一条信息<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-8-9 下午05:40:58<br/>         
	 * @param lcId 标识Id<br/>   
	 * @return LatentCuster<br/>   
	 * @version   1.0<br/>
	 */
	public LatentCuster getLatentCuster(String lcId);
	
	/**
	 * 
	 * 方法描述：查询信息列表<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-8-9 下午05:44:50<br/>         
	 * @param fields 查询字段<br/>
	 * @param latentCuster 实体信息<br/>
	 * @return Page<LatentCuster> 信息分页<br/>   
	 * @version   1.0<br/>
	 */
	public Page<LatentCuster> searchLatentCuster(Page<LatentCuster> page,LatentCuster latentCuster);
	 
}
