/*@目名称：sjzs
 *@文件名：CheckYzmServlet.java
 *@期：上午10:27:30
 *@权：2011 河南中审科技有限公司
 *@开发公司或单位:河南中审科技有限公司
 */
package com.hnzskj.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *类名称:CheckYzmServlet 类描述：<br/>
 *创建人： 平西强<br/>
 *创建时间:2013-3-5上午10:27:30<br/>
 *修改人: Administrator<br/>
 *修改时间:2013-3-5上午10:27:30<br/>
 *修改备注: <br/>
 * 
 * @version 1.0
 *@param <E>
 */
public class CheckYzmServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CheckYzmServlet() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//String type = request.getParameter("type");
		String yanzh = request.getParameter("yanzh");
		Object rand = request.getSession().getAttribute("rand");
		out.print(checkYanzh(yanzh, rand));
	}

	public String checkYanzh(String yanzh, Object rand) {
		String result = "";
		if (rand == null) {
			result = "验证码获取失败！";
		} else {
			if (yanzh == null || "".equals(yanzh)) {
				result = "请输入验证码！";
			} else if (!yanzh.equals(rand.toString())) {
				result = "验证码输入错误！";
			} else {
				result = "";
			}

		}
		return result;
	}

}
