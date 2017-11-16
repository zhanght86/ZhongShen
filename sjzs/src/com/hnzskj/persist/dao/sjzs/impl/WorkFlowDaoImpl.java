package com.hnzskj.persist.dao.sjzs.impl;

import java.io.Serializable;
import java.sql.Timestamp;

import com.hnzskj.common.BaseDao;
import com.hnzskj.persist.bean.sjzs.WorkFlowDTO;
import com.hnzskj.persist.dao.sjzs.WorkFlowDao;

public class WorkFlowDaoImpl extends BaseDao implements WorkFlowDao {

	@Override
	public int saveWorkXml(WorkFlowDTO workFlowDTO) {
		String sql = "";
		sql = "insert into workflowxml (id,textXml, updateDate, type, note1, note2,note3,deleteflag) values (?, ?, ?, ?, ?, ?,?,?)";
		Object[] params = { 
				workFlowDTO.getId(), 
				workFlowDTO.getTextXml(),
				new Timestamp(System.currentTimeMillis()),
				workFlowDTO.getType() ,
				workFlowDTO.getNote1(),
				workFlowDTO.getNote2(),
				workFlowDTO.getNote3(),
				0
		};
		int result = this.update(sql, params);
		return result;
	}

	@Override
	public int deleteWorkFlow(Serializable[] jmId) {
		String sql="update workflowxml set  updateDate=?, deleteflag = ? where id = ?";
		Object [] params ={
				new Timestamp(System.currentTimeMillis()),
				-1,
				jmId[0]
			};
		int result =this.update(sql, params);
			return result;
	}
	@Override
	public WorkFlowDTO findByType(String type) {
		String sql = "select * from workflowxml where type=? and deleteflag != -1";
		WorkFlowDTO workFlowDTO = (WorkFlowDTO) this.get(sql, WorkFlowDTO.class,
				new Object[] { type });
		return workFlowDTO;
	}
	@Override
	public WorkFlowDTO findById(String id) {
		String sql = "select * from workflowxml where id=? and deleteflag != -1";
		WorkFlowDTO workFlowDTO = (WorkFlowDTO) this.get(sql, WorkFlowDTO.class,
				new Object[] { id });
		return workFlowDTO;
	}

	@Override
	public int update(WorkFlowDTO workFlowDTO) {
		String sql="update workflowxml set  textXml=?, type=?, updatedate=?, note1=?, note2=?,note3=?, deleteflag = ? where id = ?";
		Object [] params ={
				workFlowDTO.getTextXml(),
				workFlowDTO.getType(),
				new Timestamp(System.currentTimeMillis()),
				workFlowDTO.getNote1(),
				workFlowDTO.getNote2(),
				workFlowDTO.getNote3(),
				1,
				workFlowDTO.getId()
			};
		int result =this.update(sql, params);
			return result;
	}

	@Override
	public int delWorkFlowBySjdhId(String sjdhId) {
		String sql="update workflowxml set updateDate=?, deleteflag = ? where type = ?";
		return this.update(sql, new Object[]{new Timestamp(System.currentTimeMillis()),-1,sjdhId});
	}

}
