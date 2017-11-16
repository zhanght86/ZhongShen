package com.hnzskj.persist.dao.sjzs.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.sjzs.FangFaAnLi;
import com.hnzskj.persist.dao.sjzs.FangFaAnLiDao;

public class FangFaAnLiDaoImpl extends BaseDao implements FangFaAnLiDao {

	@SuppressWarnings("unchecked")
	@Override
	public Page<FangFaAnLi> searchFfalSByParentId(Page<FangFaAnLi> page, String fields,
			String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {

		StringBuilder sql = new StringBuilder();
		
		List<FangFaAnLi> ffal = new ArrayList<FangFaAnLi>();//封装查询结果集
		Integer totalRecords = 0;//记录查询的总记录数
		String countSql = "";//查询总记录数的sql语句
		String newSql = "";//查询的sql语句
		Page<FangFaAnLi> epage = new Page<FangFaAnLi>();//临时变量，如果在page为null的情况下使用
		sqlCondition =  ("".equals(sqlCondition) || null == sqlCondition) ? " " : sqlCondition;
		countSql =sql.toString()+ "select count(id) from sjzs_ffal where deleteflag in(0,1) and FIND_IN_SET(Sort, getChildList(?)) " + sqlCondition;
		//查询总记录数
		totalRecords = Integer.parseInt( this.getSingleValue(countSql, queryParams).toString());
		if ( page != null) {//如果需要分页
			//先执行分页子句的查询结果
			String tempSql = sql.toString()+"select id from sjzs_ffal where deleteflag in(0,1) and FIND_IN_SET(id, getChildList(?))  "+BaseDao.buildOrderBy(orderby)+" limit 0, " + (page.getCurPage() - 1) * page.getMaxResult();
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
			newSql = sql.toString()+ "select * from sjzs_ffal where deleteflag in(0,1) and FIND_IN_SET(sort, getChildList(?)) ";
				newSql += " and id not in ("+idBuffer.toString()+")"+ BaseDao.buildOrderBy(orderby) +"  limit 0," + page.getMaxResult() ;
			epage = page;
			//查询结果集
			ffal =this.query(newSql, FangFaAnLi.class, queryParams);
		} else {//如果不需要分页
			newSql = sql.toString()+"select * from sjzs_ffal where deleteflag in(0,1) and FIND_IN_SET(id, getChildList(?)) "+ sqlCondition + BaseDao.buildOrderBy(orderby);
			//查询结果集
			ffal = this.query(newSql, FangFaAnLi.class, queryParams);
		}
		
		//设置总记录数
		epage.setList(ffal);
		//设置结果集
		epage.setTotalRecords(totalRecords);
		return epage;
	}

	@Override
	public FangFaAnLi getFfalById(String id) {
		String sql = "select id,title,author,department,ffalDateTime,awards,attachId,sort,content,uploadFlag from sjzs_ffal where id = ?";
		FangFaAnLi fangFaAnLi = (FangFaAnLi)this.get(sql, FangFaAnLi.class, new Object[]{id});
		return fangFaAnLi;
	}

	@Override
	public String addFFAL(FangFaAnLi fangFaAnLi) {
		String sql = " insert into sjzs_ffal (id,title,author,department,ffalDateTime,awards,attachId,sort,content,updatedate,deleteflag,note1,note2) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[]{
				fangFaAnLi.getId(),
				fangFaAnLi.getTitle(),
				fangFaAnLi.getAuthor(),
				fangFaAnLi.getDepartment(),
				fangFaAnLi.getFfalDateTime(),
				fangFaAnLi.getAwards(),
				fangFaAnLi.getAttachId(),
				fangFaAnLi.getSort(),
				fangFaAnLi.getContent(),
				new Timestamp(System.currentTimeMillis()),
				0,
				fangFaAnLi.getNote1(),
				fangFaAnLi.getNote2()
		};
		int result = update(sql, params);
		
		return result>0?fangFaAnLi.getId():null;
	}

	@Override
	public int updateFFAL(FangFaAnLi fangFaAnLi) {
		String sql = "update sjzs_ffal set title = ?,author = ?,department = ?,ffalDateTime = ?,awards = ?,attachId = ? ,sort = ?,content = ?, updatedate=?,deleteflag = ? ,note1 = ? ,note2 = ?  where id = ?";
		Object [] params = new Object[]{
				fangFaAnLi.getTitle(),
				fangFaAnLi.getAuthor(),
				fangFaAnLi.getDepartment(),
				fangFaAnLi.getFfalDateTime(),
				fangFaAnLi.getAwards(),
				fangFaAnLi.getAttachId(),
				fangFaAnLi.getSort(),
				fangFaAnLi.getContent(),
				new Timestamp(System.currentTimeMillis()),
				1,
				fangFaAnLi.getNote1(),
				fangFaAnLi.getNote2(),
				fangFaAnLi.getId()
		};
		return this.update(sql, params);
	}

	public int deleteFFAL(String Id) {
		StringBuffer sql = new StringBuffer();
//		sql.append("delete from sjzs_ffal where id = ? ");
		sql.append("update sjzs_ffal set deleteflag = ? ,updatedate=? where id = ? ");
		Object[] params = new Object[]{-1,new Timestamp(System.currentTimeMillis()),Id};
		Integer result = update(sql.toString(), params);
		if (null == result) {
			result = 0;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public Page<FangFaAnLi> searchByCondition(Page<FangFaAnLi> page, String fields,
			String sqlCondition, Object[] queryParams, LinkedHashMap<String, String> orderby) {
		List<FangFaAnLi> ffals = new ArrayList<FangFaAnLi>();//封装结果集
		Page<FangFaAnLi> ppage = new Page<FangFaAnLi>();//如果page为null时使用
		Integer totalRecords = 0;//查询的总记录数
		String sql = "";//查询的sql语句
		String countSql = "";//查询总记录数的sql语句
		String childListStr = this.getChileList(queryParams[0].toString());
		sqlCondition = ("".equals(sqlCondition) || null == sqlCondition) ? " " : sqlCondition;
		countSql = "select count(id) from sjzs_ffal " + sqlCondition +" and FIND_IN_SET(sort, '" + childListStr + "') and deleteflag in (0,1) ";
		totalRecords = Integer.valueOf(getSingleValue(countSql, null).toString());
		
		if ( page != null ) {//如果是分页查询
			sql = "select " + fields + "  from sjzs_ffal " +sqlCondition +" and FIND_IN_SET(sort, '" + childListStr + "')  and deleteflag in (0,1) "+ BaseDao.buildOrderBy(orderby)+
			"limit "+(page.getCurPage() - 1) * page.getMaxResult()+","+page.getMaxResult() ;
		
			ppage = page;			
			ffals = query(sql, FangFaAnLi.class, null);
		} else {//如果不需要分页查询
			sql = "select " + fields + " from sjzs_ffal " + sqlCondition +" and FIND_IN_SET(sort, '" + childListStr + "')  and deleteflag in (0,1) "+ buildOrderBy(orderby);
			ffals = query(sql, FangFaAnLi.class, null);
		}
		
		ppage.setTotalRecords(totalRecords);
		ppage.setList(ffals);		
		return ppage;
	}

	@Override
	public int batchDeleteFFAL(String ids, boolean phDel) {
		String sql = "";
		if(phDel){
			sql = "delete from sjzs_ffal where id in (" + buildIds(ids) + ")";
		}else{
			sql = "update sjzs_ffal set deleteflag = -1, updatedate = '" + new Timestamp(System.currentTimeMillis()) + "' where id in (" + buildIds(ids) + ")";
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
