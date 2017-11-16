/*
 * @项目名称: OA
 * @文件名称: HolidayFlowDaoImpl.java
 * @日期: 2012-7-12
 * @版权: 2012 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.persist.dao.sjzs.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.sjzs.SjzhMenuTree;
import com.hnzskj.persist.dao.sjzs.ShenJiZhuShouDao;

/**
 * 
 * 类名称：HolidayFlowBeanDaoImpl 类描述：<br/>
 * 创建人：wxz<br/>
 * 创建时间：2012-7-13 上午11:32:08 修改人：Administrator 修改时间： 修改备注：
 * 
 * @version 1.0
 */
public class ShenJiZhuShouDaoImpl extends BaseDao implements ShenJiZhuShouDao {

	@Override
	public int deleteMenus(String menuId) {
		String sql = "update sjzs_menuTree set deleteflag = -1 ,updatedate=? where menuId = '"+menuId+"'";
		
		Object[] params = new Object[]{new Timestamp(System.currentTimeMillis())};
		return this.update(sql, params);
	}

	
	@Override
	public int deleteMenusExpan(int type, String menuId, String name) {
		System.out.println(menuId);
		System.out.println(name);
		
		String sql = "update sjzs_menuTree set deleteflag = -1 ,updatedate=? where menuId = '"+menuId+"'";
		
		Object[] params = new Object[]{new Timestamp(System.currentTimeMillis())};
		
		
		String countsql ="select count(menuId) from   sjzs_menuTree where deleteflag<> -1 and  menuParent=?";
		Object[] params2 = {name};
	 int temp=	Integer.parseInt(getSingleValue(countsql, params2).toString());
	if (temp==1) {
		String parentSql="update sjzs_menuTree  set note2='1' where   menuId =?";
		Object[] tr={name};
		String sqlarray[]={sql,parentSql};
		Object[][]parentAarray = {params,tr};
		return this.update(sqlarray, parentAarray);
	}else{
		
		String sqlarray[]={sql};
		Object[][]parentAarray = {params};
		return this.update(sqlarray, parentAarray);
	}
		
	}
	
	@Override
	public SjzhMenuTree findMenuById(String menuId) {
		String sql = "select * from sjzs_menuTree where menuId=? order by menuorder asc ";
		SjzhMenuTree sm = (SjzhMenuTree) this.get(sql, SjzhMenuTree.class, new Object[] { menuId });
		return sm;
	}

	@Override
	public int addMenu(SjzhMenuTree sjzhMenuTree) {
		
		String sql = "";
		sql = "insert into sjzs_menuTree (menuId,menuName, menuParent, menuOrder, menuType, note1, note2,updateDate,deleteflag) values (?, ?, ?, ?, ?, ?,?,?,?)";
		Object[] params = { this.getGUID(), sjzhMenuTree.getMenuName(),
				sjzhMenuTree.getMenuParent(), sjzhMenuTree.getMenuOrder(),
				sjzhMenuTree.getMenuType(), sjzhMenuTree.getNote1(),
				sjzhMenuTree.getNote2(), new Timestamp(System.currentTimeMillis()),
				0};
		int result = this.update(sql, params);
		return result;
	}
	
	@Override
	public int addMenuExpan(SjzhMenuTree sjzhMenuTree) {
		
		String sql = "";
		String sql2 ="";
		sql = "insert into sjzs_menuTree (menuId,menuName, menuParent, menuOrder, menuType, note1, note2,updateDate,deleteflag) values (?, ?, ?, ?, ?, ?,?,?,?)";
		Object[] params = { this.getGUID(), sjzhMenuTree.getMenuName(),
				sjzhMenuTree.getMenuParent(), sjzhMenuTree.getMenuOrder(),
				sjzhMenuTree.getMenuType(), sjzhMenuTree.getNote1(),
				sjzhMenuTree.getNote2(), new Timestamp(System.currentTimeMillis()),
				0};
		sql2="update sjzs_menuTree set note2='0' where  menuId=?";
		Object[] params2 ={sjzhMenuTree.getMenuParent()};
		String sqlarray []={sql,sql2};
		Object[][] parames ={params,params2};
		int result = this.update(sqlarray, parames);
		return result;
	}
	

	@Override
	public Page<SjzhMenuTree> searchMenu(String fields,LinkedHashMap<String, String> orderby,String condition) {
		return this.searchMenu(null, fields, condition, null, orderby);
	}

	
	@SuppressWarnings("unchecked")
	public Page<SjzhMenuTree> searchMenu(Page<SjzhMenuTree> page,String fields, String sqlCondition, Object[] queryParams,LinkedHashMap<String, String> orderby) {
		List<SjzhMenuTree> empls = new ArrayList<SjzhMenuTree>();//封装查询结果集
		Integer totalRecords = 0;//记录查询的总记录数
		String sql = "";//查询的sql语句
		String countSql = "";//查询总记录数的sql语句
		Page<SjzhMenuTree> epage = new Page<SjzhMenuTree>();//临时变量，如果在page为null的情况下使用
		sqlCondition =  ("".equals(sqlCondition) || null == sqlCondition) ? " where 1 = 1 " : sqlCondition;
		countSql = "select count(menuId) from sjzs_menuTree " + sqlCondition + " and deleteflag in(0,1) ";
		//查询总记录数
		totalRecords = Integer.parseInt(getSingleValue(countSql, queryParams).toString());
		
		sql = "select " + fields + " from sjzs_menuTree " + sqlCondition + " and deleteflag in(0,1) " + buildOrderBy(orderby);
		System.out.println(sql);
		empls = query(sql, SjzhMenuTree.class, queryParams);
		epage.setList(empls);
		epage.setTotalRecords(totalRecords);
		return epage;
	}

	@Override
	public int updateMenu(SjzhMenuTree sjzhMenuTree) {
			String sql="update sjzs_menuTree set  menuName=?, menuParent=?, menuOrder=?, menuType=?, note1=?, note2=?, updatedate = ? ,deleteflag = ? where menuId = ?";
			Object [] params ={
					sjzhMenuTree.getMenuName(),
					sjzhMenuTree.getMenuParent(),
					sjzhMenuTree.getMenuOrder(),
					sjzhMenuTree.getMenuType(),
					sjzhMenuTree.getNote1(),
					sjzhMenuTree.getNote2(),
					new Timestamp(System.currentTimeMillis()),
					1,
					sjzhMenuTree.getMenuId()
				};
			
			
			
			int result =this.update(sql, params);
				return result;
	}
	
	public String findMenuParentCode(String menuId) {
		String sql = "select menuParent from sjzs_menuTree where menuParent=?";
		String parentCode = (String) getSingleValue(sql, new Object[]{menuId});
		return parentCode;
	}
	@Override
	public int getItemsCount(String sql) {
		Integer count  = Integer.parseInt(getSingleValue(sql, null).toString());
		return  count.intValue();
	}
	@Override
	public  List<SjzhMenuTree> findChildByParentAsync(String parentId){
		String sql = "select menuId,menuName,menuParent,menuType  from sjzs_menuTree where menuParent=? and deleteflag!=-1";
	return (List<SjzhMenuTree>)query(sql, SjzhMenuTree.class, new Object[]{parentId});
		
		
	}

	@Override
	public  List<SjzhMenuTree> findChildByParentAsync2(String parentId){
		String sql = "select menuId,menuName,menuParent,menuType from sjzs_menuTree where menuid=? and deleteflag!=-1";
	return (List<SjzhMenuTree>)query(sql, SjzhMenuTree.class, new Object[]{parentId});
		
		
	}
	
	@Override
	public int findChildById(String parentId){
		String sql = "select count(menuId) as countid  from sjzs_menuTree where menuParent=? and deleteflag!=-1";
		return Integer.parseInt(getSingleValue(sql,new Object[]{parentId}).toString());
		
		
	}


	@Override
	public int moveNotesBatch(String[] sqls, Object[][] params) {
		// TODO Auto-generated method stub
		return this.update(sqls, params);
	}


	@Override
	public String addMenuAndReturnId(SjzhMenuTree sjzhMenuTree) {
		String sql = "";
		sql = "insert into sjzs_menuTree (menuId,menuName, menuParent, menuOrder, menuType, note1, note2,updateDate,deleteflag) values (?, ?, ?, ?, ?, ?,?,?,?)";
		String id = this.getGUID();
		Object[] params = { id, sjzhMenuTree.getMenuName(),
				sjzhMenuTree.getMenuParent(), sjzhMenuTree.getMenuOrder(),
				sjzhMenuTree.getMenuType(), sjzhMenuTree.getNote1(),
				sjzhMenuTree.getNote2(), new Timestamp(System.currentTimeMillis()),
				0};
		int result = this.update(sql, params);
		return id;
	}
	
}