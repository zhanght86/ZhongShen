/*@目名称：lwsj
 *@文件名：SearchServiceImpl.java
 *@期：下午04:11:55
 *@权：2011 河南中审科技有限公司
 *@开发公司或单位:河南中审科技有限公司
 */
package com.hnzskj.service.fore.impl;

import java.util.LinkedHashMap;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.fore.QueryResult;
import com.hnzskj.persist.bean.sjzs.DataDicDTO;
import com.hnzskj.persist.bean.sjzs.DxyjLaw;
import com.hnzskj.persist.bean.sjzs.FangFaAnLi;
import com.hnzskj.persist.bean.sjzs.Law;
import com.hnzskj.persist.bean.sjzs.ShiShiFangAnDTO;
import com.hnzskj.persist.bean.sjzs.SjdhMethod;
import com.hnzskj.persist.dao.fore.SearchDao;
import com.hnzskj.service.fore.SearchService;

/**
 *类名称:SearchServiceImpl 类描述：<br/>
 *创建人： 平西强<br/>
 *创建时间:2013-3-4下午04:18:18<br/>
 *修改人: Administrator<br/>
 *修改时间:2013-3-4下午04:18:18<br/>
 *修改备注: <br/>
 * 
 * @version 1.0
 *@param <E>
 */
public class SearchServiceImpl implements SearchService {
	private SearchDao searchDao;

	/**
	 * @return the searchDao
	 */
	public SearchDao getSearchDao() {
		return searchDao;
	}

	/**
	 * @param searchDao
	 *            the searchDao to set
	 */
	public void setSearchDao(SearchDao searchDao) {
		this.searchDao = searchDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hnzskj.service.fore.SearchService#searchAllByKey(java.lang.String,
	 * com.hnzskj.common.Page)
	 */
	@Override
	public Page<QueryResult> searchAllByKey(String key, Page<QueryResult> page) {
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put("empl.id", "asc");
		page = searchDao.searchAllByKey(page, key, orderBy);
		return page;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hnzskj.service.fore.SearchService#searchDxyjByKey(java.lang.String,
	 * com.hnzskj.common.Page)
	 */
	@Override
	public Page<QueryResult> searchDxyjByKey(String key, Page<QueryResult> page) {
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put("id", "asc");
		page = searchDao.searchDxyjByKey(page, key, orderBy);
		return page;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hnzskj.service.fore.SearchService#searchFfalByKey(java.lang.String,
	 * com.hnzskj.common.Page)
	 */
	@Override
	public Page<QueryResult> searchFfalByKey(String key, Page<QueryResult> page) {
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put("id", "asc");
		page = searchDao.searchFfalByKey(page, key, orderBy);
		return page;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hnzskj.service.fore.SearchService#searchSjdhByKey(java.lang.String,
	 * com.hnzskj.common.Page)
	 */
	@Override
	public Page<QueryResult> searchSjdhByKey(String key, Page<QueryResult> page) {
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put("id", "asc");
		page = searchDao.searchSjdhByKey(page, key, orderBy);
		return page;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hnzskj.service.fore.SearchService#searchSjfgByKey(java.lang.String,
	 * com.hnzskj.common.Page)
	 */
	@Override
	public Page<QueryResult> searchSjfgByKey(String key, Page<QueryResult> page) {
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put("lawId", "asc");
		page = searchDao.searchSjfgByKey(page, key, orderBy);
		return page;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hnzskj.service.fore.SearchService#getDxyjLaw(com.hnzskj.persist.bean
	 * .sjzs.DxyjLaw)
	 */
	@Override
	public DxyjLaw getDxyjLaw(DxyjLaw dxyjLaw) {
		return searchDao.getDxyjLaw(dxyjLaw);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hnzskj.service.fore.SearchService#getFangFaAnLi(com.hnzskj.persist
	 * .bean.sjzs.FangFaAnLi)
	 */
	@Override
	public FangFaAnLi getFangFaAnLi(FangFaAnLi fangFaAnLi) {
		return searchDao.getFfal(fangFaAnLi);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hnzskj.service.fore.SearchService#getSjdhMethod(com.hnzskj.persist
	 * .bean.sjzs.SjdhMethod)
	 */
	@Override
	public SjdhMethod getSjdhMethod(SjdhMethod sjdhMethod) {
		return searchDao.getSjdh(sjdhMethod);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hnzskj.service.fore.SearchService#getSjfgLaw(com.hnzskj.persist.bean
	 * .sjzs.Law)
	 */
	@Override
	public Law getSjfgLaw(Law law) {
		return searchDao.getSjfgLaw(law);
	}

	@Override
	public DataDicDTO getSjsxMethod(DataDicDTO sjsxMethod) {
		return searchDao.getSjsx(sjsxMethod);
	}

	@Override
	public ShiShiFangAnDTO getSsfaMethod(ShiShiFangAnDTO ssfaMethod) {
		return searchDao.getSsfa(ssfaMethod);
	}

}
