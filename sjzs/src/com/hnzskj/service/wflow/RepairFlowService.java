/*
 * @项目名称: OA
 * @文件名称: SoftWareItemsService.java
 * @日期: 2012-7-16
 * @版权: 2012 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.service.wflow;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.wflow.RepairFlow;

  /**        
 * 
 * 类名称：
 * 类描述：<br/>
 * 创建人：郑辉  <br/>
 * 创建时间：2012-7-16 下午02:35:29 
 * 修改人：Administrator
 * 修改时间：
 * 修改备注：   
 * @version   1.0     
 */

public interface RepairFlowService {
	/**
	 * 类描述：添加一条
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-7-12 下午04:43:20 
	 * 修改人：
	 * 修改时间：2012-7-12 下午04:43:20  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public String addInfo(RepairFlow repair);
	/**
	 * 类描述：修改一条
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-7-12 下午04:43:32 
	 * 修改人：
	 * 修改时间：2012-7-12 下午04:43:32  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public int updateInfo(RepairFlow repair);
	
	
	/**
	 * 类描述：获取去到对象  通过实例号
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-7-13 上午08:57:40 
	 * 修改人：
	 * 修改时间：2012-7-13 上午08:57:40  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public RepairFlow getInfo(int instance_no);
	
	/**
	 * 类描述：分页查询
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-7-20 下午04:47:21 
	 * 修改人：
	 * 修改时间：2012-7-20 下午04:47:21  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public Page<RepairFlow> search(RepairFlow repair, Page<RepairFlow> page) ;
}
