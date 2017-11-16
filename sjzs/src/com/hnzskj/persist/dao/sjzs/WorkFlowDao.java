package com.hnzskj.persist.dao.sjzs;

import java.io.Serializable;

import com.hnzskj.persist.bean.sjzs.WorkFlowDTO;

public interface WorkFlowDao {

	public int saveWorkXml(WorkFlowDTO workFlowDTO);
	public WorkFlowDTO findById(String menuId);
	public int update(WorkFlowDTO workFlowDTO);
	public int deleteWorkFlow(Serializable[] jmId);
	public WorkFlowDTO findByType(String type);
	
	public int delWorkFlowBySjdhId(String sjdhId);

}
