package com.hnzskj.persist.dao.sjzs.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.sjzs.DataDicDTO;
import com.hnzskj.persist.dao.sjzs.DataDicDao;

public class DataDicDaoImpl extends BaseDao implements DataDicDao {

	@Override
	public String addDataDic(DataDicDTO dataDicDTO) {
		String sql = " insert into sjzs_datadic (dicId,dicName,dicParentId,industry,dicMemo,dicOrder,isDel,note1,note2,updatedate,deleteflag) values(?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[]{
				dataDicDTO.getDicId(),
				dataDicDTO.getDicName(),
				dataDicDTO.getDicParentId(),
				dataDicDTO.getIndustry(),
				dataDicDTO.getDicMemo(),
				dataDicDTO.getDicOrder(),
				dataDicDTO.getIsDel(),
				dataDicDTO.getNote1(),
				dataDicDTO.getNote2(),
				new Timestamp(System.currentTimeMillis()),
				0
		};
		int result = update(sql, params);
		
		return result>0?dataDicDTO.getDicId():null;
	}

	@Override
	public int deleteDataDic(String id) {
		StringBuffer sql = new StringBuffer();
		sql.append("update sjzs_datadic set deleteflag = ? ,updatedate=? where dicId = ? ");
		Object[] params = new Object[]{-1,new Timestamp(System.currentTimeMillis()),id};
		Integer result = update(sql.toString(), params);
		if (null == result) {
			result = 0;
		}
		return result;
	}

	@Override
	public DataDicDTO getDataDicById(String id) {
		String sql = "select dicId,dicName,dicParentId,dicMemo,industry,dicOrder,isDel,note1,note2,deleteflag,updatedate from sjzs_datadic where dicId = ?";
		DataDicDTO dataDicDTO = (DataDicDTO)this.get(sql, DataDicDTO.class, new Object[]{id});
		return dataDicDTO;
	}

	@Override
	public Page<DataDicDTO> searchByCondition(Page<DataDicDTO> page, String fields, String sqlCondition, Object[] queryParams, LinkedHashMap<String, String> orderby) {
		List<DataDicDTO> dataDicDTO = new ArrayList<DataDicDTO>();//封装结果集
		Page<DataDicDTO> ppage = new Page<DataDicDTO>();//如果page为null时使用
		Integer totalRecords = 0;//查询的总记录数
		String sql = "";//查询的sql语句
		String countSql = "";//查询总记录数的sql语句
		String childListStr = this.getChileList(queryParams[0].toString());
		sqlCondition = ("".equals(sqlCondition) || null == sqlCondition) ? " " : sqlCondition;
		countSql = "select count(dicId) from sjzs_datadic " + sqlCondition +" and FIND_IN_SET(dicParentId, '" + childListStr + "') and deleteflag in (0,1) ";
		totalRecords = Integer.valueOf(getSingleValue(countSql, null).toString());
		
		if ( page != null ) {//如果是分页查询
			sql = "select " + fields + "  from sjzs_datadic " +sqlCondition +" and FIND_IN_SET(dicParentId, '" + childListStr + "')  and deleteflag in (0,1) "+ BaseDao.buildOrderBy(orderby)+
			"limit "+(page.getCurPage() - 1) * page.getMaxResult()+","+page.getMaxResult() ;
		
			ppage = page;			
			dataDicDTO = query(sql, DataDicDTO.class, null);
		} else {//如果不需要分页查询
			sql = "select " + fields + " from sjzs_datadic " + sqlCondition +" and FIND_IN_SET(dicParentId, '" + childListStr + "')  and deleteflag in (0,1) "+ buildOrderBy(orderby);
			dataDicDTO = query(sql, DataDicDTO.class, null);
		}
		
		ppage.setTotalRecords(totalRecords);
		ppage.setList(dataDicDTO);		
		return ppage;
	}

	@Override
	public Page<DataDicDTO> searchDataDicByParentId(Page<DataDicDTO> page, String fields,
			String sqlCondition, Object[] queryParams, LinkedHashMap<String, String> orderby) {

		StringBuilder sql = new StringBuilder();
		
		List<DataDicDTO> dataDicDTO = new ArrayList<DataDicDTO>();//封装查询结果集
		Integer totalRecords = 0;//记录查询的总记录数
		String countSql = "";//查询总记录数的sql语句
		String newSql = "";//查询的sql语句
		Page<DataDicDTO> epage = new Page<DataDicDTO>();//临时变量，如果在page为null的情况下使用
		sqlCondition =  ("".equals(sqlCondition) || null == sqlCondition) ? " " : sqlCondition;
		countSql =sql.toString()+ "select count(dicId) from sjzs_datadic where deleteflag in(0,1) and FIND_IN_SET(dicParentId, getChildList(?)) " + sqlCondition;
		//查询总记录数
		totalRecords = Integer.parseInt( this.getSingleValue(countSql, queryParams).toString());
		if ( page != null) {//如果需要分页
			//先执行分页子句的查询结果
			String tempSql = sql.toString()+"select dicId from sjzs_datadic where deleteflag in(0,1) and FIND_IN_SET(dicId, getChildList(?))  "+BaseDao.buildOrderBy(orderby)+" limit 0, " + (page.getCurPage() - 1) * page.getMaxResult();
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
			newSql = sql.toString()+ "select * from sjzs_datadic where deleteflag in(0,1) and FIND_IN_SET(dicParentId, getChildList(?)) ";
				newSql += " and dicId not in ("+idBuffer.toString()+")"+ BaseDao.buildOrderBy(orderby) +"  limit 0," + page.getMaxResult() ;
			epage = page;
			//查询结果集
			dataDicDTO =this.query(newSql, DataDicDTO.class, queryParams);
		} else {//如果不需要分页
			newSql = sql.toString()+"select * from sjzs_datadic where deleteflag in(0,1) and FIND_IN_SET(dicId, getChildList(?)) "+ sqlCondition + BaseDao.buildOrderBy(orderby);
			//查询结果集
			dataDicDTO = this.query(newSql, DataDicDTO.class, queryParams);
		}
		
		//设置总记录数
		epage.setList(dataDicDTO);
		//设置结果集
		epage.setTotalRecords(totalRecords);
		return epage;
	}

	@Override
	public int updateDataDic(DataDicDTO dataDicDTO) {
		String sql = "update sjzs_datadic set dicName = ?,dicParentId = ?,dicMemo = ?,industry = ?,dicOrder = ?,isdel = ?,note1 = ? ,note2 = ?, updatedate=?,deleteflag = ?  where dicId = ?";
		Object [] params = new Object[]{
				dataDicDTO.getDicName(),
				dataDicDTO.getDicParentId(),
				dataDicDTO.getDicMemo(),
				dataDicDTO.getIndustry(),
				dataDicDTO.getDicOrder(),
				dataDicDTO.getIsDel(),
				dataDicDTO.getNote1(),
				dataDicDTO.getNote2(),
				new Timestamp(System.currentTimeMillis()),
				1,
				dataDicDTO.getDicId()
		};
		return this.update(sql, params);
	}


}
