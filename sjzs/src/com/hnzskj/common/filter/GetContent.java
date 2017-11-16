/*
 * @项目名称: htglxt
 * @文件名称: GetContent.java
 * @日期: 2011-6-18
 * @版权: 2011 河南中审科技有限公司 
 * @开发公司或单位：河南中审科技有限公司研发部
 */

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

import com.hnzskj.persist.bean.system.SysContent;

/**
 * 
 * 类名称：GetContent <br/>
 * 类描述：过滤器将request,response,session保存起来，<br/>
 * 在进行日志操作的时候再取出request,response,session<br/>
 * 创建人：苏国庆 <br/>
 * 创建时间：2011-6-18 下午02:57:00 <br/>
 * 修改人：Administrator <br/>
 * 修改时间：2011-6-18 下午02:57:00 <br/>
 * 修改备注： <br/>
 * 
 * @version 1.0
 */
public class GetContent implements Filter {
	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		SysContent.setRequest((HttpServletRequest) request);
		SysContent.setResponse((HttpServletResponse) response);
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {}

}
