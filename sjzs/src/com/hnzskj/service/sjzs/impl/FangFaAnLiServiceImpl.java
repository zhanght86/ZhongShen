package com.hnzskj.service.sjzs.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.Page;
import com.hnzskj.common.annotation.Description;
import com.hnzskj.persist.bean.sjzs.FangFaAnLi;
import com.hnzskj.persist.dao.sjzs.FangFaAnLiDao;
import com.hnzskj.service.sjzs.FangFaAnLiService;

public class FangFaAnLiServiceImpl  implements FangFaAnLiService{

	private FangFaAnLiDao fangFaAnLiDao = null;
	//记录缩进数目
	private Integer count = new Integer(0);
	
	
	

	/**
	 * @return the fangFaAnLiDao
	 */
	public FangFaAnLiDao getFangFaAnLiDao() {
		return fangFaAnLiDao;
	}

	/**
	 * @param fangFaAnLiDao the fangFaAnLiDao to set
	 */
	public void setFangFaAnLiDao(FangFaAnLiDao fangFaAnLiDao) {
		this.fangFaAnLiDao = fangFaAnLiDao;
	}

	/**
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public Page<FangFaAnLi> searchFFALByParentId(FangFaAnLi ffal, Page<FangFaAnLi> page) {
		Object[] params = new Object[]{ffal.getSort()};
		String fields = "id,title,author,department,ffalDateTime,awards,attachId,sort ";
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put("title", "asc");
		page = this.fangFaAnLiDao.searchFfalSByParentId(page, fields,null, params, orderBy);
		return page;
	}

	@Override
	public FangFaAnLi getFFALById(String lawId) {
		FangFaAnLi ffal = this.fangFaAnLiDao.getFfalById(lawId);
		return ffal;
	}


	@Description("添加审计法规信息")
	public String addFFAL(FangFaAnLi ffal) {
		String id = this.fangFaAnLiDao.addFFAL(ffal);
		return id;
	}


	@Override
	public int updateFFAL(FangFaAnLi ffal) {
		int result = this.fangFaAnLiDao.updateFFAL(ffal);
		return result;
	}

	@Override
	public int deleteFFAL(String id) {
		int result =  this.fangFaAnLiDao.deleteFFAL(id);
		return result;
	}

	public Page<FangFaAnLi> searchByCondition(String fields, FangFaAnLi ffal, Page<FangFaAnLi> page) {
		StringBuffer sb = new StringBuffer(" where 1 = 1 ");
		List<Object> queryParams = new ArrayList<Object>();
		if (!"".equals(ffal.getTitle()) && null != ffal.getTitle()) {
			sb.append(" and title like '%" + ffal.getTitle() + "%' ");
//			queryParams.add("%" + ffal.getTitle() + "%");
		}
		
		if (!"".equals(ffal.getId()) && null != ffal.getId()) {
//			sb.append(" and id in( ? )");
//			queryParams.add(ffal.getId());
			sb.append(" and id = '"+ ffal.getId() +"' ");
		}
		queryParams.add(ffal.getSort());
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put("id", "asc");
		page = fangFaAnLiDao.searchByCondition(page, fields, sb.toString(), queryParams.toArray(), orderBy);
		return page;
	}

}
