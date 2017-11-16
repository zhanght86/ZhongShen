/*
 * @项目名称: htglxt
 * @文件名称: CreateXmlAction.java
 * @日期: 2011-6-13
 * @版权: 2011 河南中审科技有限公司 
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.web;

import java.io.PrintWriter;

import com.hnzskj.persist.bean.system.Employee;
import com.hnzskj.persist.bean.system.Role;
import com.hnzskj.service.system.PowerService;

/**        
 * 
 * 类名称：CreateXmlAction<br/>
 * 类描述：生成用于组织dhtmltree的XML文档<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-6-13 上午09:04:22   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-6-13 上午09:04:22   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */
public class CreateXmlAction extends BaseAction {
	private static final long serialVersionUID = 5211861255471626581L;
	
	private String type;
	
	private  PowerService powerService;
	
	private Role role =  new Role();
	
	private Employee empl = new Employee();
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
	 * @return the empl
	 */
	public Employee getEmpl() {
		return empl;
	}

	/**
	 * @param empl the empl to set
	 */
	public void setEmpl(Employee empl) {
		this.empl = empl;
	}

	/**
	 * 方法描述：生成XML文档返回给客户端用于生成dhtmltree<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-13 下午02:05:58<br/>         
	 * @return
	 * @throws Exception
	 * @version   1.0  
	 */
	public String loadXML() throws Exception{
		getResponse().setContentType("text/xml");
		getResponse().setCharacterEncoding("utf-8");
		String xmltree = "";
		
		if ("role".equals(type)) {//如果为角色授权
			xmltree = powerService.buildXMLForRole(role);
		} else if ("onerole".equals(type)) {
			xmltree = powerService.buildXMLForOneRole(role);
		}
		
		PrintWriter pw = getResponse().getWriter();
		pw.println(xmltree);
		return NONE;
	}
}
