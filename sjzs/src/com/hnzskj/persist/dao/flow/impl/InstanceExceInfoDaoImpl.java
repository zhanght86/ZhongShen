/**
 * flow
 *com.hnzskj.dao.flow
 *2012-4-92012上午10:04:00
 *郑辉
 *
 */
package com.hnzskj.persist.dao.flow.impl;

import java.util.List;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.DataUtil;
import com.hnzskj.persist.bean.flow.InstanceExceInfo;
import com.hnzskj.persist.dao.flow.InstanceExceInfoDao;

/**
 *创建人：郑辉
 *描述： 	
 *创建时间：2012-4-9 上午10:04:00
 *修改人：郑辉
 *修改时间：
 */
public class InstanceExceInfoDaoImpl extends BaseDao implements InstanceExceInfoDao{
	public int addInstanceExceListInfo(InstanceExceInfo iel){
		String sql ="insert into SYS_EXEC_INFO (INSTANCE_ID,TASK_ID,TACHE_ID,TASK_STATE,TEMPLATE_ID," +
				"OVERTIME_FLAG,LAST_COMMITOR,TASK_REACH_TIME,TASK_COMMIT_TIME,TASK_COMMITOR,TACHE_TYPE,STATE,REMARK,DEPARTMENT,TACHE_NAME,BEFORE_TACHE_ID) " +
				"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object [] params={
				iel.getInstance_id(),
				iel.getTask_id(),
				iel.getTache_id(),
				iel.getTask_state(),
				iel.getTemplate_id(),
				iel.getOvertime_flag(),
				iel.getLast_commitor(),
				iel.getTask_reach_time(),
				iel.getTask_commit_time(),
				iel.getTask_commitor(),
				iel.getTache_type(),
				iel.getState(),iel.getRemark(),
				iel.getDepartment(),
				iel.getTache_name(),
				iel.getBefore_tache_id()};
		int num =this.update(sql, params);
		return num;
	}
	
	public InstanceExceInfo getInstanceExceInfo(int instance_id,int task_id,String tache_id){
		String key="";
		if(tache_id.equals(null)||tache_id.equals("")){
			key="1";
			tache_id="1";
		}else{
			key="tache_id";
		}
		String sql ="select INSTANCE_ID,TASK_ID,TACHE_ID,TASK_STATE,TEMPLATE_ID,OVERTIME_FLAG,LAST_COMMITOR," +
				"TASK_REACH_TIME,TASK_COMMIT_TIME,TASK_COMMITOR,TACHE_TYPE,STATE,REMARK,DEPARTMENT,TACHE_NAME,BEFORE_TACHE_ID " +
				"from SYS_EXEC_INFO where INSTANCE_ID=? and "+key+" =? and task_id =?  ";
		Object [] params ={instance_id,tache_id,task_id};
		InstanceExceInfo iei =(InstanceExceInfo)this.get(sql, InstanceExceInfo.class, params);
		return iei;
	}
	
	public int updateInstanceExceInfoStart(String type,int instance_id,int task_id,int tache_id){
		String sql ="update  SYS_EXEC_INFO set TASK_STATE=?,TASK_COMMIT_TIME=? where instance_id=? and tache_id =? and task_id =?";
		Object [] params ={type,DataUtil.getNowTime(),instance_id,tache_id,task_id};
		int num =this.update(sql, params);
		return num;
	}
	
	@SuppressWarnings("unchecked")
	public List<InstanceExceInfo> getlist(int instance_id){
		List<InstanceExceInfo> list =null;
		String sql ="select INSTANCE_ID,TASK_ID,TACHE_ID,TASK_STATE,TEMPLATE_ID,OVERTIME_FLAG,LAST_COMMITOR," +
				"TASK_REACH_TIME,TASK_COMMIT_TIME,TASK_COMMITOR,TACHE_TYPE,STATE,REMARK,DEPARTMENT,TACHE_NAME,BEFORE_TACHE_ID " +
				"from SYS_EXEC_INFO where instance_id =? order by task_id asc";
		Object [] params ={instance_id};
		list =this.query(sql, InstanceExceInfo.class, params);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<InstanceExceInfo> getOldTache(int instance_id){
		List<InstanceExceInfo> dotlist =null;
		String sql ="select * from SYS_EXEC_INFO where  TACHE_ID not in (select TACHE_ID from SYS_TASK_LIST where INSTANCE_ID=?)  " +
				"and TACHE_ID !=1 and TACHE_ID !=2 and TASK_STATE='E' and INSTANCE_ID =?";
		Object [] params ={instance_id,instance_id};
		dotlist =this.query(sql,InstanceExceInfo.class, params);
		return dotlist;
	}
	
	public int getMaxTaskId(int instance_id){
		int maxno=0;
		String sql ="select max(TASK_ID) no from SYS_EXEC_INFO where INSTANCE_ID=?";
		Object[] params ={instance_id};
		maxno=(Integer)this.getSingleValue(sql, params);
		return maxno;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.flow.InstanceExceInfoDao#getInstanceTacheInfo()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<InstanceExceInfo> getInstanceTacheInfo(String instance_no,String tache_id) {
		// TODO Auto-generated method stub
		String sql ="select tache_name, tache_id,task_state,LAST_COMMITOR,TASK_REACH_TIME,TASK_COMMIT_TIME,TASK_COMMITOR,BEFORE_TACHE_ID from SYS_EXEC_INFO " +
				"where INSTANCE_ID=? and TACHE_ID=? order by task_reach_time desc";
		Object [] params ={instance_no,tache_id};
		List<InstanceExceInfo> list =(List<InstanceExceInfo>)this.query(sql, InstanceExceInfo.class, params);
		if(list!=null&&list.size()!=0) return list;
		return null;
	}
	
	public InstanceExceInfo getInstanceExceInfoByTaskId(int instance_id,int task_id){
		String sql ="select INSTANCE_ID,TASK_ID,TACHE_ID,TASK_STATE,TEMPLATE_ID,TACHE_NAME,BEFORE_TACHE_ID " +
				"from SYS_EXEC_INFO where TASK_ID in(select min(TASK_ID) from SYS_EXEC_INFO where INSTANCE_ID=? and TACHE_ID=?) " +
				"and INSTANCE_ID=? and TACHE_ID=?";
		Object [] params ={instance_id,task_id,instance_id,task_id};
		InstanceExceInfo iei =(InstanceExceInfo)this.get(sql, InstanceExceInfo.class, params);
		return iei;
	}
	
	public InstanceExceInfo getInstanceExceInfoByStatus(int instance_id){
		String sql ="select INSTANCE_ID,TASK_ID,TACHE_ID,TASK_STATE,TEMPLATE_ID," +
				"TACHE_NAME,BEFORE_TACHE_ID " +
				"from SYS_EXEC_INFO where INSTANCE_ID=? and task_state='B' ";
		Object [] params ={instance_id};
		InstanceExceInfo iei =(InstanceExceInfo)this.get(sql, InstanceExceInfo.class, params);
		return iei;
	}
}
