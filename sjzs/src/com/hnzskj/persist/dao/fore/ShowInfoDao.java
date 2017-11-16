package com.hnzskj.persist.dao.fore;

import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.fore.Content;

/**
 *类名称:ShowInfoDao.java <br/>
 *类描述：<br/>
 *创建人： 平西强<br/>
 *创建时间:2013-3-22上午10:31:42<br/>
 *修改人: <br/>
 *修改时间:2013-3-22上午10:31:42<br/>
 *修改备注: <br/>
 * 
 * @version 1.0
 *@param <E>
 */
public interface ShowInfoDao {

	/**
	 *方法描述： <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-22 上午10:31:46
	 * 
	 *@return
	 *@version 1.0
	 */
	public List<Content> searchSjfgByField(int num);

	/**
	 *方法描述： <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-22 上午10:31:49
	 * 
	 *@return
	 *@version 1.0
	 */
	public List<Content> searchDxyjByField(int num);

	/**
	 *方法描述： <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-22 上午10:31:53
	 * 
	 *@return
	 *@version 1.0
	 */
	public List<Content> searchFfalByField(int num);

	/**
	 *方法描述： <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-22 上午10:31:56
	 * 
	 *@return
	 *@version 1.0
	 */
	public List<Content> searchSjdhByField(int num);

	/**
	 *方法描述： <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-26 下午04:28:57
	 * 
	 *@return
	 *@version 1.0
	 */
	public List<Content> searchSjsxByField(int num);

	/**
	 *方法描述： <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-26 下午04:29:04
	 * 
	 *@return
	 *@version 1.0
	 */
	public List<Content> searchSsfaByField(int num);

	/**
	 * 
	 * 方法描述: 查询各项最新的n条数据<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-4-23 上午09:37:43
	 * @param
	 *
	 */
	public Page getLatestMethods(Class clazz, String fields, String tableName, String condition,
			 Object[] params, LinkedHashMap<String, String> orderBy, Page page);
}
