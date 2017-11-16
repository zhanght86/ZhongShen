package com.hnzskj.service.sjzs.impl;

import java.util.LinkedHashMap;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.sjzs.CheckLogDTO;
import com.hnzskj.persist.dao.sjzs.CheckLogDao;
import com.hnzskj.service.sjzs.CheckLogService;

public class CheckLogServiceImpl implements CheckLogService {
	private CheckLogDao checkLogDao;
	@Override
	public boolean addCheckLog(CheckLogDTO checkLog) {
		return checkLogDao.addCheckLog(checkLog)>0;
	}

	@Override
	public boolean deleteCheckLog(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CheckLogDTO getCheckLogById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<CheckLogDTO> searchByCondition(Page<CheckLogDTO> page, CheckLogDTO checkLog) {
		StringBuffer condition = new StringBuffer(" where 1 = 1 ");
		if(checkLog.getInfoId()!=null&&!"".equals(checkLog.getInfoId())){
			condition.append(" and infoId = '").append(checkLog.getInfoId()).append("'");
		}
		LinkedHashMap<String,String> orderby = new LinkedHashMap<String, String>();
		orderby.put("updateDate", "desc");
		return checkLogDao.searchByCondition(page, "*", condition.toString(), null, orderby);
	}

	public CheckLogDao getCheckLogDao() {
		return checkLogDao;
	}

	public void setCheckLogDao(CheckLogDao checkLogDao) {
		this.checkLogDao = checkLogDao;
	}

}
