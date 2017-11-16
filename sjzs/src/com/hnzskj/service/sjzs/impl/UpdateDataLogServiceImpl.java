package com.hnzskj.service.sjzs.impl;

import com.hnzskj.persist.bean.sjzs.UpdateDataLog;
import com.hnzskj.persist.dao.sjzs.UpdateDataLogDao;
import com.hnzskj.service.sjzs.UpdateDataLogService;

public class UpdateDataLogServiceImpl implements UpdateDataLogService  {

	private UpdateDataLogDao updateDataLogDao = null;
	
	/**
	 * @return the updateDataLogDao
	 */
	public UpdateDataLogDao getUpdateDataLogDao() {
		return updateDataLogDao;
	}


	/**
	 * @param updateDataLogDao the updateDataLogDao to set
	 */
	public void setUpdateDataLogDao(UpdateDataLogDao updateDataLogDao) {
		this.updateDataLogDao = updateDataLogDao;
	}




	public int deleteByName(String name) {
		// TODO Auto-generated method stub
		return updateDataLogDao.deleteByName(name);
	}


	@Override
	public UpdateDataLog selectNewVersionByType(int type) {
		return this.updateDataLogDao.selectNewVersionByType(type);
	}


	/* (non-Javadoc)
	 * @see com.hnzskj.service.sjzs.UpdateDataLogService#addUpdateDataLog(com.hnzskj.persist.bean.sjzs.UpdateDataLog)
	 */
	@Override
	public boolean addUpdateDataLog(UpdateDataLog updateDataLog) {
		// TODO Auto-generated method stub
		int result = updateDataLogDao.addUpdateDataLog(updateDataLog);
		return result>0;
	}


	@Override
	public UpdateDataLog findLatestUpdateTime() {
		UpdateDataLog updateDataLog = this.updateDataLogDao.findLatestUpdateTime();
		return updateDataLog;
	}
}
