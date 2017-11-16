/*
 * @项目名称: OA
 * @文件名称: HolidayFlowDaoImpl.java
 * @日期: 2012-7-12
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
import com.hnzskj.persist.bean.wflow.HolidayFlow;
import com.hnzskj.persist.dao.wflow.HolidayFlowDao;


  /**        
 * 
 * 类名称：HolidayFlowBeanDaoImpl
 * 类描述：<br/>
 * 创建人：郑辉  <br/>
 * 创建时间：2012-7-13 上午11:32:08 
 * 修改人：Administrator
 * 修改时间：
 * 修改备注：   
 * @version   1.0     
 */
public class HolidayFlowDaoImpl extends BaseDao implements HolidayFlowDao{

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.wflow.HolidayFlowBeanDao#addInfo(com.hnzskj.persist.bean.wflow.HolidayFlowBean)
	 */
	@Override
	public String addInfo(HolidayFlow holiday) {
		// TODO Auto-generated method stub
		String guid=this.getGUID();
		String sql ="INSERT INTO dbo.holiday_flow" +
				"(hd_Id," +
				"hd_Name," +
				"hd_OrgId," +
				"hd_begin_time," +
				"hd_end_time," +
				"hd_end_days," +
				"hd_type," +
				"hd_reason," +
				"hd_Sickleave_time ," +
				"hd_Sickleave_days," +
				"hd_timeout_time ," +
				"hd_timeout_days ," +
				"hd_desc ," +
				"hd_orderPerson ," +
				"instance_no," +
				"hd_sick,befor_instance_no,isScrap) " +
				"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params ={
				guid,
				holiday.getHd_Name(),
				holiday.getHd_OrgId(),
				holiday.getHd_begin_time(),
				holiday.getHd_end_time(),
				holiday.getHd_end_days(),
				holiday.getHd_type(),
				holiday.getHd_reason(),
				holiday.getHd_Sickleave_time (),
				holiday.getHd_Sickleave_days(),
				holiday.getHd_timeout_time (),
				holiday.getHd_timeout_days (),
				holiday.getHd_desc (),
				holiday.getHd_orderPerson (),
				holiday.getInstance_no(),
				holiday.getHd_sick(),
				holiday.getBefor_instance_no(),
				holiday.getIsScrap()
				
		};
		int result =this.update(sql, params);
		if(0!=result){
			return guid;
		}
		return result+"";
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.wflow.HolidayFlowBeanDao#updateInfo(com.hnzskj.persist.bean.wflow.HolidayFlowBean)
	 */
	@Override
	public int updateInfo(HolidayFlow holiday) {
		// TODO Auto-generated method stub
		String sql ="" +
		"update holiday_flow set " +
		"hd_Name =?," +
		"hd_OrgId =?," +
		"hd_begin_time =?," +
		"hd_end_time =?," +
		"hd_end_days =?," +
		"hd_type =?," +
		"hd_reason =?," +
		"hd_Sickleave_time  =?," +
		"hd_Sickleave_days =?," +
		"hd_timeout_time  =?," +
		"hd_timeout_days  =?," +
		"hd_desc  =?," +
		"hd_orderPerson  =?," +
		"hd_sick  =?," +
		"instance_no=?," +
		"isScrap=?"+
		" where hd_id=?";
		Object[] params ={
				holiday.getHd_Name(),
				holiday.getHd_OrgId(),
				holiday.getHd_begin_time(),
				holiday.getHd_end_time(),
				holiday.getHd_end_days(),
				holiday.getHd_type(),
				holiday.getHd_reason(),
				holiday.getHd_Sickleave_time (),
				holiday.getHd_Sickleave_days(),
				holiday.getHd_timeout_time (),
				holiday.getHd_timeout_days (),
				holiday.getHd_desc (),
				holiday.getHd_orderPerson (),
				holiday.getHd_sick(),
				holiday.getInstance_no(),
				holiday.getIsScrap(),
				holiday.getHd_Id()
		};
		int result =this.update(sql, params);
		return result;
	}
	
	public int updateStatusInfo(HolidayFlow holiday) {
		// TODO Auto-generated method stub
		String sql ="update holiday_flow set hd_sick=?" +
		" where instance_no=?";
		Object[] params ={
				holiday.getHd_sick(),
				holiday.getInstance_no()
		};
		int result =this.update(sql, params);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.wflow.HolidayFlowBeanDao#getInfoByNo(int)
	 */
	@Override
	public HolidayFlow getInfoByNo(int instanceNo) {
		// TODO Auto-generated method stub
		String sql ="select hd_Id," +
				"hd_Name," +
				"hd_OrgId," +
				"hd_begin_time," +
				"hd_end_time," +
				"hd_end_days," +
				"hd_type," +
				"hd_reason," +
				"hd_Sickleave_time ," +
				"hd_Sickleave_days," +
				"hd_timeout_time ," +
				"hd_timeout_days ," +
				"hd_desc ," +
				"hd_orderPerson ,befor_instance_no," +
				"instance_no from holiday_flow where instance_no =?";
		Object[] params={instanceNo};
		HolidayFlow h =(HolidayFlow)get(sql,HolidayFlow.class,params);
		return h;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.wflow.HolidayFlowDao#getInfoByStatus()
	 */
	@Override
	public HolidayFlow getInfoByStatus(String emplId) {
		// TODO Auto-generated method stub
		String sql ="select hd_Id,instance_no from holiday_flow where hd_name=? and hd_sick =2";
		Object[] params={emplId};
		HolidayFlow h =(HolidayFlow)get(sql,HolidayFlow.class,params);
		return h;
	}
	@SuppressWarnings("unchecked")
	public Page<HolidayFlow> searchHolidayFlow(Page<HolidayFlow> page, String fields,
			String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {
		List<HolidayFlow> empls = new ArrayList<HolidayFlow>();//封装查询结果集
		Integer totalRecords = 0;//记录查询的总记录数
		String sql = "";//查询的sql语句
		String countSql = "";//查询总记录数的sql语句
		Page<HolidayFlow> epage = new Page<HolidayFlow>();//临时变量，如果在page为null的情况下使用
		
		sqlCondition =  ("".equals(sqlCondition) || null == sqlCondition) ? " " : sqlCondition;
		countSql = "select count(hd_id) from (select * from (select * from holiday_flow where befor_instance_no  is not null) a union select * from (select * from holiday_flow where befor_instance_no  is null ) b where b.instance_no not in (select befor_instance_no from holiday_flow where befor_instance_no  is not null) and b.isScrap=0) c" + sqlCondition;
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
			sql = "select top " + page.getMaxResult() + " " + fields + "  from (select * from (select * from holiday_flow where befor_instance_no  is not null) a union select * from (select * from holiday_flow where befor_instance_no  is null) b where b.instance_no not in (select befor_instance_no from holiday_flow where befor_instance_no  is not null)  and b.isScrap=0) c" 
					+ sqlCondition;
			if ( " ".equals(sqlCondition) == true ) {
				sql += " where hd_id not in (select top " + (page.getCurPage() - 1) * page.getMaxResult() + 
						" hd_id from (select * from (select * from holiday_flow where befor_instance_no  is not null) a union select * from (select * from holiday_flow where befor_instance_no  is null) b where b.instance_no not in (select befor_instance_no from holiday_flow where befor_instance_no  is not null)  and b.isScrap=0) c" 
						+ sqlCondition + BaseDao.buildOrderBy(orderby) + ")" 
						+ BaseDao.buildOrderBy(orderby);
			} else {
				sql += " and hd_id not in (select top " + (page.getCurPage() - 1) * page.getMaxResult() + 
						" hd_id from (select * from (select * from holiday_flow where befor_instance_no  is not null) a union select * from (select * from holiday_flow where befor_instance_no  is null) b where b.instance_no not in (select befor_instance_no from holiday_flow where befor_instance_no  is not null) and b.isScrap=0) c" 
						+ sqlCondition + BaseDao.buildOrderBy(orderby) + ")" 
						+ BaseDao.buildOrderBy(orderby);
			}
			epage = page;
			
			empls = query(sql, HolidayFlow.class, queryParams);
		} else {//如果不需要分页
			sql = "select " +fields + " from (select * from (select * from holiday_flow where befor_instance_no  is not null) a union select * from (select * from holiday_flow where befor_instance_no  is null) b where b.instance_no not in (select befor_instance_no from holiday_flow where befor_instance_no  is not null) and b.isScrap=0) " + sqlCondition + buildOrderBy(orderby);
			//查询结果集
			empls = query(sql, HolidayFlow.class, queryParams);
		}
		//设置总记录数
		epage.setList(empls);
		//设置结果集
		epage.setTotalRecords(totalRecords);

		return epage;
	}
	
	public int getHolidayCount(String emplId,int status){
		String sql="select count(*) from holiday_flow where hd_sick=? and hd_name=?";
		Object param[] ={status,emplId};
		int result =(Integer)this.getSingleValue(sql, param);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.wflow.HolidayFlowDao#deleteinfo(java.lang.String)
	 */
	@Override
	public int deleteinfo(String guid) {
		// TODO Auto-generated method stub
		String sql ="delete holiday_flow where hd_Id = ?";
		Object [] params ={guid};
		return this.update(sql, params);
	}
	
	/**
	 * 类描述：获取请假列表中的年度字段
	 * 创建人：郑辉  <br/>
	 * 创建时间：2013-1-7 上午10:31:13 
	 * 修改人：
	 * 修改时间：2013-1-7 上午10:31:13  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public List<String> getHolidayYearList(){
		List<String> yearList = null;
		String sql ="select distinct(substring(LTRIM(hd_begin_time),1,4)) as nowYear from holiday_flow where hd_begin_time is not null order by  substring(LTRIM(hd_begin_time),1,4) desc";
		yearList=this.getListSingleValue(sql, null);
		return yearList;
	}
}