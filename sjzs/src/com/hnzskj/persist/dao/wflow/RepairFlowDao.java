/*
 * @项目名称: OA
 * @文件名称: HolidayFlowDao.java
 * @日期: 2012-7-12
 * @版权: 2012 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.persist.dao.wflow;

import java.util.LinkedHashMap;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.wflow.RepairFlow;

  /**        
 * 
 * 类名称：HolidayFlowDao
 * 类描述：<br/>
 * 创建人：郑辉  <br/>
 * 创建时间：2012-7-12 下午04:26:06 
 * 修改人：Administrator
 * 修改时间：
 * 修改备注：   
 * @version   1.0     
 */

public interface RepairFlowDao {
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
	 * 类描述：获取去到对象  通过实例
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-7-13 上午08:57:40 
	 * 修改人：
	 * 修改时间：2012-7-13 上午08:57:40  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public RepairFlow getInfoByNo(int instance_no);
	
	
	
	/**
	 * 类描述：分页查询
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-7-18 下午05:03:12 
	 * 修改人：
	 * 修改时间：2012-7-18 下午05:03:12  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public Page<RepairFlow> search(Page<RepairFlow> page, String fields,
			String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby) ;
}
