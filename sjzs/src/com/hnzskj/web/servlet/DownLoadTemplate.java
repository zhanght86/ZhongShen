/*
 * @项目名称: lwsj
 * @文件名称: DownLoadRepTemplate.java
 * @日期:2012-2-8
 * @版权: 2011 河南中审科技有限公司 
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.web.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
 * 类名称： DownLoadRepTemplate.java <br/>
 * 类描述：<br/>
 * 创建人：毛俊玲 <br/>
 * 创建时间：2012-2-8 <br/>
 * 修改人：Administrator <br/>
 * 修改时间：2012-2-8 <br/>
 * 修改备注： <br/>
 * 
 * @version 1.0
 */
public class DownLoadTemplate extends HttpServlet {

	private static final long serialVersionUID = 8599339922937042010L;
	private static final Logger LOGGER = Logger.getLogger(DownLoadTemplate.class);

	/**
	 * Constructor of the object.
	 */
	public DownLoadTemplate() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
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

		response.setContentType("application/x-download;chartset=utf-8");
		String guid = request.getParameter("tempId");
		String name = "";// new
							// String(request.getParameter("name").getBytes("ISO-8859-1"),"utf-8");

		String s = null;
		Connection conn = null;// 数据库连接
		PreparedStatement pst = null;
		ResultSet rSet = null;

		StringBuilder sql = new StringBuilder();
		sql
				.append(
						"select tempFileName, tempFile,tempFileType from workTemplate where tempId='")
				.append(guid).append("'");
		conn = BaseDao.getConnection();
		InputStream in = null;
		OutputStream os = null;

		try {
			pst = conn.prepareStatement(sql.toString());
			rSet = pst.executeQuery();
			LOGGER.info("执行SQL语句:" + sql.toString());
			while (rSet.next()) {
				name = rSet.getString(1);
				in = rSet.getBinaryStream(2);

				try {
					// 下载文件时显示的文件保存名称
					s = java.net.URLEncoder.encode(name, "utf-8");

					response.setHeader("Content-Disposition",
							"attachment;filename=" + s);
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
			request.setAttribute("name", name);
		} catch (IOException e) {
			LOGGER.error(DataUtil.getStackTraceAsString(e));;
		} catch (SQLException e) {
			LOGGER.error(DataUtil.getStackTraceAsString(e));;
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

		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
