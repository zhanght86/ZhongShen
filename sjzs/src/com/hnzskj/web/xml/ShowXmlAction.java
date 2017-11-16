package com.hnzskj.web.xml;

import com.hnzskj.common.xml.ShowXml;
import com.hnzskj.common.xml.TransXml;
import com.hnzskj.common.xml.XmlDTO;
import com.hnzskj.persist.bean.sjzs.SjdhMethod;
import com.hnzskj.persist.bean.sjzs.WorkFlowDTO;
import com.hnzskj.service.xml.ShowXmlService;
import com.hnzskj.web.BaseAction;

public class ShowXmlAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	private String id ;
	
	private ShowXmlService showXmlService ;
	private SjdhMethod sjdhMethod = new SjdhMethod();
	private ShowXml showXml = new ShowXml();
	private XmlDTO xmlDTO = null;
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SjdhMethod getSjdhMethod() {
		return sjdhMethod;
	}

	public void setSjdhMethod(SjdhMethod sjdhMethod) {
		this.sjdhMethod = sjdhMethod;
	}

	public XmlDTO getXmlDTO() {
		return xmlDTO;
	}

	public void setXmlDTO(XmlDTO xmlDTO) {
		this.xmlDTO = xmlDTO;
	}

	public ShowXml getShowXml() {
		return showXml;
	}

	public void setShowXml(ShowXml showXml) {
		this.showXml = showXml;
	}

	public ShowXmlService getShowXmlService() {
		return showXmlService;
	}

	public void setShowXmlService(ShowXmlService showXmlService) {
		this.showXmlService = showXmlService;
	}

	public String initFlow() {
		if(id != null){
			WorkFlowDTO workFlow = showXmlService
			.getWorkFlowBySjdhId(id);
			if (workFlow != null) {
				TransXml transXml = new TransXml(workFlow.getId());
				XmlDTO xmlDTO = transXml.parseXML(workFlow.getTextXml());
				this.showXml.setXmlDTO(xmlDTO);
				this.setShowXml(showXmlService.initFlow(showXml));
				setSessionAttr("showxml", this.showXml);
				return "init";
			} else {
				return FAIL;
			}
		}else{
			return FAIL;
		}
		
	/*	
		if (sjdhMethod != null) {
			if (sjdhMethod.getId() != null && !"".equals(sjdhMethod.getId())) {
				WorkFlowDTO workFlow = showXmlService
						.getWorkFlowBySjdhId(sjdhMethod.getId());
				if (workFlow != null) {
					this.setXmlDTO(new TransXml(workFlow.getId())
							.parseXML(workFlow.getTextXml()));
					this.setShowXml(showXmlService.initFlow(showXml));
					return "init";
				} else {
					return FAIL;
				}
			} else {
				return FAIL;
			}
		} else {
			return FAIL;
		}
	}
	*/
	}
}
