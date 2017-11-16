/*
 * @项目名称: tree
 * @文件名称: DownLoadServlet.java
 * @日期: 2011-12-19
 * @版权: 2011 河南中审科技有限公司 
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.web.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.DataUtil;

/**
 * 
 * 类名称：DownLoadServlet <br/>
 * 类描述：<br/>
 * 创建人：wxz <br/>
 * 创建时间：2011-12-19 下午06:49:31 <br/>
 * 修改人：Administrator <br/>
 * 修改时间：2011-12-19 下午06:49:31 <br/>
 * 修改备注： <br/>
 * 
 * @version 1.0
 */
@SuppressWarnings("serial")
public class DownLoadServletStencil extends HttpServlet {
	private static final Logger LOOGER = Logger
			.getLogger(DownLoadServletStencil.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("***************************");
		response.setContentType("application/x-download;chartset=utf-8");

		String filepath = "";
		String fileName = "";
		InputStream in = null;
		OutputStream os = null;
		response.setCharacterEncoding("utf-8");
		// response.setContentType("application/x-download;chartset=utf-8");
		String stencilName = request.getParameter("attId");
		String updatedoc = getServletContext().getRealPath("/doc/");

		if (stencilName.equals("1")) {
			fileName = "审计事项.xls";
			filepath = updatedoc + "\\" + fileName;
		} else {
			if (stencilName.equals("2")) {
				fileName = "定性依据.xls";
				filepath = updatedoc + "\\" + fileName;
			} else {
				PrintWriter printWriter = response.getWriter();
				printWriter
						.print("<script language='javascript'>  alert('路径不正确！');history.go(-1);</script>");
			}

		}

		byte[] buf = new byte[1024];
		int len = 0;
		try {

			if (null == filepath || filepath.trim().equals("")) {
				LOOGER.error(DataUtil.getStackTraceAsString(new Exception(
						"下载失败！")));
				PrintWriter printWriter = response.getWriter();
				response.setContentType("text/html");
				printWriter
						.print("<script language='javascript'>  alert('附件不存在！');history.go(-1);</script>");
			} else {
				response.setContentType("application/x-download;chartset=utf-8");
			
				in = new FileInputStream((new File(filepath)));
				String name = "";

				try {
					// 下载文件时显示的文件保存名称
					name = java.net.URLEncoder.encode(fileName, "utf-8");

					response.setHeader("Content-Disposition",
							"attachment;filename="+ name);
					request.setAttribute("name", name);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				os = response.getOutputStream();
				byte[] b = new byte[1024];
				int i = 0;
				while ((i = in.read(b)) > 0) {
					os.write(b, 0, i);
				}
				
			}
		} catch (IOException e) {
			PrintWriter printWriter = response.getWriter();
			response.setContentType("text/html");
			printWriter
					.print("<script language='javascript'>  alert('附件不存在！');history.go(-1);</script>");
			LOOGER.error(DataUtil.getStackTraceAsString(e));
			;
		} catch (Exception e) {
			PrintWriter printWriter = response.getWriter();
			response.setContentType("text/html");
			printWriter
					.print("<script language='javascript'>  alert('附件不存在！');history.go(-1);</script>");
			LOOGER.error(DataUtil.getStackTraceAsString(e));
		} finally {

			os.close();
			in.close();
		}
	}

}
