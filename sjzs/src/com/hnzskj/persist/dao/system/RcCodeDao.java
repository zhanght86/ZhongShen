/*
 * @项目名称: OA
 * @文件名称: RcCodeDao.java
 * @日期: 2012-8-21
 * @版权: 2012 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.persist.dao.system;

import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.system.RcCode;

  /**        
 * 
 * 类名称：RcCodeDao
 * 类描述：<br/>
 * 创建人：郑辉  <br/>
 * 创建时间：2012-8-21 上午11:16:35 
 * 修改人：Administrator
 * 修改时间：
 * 修改备注：   
 * @version   1.0     
 */

public interface RcCodeDao {
	/**
	 * 类描述：添加一条数据字典
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-8-21 上午11:16:55 
	 * 修改人：
	 * 修改时间：2012-8-21 上午11:16:55  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public int addInfo(RcCode rc);
	
	/**
	 * 类描述：修改记录
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-8-21 上午11:18:30 
	 * 修改人：
	 * 修改时间：2012-8-21 上午11:18:30  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public int updateInfo(RcCode rc);
	
	/**
	 * 类描述：获取父级字典
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-8-21 上午11:19:57 
	 * 修改人：
	 * 修改时间：2012-8-21 上午11:19:57  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public Page<RcCode> findParents(Page<RcCode> page, String fields,
			String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby);
	
	/**
	 * 类描述：获取父级子字典
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-8-21 上午11:20:11 
	 * 修改人：
	 * 修改时间：2012-8-21 上午11:20:11  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public List<RcCode> findSonsByParent(String parentId);
	
	/**
	 * 类描述：获取一整条数据
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-8-21 下午02:58:47 
	 * 修改人：
	 * 修改时间：2012-8-21 下午02:58:47  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public RcCode findRcCodeInfo(String rcId);
	
	/**
	 * 类描述：通过主键删除
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-8-21 下午03:56:46 
	 * 修改人：
	 * 修改时间：2012-8-21 下午03:56:46  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public int deleteInfo(String rcId);
	
	/**
	 * 类描述：删除父下的所有字典
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-8-21 下午03:57:00 
	 * 修改人：
	 * 修改时间：2012-8-21 下午03:57:00  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public int deleteByParentInfo(String rcId);
	
	/**
	 * 类描述：通过父级编号获取对象
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-8-25 上午08:43:04 
	 * 修改人：
	 * 修改时间：2012-8-25 上午08:43:04  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public RcCode findRcCodeByNoInfo(String rcNo);
	
	/**
	 * 类描述：通过sql查询出数据
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-8-25 上午11:42:17 
	 * 修改人：
	 * 修改时间：2012-8-25 上午11:42:17  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public List<Object> getCodeObjects(String sql,String objClass);
}
