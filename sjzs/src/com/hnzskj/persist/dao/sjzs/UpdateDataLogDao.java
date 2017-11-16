package com.hnzskj.persist.dao.sjzs;

import com.hnzskj.persist.bean.sjzs.UpdateDataLog;

public interface UpdateDataLogDao {
	
	public int deleteByName(String name);

	public UpdateDataLog selectNewVersionByType(int type);
	
	public int addUpdateDataLog(UpdateDataLog updateDataLog);

	public UpdateDataLog findLatestUpdateTime();

}
