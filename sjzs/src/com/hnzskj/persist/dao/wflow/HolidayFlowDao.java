/*
 * @项目名称: OA
 * @文件名称: HolidayFlowDao.java
 * @日期: 2012-7-12
 * @版权: 2012 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.persist.dao.wflow;

import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.wflow.HolidayFlow;

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

public interface HolidayFlowDao {
	/**
	 * 类描述：添加一条
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-7-12 下午04:43:20 
	 * 修改人：
	 * 修改时间：2012-7-12 下午04:43:20  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public String addInfo(HolidayFlow holiday);
	/**
	 * 类描述：修改一条
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-7-12 下午04:43:32 
	 * 修改人：
	 * 修改时间：2012-7-12 下午04:43:32  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public int updateInfo(HolidayFlow holiday);
	
	
	/**
	 * 类描述：获取去到对象  通过实例
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-7-13 上午08:57:40 
	 * 修改人：
	 * 修改时间：2012-7-13 上午08:57:40  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public HolidayFlow getInfoByNo(int instance_no);
	
	/**
	 * 类描述：修改请假变成销假<br/>
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-7-17 下午05:46:56 
	 * 修改人：
	 * 修改时间：2012-7-17 下午05:46:56  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public int updateStatusInfo(HolidayFlow holiday);
	
	
	/**
	 * 类描述：获取到销假
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-7-17 下午05:51:25 
	 * 修改人：
	 * 修改时间：2012-7-17 下午05:51:25  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public HolidayFlow getInfoByStatus(String emplId);
	
	/**
	 * 类描述：分页查询
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-7-18 下午05:03:12 
	 * 修改人：
	 * 修改时间：2012-7-18 下午05:03:12  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public Page<HolidayFlow> searchHolidayFlow(Page<HolidayFlow> page, String fields,
			String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby) ;
	
	/**
	 * 类描述：获取当前用户是否正在请假
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-7-20 上午10:18:38 
	 * 修改人：
	 * 修改时间：2012-7-20 上午10:18:38  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public int getHolidayCount(String emplId,int status);
	/**
	 * 类描述：删除操作
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-8-13 下午05:41:53 
	 * 修改人：
	 * 修改时间：2012-8-13 下午05:41:53  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public int deleteinfo (String guid);
	
	/**
	 * 类描述：获取请假列表中的年度字段
	 * 创建人：郑辉  <br/>
	 * 创建时间：2013-1-7 上午10:31:13 
	 * 修改人：
	 * 修改时间：2013-1-7 上午10:31:13  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public List<String> getHolidayYearList();
}