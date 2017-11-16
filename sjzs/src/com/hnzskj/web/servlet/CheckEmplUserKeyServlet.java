/*@目名称：sjzs
 *@文件名：CheckEmplUserKeyServlet.java
 *@期：下午05:33:43
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

import com.hnzskj.persist.bean.system.Employee;
import com.hnzskj.persist.dao.fore.RegLoginDao;
import com.hnzskj.persist.dao.fore.impl.RegLoginDaoImpl;

/**
 *类名称:CheckEmplUserKeyServlet
 *类描述：<br/>
 *创建人： 平西强<br/>
 *创建时间:2013-3-14下午05:33:43<br/>
 *修改人: Administrator<br/>
 *修改时间:2013-3-14下午05:33:43<br/>
 *修改备注: <br/>
 *@version 1.0
 *@param <E>
 */
public class CheckEmplUserKeyServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4673620916072488189L;

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String userKeyId = request.getParameter("userKeyId");
		out.print(CheckUserKey(userKeyId));
	}
	
	public String CheckUserKey(String userKeyId){
		String result="";
		RegLoginDao regLoginDao = new RegLoginDaoImpl();
		Employee employee = regLoginDao.checkEmplInfo(userKeyId);
		if (employee != null ) {
			result = "您是U盘用户，您已经注册！进入首页 . . .";
		}
		return result;
	}



}
