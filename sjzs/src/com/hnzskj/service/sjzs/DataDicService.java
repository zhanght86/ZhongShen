package com.hnzskj.service.sjzs;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.sjzs.DataDicDTO;

public interface DataDicService {

	/**
	 * 
	 * 描述：根据ID查询<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-3-25 下午05:17:31 <br/>  
	 * @version   1.0
	 */
	public DataDicDTO getDataDicById(String id);

	/**
	 * 
	 * 描述：根据父级ID查询<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-3-25 下午05:17:31 <br/>  
	 * @version   1.0
	 */
	public Page<DataDicDTO> searchDataDicByParentId(DataDicDTO dataDicDTO, Page<DataDicDTO> page);
	/**
	 * 
	 * 描述：根据条件<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-3-25 下午05:17:31 <br/>  
	 * @version   1.0
	 */
	public Page<DataDicDTO> searchByCondition(String string, DataDicDTO dataDicDTO, Page<DataDicDTO> page);
	/**
	 * 
	 * 描述：添加<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-3-25 下午05:17:31 <br/>  
	 * @version   1.0
	 */
	public String addDataDic(DataDicDTO dataDicDTO);
	/**
	 * 
	 * 描述：更新<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-3-25 下午05:17:31 <br/>  
	 * @version   1.0
	 */
	public int updateDataDic(DataDicDTO dataDicDTO);
	/**
	 * 
	 * 描述：删除<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-3-25 下午05:17:31 <br/>  
	 * @version   1.0
	 */
	public int deleteDataDic(String string);

}
