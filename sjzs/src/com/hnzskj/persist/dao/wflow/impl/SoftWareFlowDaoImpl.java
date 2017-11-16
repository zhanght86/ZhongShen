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
import com.hnzskj.persist.bean.wflow.SoftWareFlow;
import com.hnzskj.persist.dao.wflow.SoftWareFlowDao;

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

public class SoftWareFlowDaoImpl extends BaseDao implements SoftWareFlowDao{
	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.wflow.SoftWareFlowDao#addInfo(com.hnzskj.persist.bean.wflow.SoftWareFlow)
	 */
	@Override
	public String addInfo(SoftWareFlow soft) {
		// TODO Auto-generated method stub
		String guid=this.getGUID();
		String sql ="INSERT INTO [dbo].[software_flow] ([sf_id],[sf_person],[sf_date]," +
				"[sf_use],[instance_no],sf_orgId) VALUES(?,?,?,?,?,?)";
		Object[] params ={
				guid,
				soft.getSf_person(),
				soft.getSf_date(),
				soft.getSf_use(),
				soft.getInstance_no(),
				soft.getSf_OrgId()
		};
		int result =this.update(sql, params);
		if(result !=0) return guid;
		return "";
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.wflow.SoftWareFlowDao#getInfo(int)
	 */
	@Override
	public SoftWareFlow getInfo(int instanceNo) {
		// TODO Auto-generated method stub
		SoftWareFlow soft =null;
		String sql ="select [sf_id],[sf_person],[sf_date]," +
		"[sf_use],[instance_no],sf_orgId from software_flow where instance_no=?";
		Object [] params={instanceNo};
		soft=(SoftWareFlow)this.get(sql, SoftWareFlow.class,params);
		return soft;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.wflow.SoftWareFlowDao#updateInfo(com.hnzskj.persist.bean.wflow.SoftWareFlow)
	 */
	@Override
	public int updateInfo(SoftWareFlow soft) {
		// TODO Auto-generated method stub
		String sql ="UPDATE [dbo].[software_flow] set [sf_person]=?,[sf_date]=?," +
		"[sf_use]=?,instance_no=?,sf_orgId=? where sf_id=?";
		Object[] params ={
		soft.getSf_person(),
		soft.getSf_date(),
		soft.getSf_use(),
		soft.getInstance_no(),
		soft.getSf_OrgId(),
		soft.getSf_id()
};
		int result =this.update(sql, params);
		return result;
	}
	@SuppressWarnings("unchecked")
	public Page<SoftWareFlow> search(Page<SoftWareFlow> page, String fields,
			String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {
		List<SoftWareFlow> empls = new ArrayList<SoftWareFlow>();//封装查询结果集
		Integer totalRecords = 0;//记录查询的总记录数
		String sql = "";//查询的sql语句
		String countSql = "";//查询总记录数的sql语句
		Page<SoftWareFlow> epage = new Page<SoftWareFlow>();//临时变量，如果在page为null的情况下使用
		
		sqlCondition =  ("".equals(sqlCondition) || null == sqlCondition) ? " " : sqlCondition;
		countSql = "select count(sf_id) from SoftWare_Flow " + sqlCondition;
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
			sql = "select top " + page.getMaxResult() + " " + fields + "  from SoftWare_Flow " 
					+ sqlCondition;
			if ( " ".equals(sqlCondition) == true ) {
				sql += " where sf_id not in (select top " + (page.getCurPage() - 1) * page.getMaxResult() + 
						" sf_id from SoftWare_Flow " 
						+ sqlCondition + BaseDao.buildOrderBy(orderby) + ")" 
						+ BaseDao.buildOrderBy(orderby);
			} else {
				sql += " and sf_id not in (select top " + (page.getCurPage() - 1) * page.getMaxResult() + 
						" sf_id from SoftWare_Flow " 
						+ sqlCondition + BaseDao.buildOrderBy(orderby) + ")" 
						+ BaseDao.buildOrderBy(orderby);
			}
			epage = page;
			
			empls = query(sql, SoftWareFlow.class, queryParams);
		} else {//如果不需要分页
			sql = "select " +fields + " from SoftWare_Flow " + sqlCondition + buildOrderBy(orderby);
			//查询结果集
			empls = query(sql, SoftWareFlow.class, queryParams);
		}
		//设置总记录数
		epage.setList(empls);
		//设置结果集
		epage.setTotalRecords(totalRecords);

		return epage;
	}
}
