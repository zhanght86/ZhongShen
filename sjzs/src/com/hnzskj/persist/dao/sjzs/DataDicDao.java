package com.hnzskj.persist.dao.sjzs;

import java.util.LinkedHashMap;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.sjzs.DataDicDTO;

public interface DataDicDao {

	public String addDataDic(DataDicDTO dataDicDTO);

	public DataDicDTO getDataDicById(String id);

	public Page<DataDicDTO> searchDataDicByParentId(Page<DataDicDTO> page, String fields,
			String sqlCondition, Object[] queryParams, LinkedHashMap<String, String> orderby);

	public int updateDataDic(DataDicDTO dataDicDTO);

	public Page<DataDicDTO> searchByCondition(Page<DataDicDTO> page, String fields,
			String string, Object[] array, LinkedHashMap<String, String> orderBy);


	public int deleteDataDic(String id);

}
