package com.hnzskj.service.sjzs;

import com.hnzskj.persist.bean.sjzs.UpdateDataLog;

public interface UpdateDataLogService {
	public int deleteByName(String name);
	public UpdateDataLog selectNewVersionByType(int type);
	
	public boolean addUpdateDataLog(UpdateDataLog updateDataLog);
	/**
	 * 
	 * 描述：获取最近一次更新的时间<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-5-3 下午06:14:37 <br/>  
	 * @version   1.0
	 */
	public UpdateDataLog findLatestUpdateTime();
}
