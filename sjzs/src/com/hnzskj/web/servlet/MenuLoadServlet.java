package com.hnzskj.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.hnzskj.persist.bean.fore.SimpleMenuDTO;
import com.hnzskj.persist.dao.fore.WdzsDao;
import com.hnzskj.persist.dao.fore.impl.WdzsDaoImpl;

public class MenuLoadServlet extends HttpServlet {
	
	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WdzsDao wdzsDao = new WdzsDaoImpl();
		Gson gson = new Gson();
		String menuParent = request.getParameter("menuParent");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String json = null;
		if(menuParent == null){
			json = gson.toJson(wdzsDao.initMenu());
		}else{
			json = gson.toJson(wdzsDao.getMenuByParent(menuParent));
		}
		
		out.print(json);
		System.out.println(json);
		out.flush();
		out.close();
	}
	
}
