/*
 * @项目名称: tree
 * @文件名称: DownLoadServlet.java
 * @日期: 2011-12-19
 * @版权: 2011 河南中审科技有限公司 
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.web.servlet;

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
public class DownLoadServletSJZS extends HttpServlet {
	private static final Logger LOOGER = Logger.getLogger(DownLoadServletSJZS.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
//		response.setContentType("application/x-download;chartset=utf-8");
		String guid = request.getParameter("attId");
		/*
		 * 判断附件的类型，如果attachType有值则说明是下载用户上传的数据
		 */
		String attachType = request.getParameter("attachType");
		if(null==guid||guid.trim().equals("")){
			try {
				throw new Exception("下载文件失败！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String name = "";// new
							// String(request.getParameter("name").getBytes("ISO-8859-1"),"utf-8");

		String s = null;
		Connection conn = null;// 数据库连接
		PreparedStatement pst = null;
		ResultSet rSet = null;

		StringBuilder sql = new StringBuilder();
		if(attachType == null || "".equals(attachType)){
			sql.append("select attachName,attachContentDoc from sjzs_attach where attachId='").append(guid).append("'");
		}else{
			sql.append("select attachName,attachContentDoc from sjzs_clientuploadattach where attachId='").append(guid).append("'");
		}
		conn = BaseDao.getConnection();
		InputStream in = null;
		OutputStream os = null;

		try {
			pst = conn.prepareStatement(sql.toString());
			rSet = pst.executeQuery();
			LOOGER.info("执行SQL语句:" + sql.toString());
			while (rSet.next()) {
				name = rSet.getString(1);
				in = rSet.getBinaryStream(2);
				try {
					// 下载文件时显示的文件保存名称
					s = java.net.URLEncoder.encode(name, "utf-8");

					response.setHeader("Content-Disposition","attachment;filename=" + s);
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
			if(null==name||name.trim().equals("")){
				LOOGER.error(DataUtil.getStackTraceAsString(new Exception("下载失败！")));
				PrintWriter printWriter = response.getWriter();
				response.setContentType("text/html");
				printWriter.print("<script language='javascript'>  alert('附件不存在！');history.go(-1);</script>");
			}else{
				response.setContentType("application/x-download;chartset=utf-8");
				request.setAttribute("name", name);
			}
		} catch (IOException e) {
			PrintWriter printWriter = response.getWriter();
			response.setContentType("text/html");
			printWriter.print("<script language='javascript'>  alert('附件不存在！');history.go(-1);</script>");
			LOOGER.error(DataUtil.getStackTraceAsString(e));;
		} catch (SQLException e) {
			PrintWriter printWriter = response.getWriter();
			response.setContentType("text/html");
			printWriter.print("<script language='javascript'>  alert('附件不存在！');history.go(-1);</script>");
			LOOGER.error(DataUtil.getStackTraceAsString(e));;
		} finally {
			if (null != os) {
				os.close();
			}
			if (null != in) {
				in.close();
			}
			BaseDao.close(rSet, pst, conn);
		}
	}

}
