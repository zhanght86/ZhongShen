/*
 * @项目名称: OA
 * @文件名称: ProjectDaoImpl.java
 * @日期: 2012-8-9 上午11:20:52  
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
import com.hnzskj.persist.bean.system.Project;
import com.hnzskj.persist.dao.system.ProjectDao;

/**    
 * 项目名称：OA   <br/>
 * 类名称：ProjectDaoImpl.java   <br/>
 * 类描述：项目信息维护数据库访问层实现类   <br/>
 * 创建人：王亲臣   <br/>
 * 创建时间：2012-8-9 上午11:20:52   <br/>
 * 修改人：王亲臣   <br/>
 * 修改时间：2012-8-9 上午11:20:52   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class ProjectDaoImpl extends BaseDao implements ProjectDao {

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.ProjectDao#addProject(com.hnzskj.persist.bean.system.Project)
	 */
	@Override
	public int addProject(Project project) {
		String sql="INSERT INTO PROJECT (PRONO,PRONAME,PROORDER,NOTE) VALUES(?,?,?,?)";
		Object[] params = new Object[]{
				project.getProNo(),
				project.getProName(),
				project.getProOrder(),
				project.getNote()
		};
		int result = this.update(sql, params);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.ProjectDao#delProject(java.lang.String)
	 */
	@Override
	public int delProject(String proId) {
		String  sql="DELETE FROM PROJECT WHERE PROID = ? ";
		int result = this.update(sql, new Object[]{proId});
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.ProjectDao#getProject(java.lang.String)
	 */
	@Override
	public Project getProject(String proId) {
		String sql="SELECT PROID,PRONO,PRONAME,PROORDER,PRODATETIME,NOTE FROM PROJECT WHERE PROID = ? ";
		Project project = (Project)this.get(sql, Project.class,new Object[]{proId});
		return project;
	}
	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.ProjectDao#searchProject(com.hnzskj.common.Page, java.lang.String, java.lang.String, java.lang.Object[], java.util.LinkedHashMap)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Page<Project> searchProject(Page<Project> page, String fields,
			String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {
		List<Project> empls = new ArrayList<Project>();//封装查询结果集
		Integer totalRecords = 0;//记录查询的总记录数
		String sql = "";//查询的sql语句
		String countSql = "";//查询总记录数的sql语句
		Page<Project> epage = new Page<Project>();//临时变量，如果在page为null的情况下使用
		sqlCondition =  ("".equals(sqlCondition) || null == sqlCondition) ? " " : sqlCondition;
		countSql = "select count(proId) from project " + sqlCondition;
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
			sql = "select top " + page.getMaxResult() + " " + fields + "  from project " 
					+ sqlCondition;
			if ( " ".equals(sqlCondition) == true ) {
				sql += " where proId not in (select top " + (page.getCurPage() - 1) * page.getMaxResult() + 
						" proId from project " 
						+ sqlCondition + BaseDao.buildOrderBy(orderby) + ")" 
						+ BaseDao.buildOrderBy(orderby);
			} else {
				sql += " and proId not in (select top " + (page.getCurPage() - 1) * page.getMaxResult() + 
						" proId from project " 
						+ sqlCondition + BaseDao.buildOrderBy(orderby) + ")" 
						+ BaseDao.buildOrderBy(orderby);
			}
			epage = page;
			
			empls = query(sql, Project.class, queryParams);
		} else {//如果不需要分页
			sql = "select " +fields + " from project " + sqlCondition + buildOrderBy(orderby);
			//查询结果集
			empls = query(sql, Project.class, queryParams);
		}
		//设置总记录数
		epage.setList(empls);
		//设置结果集
		epage.setTotalRecords(totalRecords);
		return epage;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.ProjectDao#updProject(com.hnzskj.persist.bean.system.Project)
	 */
	@Override
	public int updProject(Project project) {
		String sql="UPDATE PROJECT SET PRONO = ? ,PRONAME = ? , PROORDER = ? , NOTE = ? WHERE PROID = ? ";
		Object[] params = new Object[]{
				project.getProNo(),
				project.getProName(),
				project.getProOrder(),
				project.getNote(),
				project.getProId()
		};
		int result = this.update(sql, params);
		return result;
	}

}
