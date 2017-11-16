package com.hnzskj.persist.dao.sjzs;

import java.util.LinkedHashMap;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.sjzs.FangFaAnLi;

public interface FangFaAnLiDao {

	Page<FangFaAnLi> searchFfalSByParentId(Page<FangFaAnLi> page,
			String fields, String sqlCondition, Object[] params,
			LinkedHashMap<String, String> orderBy);

	FangFaAnLi getFfalById(String lawId);

	String addFFAL(FangFaAnLi ffal);

	int updateFFAL(FangFaAnLi ffal);

	int deleteFFAL(String id);

	Page<FangFaAnLi> searchByCondition(Page<FangFaAnLi> page, String fields,
			String string, Object[] array, LinkedHashMap<String, String> orderBy);
	
	/**
	 * 
	 * 方法描述: 批量删除方法案例  <br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-5-6 上午09:26:32
	 * @param ids id数组  以 "id,id..."格式拼接
	 * @param phDel 是否物理删除  true-物理删除
	 *
	 */
	public int batchDeleteFFAL(String ids, boolean phDel);

}
