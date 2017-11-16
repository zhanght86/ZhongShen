/**
 * flow
 *com.hnzskj.dao.flow
 *2012-4-92012上午09:21:53
 *郑辉
 *
 */
package com.hnzskj.persist.dao.flow.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.DataUtil;
import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.flow.InstanceWF;
import com.hnzskj.persist.dao.flow.InstanceWFDao;

/**
 *创建人：郑辉
 *描述： 	
 *创建时间：2012-4-9 上午09:21:53
 *修改人：郑辉
 *修改时间：
 */
public class InstanceWFDaoImpl extends BaseDao implements InstanceWFDao{
	/**
	 *创建人：郑辉
	 *描述：	获取实例号的自增Id，创建一个实例号，方便流程向下运行 	
	 *创建时间：2012-4-9 上午09:28:51
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： int
	 */
	public int addInstanceWFInfo(InstanceWF wf ){
		int instance_id =0;
		String sql ="insert into SYS_INSTANCE_LIST (TEMPLATE_ID,STATE,START_TIME,END_TIME,START_OPERATOR,REMARK,DEPARTMENT) values(?,?,?,?,?,?,?)";
		Object [] params ={wf.getTemplate_id(),wf.getState(),wf.getStart_time(),wf.getEnd_time(),wf.getStart_operator(),wf.getRemark(),wf.getDepartment()};
		instance_id=insertDataAndGetId(sql,params);
		return instance_id;
	}
	
	public InstanceWF getInstanceWF(InstanceWF iwf){
		
		return null;
	}
	
	/**
	 *创建人：郑辉
	 *描述： 获取用户的 完成过的历史任务
	 *创建时间：2012-4-16 下午02:04:51
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： List<InstanceWF>
	 */
	@SuppressWarnings("unchecked")
	public List<InstanceWF> getInstanceWfInfo(String enti_id){
		List<InstanceWF> wflist =null;
		String sql = "select TEMPLATE_ID,STATE,START_TIME,END_TIME,START_OPERATOR,REMARK,DEPARTMENT,instance_id from SYS_INSTANCE_LIST where INSTANCE_ID in (select INSTANCE_ID from SYS_EXEC_INFO where TASK_COMMITOR=? group by INSTANCE_ID)";

		Object [] params={enti_id};
		wflist=(List<InstanceWF>)this.query(sql, InstanceWF.class, params);
		return wflist;
	}
	
	public int update(int instance_id){
		int num =0;
		String sql ="update SYS_INSTANCE_LIST set END_TIME = ? where instance_id=?";
		Object [] params ={DataUtil.getNowTime(),instance_id};
		num =this.update(sql, params);
		return num;
	}
	public int updateState(int instance_id){
		int num =0;
		String sql ="update SYS_INSTANCE_LIST set state = ? where instance_id=?";
		Object [] params ={2,instance_id};
		num =this.update(sql, params);
		return num;
	}
	
	public InstanceWF getInstanceWfInfo(int instance_no){
		InstanceWF wf =null;
		String sql ="select TEMPLATE_ID,STATE,START_TIME,END_TIME,START_OPERATOR,REMARK,DEPARTMENT,instance_id from SYS_INSTANCE_LIST where INSTANCE_ID=?";
		Object [] params={instance_no};
		wf=(InstanceWF)this.get(sql, InstanceWF.class, params);
		return wf;
	}
	
	/**
	 *创建人：郑辉
	 *描述： 分页查询
	 *创建时间：2012-4-20 上午10:14:30
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： Page<InstanceWF>
	 */
	public Page<InstanceWF> searchPage(Page<InstanceWF> page,String fields,
			String sqlCondition, Object[] queryParams,String enti_id){
			return this.search(page, fields, sqlCondition, queryParams,null, enti_id);
	}

	/**
	 *创建人：郑辉
	 *描述： 分页查询登录用户的历史任务
	 *创建时间：2012-4-20 上午10:14:13
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： Page<InstanceWF>
	 */
	@SuppressWarnings("unchecked")
	public Page<InstanceWF> search(Page<InstanceWF> page, String fields,
		String sqlCondition, Object[] queryParams,
		LinkedHashMap<String, String> orderby,String enti_id) {
	List<InstanceWF> list = new ArrayList<InstanceWF>();//封装查询结果集
	Integer totalRecords = 0;//记录查询的总记录数
	String sql = "";//查询的sql语句
	String countSql = "";//查询总记录数的sql语句
	Page<InstanceWF> epage = new Page<InstanceWF>();//临时变量，如果在page为null的情况下使用
	
	sqlCondition =  ("".equals(sqlCondition) || null == sqlCondition) ? " " : sqlCondition;
	countSql = "select count(b.instance_id) from (select TEMPLATE_ID,STATE,START_TIME,START_OPERATOR,END_TIME,REMARK,DEPARTMENT,instance_id  from  SYS_INSTANCE_LIST where INSTANCE_ID in   (select INSTANCE_ID from SYS_EXEC_INFO where LAST_COMMITOR='"+enti_id+"' and TASK_ID >1 group by INSTANCE_ID)  ) b " + sqlCondition;
	//查询总记录数
	totalRecords = (Integer) getSingleValue(countSql, queryParams);
	if ( page != null) {//如果需要分页
		if ( null != queryParams && queryParams.length > 0) {
			//将数组的长度扩充为原来的2倍,并使得数组的第n个元素和第n+数组长度的元素的值相等,如此是为了分页查询时参数设置的需要
			Object[] newParamsArray = Arrays.copyOf(queryParams, queryParams.length*2);
			for (int i = 0; i < queryParams.length; i++) {
				newParamsArray[queryParams.length + i] = queryParams[i];
			}
			queryParams = newParamsArray;
		}
		//如果不执行模糊查询则构建where语句使用DBUtil.buildOrderBy（）的方法
		//如果执行模糊查询则构建where语句使用PolicyDao的buildSQLWhereFuzzy（）方法
		sql = "select top " + page.getMaxResult() + " " + fields + "  from (select TEMPLATE_ID,STATE,START_TIME,START_OPERATOR,END_TIME,REMARK,DEPARTMENT,instance_id  from  SYS_INSTANCE_LIST where INSTANCE_ID in   (select INSTANCE_ID from SYS_EXEC_INFO where LAST_COMMITOR='"+enti_id+"' and TASK_ID >1 group by INSTANCE_ID)  ) b " 
				+ sqlCondition;
		if ( " ".equals(sqlCondition) == true ) {
			sql += " where b.instance_id not in (select top " + (page.getCurPage() - 1) * page.getMaxResult() + 
					" d.INSTANCE_ID from (select instance_id  from  SYS_INSTANCE_LIST where INSTANCE_ID in (select INSTANCE_ID from SYS_EXEC_INFO where LAST_COMMITOR='"+enti_id+"' and TASK_ID >1 group by INSTANCE_ID)) d  " 
					+ sqlCondition + ")" ;
		} else {
			sql += " and b.instance_id not in (select top " + (page.getCurPage() - 1) * page.getMaxResult() + 
					" d.INSTANCE_ID from (select instance_id  from  SYS_INSTANCE_LIST where INSTANCE_ID in (select INSTANCE_ID from SYS_EXEC_INFO where LAST_COMMITOR='"+enti_id+"' and TASK_ID >1 group by INSTANCE_ID)) d  " 
					+ sqlCondition +")";
		}
		epage = page;
		sql += "order by START_TIME desc";
		list = query(sql, InstanceWF.class, queryParams);
	} else {//如果不需要分页
		sql = "select " +fields + " from SYS_INSTANCE_LIST " + sqlCondition + buildOrderBy(orderby);
		//查询结果集
		queryParams[queryParams.length]=enti_id;
		list = query(sql, InstanceWF.class, queryParams);
	}
	//设置总记录数
	epage.setList(list);
	//设置结果集
	epage.setTotalRecords(totalRecords);

	return epage;
}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.flow.InstanceWFDao#delete(int)
	 */
	@Override
	public int delete(int instanceNo) {
		String sql ="delete SYS_INSTANCE_LIST where instance_id=?";
		Object[] params = {instanceNo};
		return this.update(sql, params);
	}	
}
