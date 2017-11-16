/*
 * @项目名称: htglxt

 * @文件名称: BaseAction.java
 * @日期: 2011-5-27
 * @版权: 2011 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.web;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.hnzskj.persist.bean.system.Employee;
import com.opensymphony.xwork2.ActionSupport;

/**        
 * 
 * 类名称：BaseAction     <br/>
 * 类描述：<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-5-27 下午04:33:43   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-5-27 下午04:33:43   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */
public class BaseAction extends ActionSupport {
	private static final long serialVersionUID = 6911249168232980226L;
	
	/**添加页面*/
	protected static final String ADDPAGE = "addPage";
	/**添加成功*/
	protected static final String ADDSUC = "addSuc";
	/**删除成功*/
	protected static final String DELSUC = "delSuc";
	/**进入修改页面*/
	protected static final String UPDATEPAGE = "updatePage";
	/**修改成功*/
	protected static final String UPDATESUC = "updateSuc";
	/**进入列表页面*/
	protected static final String LISTPAGE = "listPage";
	/**进入失败页面*/
	protected static final String FAIL = "fail";
	/**进入内容显示页面*/
	protected static final String SHOWPAGE = "showPage";
	
	
	public BaseAction() {
	}
	
	/**
	 * 
	 * 方法描述：根据参数的名称获得request中的参数<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午04:36:20<br/>         
	 * @param parameter
	 * @return
	 * @version   1.0
	 */
	public String getParameter(String parameter) {
		return ServletActionContext.getRequest().getParameter(parameter);
	}

	/**
	 * 
	 * 方法描述：获得request<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午04:36:56<br/>         
	 * @return
	 * @version   1.0
	 */
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
	
	/**
	 * 
	 * 方法描述：获得session<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午04:37:09<br/>         
	 * @return
	 * @version   1.0
	 */
	public HttpSession getSession() {
		return getRequest().getSession();
	}
	
	/**
	 * 
	 * 方法描述：根据属性的名称从session获得属性对应在值<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午04:37:25<br/>         
	 * @param attrName
	 * @return
	 * @version   1.0
	 */
	public Object getSessoinAttr(String attrName) {
		return getRequest().getSession().getAttribute(attrName);
	}
	
	/**
	 * 方法描述：向session里面添加一个属性<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-10 下午05:52:32<br/>         
	 * @param attrName	属性的名称 
	 * @param object	属性对象
	 * @version   1.0  
	 */
	public void setSessionAttr(String attrName, Object object) {
		getRequest().getSession().setAttribute(attrName, object);
	}
	
	/**
	 * 方法描述：将Employee对象放入session中，登陆时使用<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-24 下午02:31:41<br/>         
	 * @param employee
	 * @version   1.0  
	 */
	public void setEmplToSession(Employee employee) {
		getRequest().getSession().setAttribute("loginEmpl", employee);
	} 
	
	/**
	 * 方法描述：从session中将Employee取出，登陆后需要使用Employee对象使用<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-24 下午02:33:22<br/>         
	 * @return
	 * @version   1.0  
	 */
	public Employee getEmplFromSession(){
		return (Employee) getRequest().getSession().getAttribute("loginEmpl");
	}

	/**
	 * 
	 * 方法描述：获得response<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午04:37:53<br/>         
	 * @return
	 * @version   1.0
	 */
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}
	
	/**
	 * 
	 * 方法描述：根据指定的资源获取其绝对路径<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午04:39:11<br/>         
	 * @param path
	 * @return
	 * @version   1.0
	 */
	public static String getRelPath(String path){
		return ServletActionContext.getServletContext().getRealPath(path);
	}
	
	/**
	 * 方法描述：获得服务器的路径
	 * 创建人： 丁艳伟
	 * 创建时间：2011-9-14下午02:49:40
	 * @return
	 * @return
	 */
	public static String getServerPath(){
		String serverPath = ServletActionContext.getServletContext().getRealPath("/");
		String context = ServletActionContext.getRequest().getContextPath().replaceFirst("/", "");
		return serverPath.replace(context + "\\", "");
	}
	/**
	 * 
	 * 方法描述：获得客户端真实的IP地址,用户在使用代理时也可获得真实IP地址<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-17 下午04:48:48<br/>         
	 * @return	当前用户的IP地址
	 * @version   1.0
	 */
	public String getIpAddr() {		
		String ip = getRequest().getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = getRequest().getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = getRequest().getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = getRequest().getRemoteAddr();
		}
		return ip;
	}
	
	/**
	 * 方法描述：记录用户登陆的IP和登陆的用户<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-29 上午09:17:50<br/>         
	 * @version   1.0  
	 */
	@SuppressWarnings("unchecked")
	public String recordLogin(Employee empl) {
		ServletContext context = ServletActionContext.getServletContext();
		List<Employee> empls = (List<Employee>) context.getAttribute("empls");
		
		if (-1 != empls.indexOf(empl)) {
			return "repeatEmpl";
		} else {
			empls.add(empl);
			context.setAttribute("empls", empls);
		}
		return null;
	}
	
	/**
	 * 方法描述：<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-8-5 下午02:09:21<br/>         
	 * @return
	 * @version   1.0  
	 */
	@SuppressWarnings("unchecked")
	public void removeLoginInfo(){
		Employee empl = getEmplFromSession();
		ServletContext context = ServletActionContext.getServletContext();
		List<Employee> empls = (List<Employee>) context.getAttribute("empls");
		//如果application中已经包含当前用户的登陆信息则将其从application中移除
		if (-1 != empls.indexOf(empl)) {
			empls.remove(empl);
			context.setAttribute("empls", empls);
		}
	}
}
