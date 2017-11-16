package com.hnzskj.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hnzskj.persist.bean.system.Employee;
import com.hnzskj.persist.bean.system.SysContent;
/**
 * 项目的过滤器
 * 
 * @author 常利召
 * @version 1.0
 * 
 */
public class URLFilter implements Filter {

	protected FilterConfig filterConfig;

	public void doFilter(final ServletRequest request,
			final ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res =(HttpServletResponse) response;
		HttpSession session = req.getSession(true);
		Employee empl = (Employee)session.getAttribute( "loginEmpl" );
		String URI = req.getRequestURI();
		String context = req.getContextPath() + "/";

		if ( null == empl && !URI.contains("index") 
				&& !URI.equals(context) && !URI.contains("login") ) {//如果没有登陆或登陆超时
			if ( null != session.getAttribute("forced")) {//如果用户被强制退出
				String path = req.getContextPath();
				String goURI = path + "/system/index.action?top=2";
				res.sendRedirect(goURI);
				return;	
			}
			String path = req.getContextPath();
			String goURI = path + "/system/index.action?top=1";
			res.sendRedirect(goURI);
			return;
		} else {
			//如果用户存在，就将当前的request,reponse信息存入到SysContent中的ThreadLocal中
			//以实现在日志模块可以得到用户信息
			SysContent.setRequest((HttpServletRequest) request);
			SysContent.setResponse((HttpServletResponse) response);
			filterChain.doFilter(req, res);
		}
	}

	public void destroy() {
		this.filterConfig = null;
	}

	public void init(FilterConfig config) {
		this.filterConfig = config;
	}

	public void setFilterConfig(final FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

}
