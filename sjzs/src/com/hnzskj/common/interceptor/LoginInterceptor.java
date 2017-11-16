/*
 * @项目名称: htglxt
 * @文件名称: AuthorizationInterceptor.java
 * @日期: 2011-6-26
 * @版权: 2011 河南中审科技有限公司 
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.hnzskj.persist.bean.system.Employee;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**        
 * 
 * 类名称：AuthorizationInterceptor     <br/>
 * 类描述：判断是否登陆或登陆超时<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-6-26 上午10:36:12   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-6-26 上午10:36:12   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */
public class LoginInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		Employee empl = (Employee)session.getAttribute( "loginEmpl" );
		String uri = request.getRequestURI();
		if ( null == empl && !uri.contains("index") && !uri.contains("login")) {//如果管理员没有登陆或登陆超时
			String path = request.getContextPath();
			String goURI = "/" + path + "system/index.action?top=1";
			response.sendRedirect(goURI);
			return null;	
		} else {	
			return invocation.invoke();
		}
	}
}
