/*@目名称：sjzs
 *@文件名：CheckEmplPassServlet.java
 *@期：上午08:52:17
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

import com.hnzskj.common.Encrypt;
import com.hnzskj.persist.dao.fore.RegLoginDao;
import com.hnzskj.persist.dao.fore.impl.RegLoginDaoImpl;

/**
 *类名称:CheckEmplPassServlet 类描述：<br/>
 *创建人： 平西强<br/>
 *创建时间:2013-3-13上午08:52:17<br/>
 *修改人: Administrator<br/>
 *修改时间:2013-3-13上午08:52:17<br/>
 *修改备注: <br/>
 * 
 * @version 1.0
 *@param <E>
 */
@SuppressWarnings("serial")
public class CheckEmplPassServlet extends HttpServlet {

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
		String param1 = request.getParameter("param1");
		String param2 = request.getParameter("param2");
		String type = request.getParameter("type");
		if ("1".equals(type)) {
			out.print(checkEmpl(param1, param2));
		} else {
			out.print(checkRegEmpl(param1, param2));
		}
	}

	public String checkEmpl(String emplName, String emplPass) {
		String result = "";
		if (emplName == null || "".equals(emplName.trim())) {
			result = "请输入用户名！";
			return result;
		}
		if (emplPass == null || "".equals(emplPass)) {
			result = "请输入密码！";
			return result;
		}
		RegLoginDao regLoginDao = new RegLoginDaoImpl();
		String tablePass = regLoginDao.getPassByName(emplName);
		String pass = Encrypt.digest(emplPass);
		if (tablePass == null || "".equals(tablePass)) {
			result = "用户名不存在！";
		} else if (!pass.equals(tablePass)) {
			result = "密码输入错误！";
		} else {
			result = "";
		}
		return result;
	}

	public String checkRegEmpl(String pass, String rePass) {
		String result = "";
		if (pass == null || "".equals(pass)) {
			result = "请输入密码！";
		} else if (rePass == null || "".equals(rePass)) {
			result = "请输入确认密码！";
		} else if (!pass.equals(rePass)) {
			result = "两次密码输入不一致！";
		} else {
			result = "";
		}
		return result;
	}

}
