package com.hnzskj.service.fore.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.fore.ContentMap;
import com.hnzskj.persist.bean.sjzs.DataDicDTO;
import com.hnzskj.persist.bean.sjzs.DxyjLaw;
import com.hnzskj.persist.bean.sjzs.FangFaAnLi;
import com.hnzskj.persist.bean.sjzs.Law;
import com.hnzskj.persist.bean.sjzs.ShiShiFangAnDTO;
import com.hnzskj.persist.bean.sjzs.SjdhMethod;
import com.hnzskj.persist.dao.fore.ShowInfoDao;
import com.hnzskj.service.fore.ShowInfoService;

public class ShowInfoServiceImpl implements ShowInfoService {

	private ShowInfoDao showInfoDao;

	public ShowInfoDao getShowInfoDao() {
		return showInfoDao;
	}

	public void setShowInfoDao(ShowInfoDao showInfoDao) {
		this.showInfoDao = showInfoDao;
	}

	@Override
	public List<ContentMap> getContentMaps() {
		int num = 6;
		List<ContentMap> contentMapList = new ArrayList<ContentMap>();
		contentMapList.add(getContentSjfgByField("lawDate", num));
		contentMapList.add(getContentFfalByField("ffalDateTime", num));
		contentMapList.add(getContentDxyjByField("writeDate", num));
		contentMapList.add(getContentSjdhByField("createTime", num));
		contentMapList.add(getContentSsfaByField("writeDate", num));
		contentMapList.add(getContentSjsxByField("updateDate", 5));		
		return contentMapList;
	}

	@Override
	public ContentMap getContentSjfgByField(String field, int num) {
		ContentMap contentMap = new ContentMap();
		contentMap.setType("sjfg");
		contentMap.setContentList(showInfoDao.searchSjfgByField(num));
		return contentMap;
	}

	@Override
	public ContentMap getContentDxyjByField(String field, int num) {
		ContentMap contentMap = new ContentMap();
		contentMap.setType("dxyj");
		contentMap.setContentList(showInfoDao.searchDxyjByField(num));
		return contentMap;
	}

	@Override
	public ContentMap getContentFfalByField(String field, int num) {
		ContentMap contentMap = new ContentMap();
		contentMap.setType("ffal");
		contentMap.setContentList(showInfoDao.searchFfalByField(num));
		return contentMap;
	}

	@Override
	public ContentMap getContentSjdhByField(String field, int num) {
		ContentMap contentMap = new ContentMap();
		contentMap.setType("sjdh");
		contentMap.setContentList(showInfoDao.searchSjdhByField(num));
		return contentMap;
	}

	@Override
	public ContentMap getContentSjsxByField(String field, int num) {
		ContentMap contentMap = new ContentMap();
		contentMap.setType("sjsx");
		contentMap.setContentList(showInfoDao.searchSjsxByField(num));
		return contentMap;
	}

	@Override
	public ContentMap getContentSsfaByField(String field, int num) {
		ContentMap contentMap = new ContentMap();
		contentMap.setType("ssfa");
		contentMap.setContentList(showInfoDao.searchSsfaByField(num));
		return contentMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page getLatestMethods(String type, Object entity, Page page) {
		LinkedHashMap<String,String> orderBy = new LinkedHashMap<String,String>();
		if("sjfg".equals(type)){
			return showInfoDao.getLatestMethods(Law.class, "*", "sjzs_sjfg", null, null, orderBy, page);
		}else if("dxyj".equals(type)){
			return showInfoDao.getLatestMethods(DxyjLaw.class, "*", "sjzs_dxyj", null, null, orderBy, page);
		}else if("sjff".equals(type)){
			return showInfoDao.getLatestMethods(FangFaAnLi.class, "*", "sjzs_ffal", null, null, orderBy, page);
		}else if("sjdh".equals(type)){
			return showInfoDao.getLatestMethods(SjdhMethod.class, "*", "sjzs_sjdh", null, null, orderBy, page);
		}else if("sjsx".equals(type)){
			return showInfoDao.getLatestMethods(DataDicDTO.class, "*", "sjzs_datadic", null, null, orderBy, page);
		}else if("ssfa".equals(type)){
			return showInfoDao.getLatestMethods(ShiShiFangAnDTO.class, "*", "sjzs_ssfn", null, null, orderBy, page);
		}
		return null;
	}
		
}