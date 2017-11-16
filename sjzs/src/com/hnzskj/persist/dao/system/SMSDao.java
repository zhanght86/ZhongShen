package com.hnzskj.persist.dao.system;

import java.io.Serializable;
import java.util.LinkedHashMap;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.system.SMS;


/**        
 * 
 * 类名称：短信发送记录数据访问层接口完成对短信发送记录的增加，删除和查询功能     <br/>
 * 类描述：<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-9-7 上午11:34:39   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-9-7 上午11:34:39   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */ 
public interface SMSDao {
	
	/**
	 * 
	 * 方法描述：将发送的短信存储到数据库中<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-17 下午02:52:24<br/>         
	 * @param operationLog
	 * @return
	 * @version   1.0
	 */
	public int save( SMS sMS );
	
	/**
	 * 
	 * 方法描述：根据指定的id删除数据库中的一条记录     
	 * 创建人：苏国庆   
	 * 创建时间：2011-3-3 下午02:05:52         
	 * @param sid
	 * @return 返回 1 表示删除成功 返回 0 表示删除失败
	 * @version   1.0
	 */
	public int delete( int sid );
	
	/**
	 * 
	 * 方法描述：删除一组记录，数据中记录了待删除的id   
	 * 创建人：苏国庆   
	 * 创建时间：2011-3-3 下午02:11:30         
	 * @param sids 要删除的id的数组
	 * @return 返回 int 所删除的记录的条数
	 * @version   1.0
	 */
	public int delete( Serializable... sids);
	
	/**
	 * 
	 * 方法描述：根据指定的id,从数据库中查询一条记录 ，如果记录不存在返回NULL
	 * 创建人：苏国庆   
	 * 创建时间：2011-3-3 下午02:14:39         
	 * @param sql 指定查询的SQL语句
	 * @return	返回查询到的一个OperationLog对象，如果不存在返回NULL
	 * @version   1.0
	 */
	public SMS getById( int id );
	
	
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
	 * 方法描述：判断当前系统是否已经发送过短信<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-9-8 上午10:13:35<br/>         
	 * @param sMS
	 * @return
	 * @version   1.0  
	 */
	public boolean isSendSMS(SMS sMS);
	
	/**
	 * 方法描述：指定查询，排序条件分页查询短信发送记录<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-26 下午04:27:50<br/>         
	 * @param page
	 * @param sqlCondition
	 * @param queryParams
	 * @param orderby
	 * @return
	 * @version   1.0  
	 */
	public Page<SMS> searchSMS(Page<SMS> page,String fields,
				String sqlCondition, Object[] queryParams,
				LinkedHashMap<String, String> orderby);

	/**
	 * 方法描述：根据合同id和短信类型删除短信发送记录<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-9-19 下午03:36:29<br/>         
	 * @param hbid
	 * @param spfail
	 * @return
	 * @version   1.0  
	 */	
	public int deleteByHtIdAndTypeCode(int hbid, String spfail);
}
