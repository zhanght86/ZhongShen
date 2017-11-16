package com.hnzskj.service.xml.impl;

import com.hnzskj.common.xml.ShowXml;
import com.hnzskj.persist.bean.sjzs.WorkFlowDTO;
import com.hnzskj.persist.dao.xml.ShowXmlDao;
import com.hnzskj.service.xml.ShowXmlService;

public class ShowXmlServiceImpl implements ShowXmlService {
	private ShowXmlDao showXmlDao;

	public ShowXmlDao getShowXmlDao() {
		return showXmlDao;
	}

	public void setShowXmlDao(ShowXmlDao showXmlDao) {
		this.showXmlDao = showXmlDao;
	}

	/*
	 * <p>Title: getWorkFlowBySjdhId</p> <p>Description: </p>
	 * 
	 * @return
	 * 
	 * @see com.hnzskj.service.xml.ShowXmlService#getWorkFlowBySjdhId()
	 */
	@Override
	public WorkFlowDTO getWorkFlowBySjdhId(String sjdhId) {
		String workId = showXmlDao.getWorkIdBySjdhId(sjdhId);
		if (workId != null && !"".equals(workId)) {
			return showXmlDao.getWorkFlowById(workId);
		} else {
			return null;
		}
	}

	@Override
	public ShowXml downFlow(String modeId, ShowXml showXml) {
		return showXmlDao.down(modeId, showXml);
	}

	@Override
	public ShowXml initFlow(ShowXml showXml) {
		return showXmlDao.init(showXml);
	}

	@Override
	public ShowXml upFlow(String modeId, ShowXml showXml) {
		return showXmlDao.up(modeId, showXml);
	}

}
