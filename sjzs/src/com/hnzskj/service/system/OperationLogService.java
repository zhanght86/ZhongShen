package com.hnzskj.service.system;

import java.io.Serializable;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.system.OperationLog;

public interface OperationLogService {
	
	/**
	 * 
	 * 方法描述：增加一个OperationLog实体
	 * 创建人：苏国庆   
	 * 创建时间：2011-3-4 上午09:19:28         
	 * @param operationLog 待增加的OperationLog
	 * @return	返回TRUE 操作成功 , 返回FALSE 操作失败
	 * @version   1.0
	 */
	public boolean add( OperationLog operationLog );
	
	/**
	 * 
	 * 方法描述：根据指定的Id的删除一个OperationLog实体
	 * 创建人：苏国庆   
	 * 创建时间：2011-3-4 上午09:21:05         
	 * @param id 待删除OperationLog的Id
	 * @return 返回TRUE 操作成功, 返回FALSE 操作失败
	 * @version   1.0
	 */
	public boolean delete( int id );
	
	/**
	 * 
	 * 方法描述：删除数组中所包含的id的实例
	 * 创建人：苏国庆   
	 * 创建时间：2011-3-4 上午09:22:23         
	 * @param ids 待删除OperationLog的id的数组
	 * @return 返回TRUE 操作成功, 返回FALSE 操作失败	
	 * @version   1.0
	 */
	public boolean delete( Serializable... ids );
	
	
	/**
	 * 
	 * 方法描述：根据指定的id,查找OperationLog实体
	 * 创建人：苏国庆   
	 * 创建时间：2011-3-4 上午09:25:37         
	 * @param id 
	 * @return 查找成功返回一个OperationLog对象,否则返回NULL 
	 * @version   1.0
	 */
	public OperationLog findById( String id );
	
	/**
	 * 
	 * 方法描述：根据指定的条件查询OperationLog实体
	 * 创建人：苏国庆   
	 * 创建时间：2011-3-4 上午10:21:14         
	 * @param page	用于指定需要查询的页数和每页数据的记录数
	 * @param operationLog 封装查询条件的实体operationLog
	 * @return	Page 封装了查询到的总记录数，当前页数，每页的记录数目，当前的记录
	 * @version   1.0
	 */
	public Page<OperationLog> searchOprLog( OperationLog operationLog, Page<OperationLog> page );

}	
