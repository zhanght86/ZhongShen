/*
 * @项目名称: htglxt
 * @文件名称: RoleAction.java
 * @日期: 2011-5-26
 * @版权: 2011 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.web.system;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.hnzskj.common.Page;
import com.hnzskj.common.init.SysParamUtil;
import com.hnzskj.persist.bean.init.InithtLbBean;
import com.hnzskj.persist.bean.system.Employee;
import com.hnzskj.persist.bean.system.Organization;
import com.hnzskj.persist.bean.system.Power;
import com.hnzskj.persist.bean.system.Role;
import com.hnzskj.service.system.PowerService;
import com.hnzskj.service.system.RoleService;
import com.hnzskj.web.BaseAction;

/**        
 * 
 * 类名称：RoleAction     <br/>
 * 类描述：<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-5-26 上午10:18:48   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-5-26 上午10:18:48   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */
public class RoleAction extends BaseAction{
	private static final long serialVersionUID = -4409985791561881265L;
	private Page<Organization> pages = new Page<Organization>();
	private RoleService roleService;
	private List<Role> roles=new ArrayList<Role>();//授权选择审批当前登录用户的角色列表
	private String urlType;
	private int uid=0;//用户编号
	/**
	 * 合同类型集合
	 */
	private ArrayList<InithtLbBean> listHtlb;
	
	/**
	 * 已选择的审批合同类型集合
	 */
	private ArrayList<String> listSelectHtlb=new ArrayList<String>();
	
	
	private PowerService powerService;
	
	private Page<Role> page = new Page<Role>();
	
	private List<Power> powerList = new ArrayList<Power>();
	//角色列表
	private List<Role> roleList= new ArrayList<Role>();
	
	private int type=1;//节点选择角色时用到
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	private Role role = new Role();
	
	private String[] pcodes;
	
	private String rpcodes;
	//关闭的标签页
	private String closePage;
	//刷新的标签页
	private String refreshPage;
	

	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	/**
	 * 用户uid
	 */
	public int getUid() {
		return uid;
	}
	/**
	 * 用户uid
	 */
	public void setUid(int uid) {
		this.uid = uid;
	}

	/**
	 * @return the listSelectHtlb
	 */
	public ArrayList<String> getListSelectHtlb() {
		return listSelectHtlb;
	}

	/**
	 * @param listSelectHtlb the listSelectHtlb to set
	 */
	public void setListSelectHtlb(ArrayList<String> listSelectHtlb) {
		this.listSelectHtlb = listSelectHtlb;
	}

	/**
	 * @return the listHtlb
	 * 
	 * 
	 */
	public ArrayList<InithtLbBean> getListHtlb() {
		return listHtlb;
	}

	/**
	 * @param listHtlb the listHtlb to set
	 */
	public void setListHtlb(ArrayList<InithtLbBean> listHtlb) {
		this.listHtlb = listHtlb;
	}

	/**
	 * @return the pages
	 */
	public Page<Organization> getPages() {
		return pages;
	}

	/**
	 * @param pages the pages to set
	 */
	public void setPages(Page<Organization> pages) {
		this.pages = pages;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	/**
	 * @return the urlType
	 */
	public String getUrlType() {
		return urlType;
	}

	/**
	 * @param urlType the urlType to set
	 */
	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public RoleAction() {
		page.setMaxResult(15);
	}
	
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
	 * @return the roleService
	 */
	public RoleService getRoleService() {
		return roleService;
	}

	/**
	 * @param roleService the roleService to set
	 */
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	/**
	 * @return the page
	 */
	public Page<Role> getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(Page<Role> page) {
		this.page = page;
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}
	
	/**
	 * @return the powerList
	 */
	public List<Power> getPowerList() {
		return powerList;
	}

	/**
	 * @param powerList the powerList to set
	 */
	public void setPowerList(List<Power> powerList) {
		this.powerList = powerList;
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
	 * @return the pcodes
	 */
	public String[] getPcodes() {
		return pcodes;
	}

	/**
	 * @param pcodes the pcodes to set
	 */
	public void setPcodes(String[] pcodes) {
		this.pcodes = pcodes;
	}

	/**
	 * @return the rpcodes
	 */
	public String getRpcodes() {
		return rpcodes;
	}

	/**
	 * @param rpcodes the rpcodes to set
	 */
	public void setRpcodes(String rpcodes) {
		this.rpcodes = rpcodes;
	}

	/**
	 * 
	 * 方法描述：进入角色添加页面<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午02:24:04<br/>         
	 * @return
	 * @version   1.0
	 */
	public String goAddPage() {
		SysParamUtil sp = new SysParamUtil();
		listHtlb = sp.getInithtLb();
		return ADDPAGE;
	}
	
	/**
	 * 
	 * 方法描述：添加角色信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午02:24:34<br/>         
	 * @return
	 * @version   1.0
	 */
	public String addRole() {
		boolean result = this.roleService.addRole(role);
		if (result) {
			this.addActionMessage("角色信息添加成功!");
			return ADDSUC;
		}
		this.addActionError("角色信息添加失败！");
		return FAIL;
	}
	
	/**
	 * 
	 * 方法描述：删除一个角色信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午02:27:09<br/>         
	 * @return
	 * @version   1.0
	 */
	public String deleteRole() {
		boolean result = false;
		result = this.roleService.deleteRole(role);
		if (result) {
			return DELSUC;
		}
		return FAIL;
	}
	
	/**
	 * 
	 * 方法描述：进入角色更新页面<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午02:30:41<br/>         
	 * @return
	 * @version   1.0
	 */
	public String goUpdatePage() {
		role = this.roleService.findById(role);
		return UPDATEPAGE;
	}
	
	/**
	 * 
	 * 方法描述：修改一个角色信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午02:40:37<br/>         
	 * @return
	 * @version   1.0
	 */
	public String updateRole() {
		boolean result = false;
		result = this.roleService.updateRole(role);
		if (result) {
			this.addActionMessage("角色信息修改成功！");
			return UPDATESUC;
		}
		return FAIL;
	}
	
	
	/**
	 * 方法描述：查询角色信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午02:41:03<br/>         
	 * @return
	 * @version   1.0  
	 */
	public String searchRole() {
		String fields = "*";
		
		page = this.roleService.searchRole(fields, role, page);
		return LISTPAGE;
	}
	
	/**
	 * 类描述：获取所有的角色
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-8-7 下午02:57:42 
	 * 修改人：
	 * 修改时间：2012-8-7 下午02:57:42  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public String chooseRole(){
		if(urlType.equals("checkbox")){
			page.setMaxResult(100);
		}
		page = this.roleService.searchRole("*", role, page);
		return "RoleChoose";
	}

	/**
	 * 方法描述：进入授权页面<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-30 下午05:14:43<br/>         
	 * @return
	 * @version   1.0  
	 */
	public String goAuthorize() {
		return "authpage";
	}
	
	/**
	 * 
	 * 方法描述：给角色授权<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-30 下午04:22:08<br/>         
	 * @return
	 * @version   1.0
	 */
	public String authorize() {
		pcodes = rpcodes.split(",");
		boolean result = this.roleService.authorize(role, pcodes);
		if ( result ) {
			return "authSuc";
		}
		return FAIL;
	}
	
	/**
	 * 方法描述：查看角色拥有的权限<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-7-14 下午04:33:29<br/>         
	 * @return
	 * @version   1.0  
	 */
	public String showRolePowers() {
		return "showPowers";
	}
	
	/**
	 * 方法描述：查询所有机构，用户在jsp页面生成机构树，用于选择机构<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-31 上午11:20:07<br/>         
	 * @return
	 * @version   1.0  
	 */
	public String selectOrgs() {
		//pages = this.organizationService.findAllOrganization("jmcode,jmssjg,jmname");
		return "selectOrgs";
	}
	/**
	 * 方法描述：进入用户选择主界面<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午03:01:53<br/>         
	 * @return
	 * @version   1.0  
	 */
	public String selectMains() {
		//if("1".equals(flag)){
		//	return "selectConaccess";
		//}else{
			return "selectMains";
		//}
	}
	
	public void getRoleName() throws IOException{
		String roleIds=getRequest().getParameter("roleIds");
		@SuppressWarnings("unused")
		Employee  employee =(Employee)this.getSession().getAttribute("loginEmpl");
		String roleName="";
		if(roleIds.length()!=0){
			String [] roleId =roleIds.split("_");
			for(int i =0;i<roleId.length;i++){
				Role r =new Role();
				r.setRoleId(roleId[i]);
				roleName+=this.roleService.findById(r).getRoleName()+"_";
			}
		}
		
		if(roleName.length()!=0)
			roleName=roleName.substring(0,roleName.length()-1);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(roleName);
	}
	
}
