package com.hnzskj.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hnzskj.persist.bean.system.Employee;
import com.hnzskj.persist.dao.fore.ClientInfoDao;
import com.hnzskj.persist.dao.fore.impl.ClientInfoDaoImpl;

public class ClientDownLoadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		ClientInfoDao clientInfo = new ClientInfoDaoImpl();
		int integral  = Integer.parseInt(request.getParameter("integral"));
		String upClientId = request.getParameter("upClientId");
		String mess="0";
		Object obj = request.getSession().getAttribute("employee");
		if(obj == null){
			mess ="用户没有登录!";
		}else{
			String emplId = ((Employee)obj).getEmplId();
			if(integral > clientInfo.selectEmplInteg(emplId)){
				mess="您的积分不够,请上传您的文档获取更多的积分!";
			}else{
				if(clientInfo.downClientInfo(emplId, integral, upClientId)){
					mess="0";
				}else{
					mess="下载失败!";
				}
			}			
		}
		out.println(mess);
		out.flush();
		out.close();
	}



}
