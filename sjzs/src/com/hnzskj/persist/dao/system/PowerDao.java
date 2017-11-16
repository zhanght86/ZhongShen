/*
 * @项目名称: htglxt
 * @文件名称: PowerDao.java
 * @日期: 2011-5-27
 * @版权: 2011 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.persist.dao.system;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.system.Power;

/**        
 * 
 * 类名称：PowerDao     <br/>
 * 类描述：<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-5-27 上午10:24:01   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-5-27 上午10:24:01   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */
public interface PowerDao {
	/**
	 * 方法描述：保存一个权限信息到数据库<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 上午10:29:49<br/>         
	 * @param role
	 * @return
	 * @version   1.0  
	 */
	public int addPower(Power power);
	
	/**
	 * 方法描述：根据权限编号删除一个权限<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 上午10:30:10<br/>         
	 * @param powerCode
	 * @return
	 * @version   1.0  
	 */
	public int deletePowerByPowerIds(Serializable... pcodes);
	
	/**
	 * 方法描述：更新一个权限信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 上午10:30:48<br/>         
	 * @param power
	 * @return
	 * @version   1.0  
	 */
	public int updatePower(Power power);
	
	/**
	 * 方法描述：根据权限编号查询权限<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午02:43:59<br/>         
	 * @param pcode
	 * @return
	 * @version   1.0  
	 */
	public Power findById(String pcode);
	
	/**
	 * 方法描述：无条件查询所有权限<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 上午10:31:06<br/>
	 * @param 查询字段
	 * @return
	 * @version   1.0  
	 */
	public Page<Power> searchPower(String fields);
	
	/**
	 * 方法描述：无条件分页查询所有权限信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 上午10:31:23<br/>         
	 * @param page
	 * @param fields 查询的字段
	 * @return
	 * @version   1.0  
	 */
	public Page<Power> searchPower(Page<Power> page, String fields);
	
	/**
	 * 方法描述：根据指定条件查询分页查询权限信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 上午10:34:06<br/>         
	 * @param page
	 * @param fields	查询字段
	 * @param sqlCondition 查询条件
	 * @param queryParams 查询语句参数
	 * @return
	 * @version   1.0  
	 */
	public Page<Power> searchPower(Page<Power> page, String fields,
			String sqlCondition, Object[] queryParams);
	
	/**
	 * 方法描述：根据指定排序条件分页查询权限信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 上午10:35:31<br/>         
	 * @param page
	 * @param fields	查询字段
	 * @param orderby	排序条件
	 * @return
	 * @version   1.0  
	 */
	public Page<Power> searchPower(Page<Power> page, String fields,
			LinkedHashMap<String, String> orderby);
	
	/**
	 * 方法描述：根据指定查询条件，排序条件分页查询权限信息<br/>
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
	public Page<Power> searchPower(Page<Power> page, String fields,
			String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby);
	
	/**
	 * 方法描述：根据指定的sql语句查询权限<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-13 上午10:25:56<br/>         
	 * @param sql
	 * @param params
	 * @return
	 * @version   1.0  
	 */
	public List<Power> searchPowerBySQL(String sql, Object[] params);

	/**
	 * 方法描述：根据功能编号查询父功能编号<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-7-1 上午08:48:12<br/>         
	 * @param pcode
	 * @return
	 * @version   1.0  
	 */	
	public String findParentCode(String pcode);
	
	/**
	 * 类描述：通过ID查出名字
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-7-1 上午10:36:17 
	 * 修改人：
	 * 修改时间：2012-7-1 上午10:36:17  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public Power findPowerNameById(String powerId);
}
