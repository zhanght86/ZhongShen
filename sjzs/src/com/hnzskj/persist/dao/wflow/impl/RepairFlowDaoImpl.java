/*
 * @项目名称: OA
 * @文件名称: SoftWareFlowDaO.java
 * @日期: 2012-7-16
 * @版权: 2012 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.persist.dao.wflow.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.wflow.RepairFlow;
import com.hnzskj.persist.dao.wflow.RepairFlowDao;

  /**        
 * 
 * 类名称：SoftWareFlowDaO
 * 类描述：<br/>
 * 创建人：郑辉  <br/>
 * 创建时间：2012-7-16 下午02:29:08 
 * 修改人：Administrator
 * 修改时间：
 * 修改备注：   
 * @version   1.0     
 */

public class RepairFlowDaoImpl extends BaseDao implements RepairFlowDao{

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.wflow.RepairFlowDao#addInfo(com.hnzskj.persist.bean.wflow.RepairFlow)
	 */
	@Override
	public String addInfo(RepairFlow repair) {
		// TODO Auto-generated method stub
		String guid=this.getGUID();
		String sql ="insert into repair_flow (rp_id,rp_orgId,rp_person,rp_date,rp_desc,rp_items,instance_no,rp_type) values(?,?,?,?,?,?,?,?)";
		Object [] params={
				guid,
				repair.getRp_orgId(),
				repair.getRp_person(),
				repair.getRp_date(),
				repair.getRp_desc(),
				repair.getRp_items(),
				repair.getInstance_no(),
				repair.getRp_type()
		};
		int result =this.update(sql, params);
		if(result != 0 ){
			return guid;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.wflow.RepairFlowDao#getInfoByNo(int)
	 */
	@Override
	public RepairFlow getInfoByNo(int instanceNo) {
		// TODO Auto-generated method stub
		String sql ="select rp_id,rp_orgId,rp_person,rp_type,rp_date,rp_desc,rp_items,instance_no from repair_flow where instance_no=?";
		Object [] params ={instanceNo};
		RepairFlow repair =(RepairFlow)this.get(sql, RepairFlow.class, params);
		return repair;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.wflow.RepairFlowDao#updateInfo(com.hnzskj.persist.bean.wflow.RepairFlow)
	 */
	@Override
	public int updateInfo(RepairFlow repair) {
		// TODO Auto-generated method stub
		String sql ="update repair_flow set rp_orgId=?,rp_person=?,rp_date=?,rp_desc=?,rp_items=?,rp_type=? where instance_no =?";
		Object [] params={
				repair.getRp_orgId(),
				repair.getRp_person(),
				repair.getRp_date(),
				repair.getRp_desc(),
				repair.getRp_items(),
				repair.getRp_type(),
				repair.getInstance_no()
		};
		int result =this.update(sql, params);
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.wflow.RepairFlowDao#searchHolidayFlow(com.hnzskj.common.Page, java.lang.String, java.lang.String, java.lang.Object[], java.util.LinkedHashMap)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Page<RepairFlow> search(Page<RepairFlow> page,
			String fields, String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {
		List<RepairFlow> empls = new ArrayList<RepairFlow>();//封装查询结果集
		Integer totalRecords = 0;//记录查询的总记录数
		String sql = "";//查询的sql语句
		String countSql = "";//查询总记录数的sql语句
		Page<RepairFlow> epage = new Page<RepairFlow>();//临时变量，如果在page为null的情况下使用
		
		sqlCondition =  ("".equals(sqlCondition) || null == sqlCondition) ? " " : sqlCondition;
		countSql = "select count(rp_id) from repair_flow " + sqlCondition;
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
			sql = "select top " + page.getMaxResult() + " " + fields + "  from repair_flow " 
					+ sqlCondition;
			if ( " ".equals(sqlCondition) == true ) {
				sql += " where rp_id not in (select top " + (page.getCurPage() - 1) * page.getMaxResult() + 
						" rp_id from repair_flow " 
						+ sqlCondition + BaseDao.buildOrderBy(orderby) + ")" 
						+ BaseDao.buildOrderBy(orderby);
			} else {
				sql += " and rp_id not in (select top " + (page.getCurPage() - 1) * page.getMaxResult() + 
						" rp_id from repair_flow " 
						+ sqlCondition + BaseDao.buildOrderBy(orderby) + ")" 
						+ BaseDao.buildOrderBy(orderby);
			}
			epage = page;
			
			empls = query(sql, RepairFlow.class, queryParams);
		} else {//如果不需要分页
			sql = "select " +fields + " from repair_flow " + sqlCondition + buildOrderBy(orderby);
			//查询结果集
			empls = query(sql, RepairFlow.class, queryParams);
		}
		//设置总记录数
		epage.setList(empls);
		//设置结果集
		epage.setTotalRecords(totalRecords);
		
		
		return epage;
	}
}
