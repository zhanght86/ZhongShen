package com.hnzskj.persist.dao.sjzs;

import java.util.LinkedHashMap;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.sjzs.Law;

public interface ShenJiFaGuiDao {

	Page<Law> searchLawsByParentId(Page<Law> page, String fields,
			String sqlCondition, Object[] params,
			LinkedHashMap<String, String> orderBy);

	Law getLawById(String lawId);

	String addLaw(Law law);

	int updateLaw(Law law);

	int deleteLaw(String id);

	Page<Law> searchByCondition(Page<Law> page, String fields, String string,
			Object[] array, LinkedHashMap<String, String> orderBy);
	
	/**
	 * 
	 * 方法描述: 批量删除审计法规  <br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-5-6 上午09:26:32
	 * @param ids id数组  以 "id,id..."格式拼接
	 * @param phDel 是否物理删除  true-物理删除
	 *
	 */
	public int batchDeleteLaw(String ids, boolean phDel);

}
