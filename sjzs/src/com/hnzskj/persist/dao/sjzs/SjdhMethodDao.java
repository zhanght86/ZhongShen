/*
 * @项目名称: OA
 * @文件名称: HolidayFlowDao.java
 * @日期: 2012-7-12
 * @版权: 2012 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.persist.dao.sjzs;

import java.io.Serializable;
import java.util.LinkedHashMap;

import com.hnzskj.common.Page;

import com.hnzskj.persist.bean.sjzs.SjdhMethod;

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

/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public interface SjdhMethodDao {
	/**
	 * 描述：添加
	 * 创建人:郑辉
	 * 创建时间： 2013-2-26 下午03:17:12
	 * 修改时间：
	 * 修改人：
	 */
	public int addInfo(SjdhMethod sjdh);
	/**
	 * 描述：修改
	 * 创建人:郑辉
	 * 创建时间： 2013-2-26 下午03:17:20
	 * 修改时间：
	 * 修改人：
	 */
	public int update(SjdhMethod sjdh);
	/**
	 * 描述：删除
	 * 创建人:郑辉
	 * 创建时间： 2013-2-26 下午03:17:27
	 * 修改时间：
	 * 修改人：
	 */
	public int delete(String id);
	
	/**
	 * 描述：分页查询
	 * 创建人:郑辉
	 * 创建时间： 2013-2-26 下午03:23:11
	 * 修改时间：
	 * 修改人：
	 */
	Page<SjdhMethod> searchSjdhMethodParentId(Page<SjdhMethod> page,
			String fields, String sqlCondition, Object[] params,
			LinkedHashMap<String, String> orderBy);
	

	/**
	 * 描述：修改审计导航中审计方法对应的流程图。
	 * 创建人:郑辉
	 * 创建时间： 2013-2-27 上午10:01:39
	 * 修改时间：
	 * 修改人：
	 */
	int updateTempNO(String Id,int template_no);
	/**
	 * 查询对象
	 * 描述：
	 * 创建人:郑辉
	 * 创建时间： 2013-2-28 下午04:50:08
	 * 修改时间：
	 * 修改人：
	 */
	public SjdhMethod findById(String Id);
	public int updateSJDHType(String id, String workFlowId);
}