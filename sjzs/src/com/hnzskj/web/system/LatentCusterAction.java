/*
 * @项目名称: OA
 * @文件名称: LatentCusterAction.java
 * @日期: 2012-8-9 下午06:04:05  
 * @版权: 2012 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.web.system;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.system.LatentCuster;
import com.hnzskj.service.system.LatentCusterService;
import com.hnzskj.web.BaseAction;

/**    
 * 项目名称：OA   <br/>
 * 类名称：LatentCusterAction.java   <br/>
 * 类描述：   <br/>
 * 创建人：王亲臣   <br/>
 * 创建时间：2012-8-9 下午06:04:05   <br/>
 * 修改人：王亲臣   <br/>
 * 修改时间：2012-8-9 下午06:04:05   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class LatentCusterAction extends BaseAction {

	private static final long serialVersionUID = 8169478952216878345L;
	
	/**
	 * 实体信息
	 */
	private LatentCuster latentCuster = new LatentCuster();
	
	/**
	 * 分页信息
	 */
	private Page<LatentCuster> page = new Page<LatentCuster>();
	
	/**
	 * 业务层注入
	 */
	private LatentCusterService latentCusterService = null;
	
	/**
	 * 关闭的页签
	 */
	private String closePage;
	
	/**
	 * 刷新的页签
	 */
	private String refreshPage;
	
	/**
	 * 标记字段
	 */
	private String type;

	/**
	 * 标记字段
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * 标记字段
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 实体信息
	 * @return the latentCuster
	 */
	public LatentCuster getLatentCuster() {
		return latentCuster;
	}

	/**
	 * 实体信息
	 * @param latentCuster the latentCuster to set
	 */
	public void setLatentCuster(LatentCuster latentCuster) {
		this.latentCuster = latentCuster;
	}

	/**
	 * @return the page
	 */
	public Page<LatentCuster> getPage() {
		return page;
	}

	/**
	 * 分页信息
	 * @param page the page to set
	 */
	public void setPage(Page<LatentCuster> page) {
		this.page = page;
	}

	/**
	 * 分页信息
	 * @return the closePage
	 */
	public String getClosePage() {
		return closePage;
	}

	/**
	 * 关闭的页签
	 * @param closePage the closePage to set
	 */
	public void setClosePage(String closePage) {
		this.closePage = closePage;
	}

	/**
	 * 关闭的页签
	 * @return the refreshPage
	 */
	public String getRefreshPage() {
		return refreshPage;
	}

	/**
	 * 刷新的页签
	 * @param refreshPage the refreshPage to set
	 */
	public void setRefreshPage(String refreshPage) {
		this.refreshPage = refreshPage;
	}

	/**
	 * 刷新的页签
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 业务层注入
	 * @param latentCusterService the latentCusterService to set
	 */
	public void setLatentCusterService(LatentCusterService latentCusterService) {
		this.latentCusterService = latentCusterService;
	}	
	
	/**
	 * 
	 * 方法描述：进入列表页面<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-8-9 下午06:06:57<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public String searchLatentCuster(){
		page=latentCusterService.searchLatentCuster(page, latentCuster);
		return LISTPAGE;
	}
	
	/**
	 * 
	 * 方法描述：进入新增页面<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-8-9 下午06:22:50<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public String goAddPage(){
		return ADDPAGE;
	}

	/**
	 * 
	 * 方法描述：新增一条信息<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-8-9 下午06:23:19<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public String addLatentCuster(){
		boolean result = latentCusterService.addLatentCuster(latentCuster);
		if ( result ) {
			this.addActionMessage("添加信息成功！");
			return ADDSUC;
		}
		this.addActionError("添加信息失败！");
		return FAIL;
	}
	
	/**
	 * 
	 * 方法描述：进入修改页面<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-8-9 下午06:24:26<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public String goUpdatePage(){
		latentCuster=latentCusterService.getLatentCuster(latentCuster.getLcId());
		return SHOWPAGE;
	}
	
	/**
	 * 
	 * 方法描述：修改一条信息<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-8-9 下午06:25:39<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public String updLatentCuster(){
		boolean result = latentCusterService.updLatentCuster(latentCuster);
		if ( result ) {
			this.addActionMessage("修改信息成功！");
			return UPDATESUC;
		}
		this.addActionError("修改信息失败！");
		return FAIL;
	}
	
	/**
	 * 
	 * 方法描述：删除一条信息<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-8-9 下午06:26:11<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public String delLatentCuster(){
		boolean result = latentCusterService.delLatentCuster(latentCuster.getLcId());
		if ( result ) {
			this.addActionMessage("删除信息成功！");
			if(null!=type&&"listPage".equals(type)){
				return DELSUC;
			}else{
				return "delSucByShowPage";
			}
		}
		this.addActionError("删除信息失败！");
		return FAIL;
	}
	
}
