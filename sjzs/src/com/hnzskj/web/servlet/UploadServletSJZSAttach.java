/*
 * @项目名称: tree
 * @文件名称: UploadServlet.java
 * @日期: 2011-12-19
 * @版权: 2011 河南中审科技有限公司 
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
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


@SuppressWarnings("serial")
public class UploadServletSJZSAttach extends HttpServlet {
	private static final Logger LOGGER = Logger.getLogger(UploadServletSJZSAttach.class);
	private static boolean isPross = false;
	String systempath = "";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
	
		System.out.println("调用");
	
		String updateDataPath = request.getSession().getServletContext().getRealPath("/plugins/update/attach/");
		System.out.println(updateDataPath);

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
				try {
					file = item.getInputStream();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// length1 = item.getSize();
			}
		}
		try {
			Long temptime = System.currentTimeMillis();
			File temp = new File(updateDataPath + "\\" + temptime+"_"+name);
			FileOutputStream out = new FileOutputStream(temp);
			byte[] temp1 = new byte[1024];
			int index = 0;
			while ((index = file.read(temp1)) != -1) {
				out.write(temp1, 0, index);
			}
			out.flush();
			file.close();
			out.close();

			response.setCharacterEncoding("utf-8");
			PrintWriter out1 = response.getWriter();
			StringBuffer sb = new StringBuffer();
			String attId = temptime+"";
			sb.append("{\"file\":\"").append(fileName).append("\",\"attid\":\"").append(attId).append("\"}");
			out1.print(sb.toString());
			out1.flush();
			out1.close();
		} catch (Exception e) {
			LOGGER.error(this.getClass());
		}

	}

}
