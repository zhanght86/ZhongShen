/**
 * flow
 *com.hnzskj.dao.flow
 *2012-4-92012上午11:40:41
 *郑辉
 *
 */
package com.hnzskj.persist.dao.flow.impl;

import java.util.List;

import com.hnzskj.common.BaseDao;
import com.hnzskj.persist.bean.flow.FlowMess;
import com.hnzskj.persist.dao.flow.FlowMessDao;

/**
 *创建人：郑辉
 *描述： 	
 *创建时间：2012-4-9 上午11:40:41
 *修改人：郑辉
 *修改时间：
 */
public class FlowMessDaoImpl extends BaseDao implements FlowMessDao{
	@SuppressWarnings("unchecked")
	public List<FlowMess> getFlowMessInfo(int insatnce_id){
		List<FlowMess> flowMessList =null;
		String sql ="select ID,INSTANCE_ID,DEPT_ID,DEPT_NAME,PERSON_ID,BODY,SHENPI_TIME,LABEL from SYS_FlowMess where INSTANCE_ID=?";
		Object[] params={insatnce_id};
		flowMessList=this.query(sql, FlowMessDaoImpl.class, params);;
		return flowMessList;
	}
	
	public int add(FlowMess fm){
		int num =0;
		String sql ="insert into SYS_FlowMess(INSTANCE_ID,DEPT_ID,DEPT_NAME,PERSON_ID,PERSON_NAME,BODY,SHENPI_TIME,LABEL) values(?,?,?,?,?,?,?,?)";
		Object [] params ={fm.getInstance_id(),fm.getDept_id(),fm.getDept_name(),fm.getPerson_id(),fm.getPerson_name(),fm.getBody(),fm.getShenpi_time(),fm.getLabel()};
		num =this.update(sql, params);
		return num;
	}
	
	@SuppressWarnings("unchecked")
	public List<FlowMess> getFlowMessByNoAndTache(int insatnce_no ,String LABEL)
	{
		List<FlowMess> flowMessList =null;
		String sql ="select ID,INSTANCE_ID,DEPT_ID,DEPT_NAME,PERSON_ID,PERSON_NAME,BODY,SHENPI_TIME,LABEL from SYS_FlowMess where INSTANCE_ID=? and LABEL=? order by SHENPI_TIME desc";
		Object[] params={insatnce_no,LABEL};
		flowMessList=this.query(sql, FlowMess.class, params);;
		return flowMessList;
	}
}
