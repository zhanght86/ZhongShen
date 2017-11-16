/*
 * @项目名称: htglxt
 * @文件名称: SMSService.java
 * @日期: 2011-9-7
 * @版权: 2011 河南中审科技有限公司 
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.service.system;

import java.io.Serializable;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.system.SMS;

/**        
 * 
 * 类名称：SMSService     <br/>
 * 类描述：短信发送记录业务层接口<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-9-7 上午11:34:24   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-9-7 上午11:34:24   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */
public interface SMSService {
	/**
	 * 
	 * 方法描述：增加一个SMS实体
	 * 创建人：苏国庆   
	 * 创建时间：2011-3-4 上午09:19:28         
	 * @param sMS 待增加的SMS
	 * @return	返回TRUE 操作成功 , 返回FALSE 操作失败
	 * @version   1.0
	 */
	public boolean add( SMS sMS );
	
	/**
	 * 
	 * 方法描述：根据指定的Id的删除一个SMS实体
	 * 创建人：苏国庆   
	 * 创建时间：2011-3-4 上午09:21:05         
	 * @param id 待删除SMS的Id
	 * @return 返回TRUE 操作成功, 返回FALSE 操作失败
	 * @version   1.0
	 */
	public boolean delete( int id );
	
	/**
	 * 
	 * 方法描述：删除数组中所包含的id的实例
	 * 创建人：苏国庆   
	 * 创建时间：2011-3-4 上午09:22:23         
	 * @param ids 待删除SMS的id的数组
	 * @return 返回TRUE 操作成功, 返回FALSE 操作失败	
	 * @version   1.0
	 */
	public boolean delete( Serializable... ids );
	
	
	/**
	 * 
	 * 方法描述：根据指定的id,查找短信发送记录
	 * 创建人：苏国庆   
	 * 创建时间：2011-3-4 上午09:25:37         
	 * @param id 
	 * @return 查找成功返回一个SMS对象,否则返回NULL 
	 * @version   1.0
	 */
	public SMS findById( int id );
	
	/**
	 * 
	 * 方法描述：根据指定的条件查询短信发送记录
	 * 创建人：苏国庆   
	 * 创建时间：2011-3-4 上午10:21:14         
	 * @param page	用于指定需要查询的页数和每页数据的记录数
	 * @param sMS 封装查询条件的实体sMS
	 * @return	Page 封装了查询到的总记录数，当前页数，每页的记录数目，当前的记录
	 * @version   1.0
	 */
	public Page<SMS> searchSMS( SMS sMS, Page<SMS> page );

	/**
	 * 方法描述：<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-9-19 下午03:34:08<br/>         
	 * @param hbid
	 * @param spfail
	 * @version   1.0  
	 */	
	public int deleteByHtIdAndTypeCode(int hbid, String spfail);
}
