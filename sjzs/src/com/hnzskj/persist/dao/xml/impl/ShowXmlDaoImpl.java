package com.hnzskj.persist.dao.xml.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.xml.ModeDTO;
import com.hnzskj.common.xml.ShowXml;
import com.hnzskj.common.xml.XmlDTO;
import com.hnzskj.persist.bean.flow.FlowRef;
import com.hnzskj.persist.bean.sjzs.WorkFlowDTO;
import com.hnzskj.persist.dao.flow.FlowRefDao;
import com.hnzskj.persist.dao.sjzs.SjdhMethodDao;
import com.hnzskj.persist.dao.sjzs.WorkFlowDao;
import com.hnzskj.persist.dao.xml.ShowXmlDao;

public class ShowXmlDaoImpl extends BaseDao implements ShowXmlDao {
	private ShowXml showXml = new ShowXml();
	private FlowRefDao flowRefDao;
	private WorkFlowDao workFlowDao;
	private SjdhMethodDao sjdhDao;

	public ShowXml getShowXml() {
		return showXml;
	}

	public void setShowXml(ShowXml showXml) {
		this.showXml = showXml;
	}

	public FlowRefDao getFlowRefDao() {
		return flowRefDao;
	}

	public void setFlowRefDao(FlowRefDao flowRefDao) {
		this.flowRefDao = flowRefDao;
	}

	public WorkFlowDao getWorkFlowDao() {
		return workFlowDao;
	}

	public void setWorkFlowDao(WorkFlowDao workFlowDao) {
		this.workFlowDao = workFlowDao;
	}

	public SjdhMethodDao getSjdhDao() {
		return sjdhDao;
	}

	public void setSjdhDao(SjdhMethodDao sjdhDao) {
		this.sjdhDao = sjdhDao;
	}

	/**
	 *方法描述：初始化 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-12 上午11:18:14
	 * 
	 *@return
	 *@version 1.0
	 */
	public ShowXml init(ShowXml oldShowXml) {
		this.showXml = oldShowXml;
		this.showXml.setLeft(null);
		XmlDTO xmlDTO = oldShowXml.getXmlDTO();
		String xmlId = xmlDTO.getXmlId();
		String centerModeId = xmlDTO.beginModeDTO(false).getId() + "";
		
		// 中间节点信息
		ModeDTO mode = xmlDTO.getModeById(centerModeId);
		mode.setShow(true);
		this.showXml.setMessage(new String[] { mode.getTitle(),
				mode.getModeContent() });
		// 中间节点
		FlowRef modeCont = flowRefDao.getFlowRefByXmlIdModeId(xmlId,
				centerModeId);
		Map<String, FlowRef> center = new HashMap<String, FlowRef>();
		center.put(centerModeId, modeCont);
		this.showXml.setCenter(center);
		// 右边信息
		List<String[]> right = new ArrayList<String[]>();
		for (ModeDTO newRightMode : xmlDTO.next(centerModeId)) {
			right.add(new String[] { newRightMode.getId() + "",
					newRightMode.getTitle() });
		}
		if (right.size() == 0) {
			ModeDTO neMode = xmlDTO.beginModeDTO(false);
			if (neMode != null) {
				right.add(new String[] { neMode.getId() + "",
								neMode.getTitle() });
			}
		}
		this.showXml.setRight(right);
		return this.showXml;
	}

	/**
	 *方法描述：点击上一步的时候把所有信息更新成上一步的信息， 对于如果没有了第一个节点则在前台进行控制， 这个方法只考虑向上换 <br/>
	 *参数：传过来所点击节点的ID<br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-12 上午09:03:38
	 * 
	 *@return
	 *@version 1.0
	 */
	public ShowXml up(String modeId, ShowXml oldShowXml) {
		this.showXml = oldShowXml;
		XmlDTO xmlDTO = oldShowXml.getXmlDTO();
		String xmlId = xmlDTO.getXmlId();
		// 左边节点
		ModeDTO newLeftMode = xmlDTO.up(modeId);
		this.showXml.setLeft(new String[]{newLeftMode.getId() + "", newLeftMode.getTitle()});
		// 中间节点信息
		ModeDTO mode = xmlDTO.getModeById(modeId);
		this.showXml.setMessage(new String[]{mode.getTitle(), mode.getModeContent()});
		// 中间节点
		FlowRef modeCont = flowRefDao.getFlowRefByXmlIdModeId(xmlId, modeId);
		Map<String, FlowRef> center = new HashMap<String, FlowRef>();
		center.put(modeId, modeCont);
		this.showXml.setCenter(center);
		// 右边信息
		List<String[]> right = new ArrayList<String[]>();
		for (ModeDTO newRightMode : xmlDTO.next(modeId)) {
			right.add(new String[]{newRightMode.getId() + "", newRightMode.getTitle()});
		}
		this.showXml.setRight(right);

		return this.showXml;
	}

	/**
	 *方法描述：点击下一步的时候把所有信息更新成上一步的信息， 这个方法只考虑向下换 <br/>
	 *参数：传过来所点击节点的ID<br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-12 上午09:03:38
	 * 
	 *@return
	 *@version 1.0
	 */
	public ShowXml down(String modeId, ShowXml oldShowXml) {
		this.showXml = oldShowXml;
		XmlDTO xmlDTO = oldShowXml.getXmlDTO();
		String xmlId = xmlDTO.getXmlId();
		// 右边节点
		List<String[]> right = new ArrayList<String[]>();
		for (ModeDTO newRightMode : xmlDTO.next(modeId)) {
			right.add(new String[]{(newRightMode.getId()+""), (newRightMode.getTitle())});
		}
		this.showXml.setRight(right);
		// 中间节点信息
		ModeDTO mode = xmlDTO.getModeById(modeId);
		this.showXml.setMessage(new String[]{mode.getTitle(), mode.getModeContent()});
		// 中间节点
		FlowRef modeCont = flowRefDao.getFlowRefByXmlIdModeId(xmlId, modeId);
		Map<String, FlowRef> center = new HashMap<String, FlowRef>();
		center.put(modeId, modeCont);
		this.showXml.setCenter(center);
		// 左边边信息
		ModeDTO newRightMode = xmlDTO.up(modeId);
		this.showXml.setLeft(new String[]{newRightMode.getId() + "", newRightMode.getTitle()});

		return this.showXml;
	}

	@Override
	public WorkFlowDTO getWorkFlowById(String workId) {
		return workFlowDao.findById(workId);
	}

	@Override
	public String getWorkIdBySjdhId(String sjdhId) {
		return sjdhDao.findById(sjdhId).getTemplate_no();
	}

}
