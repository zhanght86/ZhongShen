package com.hnzskj.service.sjzs;

import java.util.ArrayList;
import java.util.List;

import com.hnzskj.persist.bean.sjzs.DataTransportDTO;

public interface TransportDataLogService {
	
	/**
	 * 
	 * 描述：根据迁移的记录ID查询<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-5-4 下午02:03:02 <br/>
	 * 
	 * @version 1.0
	 */
	public ArrayList<DataTransportDTO> findByInfoId(String infoId);

	/**
	 * 
	 * 描述：根据用户的ID查询<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-5-4 下午02:03:02 <br/>
	 * 
	 * @version 1.0
	 */
	public ArrayList<DataTransportDTO> findByClientId(String clientId);

	/**
	 * 
	 * 描述：根据审核人ID查询<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-5-4 下午02:03:02 <br/>
	 * 
	 * @version 1.0
	 */
	public ArrayList<DataTransportDTO> findByTransUserId(String transUserId);

	/**
	 * 
	 * 描述：主键查询<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-5-4 下午02:03:02 <br/>
	 * 
	 * @version 1.0
	 */
	public DataTransportDTO findByPrimaryId(String primaryId);

	/**
	 * 
	 * 描述：按条件查询<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-5-4 下午02:03:02 <br/>
	 * 
	 * @version 1.0
	 */
	public ArrayList<DataTransportDTO> findByCondition(String whereSql);

	/**
	 * 
	 * 描述：添加<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-5-4 下午02:03:02 <br/>
	 * 
	 * @version 1.0
	 */
	public int addLog(DataTransportDTO dataTransportDTO);

	/**
	 * 
	 * 描述：按条件删除<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-5-4 下午02:03:02 <br/>
	 * 
	 * @version 1.0
	 */
	public int deleteBycondition(String whereSql);
	
	public int batchAddLog(List<DataTransportDTO> logList);

}
