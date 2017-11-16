package com.hnzskj.service.sjzs.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.jsoup.Jsoup;

import com.hnzskj.common.Page;
import com.hnzskj.common.annotation.Description;
import com.hnzskj.persist.bean.sjzs.DxyjLaw;
import com.hnzskj.persist.bean.sjzs.Law;
import com.hnzskj.persist.dao.sjzs.DingXingYiJuDao;
import com.hnzskj.service.sjzs.DingXingYiJuService;

public class DingXingYiJuServiceImple  implements DingXingYiJuService{

	private DingXingYiJuDao dingXingYiJuDao = null;
	//记录缩进数目
	private Integer count = new Integer(0);
	
	
	

	/**
	 * @return the dingXingYiJuDao
	 */
	public DingXingYiJuDao getDingXingYiJuDao() {
		return dingXingYiJuDao;
	}

	/**
	 * @param dingXingYiJuDao the dingXingYiJuDao to set
	 */
	public void setDingXingYiJuDao(DingXingYiJuDao dingXingYiJuDao) {
		this.dingXingYiJuDao = dingXingYiJuDao;
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
	public Page<DxyjLaw> searchLawByParentId(DxyjLaw law, Page<DxyjLaw> page) {
		Object[] params = new Object[]{law.getParentID()};
		String fields = "id,caption,isShow,isRead,nodeClass,nodeType,parentID,department,lawNo,tiao,kuan,lawContent,WriteDate ";
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put("caption", "asc");
		page = dingXingYiJuDao.searchLawsByParentId(page, fields,null, params, orderBy);
		return page;
	}
	
	public Page<DxyjLaw> searchByDepartment(DxyjLaw law, Page<DxyjLaw> page) {
		Object[] params = new Object[]{law.getParentID()};
		String fields = "id,caption,isShow,isRead,nodeClass,nodeType,parentID,department,lawNo,tiao,kuan,lawContent,WriteDate ";
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put("caption", "asc");
		page = dingXingYiJuDao.searchByDepartment(page, fields,null, params, orderBy, law.getDepartment() );
		return page;
	}
	
	public Page<DxyjLaw> searchByCondition(String fields, DxyjLaw law, Page<DxyjLaw> page) {
		StringBuffer sb = new StringBuffer(" where 1 = 1 ");
		List<Object> queryParams = new ArrayList<Object>();
		
		if (!"".equals(law.getCaption()) && null != law.getCaption()) {
			sb.append(" and caption like ? ");
			queryParams.add("%" + law.getCaption() + "%");
		}
		
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put("caption", "asc");
		page = dingXingYiJuDao.searchByCondition(page, fields, sb.toString(), queryParams.toArray(), orderBy,law.getParentID());
		return page;
	}
	
	@Override
	public DxyjLaw getLawById(String lawId) {
		DxyjLaw law = this.dingXingYiJuDao.getLawById(lawId);
		return law;
	}


	@Description("添加审计法规信息")
	public String addLaw(DxyjLaw law) {
		String id = this.dingXingYiJuDao.addLaw(law);
		return id;
	}


	@Override
	public int updateLaw(DxyjLaw law) {
		int result = this.dingXingYiJuDao.updateLaw(law);
		return result;
	}

	@Override
	public int deleteLaw(String id) {
		int result =  this.dingXingYiJuDao.deleteLaw(id);
		return result;
	}
	
	public String getConByHtml(String htmlCon) {
		String result = Jsoup.parse(htmlCon).text();
		return result;
	}

}
