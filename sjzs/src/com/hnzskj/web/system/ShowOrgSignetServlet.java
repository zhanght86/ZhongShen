/*
 * @项目名称: htglxt
 * @文件名称: ShowOrgSignetServlet.java
 * @日期: 2012-3-1 下午07:02:36  
 * @版权: 2011 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.web.system;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
 * 项目名称：htglxt   <br/>
 * 类名称：ShowOrgSignetServlet.java   <br/>
 * 类描述：   <br/>
 * 创建人：王亲臣   <br/>
 * 创建时间：2012-3-1 下午07:02:36   <br/>
 * 修改人：王亲臣   <br/>
 * 修改时间：2012-3-1 下午07:02:36   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
@SuppressWarnings("serial")
public class ShowOrgSignetServlet extends HttpServlet {

	private static final Logger LOGGER = Logger.getLogger(ShowOrgSignetServlet.class);
	
	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
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
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/gif");
		String guid = request.getParameter("id");
		//String type = request.getParameter("type");
		//String name=new String(request.getParameter("docTitle").getBytes("ISO-8859-1"),"utf-8"); 
		
		Connection conn = null;// 数据库连接
		PreparedStatement pst = null;
		ResultSet rSet = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT SIGNET FROM JGBM WHERE JMID = ?");
		conn = BaseDao.getConnection();
		InputStream in = null;
		OutputStream os = null;
		
		try {
			pst = conn.prepareStatement(sql.toString());
			pst.setString(1, guid);
			rSet = pst.executeQuery();
			LOGGER.info("执行SQL语句:" + sql.toString());
			while (rSet.next()) {
				in = rSet.getBinaryStream(1);
					// 下载文件时显示的文件保存名称
				//	s = java.net.URLEncoder.encode(name, "utf-8");
					//response.setHeader("Content-Disposition","attachment;filename="+s);
				os = response.getOutputStream();
				byte[] b = new byte[1024];
				int i = 0;
				while ((i = in.read(b)) > 0) {
					os.write(b, 0, i);
				}
			}
		} catch (IOException e) {
			//如果客户端点击下载后又点击取消按钮。这里将捕获异常。
			//log.error(DataUtil.getStackTraceAsString(e));;
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
}
