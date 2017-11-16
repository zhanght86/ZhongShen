package com.hnzskj.service.sjzs;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.sjzs.Law;

public interface ShenJiFaGuiService {

	Page<Law> searchLawByParentId(Law law, Page<Law> page);

	Law getLawById(String lawId);

	String addLaw(Law law);

	int updateLaw(Law law);

	int deleteLaw(String lawId);

	Page<Law> searchByCondition(String fields, Law law, Page<Law> page);

}
