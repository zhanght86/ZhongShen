/*
 * @项目名称: htglxt
 * @文件名称: AuthenticationInterceptor.java
 * @日期: 2011-6-26
 * @版权: 2011 河南中审科技有限公司 
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.common.interceptor;

import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.hnzskj.persist.bean.system.Employee;
import com.hnzskj.persist.bean.system.Power;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**        
 * 
 * 类名称：AuthenticationInterceptor     <br/>
 * 类描述：判断当前登陆用户是否拥有权限访问当前uri<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-6-26 上午10:43:58   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-6-26 上午10:43:58   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */
public class AuthenInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = -450799567350156247L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String path = request.getContextPath();
		String URI = request.getRequestURI().replace(path+"/", "");		
		Employee empl = (Employee)session.getAttribute( "loginEmpl" );

		if ( null != empl) {//如果管理员没有登陆或登陆超时
			String uncheck = (String) ServletActionContext.getServletContext().getAttribute("uncheck");
			if (uncheck.contains(URI)) {//判断当前uri是否是不受检查的uri
				return invocation.invoke();
			}
			if (isHave(empl,URI) ) {//如果用户拥有当前权限
				return invocation.invoke();
			}
			//如果用户没有权限跳转到无权限访问提示页面
			request.setAttribute("URI", URI);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/norights.jsp");
			dispatcher.forward(request, response);
			return null;			
		} else {
			return invocation.invoke();
		}
	}
	
	/**
	 * 方法描述：判断当前用户是否拥有对些uri的操作权限<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-26 上午11:18:28<br/>         
	 * @param powers
	 * @param URI
	 * @return
	 * @version   1.0  
	 */
	private boolean isHave(Employee empl, String URI) {
		Set<Power> powers = empl.getPowers();
		for (Power power : powers) {
			if (power.getPowerUrl().contains(URI)) {
				return true;
			}
		}
		return false;
	}
	
	
}
