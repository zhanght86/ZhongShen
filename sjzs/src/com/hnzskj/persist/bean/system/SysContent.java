/*
 * @项目名称: htglxt
 * @文件名称: SysContent.java
 * @日期: 2011-6-18
 * @版权: 2011 河南中审科技有限公司 
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.persist.bean.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * 类名称：SysContent <br/>
 * 类描述：<br/>
 * 创建人：苏国庆 <br/>
 * 创建时间：2011-6-18 下午02:55:28 <br/>
 * 修改人：Administrator <br/>
 * 修改时间：2011-6-18 下午02:55:28 <br/>
 * 修改备注： <br/>
 * 
 * @version 1.0
 */
public class SysContent {
	private static ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal<HttpServletResponse>();

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) requestLocal.get();
	}

	public static void setRequest(HttpServletRequest request) {
		requestLocal.set(request);
	}

	public static HttpServletResponse getResponse() {
		return (HttpServletResponse) responseLocal.get();
	}

	public static void setResponse(HttpServletResponse response) {
		responseLocal.set(response);
	}

	public static HttpSession getSession() {
		HttpServletRequest request = (HttpServletRequest)requestLocal.get();
		if (null == request) {
			return null;
		}
		return request.getSession();
	}
	
	public static Employee getLoginEmpl() {
		HttpServletRequest request = (HttpServletRequest)requestLocal.get();
		if (null != request && null != request.getSession()) {
			return (Employee) request.getSession().getAttribute("loginEmpl");
		}
		return null;
	}
}
