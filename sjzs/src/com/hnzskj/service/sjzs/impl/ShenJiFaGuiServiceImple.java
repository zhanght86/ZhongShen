package com.hnzskj.service.sjzs.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.Page;
import com.hnzskj.common.annotation.Description;
import com.hnzskj.persist.bean.sjzs.Law;
import com.hnzskj.persist.bean.system.Power;
import com.hnzskj.persist.dao.sjzs.ShenJiFaGuiDao;
import com.hnzskj.service.sjzs.ShenJiFaGuiService;

public class ShenJiFaGuiServiceImple  implements ShenJiFaGuiService{

	private ShenJiFaGuiDao shenJiFaGuiDao = null;
	//记录缩进数目
	private Integer count = new Integer(0);
	
	
	
	
	/**
	 * @return the shenJiFaGuiDao
	 */
	public ShenJiFaGuiDao getShenJiFaGuiDao() {
		return shenJiFaGuiDao;
	}

	/**
	 * @param shenJiFaGuiDao the shenJiFaGuiDao to set
	 */
	public void setShenJiFaGuiDao(ShenJiFaGuiDao shenJiFaGuiDao) {
		this.shenJiFaGuiDao = shenJiFaGuiDao;
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
	public Page<Law> searchLawByParentId(Law law, Page<Law> page) {
		Object[] params = new Object[]{law.getLawSort()};
		String fields = "lawId,lawName,lawNumberr,lawOrg,lawTrade,lawContent,lawDate,lawGrade,lawCategory,lawSort,lawSortName,attachId ";
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put("lawName", "asc");
		page = this.shenJiFaGuiDao.searchLawsByParentId(page, fields,null, params, orderBy);
		return page;
	}

	@Override
	public Law getLawById(String lawId) {
		Law law = this.shenJiFaGuiDao.getLawById(lawId);
		return law;
	}


	@Description("添加审计法规信息")
	public String addLaw(Law law) {
		String id = this.shenJiFaGuiDao.addLaw(law);
		return id;
	}


	@Override
	public int updateLaw(Law law) {
		int result = this.shenJiFaGuiDao.updateLaw(law);
		return result;
	}

	@Override
	public int deleteLaw(String id) {
		int result =  this.shenJiFaGuiDao.deleteLaw(id);
		return result;
	}

	@Override
	public Page<Law> searchByCondition(String fields, Law law, Page<Law> page) {
		StringBuffer sb = new StringBuffer(" where 1 = 1 ");
		List<Object> queryParams = new ArrayList<Object>();
		
		if (!"".equals(law.getLawName()) && null != law.getLawName()) {
			sb.append(" and lawName like '%" + law.getLawName() + "%' ");
//			queryParams.add("%" + law.getLawName() + "%");
		}
		
		if (!"".equals(law.getLawId()) && null != law.getLawId()) {
//			sb.append(" and lawId in (?) ");
//			queryParams.add(law.getLawId());
			sb.append(" and lawId = '" + law.getLawId() + "' ");
		}
		queryParams.add(law.getLawSort());
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put("lawName", "asc");
		page = shenJiFaGuiDao.searchByCondition(page, fields, sb.toString(), queryParams.toArray(), orderBy);
		return page;
	}
}
