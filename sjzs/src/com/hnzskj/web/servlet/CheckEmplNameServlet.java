/*@目名称：sjzs
 *@文件名：CheckEmplNameServlet.java
 *@期：上午10:18:31
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
 *类名称:CheckEmplNameServlet 类描述：<br/>
 *创建人： 平西强<br/>
 *创建时间:2013-3-5上午10:18:31<br/>
 *修改人: Administrator<br/>
 *修改时间:2013-3-5上午10:18:31<br/>
 *修改备注: <br/>
 * 
 * @version 1.0
 *@param <E>
 */
public class CheckEmplNameServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3997865999735059371L;

	/**
	 * Constructor of the object.
	 */
	public CheckEmplNameServlet() {
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
		String emplName = request.getParameter("emplName");
		String type = request.getParameter("type");
		String message = "";
		if (emplName == null || "".equals(emplName)) {
			message = "请输入用户名！";
		} else {
			emplName = emplName.trim();
			if ("0".equals(type)) {
				if (checkName(emplName)) {
					message = "用户名已经存在！";
				} else {
					message = "";
				}
			} else {
				if (!checkName(emplName)) {
					message = "用户名不存在！";
				} else {
					message = "";
				}
			}

		}
		out.print(message);
		return;
	}

	public boolean checkName(String emplName) {
		boolean ret = false;
		RegLoginDao regLoginDao = new RegLoginDaoImpl();
		// regLoginDao;	
		String result = regLoginDao.getPassByName(emplName);
		if (("".equals(result))||("0".equals(result))) {
			ret = false;
		} else {
			ret = true;
		}
		return ret;
	}

}
