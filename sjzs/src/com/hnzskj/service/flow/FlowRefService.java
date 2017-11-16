package com.hnzskj.service.flow;

import java.util.List;

import com.hnzskj.persist.bean.flow.FlowRef;

public interface FlowRefService {
	public boolean addFlowRef(FlowRef flowRef);

	public boolean delFlowRef(String id);

	public boolean updFlowRef(FlowRef flowRef);

	public FlowRef getFlowRefById(String id);

	public List<FlowRef> getFlowRefByXmlId(String xmlId);
	
	public boolean delFlowRefByXmlId(String sjdhId);
	
	public List<String> getAttachIds(String sjdhId);
}
