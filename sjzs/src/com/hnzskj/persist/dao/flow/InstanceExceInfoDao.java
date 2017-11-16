/**
 * flow
 *com.hnzskj.dao.flow
 *2012-4-92012上午10:04:00
 *郑辉
 *
 */
package com.hnzskj.persist.dao.flow;

import java.util.List;

import com.hnzskj.persist.bean.flow.InstanceExceInfo;

/**
 *创建人：郑辉
 *描述： 	
 *创建时间：2012-4-9 上午10:04:00
 *修改人：郑辉
 *修改时间：
 */
public interface InstanceExceInfoDao{
	/**
	 *创建人：郑辉
	 *描述：添加一条实例对象 	
	 *创建时间：2012-4-23 下午02:31:15
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： int
	 */
	public int addInstanceExceListInfo(InstanceExceInfo iel);
	
	/**
	 *创建人：郑辉
	 *描述： 	通过实例号，任务号，环节号码，获取当前或者上一或者下一环节实例对象
	 *创建时间：2012-4-23 下午02:31:28
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： InstanceExceInfo
	 */
	public InstanceExceInfo getInstanceExceInfo(int instance_id,int task_id,String tache_id);
	
	/**
	 *创建人：郑辉
	 *描述： 	修改实例信息
	 *创建时间：2012-4-23 下午02:32:08
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： int
	 */
	public int updateInstanceExceInfoStart(String type,int instance_id,int task_id,int tache_id);
	
	/**
	 *创建人：郑辉
	 *描述：获取该实例的所有环节属性 	
	 *创建时间：2012-4-23 下午02:32:20
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： List<InstanceExceInfo>
	 */
	public List<InstanceExceInfo> getlist(int instance_id);
	
	/**
	 *创建人：郑辉
	 *描述：获取当前环节属性的信息 	
	 *创建时间：2012-4-23 下午02:32:43
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： List<InstanceExceInfo>
	 */
	public List<InstanceExceInfo> getOldTache(int instance_id);
	
	/**
	 *创建人：郑辉
	 *描述： 获取当前实例下的最大任务号码	
	 *创建时间：2012-4-23 下午02:33:07
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： int
	 */
	public int getMaxTaskId(int instance_id);
	
	/**
	 *创建人：郑辉
	 *描述： 	通过实例编号，和环节编号，获取当前环节的信息
	 *创建时间：2012-4-23 下午02:33:28
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： List<InstanceExceInfo>
	 */
	public List<InstanceExceInfo> getInstanceTacheInfo(String instance_no,String tache_id);
	
	/**
	 *创建人：郑辉
	 *描述： 通过实例号和任务环节，获取到实例对象	
	 *创建时间：2012-6-16 下午03:42:53
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： InstanceExceInfo
	 */
	public InstanceExceInfo getInstanceExceInfoByTaskId(int instance_id,int task_id);
	
	/**
	 *创建人：郑辉
	 *描述：  	通过实例号和实例状态，获取到对象
	 *创建时间：2012-6-16 下午03:43:30
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： InstanceExceInfo
	 */
	public InstanceExceInfo getInstanceExceInfoByStatus(int instance_id);
}
