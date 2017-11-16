/**
 * flow
 *com.hnzskj.dao.flow
 *2012-4-92012上午10:27:31
 *郑辉
 *
 */
package com.hnzskj.persist.dao.flow.impl;

import java.util.List;

import com.hnzskj.common.BaseDao;
import com.hnzskj.persist.bean.flow.TaskList;
import com.hnzskj.persist.dao.flow.TaskListDao;

/**
 *创建人：郑辉
 *描述： 	
 *创建时间：2012-4-9 上午10:27:31
 *修改人：郑辉
 *修改时间：
 */
public class TaskListDaoImpl extends BaseDao implements TaskListDao{
	/**
	 *创建人：郑辉
	 *描述： 	添加方法
	 *创建时间：2012-4-9 上午10:36:07
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： int
	 */
	public int addTaskList(TaskList task){
		String sql ="insert into SYS_TASK_LIST (INSTANCE_ID,TASK_ID,TACHE_ID,ENTI_ID,ENTI_TYPE,TEMPLATE_ID,TASK_STATE,LAST_COMMITOR,OVERTIME_FLAG,TACHE_TYPE,TASK_REACH_TIME,START_OPERATOR,DEPARTMENT,ROLE_CODE,STATE,TACHE_NAME) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object [] params={task.getInstance_id(),task.getTask_id(),task.getTache_id(),task.getEnti_id(),task.getEnti_type(),task.getTemplate_id(),
				task.getTask_state(),task.getLast_commitor(),task.getOvertime_flag(),task.getTache_type(),task.getTask_reach_time(),task.getStart_operator(),task.getDepartment(),task.getRole_code(),task.getState(),task.getTache_name()};
		int num =0;
		num =this.update(sql, params);
		return num;
	}
	
	/**
	 *创建人：郑辉
	 *描述： 获取
	 *创建时间：2012-4-9 上午11:16:30
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： TaskList
	 */
	public TaskList getTaskByInstanceID(int instance_id,String enti_id){
		TaskList task =null;
		String sql ="select INSTANCE_ID,TASK_ID,TACHE_ID,ENTI_ID,ENTI_TYPE,TEMPLATE_ID,TASK_STATE,LAST_COMMITOR,OVERTIME_FLAG,TACHE_TYPE,TASK_REACH_TIME,START_OPERATOR,DEPARTMENT,ROLE_CODE,STATE,TACHE_NAME from SYS_TASK_LIST where INSTANCE_ID=? and enti_id=?";
		Object[] params ={instance_id,enti_id};
		task =(TaskList)this.get(sql, TaskList.class, params);
		if(null ==task) return null;
		return task;
	}
	

	/**
	 *创建人：郑辉
	 *描述： 获取
	 *创建时间：2012-4-9 上午11:16:30
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： TaskList
	 */
	@SuppressWarnings("unchecked")
	public List<TaskList> getTaskByInstanceID(int instance_id){
		List<TaskList> taskList=null;
		String sql ="select INSTANCE_ID,TASK_ID,TACHE_ID,ENTI_ID,ENTI_TYPE,TEMPLATE_ID,TASK_STATE,LAST_COMMITOR,OVERTIME_FLAG,TACHE_TYPE,TASK_REACH_TIME,START_OPERATOR,DEPARTMENT,ROLE_CODE,STATE,TACHE_NAME from SYS_TASK_LIST where INSTANCE_ID=?";
		Object[] params ={instance_id};
		taskList =(List<TaskList>)this.query(sql, TaskList.class, params);
		if(null ==taskList) return null;
		return taskList;
	}
	public int deleteTask(int instance_id,String emplId){
		String sql ="delete SYS_TASK_LIST where INSTANCE_ID=? and enti_id=?";
		Object[] params ={instance_id,emplId};
		int num =this.update(sql, params);
		return num;
	}
	
	public int deleteTaskByInstance(int instance_id){
		String sql ="delete SYS_TASK_LIST where INSTANCE_ID=?";
		Object[] params ={instance_id};
		int num =this.update(sql, params);
		return num;
	}
	
	@SuppressWarnings("unchecked")
	public List<TaskList> getTaskListByEnti(String enti_id){
		List<TaskList> tasklist =null;
		String sql ="select INSTANCE_ID,TASK_ID,TACHE_ID,dbo.getEmplNameById(ENTI_ID) as enti_ids,ENTI_ID,ENTI_TYPE,TEMPLATE_ID,dbo.getTemplateUrlById(TEMPLATE_ID) as template_url,dbo.getTemplateNameById(TEMPLATE_ID) as template_name,TASK_STATE,dbo.getEmplNameById(LAST_COMMITOR) as LAST_COMMITORs,LAST_COMMITOR,OVERTIME_FLAG,TACHE_TYPE,TASK_REACH_TIME,dbo.getEmplNameById(START_OPERATOR) as START_OPERATORs ,START_OPERATOR,DEPARTMENT,ROLE_CODE,STATE,TACHE_NAME " +
				"from SYS_TASK_LIST where ENTI_ID=? and TASK_STATE='B' order by instance_id desc";
		Object [] params ={enti_id};
		tasklist=this.query(sql, TaskList.class, params);
		return tasklist;
	}
	
	public int deleteTaskList (int template_no){
		int num =0;
		String sql ="delete SYS_TASK_LIST where template_id =?"; 
		Object [] params ={template_no};
		num =this.update(sql, params);
		return num;
	}
}
