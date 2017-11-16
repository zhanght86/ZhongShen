package com.hnzskj.service.sjzs.impl;

import java.util.ArrayList;
import java.util.List;

import com.hnzskj.persist.bean.sjzs.DataTransportDTO;
import com.hnzskj.persist.dao.sjzs.TransportDataLogDao;
import com.hnzskj.service.sjzs.TransportDataLogService;

public class TransportDataLogServiceImpl implements TransportDataLogService {

	private TransportDataLogDao transportDataLogDao = null;

	public TransportDataLogDao getDataTransportDao() {
		return transportDataLogDao;
	}

	public void setDataTransportDao(TransportDataLogDao dataTransportDao) {
		this.transportDataLogDao = dataTransportDao;
	}

	@Override
	public int addLog(DataTransportDTO dataTransportDTO) {
		int count = this.transportDataLogDao.addLog(dataTransportDTO);
		return count;
	}

	@Override
	public int deleteBycondition(String whereSql) {
		int count = this.transportDataLogDao.deleteBycondition(whereSql);
		return count;
	}

	@Override
	public ArrayList<DataTransportDTO> findByClientId(String clientId) {
		ArrayList<DataTransportDTO> list = this.transportDataLogDao.findByClientId(clientId);
		return list;
	}

	@Override
	public ArrayList<DataTransportDTO> findByCondition(String whereSql) {
		ArrayList<DataTransportDTO> list = this.transportDataLogDao.findByCondition(whereSql);
		return list;
	}

	@Override
	public ArrayList<DataTransportDTO> findByInfoId(String infoId) {
		ArrayList<DataTransportDTO> list = this.transportDataLogDao.findByInfoId(infoId);
		return list;
	}

	@Override
	public DataTransportDTO findByPrimaryId(String primaryId) {
		DataTransportDTO dataTransportDTO = this.transportDataLogDao.findByPrimaryId(primaryId);
		return dataTransportDTO;
	}

	@Override
	public ArrayList<DataTransportDTO> findByTransUserId(String transUserId) {
		ArrayList<DataTransportDTO> list = this.transportDataLogDao.findByTransUserId(transUserId);
		return list;
	}

	@Override
	public int batchAddLog(List<DataTransportDTO> logList) {
		return this.transportDataLogDao.batchAddLog(logList);
	}

}
