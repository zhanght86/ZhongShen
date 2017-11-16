/**
 * flow
 *com.hnzskj.dao.flow
 *2012-4-92012上午09:21:53
 *郑辉
 *
 */
package com.hnzskj.persist.dao.flow;

import java.util.List;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.flow.InstanceWF;

/**
 *创建人：郑辉
 *描述： 	
 *创建时间：2012-4-9 上午09:21:53
 *修改人：郑辉
 *修改时间：
 */
public interface InstanceWFDao{
	/**
	 *创建人：郑辉
	 *描述：	获取实例号的自增Id，创建一个实例号，方便流程向下运行 	
	 *创建时间：2012-4-9 上午09:28:51
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： int
	 */
	public int addInstanceWFInfo(InstanceWF wf );
	
	/**
	 * 类描述：删除实例
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-12-4 下午05:42:11 
	 * 修改人：
	 * 修改时间：2012-12-4 下午05:42:11  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public int delete(int instance_no);
	
	public InstanceWF getInstanceWF(InstanceWF iwf);
	
	/**
	 *创建人：郑辉
	 *描述： 获取用户的 完成过的历史任务
	 *创建时间：2012-4-16 下午02:04:51
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： List<InstanceWF>
	 */
	public List<InstanceWF> getInstanceWfInfo(String enti_id);
	
	public int update(int instance_id);
	
	public int updateState(int instance_id);
	
	public InstanceWF getInstanceWfInfo(int instance_no);
	
	public Page<InstanceWF> searchPage(Page<InstanceWF> page,String fields, String sqlCondition, Object[] queryParams,String enti_id);
}
