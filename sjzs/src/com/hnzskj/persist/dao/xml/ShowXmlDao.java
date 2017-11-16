package com.hnzskj.persist.dao.xml;

import com.hnzskj.common.xml.ShowXml;
import com.hnzskj.persist.bean.sjzs.WorkFlowDTO;

public interface ShowXmlDao {
	public ShowXml init(ShowXml oldShowXml);

	public ShowXml up(String modeId, ShowXml oldShowXml);

	public ShowXml down(String modeId, ShowXml oldShowXml);

	public String getWorkIdBySjdhId(String sjdhId);

	public WorkFlowDTO getWorkFlowById(String workId);

}
