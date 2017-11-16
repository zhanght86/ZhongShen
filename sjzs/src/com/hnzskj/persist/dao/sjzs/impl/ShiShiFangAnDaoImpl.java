package com.hnzskj.persist.dao.sjzs.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.sjzs.ShiShiFangAnDTO;
import com.hnzskj.persist.dao.sjzs.ShiShiFangAnDao;

public class ShiShiFangAnDaoImpl extends BaseDao implements ShiShiFangAnDao {


	@Override
	public String addSSFA(ShiShiFangAnDTO shiShiFangAn) {
		String sql = " insert into sjzs_ssfn (id,name,industry,attachid,keyword,sort,writedate,author,updatedate,deleteflag,note1,note2) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[]{
				shiShiFangAn.getId(),
				shiShiFangAn.getName(),
				shiShiFangAn.getIndustry(),
				shiShiFangAn.getAttachId(),
				shiShiFangAn.getKeyWord(),
				shiShiFangAn.getSort(),
				shiShiFangAn.getWriteDate(),
				shiShiFangAn.getAuthor(),
				new Timestamp(System.currentTimeMillis()),
				0,
				shiShiFangAn.getNote1(),
				shiShiFangAn.getNote2()
		};
		int result = update(sql, params);
		
		return result>0?shiShiFangAn.getId():null;
	}

	@Override
	public int deleteSSFA(String id) {
		StringBuffer sql = new StringBuffer();
		sql.append("update sjzs_ssfn set deleteflag = ? ,updatedate=? where id = ? ");
		Object[] params = new Object[]{-1,new Timestamp(System.currentTimeMillis()),id};
		Integer result = update(sql.toString(), params);
		if (null == result) {
			result = 0;
		}
		return result;
	}

	@Override
	public ShiShiFangAnDTO getSSFAById(String id) {
		String sql = "select id,name,industry,attachid,keyword,sort,writedate,author from sjzs_ssfn where id = ?";
		ShiShiFangAnDTO shiShiFangAn = (ShiShiFangAnDTO)this.get(sql, ShiShiFangAnDTO.class, new Object[]{id});
		return shiShiFangAn;
	}
	@Override
	public int updateSSFA(ShiShiFangAnDTO shiShiFangAn) {
		String sql = "update sjzs_ssfn set name = ?,industry = ?,attachid = ?,keyword = ?,sort = ?,writedate = ?,author = ?,updatedate = ?,deleteflag = ?,note1 = ?,note2 = ?  where id = ?";
		Object [] params = new Object[]{
				shiShiFangAn.getName(),
				shiShiFangAn.getIndustry(),
				shiShiFangAn.getAttachId(),
				shiShiFangAn.getKeyWord(),
				shiShiFangAn.getSort(),
				shiShiFangAn.getWriteDate(),
				shiShiFangAn.getAuthor(),
				new Timestamp(System.currentTimeMillis()),
				1,
				shiShiFangAn.getNote1(),
				shiShiFangAn.getNote2(),
				shiShiFangAn.getId()
		};
		return this.update(sql, params);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Page<ShiShiFangAnDTO> searchByCondition(Page<ShiShiFangAnDTO> page, String fields, String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {
		List<ShiShiFangAnDTO> ffals = new ArrayList<ShiShiFangAnDTO>();//封装结果集
		Page<ShiShiFangAnDTO> ppage = new Page<ShiShiFangAnDTO>();//如果page为null时使用
		Integer totalRecords = 0;//查询的总记录数
		String sql = "";//查询的sql语句
		String countSql = "";//查询总记录数的sql语句
		String childListStr = this.getChileList(queryParams[0].toString());
		sqlCondition = ("".equals(sqlCondition) || null == sqlCondition) ? " " : sqlCondition;
		countSql = "select count(id) from sjzs_ssfn " + sqlCondition +" and FIND_IN_SET(sort, '" + childListStr + "') and deleteflag in (0,1) ";
		totalRecords = Integer.valueOf(getSingleValue(countSql, null).toString());
		
		if ( page != null ) {//如果是分页查询
			sql = "select " + fields + "  from sjzs_ssfn " +sqlCondition +" and FIND_IN_SET(sort, '" + childListStr + "')  and deleteflag in (0,1) "+ BaseDao.buildOrderBy(orderby)+
			"limit "+(page.getCurPage() - 1) * page.getMaxResult()+","+page.getMaxResult() ;
		
			ppage = page;			
			ffals = query(sql, ShiShiFangAnDTO.class, null);
		} else {//如果不需要分页查询
			sql = "select " + fields + " from sjzs_ssfn " + sqlCondition +" and FIND_IN_SET(sort, '" + childListStr + "')  and deleteflag in (0,1) "+ buildOrderBy(orderby);
			ffals = query(sql, ShiShiFangAnDTO.class, null);
		}
		
		ppage.setTotalRecords(totalRecords);
		ppage.setList(ffals);		
		return ppage;
      
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<ShiShiFangAnDTO> searchSSFAByParentId( Page<ShiShiFangAnDTO> page, String fields, String sqlCondition,Object[] queryParams, LinkedHashMap<String, String> orderby) {
		    
		    StringBuilder sql = new StringBuilder();
			
			List<ShiShiFangAnDTO> ssfnList = new ArrayList<ShiShiFangAnDTO>();//封装查询结果集
			Integer totalRecords = 0;//记录查询的总记录数
			String countSql = "";//查询总记录数的sql语句
			String newSql = "";//查询的sql语句
			Page<ShiShiFangAnDTO> epage = new Page<ShiShiFangAnDTO>();//临时变量，如果在page为null的情况下使用
			sqlCondition =  ("".equals(sqlCondition) || null == sqlCondition) ? " " : sqlCondition;
			countSql =sql.toString()+ "select count(id) from sjzs_ssfn where deleteflag in(0,1) and FIND_IN_SET(Sort, getChildList(?)) " + sqlCondition;
			//查询总记录数
			totalRecords = Integer.parseInt( this.getSingleValue(countSql, queryParams).toString());
			if ( page != null) {//如果需要分页
				//先执行分页子句的查询结果
				String tempSql = sql.toString()+"select id from sjzs_ssfn where deleteflag in(0,1) and FIND_IN_SET(id, getChildList(?))  "+BaseDao.buildOrderBy(orderby)+" limit 0, " + (page.getCurPage() - 1) * page.getMaxResult();
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
				newSql = sql.toString()+ "select * from sjzs_ssfn where deleteflag in(0,1) and FIND_IN_SET(sort, getChildList(?)) ";
					newSql += " and id not in ("+idBuffer.toString()+")"+ BaseDao.buildOrderBy(orderby) +"  limit 0," + page.getMaxResult() ;
				epage = page;
				//查询结果集
				ssfnList =this.query(newSql, ShiShiFangAnDTO.class, queryParams);
			} else {//如果不需要分页
				newSql = sql.toString()+"select * from sjzs_ssfn where deleteflag in(0,1) and FIND_IN_SET(id, getChildList(?)) "+ sqlCondition + BaseDao.buildOrderBy(orderby);
				//查询结果集
				ssfnList = this.query(newSql, ShiShiFangAnDTO.class, queryParams);
			}
			
			//设置总记录数
			epage.setList(ssfnList);
			//设置结果集
			epage.setTotalRecords(totalRecords);
			return epage;
	}
	
	@Override
	public int batchDeleteSSFA(String ids, boolean phDel) {
		String sql = "";
		if(phDel){
			sql = "delete from sjzs_ssfn where id in (" + buildIds(ids) + ")";
		}else{
			sql = "update sjzs_ssfn set deleteflag = -1, updatedate = '" + new Timestamp(System.currentTimeMillis()) + "' where id in (" + buildIds(ids) + ")";
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
