/*
 * @项目名称: htglxt
 * @文件名称: RoleDaoImpl.java
 * @日期: 2011-5-27
 * @版权: 2011 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.persist.dao.system.impl;
import com.hnzskj.common.DataUtil;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.Constant;
import com.hnzskj.common.Page;
import com.hnzskj.common.SystemException;
import com.hnzskj.persist.bean.system.Power;
import com.hnzskj.persist.bean.system.Role;
import com.hnzskj.persist.dao.system.PowerDao;
import com.hnzskj.persist.dao.system.RoleDao;

/**        
 * 
 * 类名称：RoleDaoImpl     <br/>
 * 类描述：<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-5-27 上午10:38:32   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-5-27 上午10:38:32   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */
public class RoleDaoImpl extends BaseDao implements RoleDao {
	private static Logger log = Logger.getLogger(RoleDaoImpl.class);
	private PowerDao powerDao;

	/**
	 * @return the powerDao
	 */
	public PowerDao getPowerDao() {
		return powerDao;
	}

	/**
	 * @param powerDao the powerDao to set
	 */
	public void setPowerDao(PowerDao powerDao) {
		this.powerDao = powerDao;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.RoleDao#addRole(com.hnzskj.persist.bean.system.Role)
	 */
	@Override
	public int addRole(Role role) {
		String sql = "insert into role (roleId,roleName,roleRemark,roleType) values (?,?,?,?)";
		Object[] params = new Object[]{
				this.getGUID(),
				role.getRoleName(),
				role.getRoleRemark(),
				role.getRoleType()
			};
		int result = 0;
		result = update(sql, params);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.RoleDao#deleteRoleByRoleCode(java.lang.String)
	 */
	@Override
	public int deleteRoleById(String roleCode) {
		//删除角色前先从用户角色表查询表中是否存在角色的信息
		String countSql = "select count(roleId) from user_role where roleId=?";
		Integer records = Integer.valueOf(getSingleValue(countSql, new Object[]{roleCode}).toString());
		if ( records > 0 ) {//如果用户角色表存在角色的信息，则不允许删除当前角色
			throw new SystemException("当前角色正在被使用，不可删除！");
		}
		//删除角色信息，并且从角色权限联系表中把该角色的信息删除掉
		String[] sqls = new String[2];
		sqls[0] = "delete from role where roleId=?";
		sqls[1] = "delete from role_power where roleId=?";
		Object[][] params = new Object[2][];
		params[0] = new Object[]{roleCode};
		params[1] = new Object[]{roleCode};
		return this.update(sqls, params);
	}
	
	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.RoleDao#updateRole(com.hnzskj.persist.bean.system.Role)
	 */
	@Override
	public int updateRole(Role role) {
		String sql = "update role set roleName=?, roleRemark=?, roleType=? where roleId=?";
		Object[] params = new Object[]{
				role.getRoleName(),
				role.getRoleRemark(), 
				role.getRoleType(),
				role.getRoleId()
			};
		return this.update(sql, params);
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.RoleDao#findByRoleCode(java.lang.String)
	 */
	@Override
	public Role findById(String roleId) {
		String sql = "select roleId, roleName, roleRemark, roleType from role where roleId=?";
		return (Role) this.get(sql, Role.class, new Object[]{roleId});
	}
	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.RoleDao#findByRoleCode(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findByRcodes(String rcode) {
		String sql = "select roleId, roleName, roleRemark, roleType from role where rcode in("+rcode+")";
		List<Role> roles = query(sql, Role.class, null);
		return roles;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.RoleDao#searchRole(java.lang.String)
	 */
	@Override
	public Page<Role> searchRole(String fields) {
		return this.searchRole(null, fields, null, null, null);
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.RoleDao#searchRole(com.hnzskj.common.Page, java.lang.String)
	 */
	@Override
	public Page<Role> searchRole(Page<Role> page, String fields) {
		return this.searchRole(page, fields, null, null, null);
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.RoleDao#searchRole(com.hnzskj.common.Page, java.lang.String, java.lang.String, java.lang.Object[])
	 */
	@Override
	public Page<Role> searchRole(Page<Role> page, String fields,
			String sqlCondition, Object[] queryParams) {
		LinkedHashMap<String, String> orderby=new LinkedHashMap<String, String>();
		orderby.put("roleType", "asc");
		return this.searchRole(page, fields, sqlCondition, queryParams, orderby);
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.RoleDao#searchRole(com.hnzskj.common.Page, java.lang.String, java.util.LinkedHashMap)
	 */
	@Override
	public Page<Role> searchRole(Page<Role> page, String fields,
			LinkedHashMap<String, String> orderby) {
		return this.searchRole(page, fields, null, null, orderby);
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.RoleDao#searchRole(com.hnzskj.common.Page, java.lang.String, java.lang.String, java.lang.Object[], java.util.LinkedHashMap)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Page<Role> searchRole(Page<Role> page, String fields,
			String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {
		List<Role> roles = new ArrayList<Role>();//封装结果集
		Page<Role> rpage = new Page<Role>();//如果page为null时使用
		Integer totalRecords = 0;//查询的总记录数
		String sql = "";//查询的sql语句
		String countSql = "";//查询总记录数的sql语句
		
		sqlCondition = ("".equals(sqlCondition) || null == sqlCondition) ? " " : sqlCondition;
		countSql = "select count(roleId) from role " + sqlCondition;
		
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
			sql = "select " + fields + "  from role " +sqlCondition + BaseDao.buildOrderBy(orderby) +
			"limit "+(page.getCurPage() - 1) * page.getMaxResult()+","+page.getMaxResult() ;
			rpage = page;
			
			roles = query(sql, Role.class, queryParams);
		} else {//如果不需要分页查询
			sql = "select " + fields + " from role " + sqlCondition + buildOrderBy(orderby);
			roles = query(sql, Role.class, queryParams);
		}
		if(roles!=null&&roles.size()>1){
			Collections.sort(roles, new Comparator<Role>() {
				public int compare(Role s1, Role s2) {
					Integer p1 = Integer.parseInt(s1.getRoleType());
					Integer p2 = Integer.parseInt(s2.getRoleType());
					return p1.compareTo(p2);
				}
			});
		}
		totalRecords = Integer.valueOf(getSingleValue(countSql, queryParams).toString());
		rpage.setTotalRecords(totalRecords);
		rpage.setList(roles);		
		return rpage;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.RoleDao#saveRolePower(com.hnzskj.persist.bean.system.Role, java.lang.String[])
	 */
	@Override
	public int saveRolePower(Role role, String[] pcodes) {
		int result = 0;
		String delSql = "delete from role_power where roleId=?";
		String sql = "insert into role_power (powerId, roleId) values (?, ?)";

		try {
			conn = getConnection();
			conn.getAutoCommit();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(delSql);
			pstmt.setString(1, role.getRoleId());
			result = pstmt.executeUpdate();
			log.info("执行SQL语句：" + delSql + outPutArray("参数列表：", new Object[]{role.getRoleId()}));
			pstmt = conn.prepareStatement(sql);			
			for (int i = 0; i < pcodes.length; i++) {
				setQueryParamters(pstmt, new Object[]{pcodes[i], role.getRoleId()});
				result = pstmt.executeUpdate();
				log.info("执行SQL语句：" + sql + 
						outPutArray("参数列表：", new Object[]{pcodes[i], role.getRoleId()}));
			}
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			log.error(DataUtil.getStackTraceAsString(e));;
		} finally {
			close(null, pstmt, conn);
		}
		return result;		
	}
	
	@SuppressWarnings("unchecked")
	public List<Power> getPowersCode(String rcode) {
		String sql = "select powerId from role_power where roleId=?";
		List<Power> powers = query(sql, Power.class, new Object[]{rcode});
		return powers;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.RoleDao#getPowers(java.lang.String)
	 */
	@Override
	public List<Power> getPowers(String rcode) {
		List<Power> powers = new ArrayList<Power>();
		String sql = "select powerId,powerName,powerParent from power where powerId in " +
				"(select powerId from role_power where roleId=?)";
		powers = powerDao.searchPowerBySQL(sql, new Object[]{rcode});
		return powers;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.RoleDao#searchRoleBySQL(java.lang.String, java.lang.Object[])
	 */
	@Override
	public List<Role> searchRoleBySQL(String sql, Object[] params) {
		List<Role> roles = new ArrayList<Role>();
		Connection conn = getConnection();		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			setQueryParamters(pstmt, params);
			rs = pstmt.executeQuery();
			log.info("执行SQL语句：" + sql + outPutArray("参数列表为：",params));
			while ( rs.next() ) {
				Role role;
				ResultSetMetaData meta = rs. getMetaData(); //获取结果集元数据   
				int columnCount = meta.getColumnCount(); //获得结果集每一行的列数  
				Object instance = null;
				try {
					instance = Role.class.newInstance();
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
						currentField = Role.class.getDeclaredField ( currentFieldName );
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
				role = (Role)instance;
				roles.add(role);
			}
		} catch (SQLException e) {
			log.info("执行SQL语句出错：" + sql + outPutArray("参数列表为：",params));
			log.error(DataUtil.getStackTraceAsString(e));;
		} finally {
			close(rs, pstmt, conn);
		}
		return roles;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.RoleDao#getRoleCountByIdAndName(int, java.lang.String)
	 */
	@Override
	public int getRoleCountByIdAndName(String roleId, String rname) {
		String sql = "select count(roleId) from role where roleId != ? and roleName = ?";
		int result = Integer.valueOf(getSingleValue(sql, new Object[]{roleId, rname}).toString());
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRoleList() {
		List<Role> roleList =new ArrayList<Role>();
		String sql= "select * from role where rtype = "+ Constant.RTYPEY;
		roleList = query(sql,Role.class, null); 
		return roleList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRoleByUid(int uid) {
		//查询授权用户的审批角色
		String sql = "select * from role where roleId in (select distinct(roleId) from user_role where uid = ? ) and roleType = 0 ";
		List<Role> roles = new BaseDao().query(sql, Role.class, new Object[]{uid});
		return roles;
	}
	
	public boolean checkRole(String rname) {
		String sql = "select count(roleId) from role where roleName = ?";
		int result = Integer.valueOf(getSingleValue(sql, new Object[]{rname}).toString());
		if(result !=0) return true;
		return false;
	}
	
	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.RoleDao#getRoleNameByUid(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRoleNameByUid(String emplId){
		//查询授权用户的审批角色
		String sql = "select roleId,roleName,roleRemark,roleType from role where roleId in (select distinct(roleId) from user_role where emplId = ? ) and roleType = 0 ";
		List<Role> roles = new BaseDao().query(sql, Role.class, new Object[]{emplId});
		return roles;
	}
}
