/*
 * @项目名称: htglxt
 * @文件名称: OperationLogAction.java
 * @日期: 2011-6-21
 * @版权: 2011 河南中审科技有限公司 
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.web.system;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.system.OperationLog;
import com.hnzskj.service.system.OperationLogService;
import com.hnzskj.web.BaseAction;

/**        
 * 
 * 类名称：OperationLogAction     <br/>
 * 类描述：<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-6-21 上午11:45:49   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-6-21 上午11:45:49   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */
public class OperationLogAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	private OperationLogService operationLogService;
	
	private OperationLog operationLog = new OperationLog();
	
	private Page<OperationLog> page = new Page<OperationLog>();
	
	public OperationLogAction () {
		page.setMaxResult(15);
	}

	/**
	 * @return the operationLogService
	 */
	public OperationLogService getOperationLogService() {
		return operationLogService;
	}

	/**
	 * @param operationLogService the operationLogService to set
	 */
	public void setOperationLogService(OperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}

	/**
	 * @return the operationLog
	 */
	public OperationLog getOperationLog() {
		return operationLog;
	}

	/**
	 * @param operationLog the operationLog to set
	 */
	public void setOperationLog(OperationLog operationLog) {
		this.operationLog = operationLog;
	}

	/**
	 * @return the page
	 */
	public Page<OperationLog> getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(Page<OperationLog> page) {
		this.page = page;
	}
	
	/**
	 * 方法描述：日志列表页面<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-21 上午11:47:53<br/>         
	 * @return
	 * @version   1.0  
	 */
	public String searchLog() {
		page = this.operationLogService.searchOprLog(operationLog, page);
		return LISTPAGE;
	}
	
	/**
	 * 方法描述：查看一条日志信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-24 下午01:56:28<br/>         
	 * @return
	 * @version   1.0  
	 */
	public String show() {
		operationLog = operationLogService.findById(operationLog.getOid());
		return SHOWPAGE;
	}

}
