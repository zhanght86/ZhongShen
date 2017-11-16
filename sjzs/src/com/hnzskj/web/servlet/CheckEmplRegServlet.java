/*@目名称：sjzs
 *@文件名：CheckEmplRegServlet.java
 *@期：下午04:04:44
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

import com.hnzskj.persist.dao.fore.RegLoginDao;
import com.hnzskj.persist.dao.fore.impl.RegLoginDaoImpl;

/**
 *类名称:CheckEmplRegServlet 类描述：<br/>
 *创建人： 平西强<br/>
 *创建时间:2013-3-13下午04:04:44<br/>
 *修改人: Administrator<br/>
 *修改时间:2013-3-13下午04:04:44<br/>
 *修改备注: <br/>
 * 
 * @version 1.0
 *@param <E>
 */
public class CheckEmplRegServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		String pass = request.getParameter("pass");
		String repass = request.getParameter("repass");
		String yanzh = request.getParameter("yanzh");
		Object rand = request.getSession().getAttribute("rand");
		out.print(checkReg(emplName, pass, repass, yanzh, rand));

	}

	public String checkReg(String emplName, String pass, String rePass,
			String yanzh, Object rand) {
		String result = "";
		if (emplName == null || "".equals(emplName.trim())) {
			result = "请输入用户名！";
			return result;
		}
		if (pass == null || "".equals(pass)) {
			result = "请输入密码！";
			return result;
		}
		if (rePass == null || "".equals(rePass)) {
			result = "请输入确认密码！";
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
		if ((tablePass != null) && (!"".equals(tablePass))&& (!"0".equals(tablePass))) {
			result = "用户名已经存在！";
		}else if (!pass.equals(rePass)) {
			result = "两次密码输入不一致！";
		} else if (!yanzh.equals(rand.toString())) {
			result = "验证码输入错误！";
		} else {
			result = "";
		}
		return result;
	}

}
