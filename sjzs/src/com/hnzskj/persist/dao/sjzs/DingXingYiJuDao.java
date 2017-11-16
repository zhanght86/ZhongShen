package com.hnzskj.persist.dao.sjzs;

import java.util.LinkedHashMap;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.sjzs.DxyjLaw;

public interface DingXingYiJuDao {

	Page<DxyjLaw> searchLawsByParentId(Page<DxyjLaw> page, String fields,
			String sqlString, Object[] params,
			LinkedHashMap<String, String> orderBy);
	
	Page<DxyjLaw> searchByDepartment(Page<DxyjLaw> page, String fields,
			String sqlString, Object[] params,
			LinkedHashMap<String, String> orderBy, String department);

	DxyjLaw getLawById(String lawId);

	String addLaw(DxyjLaw law);

	int updateLaw(DxyjLaw law);

	int deleteLaw(String id);

	Page<DxyjLaw> searchByCondition(Page<DxyjLaw> page, String fields, String string,
			Object[] array, LinkedHashMap<String, String> orderBy,String parentID);

}
