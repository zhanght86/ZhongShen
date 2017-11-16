package com.hnzskj.service.sjzs;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.sjzs.CheckLogDTO;

public interface CheckLogService {
	
	public boolean addCheckLog(CheckLogDTO checkLog);
	
	public CheckLogDTO getCheckLogById(String id);
	
	public boolean deleteCheckLog(String id);
	
	public Page<CheckLogDTO> searchByCondition(Page<CheckLogDTO> page, CheckLogDTO checkLog);
}
