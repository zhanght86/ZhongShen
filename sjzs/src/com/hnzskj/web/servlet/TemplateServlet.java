/*
 * @项目名称: lwsj
 * @文件名称: RepTemplateServlet.java
 * @日期:2012-2-8
 * @版权: 2011 河南中审科技有限公司 
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

//import com.hnzskj.persist.bean.report.ReportTemplate;

/**
 * 
 * 类名称： RepTemplateServlet.java <br/>
 * 类描述：<br/>
 * 创建人：毛俊玲 <br/>
 * 创建时间：2012-2-8 <br/>
 * 修改人：Administrator <br/>
 * 修改时间：2012-2-8 <br/>
 * 修改备注： <br/>
 * 
 * @version 1.0
 */
@SuppressWarnings("serial")
public class TemplateServlet extends HttpServlet {
	private static final Logger LOGGER = Logger.getLogger(TemplateServlet.class);

	/**
	 * Constructor of the object.
	 */
	public TemplateServlet() {
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
		doPost(request, response);
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
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String repId = request.getParameter("tempId");
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
		/**
		 * 因为要对file文件进行两次操作，所以声明两个变量 存相同的值，避免一种操作对其值产生影响
		 */
		InputStream file = null;
		@SuppressWarnings("unused")
		InputStream file1 = null;
		int repSheet = 0;
		long length1 = 0L;

		while (it.hasNext()) {
			FileItem item = it.next();
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
				file = item.getInputStream(); // 用于添加数据到数据库
				length1 = item.getSize();
				/**
				 * 
				 file1 = item.getInputStream(); //用于供读取此xls文件的工作表的个数
				 * if(fileType.equals("xls")){ try {
				 * //此处用的是file1，调用save方法时一定记得不要用file1 jxl.Workbook rwb =
				 * Workbook.getWorkbook(file1); repSheet =
				 * rwb.getNumberOfSheets();
				 * System.out.println("Excel工作表的个数为：************************"
				 * +repSheet); } catch (BiffException e) { // TODO
				 * Auto-generated catch block log.error(DataUtil.getStackTraceAsString(e));; }
				 * 
				 * }
				 */
			}
		}

		String info = save(fileName, repSheet, file, length1, repId, fileType);
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(info);
		out.flush();
		out.close();
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

	/**
	 * 方法描述：将上传的附件信息上传至数据库中<br/>
	 * 创建人：苏国庆 <br/>
	 * 创建时间：2011-12-19 下午07:17:17<br/>
	 * 
	 * @return
	 * @version 1.0
	 */
	private String save(String fileName, int repSheet, InputStream file,
			long fileLength, String repId, String fileType) {
		Connection conn = BaseDao.getConnection();
		PreparedStatement pstmt = null;
		StringBuilder sql = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		@SuppressWarnings("unused")
		String guid = new BaseDao().getGUID();
		try {
			sql
					.append("update workTemplate set tempFile = ?,tempFileName = ?,tempFileType= ? where tempId = '"
							+ repId + "'");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setBinaryStream(1, file, (int) fileLength);
			pstmt.setString(2, fileName);
			pstmt.setString(3, fileType);

			pstmt.executeUpdate();
			// 设置已经上传文件的信息，返回给客户端
			sb.append("{\"fileName\":\"").append(fileName).append(
					"\",\"tempId\":\"").append(repId).append("\"}");
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

	/**
	 * 方法描述：获得指定数据库表中是否还有可以上传的文件<br/>
	 * 创建人：苏国庆 <br/>
	 * 创建时间：2011-12-10 上午10:44:44<br/>
	 * 
	 * @return
	 * @version 1.0
	 */
	/*
	 * public String getUploadFiled(String repId) { StringBuilder sql = new
	 * StringBuilder();
	 * sql.append("select repFileName from reportTemplate where repId = '"
	 * +repId+"'"); ReportTemplate rep = (ReportTemplate)new
	 * BaseDao().get(sql.toString(),ReportTemplate.class,null); if(rep==null){
	 * return null; } else{ return "-1"; } }
	 */
}