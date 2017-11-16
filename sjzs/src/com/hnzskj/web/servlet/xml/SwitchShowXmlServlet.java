package com.hnzskj.web.servlet.xml;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hnzskj.common.xml.ModeDTO;
import com.hnzskj.common.xml.ShowXml;
import com.hnzskj.common.xml.XmlDTO;
import com.hnzskj.persist.bean.flow.FlowRef;
import com.hnzskj.persist.dao.flow.FlowRefDao;
import com.hnzskj.persist.dao.flow.impl.FlowRefDaoImpl;

public class SwitchShowXmlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ShowXml showXml = new ShowXml();
	private FlowRefDao flowRefDao = new FlowRefDaoImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		response.setHeader("ContentType", "text/xml");
		String modeId = request.getParameter("modeId");
		String bear = request.getParameter("bear");
		Object objShow = request.getSession().getAttribute("showxml");
		ShowXml show = null;
		if (objShow != null && modeId != null && !"".equals(modeId.trim())
				&& bear != null && !"".equals(bear.trim())) {
			show = (ShowXml) objShow;
			if ("left".equals(bear)) {
				this.showXml = up(modeId, show);
			} else if ("right".equals(bear)) {
				this.showXml = down(modeId, show);
			}
			out.write(getXml(show));
		} else {
			out.println("出错了额！");
			return;
		}
		out.flush();
		out.close();
	}

	public String getXml(ShowXml showXml) {
		String[] strs = null;
		StringBuilder xmlStr = new StringBuilder();
		// xmlStr.append();
		xmlStr.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		xmlStr.append("<show>");
		strs = showXml.getMessage();
		xmlStr.append("<mess>");
		xmlStr.append("<title>");
		xmlStr.append(strs[0]);
		xmlStr.append("</title>");
		xmlStr.append("<cont>");
		xmlStr.append(strs[1]);
		xmlStr.append("</cont>");
		xmlStr.append("</mess>");
		strs = showXml.getLeft();
		xmlStr.append("<left>");
		xmlStr.append("<id>");
		xmlStr.append(strs[0]);
		xmlStr.append("</id>");
		xmlStr.append("<title>");
		xmlStr.append(strs[1]);
		xmlStr.append("</title>");
		xmlStr.append("</left>");
		Map.Entry<String, FlowRef> entry = this.showXml.getCenter().entrySet()
				.iterator().next();
		xmlStr.append("<center>");
		xmlStr.append("<key>");
		xmlStr.append(entry.getKey());
		xmlStr.append("</key>");
		FlowRef flow = entry.getValue();
		if(flow != null){
			xmlStr.append("<flow>");
			xmlStr.append("<id>");
			xmlStr.append(flow.getId());
			xmlStr.append("</id>");
			xmlStr.append("<sjdhId>");
			xmlStr.append(flow.getSjdhId());
			xmlStr.append("</sjdhId>");
			xmlStr.append("<xmlId>");
			xmlStr.append(flow.getXmlId());
			xmlStr.append("</xmlId>");
			xmlStr.append("<modeId>");
			xmlStr.append(flow.getModeId());
			xmlStr.append("</modeId>");
			xmlStr.append("<attachId>");
			xmlStr.append(flow.getAttachId());
			xmlStr.append("</attachId>");
			xmlStr.append("<fangFaId>");
			xmlStr.append(flow.getFangFaId());
			xmlStr.append("</fangFaId>");
			xmlStr.append("<deleteflag>");
			xmlStr.append(flow.getDeleteflag());
			xmlStr.append("</deleteflag>");
			xmlStr.append("<updatedate>");
			xmlStr.append(flow.getUpdatedate());
			xmlStr.append("</updatedate>");
			xmlStr.append("<fangFaUrl>");
			xmlStr.append(flow.getFangFaUrl());
			xmlStr.append("</fangFaUrl>");
			xmlStr.append("<attachUrl>");
			xmlStr.append(flow.getAttachUrl());
			xmlStr.append("</attachUrl>");
			xmlStr.append("<fangFaName>");
			xmlStr.append(flow.getFangFaName());
			xmlStr.append("</fangFaName>");
			xmlStr.append("<attachName>");
			xmlStr.append(flow.getAttachName());
			xmlStr.append("</attachName>");
			xmlStr.append("</flow>");
		}
		xmlStr.append("</center>");
		for (String[] rig : this.showXml.getRight()) {
			xmlStr.append("<right>");
			xmlStr.append("<id>");
			xmlStr.append(rig[0]);
			xmlStr.append("</id>");
			xmlStr.append("<title>");
			xmlStr.append(rig[1]);
			xmlStr.append("</title>");
			xmlStr.append("</right>");
		}
		xmlStr.append("</show>");

		System.out.println("$_$" + xmlStr);

		return xmlStr.toString();
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
		if (newLeftMode != null) {
			this.showXml.setLeft(new String[] { newLeftMode.getId() + "",
					newLeftMode.getTitle() });
		} else {
			newLeftMode = xmlDTO.beginModeDTO(true);
			if (newLeftMode != null) {
				this.showXml.setLeft(new String[] { newLeftMode.getId() + "",
						newLeftMode.getTitle() });
			}
			this.showXml.setLeft(new String[] { null, null });
		}
		// 中间节点信息
		ModeDTO mode = xmlDTO.getModeById(modeId);
		this.showXml.setMessage(new String[] { mode.getTitle(),
				mode.getModeContent() });
		mode.setShow(true);
		// 中间节点
		FlowRef modeCont = flowRefDao.getFlowRefByXmlIdModeId(xmlId, modeId);
		Map<String, FlowRef> center = new HashMap<String, FlowRef>();
		center.put(modeId, modeCont);
		this.showXml.setCenter(center);
		// 右边信息
		List<String[]> right = new ArrayList<String[]>();
		for (ModeDTO newRightMode : xmlDTO.next(modeId)) {
			newRightMode.setShow(false);
			right.add(new String[] { newRightMode.getId() + "",
					newRightMode.getTitle() });
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
			newRightMode.setShow(false);
			right.add(new String[] { (newRightMode.getId() + ""),
					(newRightMode.getTitle()) });
		}
		if (right.size() == 0) {
			ModeDTO neMode = xmlDTO.beginModeDTO(false);
			System.out.println("new mode ---"+neMode);
			if (neMode != null) {
				right.add(new String[] { neMode.getId() + "",
								neMode.getTitle() });
			}
		}
		this.showXml.setRight(right);
		// 中间节点信息
		ModeDTO mode = xmlDTO.getModeById(modeId);
		this.showXml.setMessage(new String[] { mode.getTitle(),
				mode.getModeContent() });
		mode.setShow(true);
		// 中间节点
		FlowRef modeCont = flowRefDao.getFlowRefByXmlIdModeId(xmlId, modeId);
		Map<String, FlowRef> center = new HashMap<String, FlowRef>();
		center.put(modeId, modeCont);
		this.showXml.setCenter(center);
		// 左边边信息
		ModeDTO newLeftMode = xmlDTO.up(modeId);
		if (newLeftMode != null) {
			this.showXml.setLeft(new String[] { newLeftMode.getId() + "",
					newLeftMode.getTitle() });
		} else {
			newLeftMode = xmlDTO.beginModeDTO(true);
			if (newLeftMode != null) {
				this.showXml.setLeft(new String[] { newLeftMode.getId() + "",
						newLeftMode.getTitle() });
			}
			this.showXml.setLeft(new String[] { null, null });
		}
		return this.showXml;
	}
}
