package com.hnzskj.service.flow.impl;

import java.util.List;

import com.hnzskj.persist.bean.flow.FlowRef;
import com.hnzskj.persist.dao.flow.FlowRefDao;
import com.hnzskj.service.flow.FlowRefService;

public class FlowRefServiceImpl implements FlowRefService {
	private FlowRefDao flowRefDao;

	public FlowRefDao getFlowRefDao() {
		return flowRefDao;
	}

	public void setFlowRefDao(FlowRefDao flowRefDao) {
		this.flowRefDao = flowRefDao;
	}

	@Override
	public FlowRef getFlowRefById(String id) {
		return flowRefDao.getFlowRefById(id);
	}

	@Override
	public List<FlowRef> getFlowRefByXmlId(String xmlId) {
		return flowRefDao.getFlowRefByXmlId(xmlId);
	}

	@Override
	public boolean addFlowRef(FlowRef flowRef) {
		return flowRefDao.addFlowRef(flowRef) > 0;
	}

	@Override
	public boolean delFlowRef(String id) {
		return flowRefDao.delFlowRef(id) > 0;
	}

	@Override
	public boolean updFlowRef(FlowRef flowRef) {
		return flowRefDao.updFlowRef(flowRef) > 0;
	}

	@Override
	public boolean delFlowRefByXmlId(String sjdhId) {
		
		return flowRefDao.delFlowRefBySjdhId(sjdhId)>0;
	}

	@Override
	public List<String> getAttachIds(String sjdhId) {
		
		return flowRefDao.getAttachIds(sjdhId);
	}

}
