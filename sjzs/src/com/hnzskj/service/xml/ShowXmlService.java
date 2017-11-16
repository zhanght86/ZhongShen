package com.hnzskj.service.xml;

import com.hnzskj.common.xml.ShowXml;
import com.hnzskj.persist.bean.sjzs.WorkFlowDTO;

public interface ShowXmlService {

	/**
	 *方法描述： 根据流程图Id获取流程图信息<br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-12 下午01:56:28
	 * 
	 *@return
	 *@version 1.0
	 */
	public WorkFlowDTO getWorkFlowBySjdhId(String sjdhId);

	public ShowXml initFlow(ShowXml showXml);

	public ShowXml upFlow(String modeId, ShowXml showXml);

	public ShowXml downFlow(String modeId, ShowXml showXml);

}
