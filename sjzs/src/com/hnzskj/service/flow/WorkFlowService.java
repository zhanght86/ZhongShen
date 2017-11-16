/**
 * flow
 *com.hnzskj.service.flow
 *2012-4-72012下午06:51:13
 *郑辉
 *
 */
package com.hnzskj.service.flow;

import java.util.*;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.flow.*;
import com.hnzskj.persist.bean.sjzs.WorkFlowDTO;
import com.hnzskj.persist.bean.system.Employee;

/**
 *创建人：郑辉 描述： 创建时间：2012-4-7 下午06:51:13 修改人：郑辉 修改时间：
 */
public interface WorkFlowService {	
	/**
	 *创建人：郑辉 
	 *描述：创建工作流实例 分别插入相关的数据 ,返回创建出来的实例号 
	 *创建时间：2012-4-7 下午06:54:43
	 *修改人：郑辉
	 *修改时间： 返回类型： WorkFlow
	 */
	public int createJob(Template template,Employee employee);
	/**
	 *创建人：郑辉 描述： 添加一个工作流实例 insert SYS_INSTANCE_LIST 并且返回实例号 创建时间：2012-4-9
	 * 上午09:11:12 修改人：郑辉 修改时间： 返回类型： int
	 */
	public int initFlow(Template temp,Employee employee);

	/**
	 *创建人：郑辉 
	 *描述： 通过实例号，来初始化相关字段 
	 *创建时间：2012-4-7 下午06:55:14 
	 *修改人：郑辉 修改时间： 
	 *返回类型：
	 * void
	 */
	public WorkFlow initJob(int instance_id,Employee employee,WorkFlow flow);

	/**
	 *创建人：郑辉
	 *描述：获取环节属性中人员处理的 人员名称 	
	 *创建时间：2012-4-17 下午01:44:01
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： Object[]
	 */
	public Object[] getEmplIds(Tache tache,WorkFlow wflow);
	/**
	 *创建人：郑辉 
	 *描述： 完成调用工作流的对象的相关操作。 用来保存 或者修改或者业务逻辑调用实例对象
	 *创建时间：2012-4-7
	 *下午06:59:02 
	 *修改人：郑辉 
	 *修改时间： 返回类型： void
	 */
	public void commitSave(WorkFlow workflow,Employee employee) ;
	
	/**
	 *创建人：郑辉
	 *描述：登录用户的当前的任务 	
	 *创建时间：2012-4-16 上午10:26:10
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： List<TaskList>
	 */
	public List<TaskList> getTaskListByEntiID(String enti_id);
	
	/**
	 *创建人：郑辉
	 *描述： 	获取登录用户已经处理过的任务列表
	 *创建时间：2012-4-16 下午02:07:43
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： List<InstanceWF>
	 */
	public Page<InstanceWF> getInstanceWfByEmpId(String enti_id,InstanceWF wf, Page<InstanceWF> page);
	
	/**
	 *创建人：郑辉
	 *描述： 	添加任务环节审批意见
	 *创建时间：2012-4-13 下午04:43:19
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： void
	 */
	public void workflowMessSave(WorkFlow wf ,String mes,Employee emp);
	
	/**
	 *创建人：郑辉
	 *描述： 	获取任务环节的审批意见
	 *创建时间：2012-4-13 下午04:42:53
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： List<FlowMess>
	 */
	public List<FlowMess> getFlowMessByNoAndTache(int insatnce_no ,String LABEL);
	
	public int deleteInstanceAndTask(int template_no);
	
	/**
	 *创建人：郑辉
	 *描述： 	通过实例编号，和环节编号，获取当前环节的信息
	 *创建时间：2012-4-23 下午02:34:53
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： List<InstanceExceInfo>
	 */
	public List<InstanceExceInfo> getInstanceTacheInfo(String instance_no,String tache_id);
	
	/**
	 *创建人：郑辉
	 *描述：通过实例号和和任务编号获取上一环节的信息 	
	 *创建时间：2012-6-16 下午03:49:09
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： InstanceExceInfo
	 */
	public InstanceExceInfo getInstanceExecInfoByTask(int instance_no);
	
	/**
	 * 类描述：获取员工ID
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-7-25 上午08:47:57 
	 * 修改人：
	 * 修改时间：2012-7-25 上午08:47:57  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public Employee getEmplByEmplId(String emplId);
	
	/**
	 * 类描述：通过连接线的编号和模板获取对象
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-8-9 下午02:22:05 
	 * 修改人：
	 * 修改时间：2012-8-9 下午02:22:05  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public Line getLineInfoByLineId(String templateId,String lineId);
	
	/**
	 * 类描述：获取实例对象
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-8-15 上午11:38:37 
	 * 修改人：
	 * 修改时间：2012-8-15 上午11:38:37  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public InstanceWF getInstanceWfInfo(int instance_no);
	/**
	 * 类描述：保存流程图xml
	 * 创建人：wxz <br/>
	 * 创建时间：2012-8-15 上午11:38:37 
	 * 修改人：
	 * 修改时间：2012-8-15 上午11:38:37  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public int saveWorkXml(WorkFlowDTO workFlowDTO);
	public WorkFlowDTO findWorkXmlByType(String type);
	public int updateWorkXml(WorkFlowDTO tempDto);
	public WorkFlowDTO getWorkFlowInfo(String id);
	
	/**
	 * 根据导航图id删除流程图
	 * @param sjdhId
	 * @return
	 */
	public boolean delWorkFlowBySjdhId(String sjdhId);
}
