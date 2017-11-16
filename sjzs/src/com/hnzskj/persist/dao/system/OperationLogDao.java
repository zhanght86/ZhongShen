package com.hnzskj.persist.dao.system;

import java.io.Serializable;
import java.util.LinkedHashMap;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.system.OperationLog;


public interface OperationLogDao {
	
	/**
	 * 
	 * 方法描述：将操作日志存储到数据库中<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-17 下午02:52:24<br/>         
	 * @param operationLog
	 * @return
	 * @version   1.0
	 */
	public int save( OperationLog operationLog );
	
	/**
	 * 
	 * 方法描述：根据指定的id删除数据库中的一条记录     
	 * 创建人：苏国庆   
	 * 创建时间：2011-3-3 下午02:05:52         
	 * @param id
	 * @return 返回 1 表示删除成功 返回 0 表示删除失败
	 * @version   1.0
	 */
	public int delete( int id );
	
	/**
	 * 
	 * 方法描述：删除一组记录，数据中记录了待删除的id   
	 * 创建人：苏国庆   
	 * 创建时间：2011-3-3 下午02:11:30         
	 * @param ids 要删除的id的数组
	 * @return 返回 int 所删除的记录的条数
	 * @version   1.0
	 */
	public int delete( Serializable... ids);
	
	/**
	 * 
	 * 方法描述：根据指定的id,从数据库中查询一条记录 ，如果记录不存在返回NULL
	 * 创建人：苏国庆   
	 * 创建时间：2011-3-3 下午02:14:39         
	 * @param sql 指定查询的SQL语句
	 * @return	返回查询到的一个OperationLog对象，如果不存在返回NULL
	 * @version   1.0
	 */
	public OperationLog getById( String id );
	
	
	/**
	 * 
	 * 方法描述：根据指定条件返回查询到的记录的数目
	 * 创建人：苏国庆   
	 * 创建时间：2011-3-4 下午05:17:45         
	 * @param sqlCondition 指定的查询语句的条件字段如"where field1=?"
	 * @param params	
	 * @return
	 * @version   1.0
	 */
	public int getCount( String sqlCondition, Object[] params);
	
	/**
	 * 
	 * 方法描述：无条件查询所有<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-26 下午03:56:24<br/>         
	 * @return
	 * @version   1.0
	 */
	public Page<OperationLog> searchOperationLog(String fields);
	
	/**
	 * 
	 * 方法描述：无条件分布查询所有<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-26 下午04:10:59<br/>         
	 * @param page
	 * @return
	 * @version   1.0
	 */
	public Page<OperationLog> searchOperationLog(Page<OperationLog> page,String fields);
	
	/**
	 * 
	 * 方法描述：根据查询条件分布查询<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-26 下午04:11:17<br/>         
	 * @param page
	 * @param sqlCondition
	 * @param queryParams
	 * @return
	 * @version   1.0
	 */
	public Page<OperationLog> searchOperationLog(Page<OperationLog> page,String fields,
			String sqlCondition,Object[] queryParams);

	/**
	 * 
	 * 方法描述：无条件分页，按指定排序条件查询<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-26 下午04:11:39<br/>         
	 * @param page
	 * @param orderby
	 * @return
	 * @version   1.0
	 */
	public Page<OperationLog> searchOperationLog(Page<OperationLog> page,String fields,
			LinkedHashMap<String, String> orderby);
	
	
	/**
	 * 方法描述：指定查询，排序条件分页查询<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-26 下午04:27:50<br/>         
	 * @param page
	 * @param sqlCondition
	 * @param queryParams
	 * @param orderby
	 * @return
	 * @version   1.0  
	 */
	public Page<OperationLog> searchOperationLog(Page<OperationLog> page,String fields,
				String sqlCondition, Object[] queryParams,
				LinkedHashMap<String, String> orderby);
}
