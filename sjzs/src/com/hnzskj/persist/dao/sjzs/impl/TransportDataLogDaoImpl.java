package com.hnzskj.persist.dao.sjzs.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hnzskj.common.BaseDao;
import com.hnzskj.persist.bean.sjzs.DataTransportDTO;
import com.hnzskj.persist.dao.sjzs.TransportDataLogDao;


public class TransportDataLogDaoImpl extends BaseDao implements TransportDataLogDao {
	private static Logger logger = Logger.getLogger(TransportDataLogDaoImpl.class);

	@Override
	public int batchAddLog(List<DataTransportDTO> logList) {
		String sql = "insert into sjzs_data_transport (id,infoId,attachId,clientName,clientId,transUserId," +
				     "transUserName,transDate,flag,type,note1,note2,note3,note4,note5) values " +
				     "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[][] params = new Object[logList.size()][];
		for(int i=0;i<logList.size();i++){
			DataTransportDTO log = logList.get(i);
			Object[] temp = {log.getId()==null?this.getGUID():log.getId(),log.getInfoId(),log.getAttachId(),log.getClientName(),log.getClientId(),
					         log.getTransUserId(),log.getTransUserName(),new Timestamp(System.currentTimeMillis()),
					         log.getFlag(),log.getType(),log.getNote1(),log.getNote2(),log.getNote3(),log.getNote4(),
					         log.getNote5()};
			params[i] = temp;
		}
		return this.updateBatch(sql, params, logList.size());
	}

   
	@Override
	public int addLog(DataTransportDTO dataTransportDTO) {
		StringBuffer buffer = new StringBuffer();

		buffer.append(" insert into sjzs_data_transport(");
		buffer.append("id,");
		buffer.append("infoId,");
		buffer.append("attachId,");
		buffer.append("clientName,");
		buffer.append("clientId,");
		buffer.append("transUserId,");
		buffer.append("transUserName,");
		buffer.append("transDate,");
		buffer.append("flag,");
		buffer.append("type,");
		buffer.append("note1,");
		buffer.append("note2,");
		buffer.append("note3,");
		buffer.append("note4,");
		buffer.append("note5 ");
		buffer.append(") ");
		buffer.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		 if(logger.isDebugEnabled()){
	            StringBuffer debugBuffer =  new StringBuffer(buffer.length()*4);
	            debugBuffer.append(buffer);
	            debugBuffer.append("VALUES(");
	            debugBuffer.append(" ").append(dataTransportDTO.getId()).append(",");
	            debugBuffer.append("'").append(dataTransportDTO.getInfoId()).append("',");
	            debugBuffer.append("'").append(dataTransportDTO.getAttachId()).append("',");
	            debugBuffer.append("'").append(dataTransportDTO.getClientName()).append("',");
	            debugBuffer.append("'").append(dataTransportDTO.getClientId()).append("',");
	            debugBuffer.append("'").append(dataTransportDTO.getTransUserId()).append("',");
	            debugBuffer.append("'").append(dataTransportDTO.getTransUserName()).append("',");
	            debugBuffer.append("'").append(dataTransportDTO.getTransDate()).append("',");
	            debugBuffer.append("'").append(dataTransportDTO.getFlag()).append("',");
	            debugBuffer.append("'").append(dataTransportDTO.getType()).append("',");
	            debugBuffer.append("'").append(dataTransportDTO.getFlag()).append("')");
	            debugBuffer.append("'").append(dataTransportDTO.getNote1()).append("')");
	            debugBuffer.append("'").append(dataTransportDTO.getNote2()).append("')");
	            debugBuffer.append("'").append(dataTransportDTO.getNote3()).append("')");
	            debugBuffer.append("'").append(dataTransportDTO.getNote4()).append("')");
	            debugBuffer.append("'").append(dataTransportDTO.getNote5()).append("')");
	            logger.debug(debugBuffer.toString());
	        };
		 Object[] params = {
				 dataTransportDTO.getId(),
				 dataTransportDTO.getInfoId(),
				 dataTransportDTO.getAttachId(),
				 dataTransportDTO.getClientName(),
				 dataTransportDTO.getClientId(),
				 dataTransportDTO.getTransUserId(),
				 dataTransportDTO.getTransUserName(),
				 dataTransportDTO.getTransDate(),
				 dataTransportDTO.getFlag(),
				 dataTransportDTO.getType(),
				 dataTransportDTO.getFlag(),
				 dataTransportDTO.getNote1(),
				 dataTransportDTO.getNote2(),
				 dataTransportDTO.getNote3(),
				 dataTransportDTO.getNote4(),
				 dataTransportDTO.getNote5()
		 };
	       
			int result = this.update(buffer.toString(), params);
			return result;
	}

	@Override
	public int deleteBycondition(String whereSql) {
		StringBuffer buffer = new StringBuffer(100);
		buffer.append("DELETE FROM sjzs_data_transport WHERE ");
		buffer.append(whereSql);
		if (logger.isDebugEnabled()) {
			logger.debug(buffer.toString());
		}
		int count = this.update(buffer.toString(), null);
		return count;
	}

	@Override
	public ArrayList<DataTransportDTO> findByClientId(String clientId) {
		StringBuffer buffer = new StringBuffer(200);
        buffer.append("SELECT ");
		buffer.append("id,");
		buffer.append("infoId,");
		buffer.append("attachId,");
		buffer.append("clientName,");
		buffer.append("clientId,");
		buffer.append("transUserId,");
		buffer.append("transUserName,");
		buffer.append("transDate,");
		buffer.append("flag,");
		buffer.append("type,");
		buffer.append("note1,");
		buffer.append("note2,");
		buffer.append("note3,");
		buffer.append("note4,");
		buffer.append("note5 ");
        buffer.append("FROM sjzs_data_transport WHERE clientId =? ");
        if(logger.isDebugEnabled()){
            logger.debug(buffer.toString());
        }
        
        ArrayList<DataTransportDTO>  list = (ArrayList<DataTransportDTO>)query(buffer.toString(), DataTransportDTO.class, new Object[]{clientId});
       
        return list;
	}

	@Override
	public ArrayList<DataTransportDTO> findByCondition(String whereSql) {
		StringBuffer buffer = new StringBuffer(200);
        buffer.append("SELECT ");
		buffer.append("id,");
		buffer.append("infoId,");
		buffer.append("attachId,");
		buffer.append("clientName,");
		buffer.append("clientId,");
		buffer.append("transUserId,");
		buffer.append("transUserName,");
		buffer.append("transDate,");
		buffer.append("flag,");
		buffer.append("type,");
		buffer.append("note1,");
		buffer.append("note2,");
		buffer.append("note3,");
		buffer.append("note4,");
		buffer.append("note5 ");
        buffer.append("FROM sjzs_data_transport WHERE  ");
        buffer.append(whereSql);
        if(logger.isDebugEnabled()){
            logger.debug(buffer.toString());
        }
        
        ArrayList<DataTransportDTO>  list = (ArrayList<DataTransportDTO>)query(buffer.toString(), DataTransportDTO.class, null);
       
        return list;
	}

	@Override
	public ArrayList<DataTransportDTO> findByInfoId(String infoId) {
		StringBuffer buffer = new StringBuffer(200);
        buffer.append("SELECT ");
		buffer.append("id,");
		buffer.append("infoId,");
		buffer.append("attachId,");
		buffer.append("clientName,");
		buffer.append("clientId,");
		buffer.append("transUserId,");
		buffer.append("transUserName,");
		buffer.append("transDate,");
		buffer.append("flag,");
		buffer.append("type,");
		buffer.append("note1,");
		buffer.append("note2,");
		buffer.append("note3,");
		buffer.append("note4,");
		buffer.append("note5 ");
        buffer.append("FROM sjzs_data_transport WHERE infoId =? ");
        if(logger.isDebugEnabled()){
            logger.debug(buffer.toString());
        }
        
        ArrayList<DataTransportDTO>  list = (ArrayList<DataTransportDTO>)query(buffer.toString(), DataTransportDTO.class, new Object[]{infoId});
       
        return list;
	}

	@Override
	public DataTransportDTO findByPrimaryId(String primaryId) {
		StringBuffer buffer = new StringBuffer(200);
        buffer.append("SELECT ");
		buffer.append("id,");
		buffer.append("infoId,");
		buffer.append("attachId,");
		buffer.append("clientName,");
		buffer.append("clientId,");
		buffer.append("transUserId,");
		buffer.append("transUserName,");
		buffer.append("transDate,");
		buffer.append("flag,");
		buffer.append("type,");
		buffer.append("note1,");
		buffer.append("note2,");
		buffer.append("note3,");
		buffer.append("note4,");
		buffer.append("note5 ");
        buffer.append("FROM sjzs_data_transport WHERE  id = ? ");
        if(logger.isDebugEnabled()){
            logger.debug(buffer.toString());
        }
        
        DataTransportDTO  dataTransportDTO = (DataTransportDTO) this.get(buffer.toString(), DataTransportDTO.class ,new Object[]{primaryId});
       
        return dataTransportDTO;
	}

	@Override
	public ArrayList<DataTransportDTO> findByTransUserId(String transUserId) {
		StringBuffer buffer = new StringBuffer(200);
        buffer.append("SELECT ");
		buffer.append("id,");
		buffer.append("infoId,");
		buffer.append("attachId,");
		buffer.append("clientName,");
		buffer.append("clientId,");
		buffer.append("transUserId,");
		buffer.append("transUserName,");
		buffer.append("transDate,");
		buffer.append("flag,");
		buffer.append("type,");
		buffer.append("note1,");
		buffer.append("note2,");
		buffer.append("note3,");
		buffer.append("note4,");
		buffer.append("note5 ");
        buffer.append("FROM sjzs_data_transport WHERE transUserId =? ");
        if(logger.isDebugEnabled()){
            logger.debug(buffer.toString());
        }
        
        ArrayList<DataTransportDTO>  list = (ArrayList<DataTransportDTO>)query(buffer.toString(), DataTransportDTO.class, new Object[]{transUserId});
       
        return list;
	}
}
