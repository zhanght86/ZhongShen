package com.hnzskj.service.sjzs;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.sjzs.FangFaAnLi;

public interface FangFaAnLiService {

	Page<FangFaAnLi> searchFFALByParentId(FangFaAnLi ffal, Page<FangFaAnLi> page);

	FangFaAnLi getFFALById(String id);

	String addFFAL(FangFaAnLi ffal);

	int updateFFAL(FangFaAnLi ffal);

	int deleteFFAL(String id);

	Page<FangFaAnLi> searchByCondition(String string, FangFaAnLi ffal,
			Page<FangFaAnLi> page);

}
