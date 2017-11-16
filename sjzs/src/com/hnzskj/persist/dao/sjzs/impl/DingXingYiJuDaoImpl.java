package com.hnzskj.persist.dao.sjzs.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.sjzs.DxyjLaw;
import com.hnzskj.persist.dao.sjzs.DingXingYiJuDao;

public class DingXingYiJuDaoImpl extends BaseDao implements DingXingYiJuDao {

	@SuppressWarnings("unchecked")
	@Override
	public Page<DxyjLaw> searchLawsByParentId(Page<DxyjLaw> page, String fields,
			String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {

		StringBuilder sql = new StringBuilder();
		
		List<DxyjLaw> ffal = new ArrayList<DxyjLaw>();//封装查询结果集
		Integer totalRecords = 0;//记录查询的总记录数
		String countSql = "";//查询总记录数的sql语句
		String newSql = "";//查询的sql语句
		Page<DxyjLaw> epage = new Page<DxyjLaw>();//临时变量，如果在page为null的情况下使用
		sqlCondition =  ("".equals(sqlCondition) || null == sqlCondition) ? " " : sqlCondition;
		countSql =sql.toString()+ "select count(id) from sjzs_dxyj where deleteflag in (0,1) and FIND_IN_SET(parentId, getChildList(?)) " + sqlCondition;
		//查询总记录数
		totalRecords = Integer.parseInt( this.getSingleValue(countSql, queryParams).toString());
		if ( page != null) {//如果需要分页
			//先执行分页子句的查询结果
			String tempSql = sql.toString()+"select id from sjzs_dxyj where deleteflag in (0,1) and FIND_IN_SET(parentId, getChildList(?))  "+BaseDao.buildOrderBy(orderby)+" limit 0, " + (page.getCurPage() - 1) * page.getMaxResult();
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
			newSql = sql.toString()+ "select * from sjzs_dxyj where deleteflag in (0,1) and FIND_IN_SET(parentId, getChildList(?)) ";
				newSql += " and id not in ("+idBuffer.toString()+")"+ BaseDao.buildOrderBy(orderby) +"  limit 0," + page.getMaxResult() ;
			epage = page;
			//查询结果集
			ffal =this.query(newSql, DxyjLaw.class, queryParams);
		} else {//如果不需要分页
			newSql = sql.toString()+"select * from sjzs_dxyj where deleteflag in (0,1) and FIND_IN_SET(parentId, getChildList(?)) "+ sqlCondition + BaseDao.buildOrderBy(orderby);
			//查询结果集
			ffal = this.query(newSql, DxyjLaw.class, queryParams);
		}
		
		//设置总记录数
		epage.setList(ffal);
		//设置结果集
		epage.setTotalRecords(totalRecords);
		return epage;
	}
	
	@Override
	public Page<DxyjLaw> searchByDepartment(Page<DxyjLaw> page, String fields,
			String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby, String department) {

		StringBuilder sql = new StringBuilder();
		
		List<DxyjLaw> ffal = new ArrayList<DxyjLaw>();//封装查询结果集
		Integer totalRecords = 0;//记录查询的总记录数
		String countSql = "";//查询总记录数的sql语句
		String newSql = "";//查询的sql语句
		Page<DxyjLaw> epage = new Page<DxyjLaw>();//临时变量，如果在page为null的情况下使用
		sqlCondition =  ("".equals(sqlCondition) || null == sqlCondition) ? " " : sqlCondition;
		countSql =sql.toString()+ "select count(id) from sjzs_dxyj where deleteflag in (0,1) and caption like '%"+department+"%' and FIND_IN_SET(parentId, getChildList(?)) " + sqlCondition;
		//查询总记录数
		totalRecords = Integer.parseInt( this.getSingleValue(countSql, queryParams).toString());
		if ( page != null) {//如果需要分页
			//先执行分页子句的查询结果
			String tempSql = sql.toString()+"select id from sjzs_dxyj where deleteflag in (0,1) and caption like '%"+department+"%' and FIND_IN_SET(parentId, getChildList(?))  "+BaseDao.buildOrderBy(orderby)+" limit 0, " + (page.getCurPage() - 1) * page.getMaxResult();
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
			newSql = sql.toString()+ "select * from sjzs_dxyj where deleteflag in (0,1) and caption like '%"+department+"%' and FIND_IN_SET(parentId, getChildList(?)) ";
				newSql += " and id not in ("+idBuffer.toString()+")"+ BaseDao.buildOrderBy(orderby) +"  limit 0," + page.getMaxResult() ;
			epage = page;
			//查询结果集
			ffal =this.query(newSql, DxyjLaw.class, queryParams);
		} else {//如果不需要分页
			newSql = sql.toString()+"select * from sjzs_dxyj where deleteflag in (0,1) and caption like '%"+department+"%' and FIND_IN_SET(parentId, getChildList(?)) "+ sqlCondition + BaseDao.buildOrderBy(orderby);
			//查询结果集
			ffal = this.query(newSql, DxyjLaw.class, queryParams);
		}
		
		//设置总记录数
		epage.setList(ffal);
		//设置结果集
		epage.setTotalRecords(totalRecords);
		return epage;
	}
	
	@Override
	public DxyjLaw getLawById(String lawId) {
		String sql = "select id,caption,isShow,isRead,nodeClass,nodeType,parentID,department,lawNo,tiao,kuan,lawContent, writeDate from sjzs_dxyj where deleteflag in (0,1) and id = ?";
		DxyjLaw law = (DxyjLaw)this.get(sql, DxyjLaw.class, new Object[]{lawId});
		return law;
	}

	private Integer getNodeClass(String parentId){
		String sql = "select menuType from sjzs_menutree where menuId = ?";
		Object[] parma = new Object[]{	parentId	}; 
		Integer maxOrder = Integer.parseInt(getSingleValue(sql, parma).toString());
		
		System.out.println(maxOrder);
		
		if (null == maxOrder) {
			maxOrder = 0;
		} else {
			maxOrder = maxOrder + 1;
		}
		return maxOrder;
	}
	
	@Override
	public String addLaw(DxyjLaw law) {
		Integer nodeClass = this.getNodeClass(law.getParentID());
		String sql = " insert into sjzs_dxyj (id,nodeType,caption,nodeClass,parentID,department,lawNo,tiao,kuan,lawContent,writeDate,updatedate,deleteflag,note1,note2 ) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[]{
				law.getId(),
				"1",
				law.getCaption(),
				nodeClass,
				law.getParentID(),
				law.getDepartment(),
				law.getLawNo(),
				law.getTiao(),
				law.getKuan(),
				law.getLawContent(),
				law.getWriteDate(),
				new Timestamp(System.currentTimeMillis()),
				0,
				law.getNote1(),
				law.getNote2()
				
		};
		int result = update(sql, params);
		
		return result>0?law.getId():null;
	}

	@Override
	public int updateLaw(DxyjLaw law) {
		String sql = "update sjzs_dxyj set caption = ?,department = ?,lawNo = ?,tiao = ?,kuan = ?,lawContent = ? ,writeDate = ?, updatedate=? ,deleteflag=?,note1=?,note2=? where id = ?";
		Object [] params = new Object[]{
				law.getCaption(),
				law.getDepartment(),
				law.getLawNo(),
				law.getTiao(),
				law.getKuan(),
				law.getLawContent(),
				law.getWriteDate(),
				new Timestamp(System.currentTimeMillis()),
				1,
				law.getNote1(),
				law.getNote2(),
				law.getId()
		};
		return this.update(sql, params);
	}

	@Override
	public int deleteLaw(String Id) {
		StringBuffer sql = new StringBuffer();
//		sql.append("delete from sjzs_dxyj where Id=? ");
		sql.append("update sjzs_dxyj set deleteflag = ? ,updatedate=? where Id = ?");
		Object[] params = new Object[]{-1, new Timestamp(System.currentTimeMillis()), Id};
		Integer result = update(sql.toString(), params);
		if (null == result) {
			result = 0;
		}
		return result;
	}

	public Page<DxyjLaw> searchByCondition(Page<DxyjLaw> page, String fields,
			String sqlCondition, Object[] queryParams, LinkedHashMap<String, String> orderby,String parentID) {
		List<DxyjLaw> laws = new ArrayList<DxyjLaw>();//封装结果集
		Page<DxyjLaw> ppage = new Page<DxyjLaw>();//如果page为null时使用
		Integer totalRecords = 0;//查询的总记录数
		String sql = "";//查询的sql语句
		String countSql = "";//查询总记录数的sql语句
		String childListStr = this.getChileList(parentID);
		sqlCondition = ("".equals(sqlCondition) || null == sqlCondition) ? " " : sqlCondition;
		countSql = "select count(id) from sjzs_dxyj " + sqlCondition +" and FIND_IN_SET(parentID, '" + childListStr + "') and deleteflag in (0,1) ";
		totalRecords = Integer.valueOf(getSingleValue(countSql, queryParams).toString());
		
		if ( page != null ) {//如果是分页查询
			sql = "select " + fields + "  from sjzs_dxyj " +sqlCondition +" and FIND_IN_SET(parentID, '" + childListStr + "') and deleteflag in (0,1) "+ BaseDao.buildOrderBy(orderby)+
			"limit "+(page.getCurPage() - 1) * page.getMaxResult()+","+page.getMaxResult() ;
		
			ppage = page;			
			laws = query(sql, DxyjLaw.class, queryParams);
		} else {//如果不需要分页查询
			sql = "select " + fields + " from sjzs_dxyj " + sqlCondition +" and FIND_IN_SET(parentID, '" + childListStr + "')) and deleteflag in (0,1) "+ buildOrderBy(orderby);
			laws = query(sql, DxyjLaw.class, queryParams);
		}
		
		ppage.setTotalRecords(totalRecords);
		ppage.setList(laws);		
		return ppage;
	}
}
