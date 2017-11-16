package com.hnzskj.persist.dao.sjzs.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.sjzs.Law;
import com.hnzskj.persist.dao.sjzs.ShenJiFaGuiDao;

public class ShenJiFaGuiDaoImpl extends BaseDao implements ShenJiFaGuiDao {

	@SuppressWarnings("unchecked")
	@Override
	public Page<Law> searchLawsByParentId(Page<Law> page, String fields,
			String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {

		StringBuilder sql = new StringBuilder();
//		sql.append("with ");
//		sql.append("temptab (menuid, menuParent) ");
//		sql.append("as ( ");
//		sql.append("select root.menuid, root.menuParent  ");           
//		sql.append("from sjzs_menuTree root ");
//		sql.append("where menuid= ? ");
//		sql.append("union all ");
//		sql.append("select sub.menuid, sub.menuParent  ");          
//		sql.append("from sjzs_menuTree sub, temptab super ");
//		sql.append("where sub.menuParent = super.menuid  ) ");
		
		List<Law> laws = new ArrayList<Law>();//封装查询结果集
		Integer totalRecords = 0;//记录查询的总记录数
		String countSql = "";//查询总记录数的sql语句
		String newSql = "";//查询的sql语句
		Page<Law> epage = new Page<Law>();//临时变量，如果在page为null的情况下使用
		sqlCondition =  ("".equals(sqlCondition) || null == sqlCondition) ? " " : sqlCondition;
		countSql =sql.toString()+ "select count(lawId) from sjzs_sjfg where deleteflag in (0,1) and FIND_IN_SET(lawSort, getChildList(?)) " + sqlCondition;
		//查询总记录数
		totalRecords = Integer.parseInt( this.getSingleValue(countSql, queryParams).toString());
		if ( page != null) {//如果需要分页
			//先执行分页子句的查询结果
			String tempSql = sql.toString()+"select lawId from sjzs_sjfg where deleteflag in (0,1) and FIND_IN_SET(lawSort, getChildList(?))  "+BaseDao.buildOrderBy(orderby)+" limit 0,"+ (page.getCurPage() - 1) * page.getMaxResult() ;
			List<String> idsList = this.getListSingleValue(tempSql, queryParams);
			StringBuffer idBuffer = new StringBuffer();
			if(idsList!=null&&idsList.size()>0){
				for(int i =0;i<idsList.size();i++){
					if(i==0){
						idBuffer.append("'"+idsList.get(i)+"'");
					}else{
						idBuffer.append(",'"+idsList.get(i)+"'");
					}
				}
			}
			else{
				idBuffer.append("''");
			}
			//如果不执行模糊查询则构建where语句使用DBUtil.buildOrderBy（）的方法
			//如果执行模糊查询则构建where语句使用PolicyDao的buildSQLWhereFuzzy（）方法
			newSql = sql.toString()+ "select * from sjzs_sjfg where deleteflag in (0,1) and FIND_IN_SET(lawSort, getChildList(?)) ";
				newSql += " and lawId not in ("+idBuffer.toString()+")"+ BaseDao.buildOrderBy(orderby) +"  limit 0," + page.getMaxResult() ;
			epage = page;
			//查询结果集
			laws =this.query(newSql, Law.class, queryParams);
		} else {//如果不需要分页
			newSql = sql.toString()+"select * from sjzs_sjfg where deleteflag in (0,1) and FIND_IN_SET(lawSort, getChildList(?)) "+ sqlCondition + BaseDao.buildOrderBy(orderby);
			//查询结果集
			laws = this.query(newSql, Law.class, queryParams);
		}
		
		//设置总记录数
		epage.setList(laws);
		//设置结果集
		epage.setTotalRecords(totalRecords);
		return epage;
	}

	@Override
	public Law getLawById(String lawId) {
		String sql = "select lawId,lawName,lawNumber,lawOrg,lawTrade,lawContent,lawDate,lawGrade,lawCategory,lawSort,lawSortName,attachId from sjzs_sjfg where lawId = ?";
		Law law = (Law)this.get(sql, Law.class, new Object[]{lawId});
		return law;
	}

	@Override
	public String addLaw(Law law) {
		String sql = " insert into sjzs_sjfg (lawId,lawName,lawNumber,lawOrg,lawTrade,lawContent,lawDate,lawGrade,lawCategory,lawSort,lawSortName,attachId,updatedate,deleteflag,note1,note2) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[]{
				law.getLawId(),
				law.getLawName(),
				law.getLawNumber(),
				law.getLawOrg(),
				law.getLawTrade(),
				law.getLawContent(),
				law.getLawDate(),
				law.getLawGrade(),
				law.getLawCategory(),
				law.getLawSort(),
				law.getLawSortName(),
				law.getAttachId(),
				new Timestamp(System.currentTimeMillis()),
				0,
				law.getNote1(),
				law.getNote2()
		};
		int result = update(sql, params);
		
		return result>0?law.getLawId():null;
	}

	@Override
	public int updateLaw(Law law) {
		String sql = "update sjzs_sjfg set lawName = ?,lawNumber = ?,lawOrg = ?,lawTrade = ?,lawContent = ?,lawDate = ? ,lawGrade = ?, lawCategory = ?,lawSort = ?,lawSortName = ?, attachId =?,  updatedate =?, deleteflag =? , note1 =? , note2 =? where lawId = ?";
		Object [] params = new Object[]{
				law.getLawName(),
				law.getLawNumber(),
				law.getLawOrg(),
				law.getLawTrade(),
				law.getLawContent(),
				law.getLawDate(),
				law.getLawGrade(),
				law.getLawCategory(),
				law.getLawSort(),
				law.getLawSortName(),
				law.getAttachId(),
				new Timestamp(System.currentTimeMillis()),
				1,
				law.getNote1(),
				law.getNote2(),
				law.getLawId()
		};
		return this.update(sql, params);
	}

	@Override
	public int deleteLaw(String Id) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
//		sql.append("delete from sjzs_sjfg where lawId = ? ");
		sql.append("update sjzs_sjfg set deleteflag = ? ,updatedate=? where lawId = ?");
		Object[] params = new Object[]{-1,new Timestamp(System.currentTimeMillis()) ,Id};
		Integer result = update(sql.toString(), params);
		if (null == result) {
			result = 0;
		}
		return result;
	}

	@SuppressWarnings({ "unchecked", "unchecked" })
	@Override
	public Page<Law> searchByCondition(Page<Law> page, String fields,
			String sqlCondition, Object[] queryParams, LinkedHashMap<String, String> orderby) {
		List<Law> laws = new ArrayList<Law>();//封装结果集
		Page<Law> ppage = new Page<Law>();//如果page为null时使用
		Integer totalRecords = 0;//查询的总记录数
		String sql = "";//查询的sql语句
		String countSql = "";//查询总记录数的sql语句
		String childListStr = this.getChileList(queryParams[0].toString());
		sqlCondition = ("".equals(sqlCondition) || null == sqlCondition) ? " " : sqlCondition;
		countSql = "select count(lawId) from sjzs_sjfg " + sqlCondition +" and FIND_IN_SET(lawSort, '" + childListStr + "') and deleteflag in (0,1) ";
		
		totalRecords = Integer.valueOf(getSingleValue(countSql, null).toString());
		
		if ( page != null ) {//如果是分页查询
			sql = "select " + fields + " from sjzs_sjfg " +sqlCondition +" and FIND_IN_SET(lawSort, '" + childListStr + "') and deleteflag in (0,1) "+ BaseDao.buildOrderBy(orderby)+
			"limit "+(page.getCurPage() - 1) * page.getMaxResult()+","+page.getMaxResult() ;
		
			ppage = page;			
			laws = query(sql, Law.class, null);
		} else {//如果不需要分页查询
			sql = "select " + fields + " from sjzs_sjfg " + sqlCondition +" and FIND_IN_SET(lawSort, '" + childListStr + "') and deleteflag in (0,1) "+ buildOrderBy(orderby);
			laws = query(sql, Law.class, null);
		}
		
		ppage.setTotalRecords(totalRecords);
		ppage.setList(laws);		
		return ppage;
	}
	
	@Override
	public int batchDeleteLaw(String ids, boolean phDel) {
		String sql = "";
		if(phDel){
			sql = "delete from sjzs_sjfg where lawId in (" + buildIds(ids) + ")";
		}else{
			sql = "update sjzs_sjfg set deleteflag = -1, updatedate = '" + new Timestamp(System.currentTimeMillis()) + "' where lawId in (" + buildIds(ids) + ")";
		}
		return this.update(sql, null);
	}

	private String buildIds(String ids){
		String newIds = "";
		if(ids!=null&&!"".equals(ids)){
			String[] temp = ids.split(",");
			for(int i=0;i<temp.length;i++){
				newIds += "'" + temp[i] + "', ";
			}
			newIds = newIds.substring(0,newIds.length()-2);
		}
		return newIds;
	}
}
