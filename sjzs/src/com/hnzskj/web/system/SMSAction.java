/*
 * @项目名称: htglxt
 * @文件名称: SMSAction.java
 * @日期: 2011-9-7
 * @版权: 2011 河南中审科技有限公司 
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.web.system;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.system.SMS;
import com.hnzskj.service.system.SMSService;
import com.hnzskj.web.BaseAction;

/**        
 * 
 * 类名称：SMSAction     <br/>
 * 类描述：短信发送记录控制层<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-9-7 下午12:18:25   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-9-7 下午12:18:25   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */
public class SMSAction extends BaseAction {
	private static final long serialVersionUID = -7546044421415096247L;

	private SMS sms = new SMS();
	
	private Page<SMS> page = new Page<SMS>();
	
	private SMSService sMSService;
	
	public SMSAction() {
		page.setMaxResult(15);
	}
	
	/**
	 * @return the sMSService
	 */
	public SMSService getsMSService() {
		return sMSService;
	}

	/**
	 * @param sMSService the sMSService to set
	 */
	public void setsMSService(SMSService sMSService) {
		this.sMSService = sMSService;
	}

	/**
	 * @return the sms
	 */
	public SMS getSms() {
		return sms;
	}

	/**
	 * @param sms the sms to set
	 */
	public void setSms(SMS sms) {
		this.sms = sms;
	}

	/**
	 * @return the page
	 */
	public Page<SMS> getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(Page<SMS> page) {
		this.page = page;
	}
	
	/**
	 * 方法描述：分页查看短信发送信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-9-7 下午12:29:15<br/>         
	 * @return
	 * @version   1.0  
	 */
	public String searchSMS() {
		page = sMSService.searchSMS(sms, page);
		return LISTPAGE;
	}
	
	/**
	 * 方法描述：查看短信详细内容<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-9-7 下午12:29:01<br/>         
	 * @return
	 * @version   1.0  
	 */
	public String showSMS() {
		sms = sMSService.findById(sms.getSid());
		return SHOWPAGE;
	}
}
