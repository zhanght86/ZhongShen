/**
 * flow
 *com.hnzskj.dao.flow
 *2012-4-92012上午10:27:31
 *郑辉
 *
 */
package com.hnzskj.persist.dao.flow;

import java.util.List;

import com.hnzskj.persist.bean.flow.TaskList;

/**
 *创建人：郑辉
 *描述： 	
 *创建时间：2012-4-9 上午10:27:31
 *修改人：郑辉
 *修改时间：
 */
public interface TaskListDao{
	/**
	 *创建人：郑辉
	 *描述： 	添加方法
	 *创建时间：2012-4-9 上午10:36:07
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： int
	 */
	public int addTaskList(TaskList task);
	
	/**
	 *创建人：郑辉
	 *描述： 获取
	 *创建时间：2012-4-9 上午11:16:30
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： TaskList
	 */
	public TaskList getTaskByInstanceID(int instance_id,String enti_id);
	

	/**
	 *创建人：郑辉
	 *描述： 获取
	 *创建时间：2012-4-9 上午11:16:30
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： TaskList
	 */
	public List<TaskList> getTaskByInstanceID(int instance_id);
	
	public int deleteTask(int instance_id,String emplId);
	
	public List<TaskList> getTaskListByEnti(String enti_id);
	
	public int deleteTaskList (int template_no);
	
	/**
	 *创建人：郑辉
	 *描述： 删除该实例的所有任务	
	 *创建时间：2012-6-14 下午02:41:18
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： int
	 */
	public int deleteTaskByInstance(int instance_id);
}
