package com.hnzskj.persist.dao.sjzs.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.sjzs.CheckLogDTO;
import com.hnzskj.persist.dao.sjzs.CheckLogDao;

public class CheckLogDaoImpl extends BaseDao implements CheckLogDao {

	@Override
	public int addCheckLog(CheckLogDTO checkLog) {
		String sql = "insert into sjzs_checklog (id,checkUserId,checkUserName,clientId,clientName," +
					 "infoId,infoName,attachId,updateDate,checkLog,checkResult,isTransport) values (?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {
						   this.getGUID(),checkLog.getCheckUserId(),checkLog.getCheckUserName(),
						   checkLog.getClientId(),checkLog.getClientName(),checkLog.getInfoId(),checkLog.getInfoName(),
						   checkLog.getAttachId(),new Timestamp(System.currentTimeMillis()),checkLog.getCheckLog(),
						   checkLog.getCheckResult(),checkLog.getIsTransport()
						   };
		return this.update(sql, params);
	}

	@Override
	public int deleteCheckLog(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CheckLogDTO getCheckLogById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<CheckLogDTO> searchByCondition(Page<CheckLogDTO> page,String fields, String condition, Object[] params,
			LinkedHashMap<String, String> orderby) {
		List<CheckLogDTO> checkLogs = new ArrayList<CheckLogDTO>();//封装结果集
		Page<CheckLogDTO> ppage = new Page<CheckLogDTO>();//如果page为null时使用
		Integer totalRecords = 0;//查询的总记录数
		String sql = "";//查询的sql语句
		String countSql = "";//查询总记录数的sql语句
		condition = ("".equals(condition) || null == condition) ? " " : condition;
		countSql = "select count(id) from sjzs_checklog " + condition ;
		
		totalRecords = Integer.valueOf(getSingleValue(countSql, null).toString());
		
		if ( page != null ) {//如果是分页查询
			sql = "select " + fields + " from sjzs_checklog " + condition + BaseDao.buildOrderBy(orderby)+
			"limit "+(page.getCurPage() - 1) * page.getMaxResult()+","+page.getMaxResult() ;
			ppage = page;			
			checkLogs = query(sql, CheckLogDTO.class, null);
		} else {//如果不需要分页查询
			sql = "select " + fields + " from sjzs_checklog " + condition + buildOrderBy(orderby);
			checkLogs = query(sql, CheckLogDTO.class, null);
		}
		
		ppage.setTotalRecords(totalRecords);
		ppage.setList(checkLogs);		
		return ppage;
	}

}
