/**
 * flow
 *com.hnzskj.common.tag
 *2012-4-132012上午11:39:56
 *郑辉
 *
 */
package com.hnzskj.common.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.hnzskj.persist.bean.flow.FlowMess;
import com.hnzskj.service.flow.WorkFlowService;

/**
 *创建人：郑辉 描述： 用来输出审批意见 创建时间：2012-4-13 上午11:39:56 修改人：郑辉 修改时间：
 */
@SuppressWarnings("serial")
public class TagOpinion extends RequestContextAwareTag {
	private String label;
	private int instance_no;
	private WorkFlowService wfSer ; 

	public WorkFlowService getWfSer() {
		return wfSer;
	}

	public void setWfSer(WorkFlowService wfSer) {
		this.wfSer = wfSer;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getInstance_no() {
		return instance_no;
	}

	public void setInstance_no(int instanceNo) {
		instance_no = instanceNo;
	}


//	public int doStartTag() throws JspException {
//		
//	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.tags.RequestContextAwareTag#doStartTagInternal()
	 */
	@Override
	protected int doStartTagInternal() throws Exception {
		// TODO Auto-generated method stub
		wfSer = (WorkFlowService)this.getRequestContext().getWebApplicationContext().getBean("workFlowService");   

		StringBuffer sb = new StringBuffer();
		String mes = "";
		List<FlowMess> flowmesslist = wfSer.getFlowMessByNoAndTache(
				instance_no, label);
		if (flowmesslist.size() != 0 && flowmesslist != null) {
			for (int i = 0; i < flowmesslist.size(); i++) {
				FlowMess flowMess = flowmesslist.get(i);
				mes += "审批人：" + flowMess.getPerson_name() + " . 审批意见："
						+ flowMess.getBody() + "\n<br/>";
				mes += "审批时间:" + flowMess.getShenpi_time()+"\n<br/>";
			}
		}
		JspWriter out = pageContext.getOut();
		sb.append("<div style=\"font-size:12px;height:50px;width:560px;border:1px solid #999999;margin-top:10px\">").append(mes).append("</div>");
		try {
			out.print(sb.toString());
		} catch (IOException e) {
			throw new JspException(e);
		}
		return SKIP_BODY; // 不包含主体内容
	}

}
