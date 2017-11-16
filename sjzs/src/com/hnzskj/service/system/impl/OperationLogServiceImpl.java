package com.hnzskj.service.system.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.Page;
import com.hnzskj.common.annotation.Description;
import com.hnzskj.persist.bean.system.OperationLog;
import com.hnzskj.persist.dao.system.OperationLogDao;
import com.hnzskj.service.system.OperationLogService;

public class OperationLogServiceImpl implements OperationLogService{
	private OperationLogDao operationLogDao;
	
	public OperationLogDao getOperationLogDao() {
		return operationLogDao;
	}

	public void setOperationLogDao(OperationLogDao operationLogDao) {
		this.operationLogDao = operationLogDao;
	}

	@Override
	@Description("增加一个OperationLog实体")
	public boolean add(OperationLog operationLog) {
		int result = 0;
		result = operationLogDao.save(operationLog);
		return result == 1 ? true : false;
	}

	@Override
	@Description("根据指定的Id的删除一个OperationLog实体")
	public boolean delete(int id) {
		int result = 0;
		result = operationLogDao.delete(id);
		return result == 1 ? true : false;
	}

	@Override
	@Description("删除数组中所包含的id的实例")
	public boolean delete(Serializable... ids) {
		int result = 0;
		result = operationLogDao.delete(ids);
		return result == ids.length ? true : false;
	}

	@Override
	@Description("根据指定的id,查找OperationLog实体")
	public OperationLog findById(String id) {
		OperationLog operationLog = operationLogDao.getById(id);
		return operationLog;
	}

	@Override
	@Description("查询日志信息")
	public Page<OperationLog> searchOprLog(OperationLog operationLog,
			Page<OperationLog> page) {
		StringBuffer whereSQL = new StringBuffer(" where 1=1 ");
		List<Object> params = new ArrayList<Object>();//封装查询的字段所对应的值
		if ( null != operationLog.getOperator()
				&& !"".equals( operationLog.getOperator() )
				&& null != operationLog.getOperator() ) {
			whereSQL.append(" and operator like ? ");
			params.add("%" + operationLog.getOperator() + "%");
		}
		if (null != operationLog.getOprtime() ) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			whereSQL.append(" and oprtime >= ? and oprtime < ?");
			params.add(sdf.format( operationLog.getOprtime() ));
			long nextTime = operationLog.getOprtime().getTime() + 24 * 60 * 60 * 1000;
			params.add(sdf.format( new Date(nextTime) ));
		}
		if ( null != operationLog.getOperation() && !"".equals(operationLog.getOperation()) ) {
			whereSQL.append(" and operation like ? ");
			params.add("%" + operationLog.getOperation() + "%");
		}
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("oprtime", "desc");
		page = operationLogDao.searchOperationLog(page,"*" ,whereSQL.toString(), params.toArray(), orderby);
		return page;
	}
}
