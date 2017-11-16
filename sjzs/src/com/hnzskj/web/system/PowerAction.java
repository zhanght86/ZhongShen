/*
 * @项目名称: htglxt
 * @文件名称: RoleAction.java
 * @日期: 2011-5-26
 * @版权: 2011 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.web.system;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.system.Power;
import com.hnzskj.service.system.PowerService;
import com.hnzskj.web.BaseAction;

/**        
 * 
 * 类名称：PowerAction     <br/>
 * 类描述：<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-5-26 上午10:18:48   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-5-26 上午10:18:48   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */
public class PowerAction extends BaseAction{
	private static final long serialVersionUID = -4409985791561881265L;
	
	private PowerService powerService;
	
	private Page<Power> page = new Page<Power>();
	
	private Power power = new Power();
	
	private String parentCode;
	//执行批量删除是使用
	private String powerCodes;
	//关闭的标签页
	private String closePage;
	//刷新的标签页
	private String refreshPage;
	
	/**
	 * @return the closePage
	 */
	public String getClosePage() {
		return closePage;
	}

	/**
	 * @param closePage the closePage to set
	 */
	public void setClosePage(String closePage) {
		this.closePage = closePage;
	}

	/**
	 * @return the refreshPage
	 */
	public String getRefreshPage() {
		return refreshPage;
	}

	/**
	 * @param refreshPage the refreshPage to set
	 */
	public void setRefreshPage(String refreshPage) {
		this.refreshPage = refreshPage;
	}

	/**
	 * @return the powerCodes
	 */
	public String getPowerCodes() {
		return powerCodes;
	}

	/**
	 * @param powerCodes the powerCodes to set
	 */
	public void setPowerCodes(String powerCodes) {
		this.powerCodes = powerCodes;
	}

	/**
	 * @return the parentCode
	 */
	public String getParentCode() {
		return parentCode;
	}

	/**
	 * @param parentCode the parentCode to set
	 */
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	/**
	 * @return the powerService
	 */
	public PowerService getPowerService() {
		return powerService;
	}

	/**
	 * @param powerService the powerService to set
	 */
	public void setPowerService(PowerService powerService) {
		this.powerService = powerService;
	}

	/**
	 * @return the page
	 */
	public Page<Power> getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(Page<Power> page) {
		this.page = page;
	}

	/**
	 * @return the power
	 */
	public Power getPower() {
		return power;
	}

	/**
	 * @param power the power to set
	 */
	public void setPower(Power power) {
		this.power = power;
	}
	
	/**
	 * 
	 * 方法描述：进入权限添加页面<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午02:24:04<br/>         
	 * @return
	 * @version   1.0
	 */
	public String goAddPage() {
		return ADDPAGE;
	}
	
	/**
	 * 
	 * 方法描述：添加权限信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午02:24:34<br/>         
	 * @return
	 * @version   1.0
	 */
	public String addPower() {
		boolean result = this.powerService.addPower(power);
		parentCode = power.getPowerParent();
		if (result) {
			this.addActionMessage("权限信息添加成功!");
			return ADDSUC;
		}
		this.addActionError("权限信息添加失败！");
		return FAIL;
	}
	
	/**
	 * 
	 * 方法描述：删除一个权限信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午02:27:09<br/>         
	 * @return
	 * @version   1.0
	 */
	public String deletePower() {
		boolean result = false;
		String[] delCodes = powerCodes.split(", ");
		result = this.powerService.deletePowers((Serializable[])delCodes);
		if (result) {
			return DELSUC;
		}
		return FAIL;
	}
	
	/**
	 * 
	 * 方法描述：进入权限更新页面<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午02:30:41<br/>         
	 * @return
	 * @version   1.0
	 */
	public String goUpdatePage() {
		power = this.powerService.findById(power);
		return UPDATEPAGE;
	}
	
	/**
	 * 
	 * 方法描述：修改一个权限信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午02:40:37<br/>         
	 * @return
	 * @version   1.0
	 */
	public String updatePower() {
		boolean result = false;
		result = this.powerService.updatePower(power);
		parentCode = power.getPowerParent();
		if (result) {
			this.addActionMessage("权限信息修改成功！");
			return UPDATESUC;
		}
		return FAIL;
	}
	
//	/**
//	 * 方法描述：查询权限信息<br/>
//	 * 创建人：苏国庆   <br/>
//	 * 创建时间：2011-5-27 下午02:41:03<br/>         
//	 * @return
//	 * @version   1.0  
//	 */
//	public String searchPower() {
//		System.out.println(new BaseDao().getGUID());
//		parentCode = powerService.findParentCode(power.getPowerId());
//		if (null == parentCode) {
//			parentCode = "00000000";
//		}
//		page = this.powerService.searchPower("*", power, page);
//		return LISTPAGE;
//	}
//	
//	
	
	/**
	 * 方法描述：查询权限信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午02:41:03<br/>         
	 * @return
	 * @version   1.0  
	 */
	public String searchPower() {
		parentCode = powerService.findParentCode(power.getPowerId());
		if (null == parentCode) {
			parentCode = "00000000";
		}
		page = this.powerService.searchPower("*", power, page);
		return LISTPAGE;
	}
	
	
	
	/**
	 * 方法描述：查询权限的子权限<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午02:41:03<br/>         
	 * @return
	 * @version   1.0  
	 */
	/*public String searchChild() {
		if (null == power.getPowerId()) {
			power.setPowerId("00000000");
		}
		parentCode = powerService.findParentCode(power.getPowerId());
		if (null == parentCode) {
			parentCode = "00000000";
		}
		page = this.powerService.findChildren(power,"*");
		return LISTPAGE;
	}*/
	
	/**
	 * 方法描述：权限结构树，选择权限页面<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-10 下午03:15:36<br/>         
	 * @return
	 * @version   1.0  
	 */
	public String selectPower() {
		page = this.powerService.searchPower("*");
		return "selectPower";
	}
	
	public void getPowerType() throws IOException{
		String powerId=getRequest().getParameter("powerId").toString();
		power.setPowerId(powerId);
		power = this.powerService.findById(power);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.getWriter().print(power.getPowerType());
	}
}
