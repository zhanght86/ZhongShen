/*
 * @项目名称: OA
 * @文件名称: LatentCusterDaoImpl.java
 * @日期: 2012-8-9 下午05:48:29  
 * @版权: 2012 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.persist.dao.system.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.system.LatentCuster;
import com.hnzskj.persist.dao.system.LatentCusterDao;

/**    
 * 项目名称：OA   <br/>
 * 类名称：LatentCusterDaoImpl.java   <br/>
 * 类描述：   <br/>
 * 创建人：王亲臣   <br/>
 * 创建时间：2012-8-9 下午05:48:29   <br/>
 * 修改人：王亲臣   <br/>
 * 修改时间：2012-8-9 下午05:48:29   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class LatentCusterDaoImpl extends BaseDao implements LatentCusterDao {

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.LatentCusterDao#addLatentCuster(com.hnzskj.persist.bean.system.LatentCuster)
	 */
	@Override
	public int addLatentCuster(LatentCuster latentCuster) {
		String sql="INSERT INTO LATENTCUSTER (LCTEXT,LCORDER,NOTE) VALUES(?,?,?)";
		Object []params = new Object[]{
				latentCuster.getLcText(),
				latentCuster.getLcOrder(),
				latentCuster.getNote()
		};
		int result = this.update(sql,params );
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.LatentCusterDao#delLatentCuster(java.lang.String)
	 */
	@Override
	public int delLatentCuster(String lcId) {
		String  sql="DELETE FROM LATENTCUSTER WHERE LCID = ? ";
		int result = this.update(sql, new Object[]{lcId});
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.LatentCusterDao#getLatentCuster(java.lang.String)
	 */
	@Override
	public LatentCuster getLatentCuster(String lcId) {
		String sql="SELECT LCID,LCTEXT,LCORDER,LCDATETIME,NOTE FROM LATENTCUSTER WHERE LCID = ? ";
		LatentCuster latentCuster = (LatentCuster)this.get(sql, LatentCuster.class,new Object[]{lcId});
		return latentCuster;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.LatentCusterDao#searchLatentCuster(com.hnzskj.common.Page, java.lang.String, java.lang.String, java.lang.Object[], java.util.LinkedHashMap)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Page<LatentCuster> searchLatentCuster(Page<LatentCuster> page,
			String fields, String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {
		List<LatentCuster> empls = new ArrayList<LatentCuster>();//封装查询结果集
		Integer totalRecords = 0;//记录查询的总记录数
		String sql = "";//查询的sql语句
		String countSql = "";//查询总记录数的sql语句
		Page<LatentCuster> epage = new Page<LatentCuster>();//临时变量，如果在page为null的情况下使用
		sqlCondition =  ("".equals(sqlCondition) || null == sqlCondition) ? " " : sqlCondition;
		countSql = "select count(lcId) from latentCuster " + sqlCondition;
		//查询总记录数
		totalRecords = Integer.valueOf(getSingleValue(countSql, queryParams).toString());
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
			sql = "select top " + page.getMaxResult() + " " + fields + "  from latentCuster " 
					+ sqlCondition;
			if ( " ".equals(sqlCondition) == true ) {
				sql += " where lcId not in (select top " + (page.getCurPage() - 1) * page.getMaxResult() + 
						" lcId from latentCuster " 
						+ sqlCondition + BaseDao.buildOrderBy(orderby) + ")" 
						+ BaseDao.buildOrderBy(orderby);
			} else {
				sql += " and lcId not in (select top " + (page.getCurPage() - 1) * page.getMaxResult() + 
						" lcId from latentCuster " 
						+ sqlCondition + BaseDao.buildOrderBy(orderby) + ")" 
						+ BaseDao.buildOrderBy(orderby);
			}
			epage = page;
			
			empls = query(sql, LatentCuster.class, queryParams);
		} else {//如果不需要分页
			sql = "select " +fields + " from latentCuster " + sqlCondition + buildOrderBy(orderby);
			//查询结果集
			empls = query(sql, LatentCuster.class, queryParams);
		}
		//设置总记录数
		epage.setList(empls);
		//设置结果集
		epage.setTotalRecords(totalRecords);
		return epage;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.LatentCusterDao#updLatentCuster(com.hnzskj.persist.bean.system.LatentCuster)
	 */
	@Override
	public int updLatentCuster(LatentCuster latentCuster) {
		String sql="UPDATE LATENTCUSTER SET LCTEXT = ? , LCORDER = ? ,NOTE = ? WHERE LCID = ? ";
		Object[]params = new Object[]{
				latentCuster.getLcText(),
				latentCuster.getLcOrder(),
				latentCuster.getNote(),
				latentCuster.getLcId()
				
		};
		int result = this.update(sql, params);
		return result;
	}

}
