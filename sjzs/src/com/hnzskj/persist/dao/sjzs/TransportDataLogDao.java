package com.hnzskj.persist.dao.sjzs;

import java.util.ArrayList;
import java.util.List;

import com.hnzskj.persist.bean.sjzs.DataTransportDTO;

public interface TransportDataLogDao {
	
	public int addLog(DataTransportDTO log);
	
	public int batchAddLog(List<DataTransportDTO> logList);
	
	public int deleteBycondition(String whereSql);

	public ArrayList<DataTransportDTO> findByClientId(String clientId);

	public ArrayList<DataTransportDTO> findByCondition(String whereSql);

	public ArrayList<DataTransportDTO> findByInfoId(String infoId);

	public DataTransportDTO findByPrimaryId(String primaryId);

	public ArrayList<DataTransportDTO> findByTransUserId(String transUserId);
	
}
