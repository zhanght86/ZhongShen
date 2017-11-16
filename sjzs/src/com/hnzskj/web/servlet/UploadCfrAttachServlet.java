/*
 * @项目名称: OA
 * @文件名称: UploadAttachServlet.java
 * @日期: 2012-5-30
 * @版权: 2012 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.web.servlet;


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.DataUtil;
import com.hnzskj.web.system.ShowSignetServlet;

/**
 * 
 * 类名称：UploadServlet <br/>
 * 类描述：<br/>
 * 创建人：苏国庆 <br/>
 * 创建时间：2011-12-19 下午06:48:49 <br/>
 * 修改人：Administrator <br/>
 * 修改时间：2011-12-19 下午06:48:49 <br/>
 * 修改备注： <br/>
 * 
 * @version 1.0
 */
@SuppressWarnings("serial")
public class UploadCfrAttachServlet extends HttpServlet {
	private static final Logger LOGGER = Logger.getLogger(ShowSignetServlet.class);


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String cfrId = request.getParameter("cfrId");

		DiskFileItemFactory fac = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fac);
		upload.setHeaderEncoding("utf-8");
		List<FileItem> filelList = null;
		try {
			filelList = upload.parseRequest(request);
		} catch (FileUploadException ex) {
			LOGGER.error("解析Request出错！");
			return;
		}

		Iterator<FileItem> it = filelList.iterator();
		String name = ""; // 文件名
		String extName = ""; // 文件后缀名

		// 文件1名称，类型，文件
		String fileName = null;
		String fileType = null;
		InputStream file = null;
		long length1 = 0L;

		while (it.hasNext()) {
			FileItem item = it.next();
			if ("cfrId".equals(item.getFieldName())) {
				cfrId = item.getString();
			}
			if (!item.isFormField()) {
				name = item.getName();// 上传的文件名称
				if (name == null || name.trim().equals("")) {
					continue;
				}
				// 文件后缀名
				if (-1 != name.lastIndexOf(".")) {
					extName = name.substring(name.lastIndexOf(".") + 1)
							.toLowerCase();
				}
				fileName = name;
				fileType = extName;
				file = item.getInputStream();
				length1 = item.getSize();
			}
		}

		String info = save(fileName, fileType, file, cfrId, length1);

		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(info);
		out.flush();
		out.close();
	}

	/**
	 * 方法描述：将上传的附件信息上传至数据库中<br/>
	 * 创建人：苏国庆 <br/>
	 * 创建时间：2011-12-19 下午07:17:17<br/>
	 * 
	 * @return
	 * @version 1.0
	 */
	private String save(String fileName, String fileType, InputStream file,
			String cfrId, long fileLength) {
		Connection conn = BaseDao.getConnection();
		PreparedStatement pstmt = null;
		StringBuilder sql = new StringBuilder();
		String guid = new BaseDao().getGUID();
		StringBuilder sb = new StringBuilder();
		try {
			

			sql.append("insert into attach (attachId,attachName,attachType,attachContent,journalId) values (?,?,?,?,?) ");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, guid);
			pstmt.setString(2, fileName);
			pstmt.setString(3, fileType);
			pstmt.setBinaryStream(4, file, (int) fileLength);
			pstmt.setString(5, cfrId);

			pstmt.executeUpdate();
			// 设置已经上传文件的信息，返回给客户端
			sb.append("{\"file\":\"").append(fileName)
					.append("\",\"attid\":\"").append(guid).append("\"}");
			LOGGER.error("执行SQL语句成功：" + sql);
		} catch (SQLException e1) {
			LOGGER.error("执行SQL语句出错：" + sql);
			e1.printStackTrace();
		} finally {
			try {
				if (null != file) {
					file.close();
				}
			} catch (IOException e) {
				LOGGER.error("关闭文件流错误");
				LOGGER.error(DataUtil.getStackTraceAsString(e));;
			}
			BaseDao.close(null, pstmt, conn);
		}
		return sb.toString();
	}
}
