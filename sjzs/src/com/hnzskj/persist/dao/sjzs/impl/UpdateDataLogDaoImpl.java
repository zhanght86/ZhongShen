package com.hnzskj.persist.dao.sjzs.impl;

import java.sql.Timestamp;

import com.hnzskj.common.BaseDao;
import com.hnzskj.persist.bean.sjzs.UpdateDataLog;
import com.hnzskj.persist.dao.sjzs.UpdateDataLogDao;

public class UpdateDataLogDaoImpl extends BaseDao implements UpdateDataLogDao {

	@Override
	public int deleteByName(String name) {
		String sql = "delete from updatedatalog where fileName ='" + name
				+ "'";
		return this.update(sql, null);
	}

	@Override
	public UpdateDataLog selectNewVersionByType(int type) {
		// TODO Auto-generated method stub
		String  sqlString = " select fileName,oldVersion,newVersion,uploadDate,updatedate from updatedatalog where type = ?  order by newVersion desc limit 0,1 ";
		UpdateDataLog updateDataLog = (UpdateDataLog) this.get(sqlString, UpdateDataLog.class, new Object[]{type});
		return updateDataLog;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.sjzs.UpdateDataLogDao#addUpdateDataLog(com.hnzskj.persist.bean.sjzs.UpdateDataLog)
	 */
	@Override
	public int addUpdateDataLog(UpdateDataLog updateDataLog) {
		String sql = "insert into updatedatalog(filename,oldversion,newversion,type,uploaddate,updatedate) values (?,?,?,?,?,?)";
		Object[] params = new Object[]{
				updateDataLog.getFileName(),
				updateDataLog.getOldVersion(),
				updateDataLog.getNewVersion(),
				updateDataLog.getType(),
				new Timestamp(System.currentTimeMillis()),
				new Timestamp(System.currentTimeMillis())
		};
		int result = update(sql, params);
		return result;
	}

	@Override
	public UpdateDataLog findLatestUpdateTime() {
		String  sqlString = " select fileName,oldVersion,newVersion,uploadDate,updatedate from updatedatalog  order by uploadDate desc limit 0,1 ";
		UpdateDataLog updateDataLog = (UpdateDataLog) this.get(sqlString, UpdateDataLog.class,null);
		return updateDataLog;
	}
}
