/*
 * @项目名称: htglxt
 * @文件名称: PowerDaoImpl.java
 * @日期: 2011-5-27
 * @版权: 2011 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.persist.dao.system.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.DataUtil;
import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.system.Power;
import com.hnzskj.persist.dao.system.PowerDao;

/**        
 * 
 * 类名称：PowerDaoImpl     <br/>
 * 类描述：<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-5-27 上午10:38:32   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-5-27 上午10:38:32   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */
public class PowerDaoImpl extends BaseDao implements PowerDao {

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.PowerDao#addPower(com.hnzskj.persist.bean.system.Power)
	 */
	@Override
	public int addPower(Power power) {
		String sql = "insert into power (powerId,powerName, powerUrl, powerParent, powerModule, powerSuperPower, powerOrder, powerImg,powerType) " +
				" values (?,?, ?, ?, ?, ?, ?, ? ,?)";
		Object[] params = new Object[]{
				this.getGUID(),
					power.getPowerName(),
					power.getPowerUrl(),
					power.getPowerParent(),
					power.isPowerModule(),
					power.isPowerSuperPower(),
					power.getPowerOrder(),
					power.getPowerImg(),
					power.getPowerType()
				};
		int result = update(sql, params);		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.PowerDao#deletePowerByPowerCode(java.lang.String)
	 */
	@Override
	public int deletePowerByPowerIds(Serializable...powerIds )  {
		String powerIdStr = DataUtil.arrayToString((String[])powerIds);
		String[] sqls = {
				"delete from role_power where powerId in (" + powerIdStr + ")",
				"delete from power where PowerId in (" + powerIdStr + ")"
		};
		int result = update(sqls, null);
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.PowerDao#updatePower(com.hnzskj.persist.bean.system.Power)
	 */
	@Override
	public int updatePower(Power power) {
		String sql = "update power set powerName=?, powerUrl=?, powerParent=?, powerModule=?, powerSuperPower=?, powerOrder=?, powerImg=? ,powerType=?" +
				" where powerId=? ";
		Object[] params = new Object[]{
					power.getPowerName(),
					power.getPowerUrl(),
					power.getPowerParent(),
					power.isPowerModule(),
					power.isPowerSuperPower(),
					power.getPowerOrder(),
					power.getPowerImg(),
					power.getPowerType(),
					power.getPowerId()
				};
		return this.update(sql, params);
	}
	
	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.PowerDao#findByPcode(com.hnzskj.persist.bean.system.Power)
	 */
	@Override
	public Power findById(String powerId) {
		String sql = "select  powerId, powerName, powerUrl, powerParent, powerModule, powerSuperPower, " +
				" powerOrder, powerImg ,powerType" +
				" from power where powerId='"+powerId+"' limit 1";
		Power power = (Power) this.get(sql, Power.class, null);
		return power;
	}
	
	public Power findPowerNameById(String powerId) {
		String sql = "select powerId, powerName" +
				" from power where powerId='"+powerId+"'  limit 1";
		Power power = (Power) this.get(sql, Power.class, null);
		return power;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.PowerDao#searchPower(java.lang.String)
	 */
	@Override
	public Page<Power> searchPower(String fields) {
		return this.searchPower(null, fields, null, null, null);
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.PowerDao#searchPower(com.hnzskj.common.Page, java.lang.String)
	 */
	@Override
	public Page<Power> searchPower(Page<Power> page, String fields) {
		return this.searchPower(page, fields, null, null, null);
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.PowerDao#searchPower(com.hnzskj.common.Page, java.lang.String, java.lang.String, java.lang.Object[])
	 */
	@Override
	public Page<Power> searchPower(Page<Power> page, String fields,
			String sqlCondition, Object[] queryParams) {
		return this.searchPower(page, fields, sqlCondition, queryParams, null);
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.PowerDao#searchPower(com.hnzskj.common.Page, java.lang.String, java.util.LinkedHashMap)
	 */
	@Override
	public Page<Power> searchPower(Page<Power> page, String fields,
			LinkedHashMap<String, String> orderby) {
		return this.searchPower(page, fields, null, null, orderby);
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.PowerDao#searchPower(com.hnzskj.common.Page, java.lang.String, java.lang.String, java.lang.Object[], java.util.LinkedHashMap)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Page<Power> searchPower(Page<Power> page, String fields,
			String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {
		List<Power> powers = new ArrayList<Power>();//封装结果集
		Page<Power> ppage = new Page<Power>();//如果page为null时使用
		Integer totalRecords = 0;//查询的总记录数
		String sql = "";//查询的sql语句
		String countSql = "";//查询总记录数的sql语句
		
		sqlCondition = ("".equals(sqlCondition) || null == sqlCondition) ? " " : sqlCondition;
		countSql = "select count(powerId) from power " + sqlCondition;
		totalRecords = Integer.valueOf(getSingleValue(countSql, queryParams).toString());
		
		if ( page != null ) {//如果是分页查询
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
			sql = "select " + fields + "  from power " +sqlCondition + BaseDao.buildOrderBy(orderby) +
			"limit "+(page.getCurPage() - 1) * page.getMaxResult()+","+page.getMaxResult() ;
		
			ppage = page;			
			powers = query(sql, Power.class, queryParams);
		} else {//如果不需要分页查询
			sql = "select " + fields + " from power " + sqlCondition + buildOrderBy(orderby);
			powers = query(sql, Power.class, queryParams);
		}
		
		ppage.setTotalRecords(totalRecords);
		ppage.setList(powers);		
		return ppage;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.PowerDao#searchPowerBySQL(java.lang.String, java.lang.Object[])
	 */
	@Override
	public List<Power> searchPowerBySQL(String sql, Object[] params) {
		List<Power> powers = new ArrayList<Power>();
		Connection conn = getConnection();		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			setQueryParamters(pstmt, params);
			rs = pstmt.executeQuery();
			log.info("执行SQL语句：" + sql + outPutArray("参数列表为：",params));
			while ( rs.next() ) {
				Power power;
				ResultSetMetaData meta = rs. getMetaData(); //获取结果集元数据   
				int columnCount = meta.getColumnCount(); //获得结果集每一行的列数  
				Object instance = null;
				try {
					instance = Power.class.newInstance();
				} catch (InstantiationException e) {
					log.error(DataUtil.getStackTraceAsString(e));;
				} catch (IllegalAccessException e) {
					log.error(DataUtil.getStackTraceAsString(e));;
				} //生成clazz类的Java Bean实例   
				for ( int index = 1; index <= columnCount; index++ ) {   
					String currentFieldName = meta.getColumnName(index);//获得列名 
					//根据结果集的字段名获得Java Bean的对应属性   
					Field currentField = null;
					try {
						currentField = Power.class.getDeclaredField ( currentFieldName );
					} catch (SecurityException e) {
						log.error(DataUtil.getStackTraceAsString(e));;
					} catch (NoSuchFieldException e) {
						log.error(DataUtil.getStackTraceAsString(e));;
					}   
					currentField.setAccessible ( true ) ;//设置属性为可访问   
					try {
						currentField.set( instance, rs.getObject( index ) ) ;
					} catch (IllegalArgumentException e) {
						log.error(DataUtil.getStackTraceAsString(e));;
					} catch (IllegalAccessException e) {
						log.error(DataUtil.getStackTraceAsString(e));;
					}//根据结果集中的字段值设置属性值
				}
				power = (Power)instance;
				powers.add(power);
			}
		} catch (SQLException e) {
			log.info("执行SQL语句出错：" + sql + outPutArray("参数列表为：",params));
			log.error(DataUtil.getStackTraceAsString(e));;
		} finally {
			close(rs, pstmt, conn);
		}
		return powers;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.PowerDao#findParentCode(java.lang.String)
	 */
	@Override
	public String findParentCode(String powerId) {
		String sql = "select powerParent from power where powerId=?";
		String parentCode = (String) getSingleValue(sql, new Object[]{powerId});
		return parentCode;
	}
}
