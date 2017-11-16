package com.hnzskj.persist.dao.flow;

import java.util.List;

import com.hnzskj.persist.bean.flow.FlowRef;

public interface FlowRefDao {
	public int addFlowRef(FlowRef flowRef);

	public int delFlowRef(String id);

	public int updFlowRef(FlowRef flowRef);

	public FlowRef getFlowRefById(String id);

	public List<FlowRef> getFlowRefByXmlId(String xmlId);
	
	public int delFlowRefBySjdhId(String sjdhId);
	
	public List<String> getAttachIds(String sjdhId);
	
	public FlowRef getFlowRefByXmlIdModeId(String xmlId,String modeId);
}
