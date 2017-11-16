package com.hnzskj.service.sjzs;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.sjzs.DxyjLaw;

public interface DingXingYiJuService {

	public Page<DxyjLaw> searchLawByParentId(DxyjLaw law, Page<DxyjLaw> page);
	
	public Page<DxyjLaw> searchByDepartment(DxyjLaw law, Page<DxyjLaw> page);
	
	public DxyjLaw getLawById(String id);

	public String addLaw(DxyjLaw law);

	public int updateLaw(DxyjLaw law);

	public int deleteLaw(String id);
	
	public String getConByHtml(String htmlCon);

	public Page<DxyjLaw> searchByCondition(String string, DxyjLaw law,
			Page<DxyjLaw> page);

}
