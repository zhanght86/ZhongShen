package com.hnzskj.persist.dao.system.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.DataUtil;
import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.system.OperationLog;
import com.hnzskj.persist.dao.system.OperationLogDao;

public class OperationLogDaoImpl extends BaseDao implements OperationLogDao{

	@Override
	public int delete(int id) {
		String sql = "delete from oprlog where oid = ?";
		Object[] params = new Object[]{id};
		int result = update(sql, params);
		return result;
	}

	@Override
	public int delete(Serializable... ids) {
		StringBuffer idStr = new StringBuffer("");
		if (ids != null && ids.length > 0) {
			for (Object id : ids ) {
				idStr.append((Integer)id).append(",");
			}
			idStr.deleteCharAt(idStr.length() - 1);
		}
		String sql = "delete from oprlog where oid in ( " + idStr.toString() + ")";
		int result = update(sql, null);
		return result;
	}

	@Override
	public OperationLog getById(String id) {
		String sql = "select oid,operator,oprtime,operation,loginIp from oprlog where oid=?";
		Object[] param = new Object[]{id};
		OperationLog log = (OperationLog) get(sql, OperationLog.class, param);
		return log;
	}

	@Override
	public int getCount(String sqlCondition, Object[] params) {
		int result = 0;
		String sql = "select count(oid) from oprlog " 
			+  ("".equals(sqlCondition) || null == sqlCondition ? " " : sqlCondition);
		result = Integer.valueOf( getSingleValue(sql, null).toString());
		return result;
	}

	@Override
	public int save(OperationLog operationLog) {
		String sql = "insert into oprlog (operator,operation,loginIp,oprtime) values (?,?,?,?)";
		Object[] paramters = new Object[]{
			operationLog.getOperator(),
			operationLog.getOperation(),
			operationLog.getLoginIp(),
			DataUtil.getNowTime()
		};
		int result = update(sql, paramters);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.log.OperationLogDao#searchEmployee(com.hnzskj.common.Page, java.lang.String, java.util.LinkedHashMap)
	 */
	@Override
	public Page<OperationLog> searchOperationLog(Page<OperationLog> page,
			String fields, LinkedHashMap<String, String> orderby) {
		return searchOperationLog(page, fields, null, null, orderby);
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.log.OperationLogDao#searchEmployee(com.hnzskj.common.Page, java.lang.String, java.lang.String, java.lang.Object[], java.util.LinkedHashMap)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Page<OperationLog> searchOperationLog(Page<OperationLog> page,
			String fields, String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {
		List<OperationLog> empls = new ArrayList<OperationLog>();//封装查询结果集
		Integer totalRecords = 0;//记录查询的总记录数
		String sql = "";//查询的sql语句
		String countSql = "";//查询总记录数的sql语句
		Page<OperationLog> epage = new Page<OperationLog>();//临时变量，如果在page为null的情况下使用
		
		sqlCondition =  ("".equals(sqlCondition) || null == sqlCondition) ? " " : sqlCondition;
		countSql = "select count(oid) from oprlog " + sqlCondition;
		//查询总记录数
		totalRecords = Integer.valueOf(getSingleValue(countSql, queryParams).toString());
		if ( page != null) {//如果需要分页
//			if ( null != queryParams && queryParams.length > 0) {
//				//将数组的长度扩充为原来的2倍,并使得数组的第n个元素和第n+数组长度的元素的值相等,如此是为了分页查询时参数设置的需要
//				Object[] newParamsArray = Arrays.copyOf(queryParams, queryParams.length*2);
//				for (int i = 0; i < queryParams.length; i++) {
//					newParamsArray[queryParams.length + i] = queryParams[i];
//				}
//				queryParams = newParamsArray;
//			}
			//如果不执行模糊查询则构建where语句使用DBUtil.buildOrderBy（）的方法
			//如果执行模糊查询则构建where语句使用PolicyDao的buildSQLWhereFuzzy（）方法
			sql = "select " + fields + "  from oprlog " +sqlCondition + BaseDao.buildOrderBy(orderby) +
			"limit "+(page.getCurPage() - 1) * page.getMaxResult()+","+page.getMaxResult() ;
		
			epage = page;
			//查询结果集
			empls = query(sql, OperationLog.class, queryParams);
		} else {//如果不需要分页
			sql = "select " +fields + " from oprlog " + sqlCondition + buildOrderBy(orderby);
			//查询结果集
			empls = query(sql, OperationLog.class, queryParams);
		}
		//设置总记录数
		epage.setList(empls);
		//设置结果集
		epage.setTotalRecords(totalRecords);
		return epage;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.log.OperationLogDao#searchEmployee(com.hnzskj.common.Page, java.lang.String, java.lang.String, java.lang.Object[])
	 */
	@Override
	public Page<OperationLog> searchOperationLog(Page<OperationLog> page,
			String fields, String sqlCondition, Object[] queryParams) {
		return searchOperationLog(page, fields, sqlCondition, queryParams, null);
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.log.OperationLogDao#searchEmployee(com.hnzskj.common.Page, java.lang.String)
	 */
	@Override
	public Page<OperationLog> searchOperationLog(Page<OperationLog> page,
			String fields) {
		return searchOperationLog(page, fields, null, null, null);
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.log.OperationLogDao#searchOperationLog(java.lang.String)
	 */
	@Override
	public Page<OperationLog> searchOperationLog(String fields) {
		return searchOperationLog(null, fields, null, null, null);
	}
}
