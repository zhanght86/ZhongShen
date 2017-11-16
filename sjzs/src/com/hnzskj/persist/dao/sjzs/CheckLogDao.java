package com.hnzskj.persist.dao.sjzs;

import java.util.LinkedHashMap;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.sjzs.CheckLogDTO;

public interface CheckLogDao {
	
	public int addCheckLog(CheckLogDTO checkLog);
	
	public CheckLogDTO getCheckLogById(String id);
	
	public int deleteCheckLog(String id);
	
	public Page<CheckLogDTO> searchByCondition(Page<CheckLogDTO> page, String fields, 
			String condition, Object[] params, LinkedHashMap<String, String> orderby);
}
