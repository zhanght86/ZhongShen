/*@目名称：sjzs
 *@文件名：CheckEmplLoginServlet.java
 *@期：上午09:57:33
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
 *类名称:CheckEmplLoginServlet 类描述：<br/>
 *创建人： 平西强<br/>
 *创建时间:2013-3-13上午09:57:33<br/>
 *修改人: Administrator<br/>
 *修改时间:2013-3-13上午09:57:33<br/>
 *修改备注: <br/>
 * 
 * @version 1.0
 *@param <E>
 */
public class CheckEmplLoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4658380690467645608L;

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
		String emplName = request.getParameter("emplName");
		String emplPass = request.getParameter("emplPass");
		String yanzh = request.getParameter("yanzh");
		Object rand = request.getSession().getAttribute("rand");

		out.print(checkLogin(request, emplName, emplPass, yanzh, rand));

	}

	public String checkLogin(HttpServletRequest request, String emplName,
			String emplPass, String yanzh, Object rand) {
		String result = "";
		if (emplName == null || "".equals(emplName.trim())) {
			result = "请输入用户名！";
			return result;
		}
		if (emplPass == null || "".equals(emplPass)) {
			result = "请输入密码！";
			return result;
		}
		if (rand == null) {
			result = "验证码获取失败！";
			return result;
		}
		if (yanzh == null || "".equals(yanzh)) {
			result = "请输入验证码！";
			return result;
		}
		RegLoginDao regLoginDao = new RegLoginDaoImpl();
		String tablePass = regLoginDao.getPassByName(emplName);
		String pass = Encrypt.digest(emplPass);
		if (tablePass == null || "".equals(tablePass)) {
			result = "用户名不存在！";
		} else if (!pass.equals(tablePass)) {
			result = "密码输入错误！";
		} else if (!yanzh.equals(rand.toString())) {
			result = "验证码输入错误！";
		} else {
				result = "";
		}
		return result;
	}
}
