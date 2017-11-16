/*
 * @项目名称: tree
 * @文件名称: UploadServlet.java
 * @日期: 2011-12-19
 * @版权: 2011 河南中审科技有限公司 
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.web.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
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

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.DataUtil;
import com.hnzskj.web.sjzs.FlashPaper;
@SuppressWarnings("serial")
public class UploadServletSJZS extends HttpServlet {
	private static final Logger LOGGER = Logger.getLogger(UploadServletSJZS.class);
	private static  boolean isPross = false;
	String prossInitPath = "";
	String systempath = "";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String type = request.getParameter("type");
		String fore = request.getParameter("fore");
		String updateProjectPath = request.getSession().getServletContext().getRealPath("/plugins/update/project/");
		String updateDataPath = request.getSession().getServletContext().getRealPath("/plugins/update/data/");
		
		String imageFileName = "";

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
		InputStream fileDoc = null;
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
				file = item.getInputStream();
				fileDoc = item.getInputStream();
				length1 = item.getSize();
			}
		}
		
		String info = "";
		if(null!=type && !type.trim().equals("")){//数据或程序更新上传，不需要保存到数据库中
			StringBuilder sb = new StringBuilder();
			boolean isHave = false;
			File dirFile = null;
			if(type.trim().equals("0")){//程序
				dirFile = new File(updateProjectPath + "/" + fileName);
			}else{//数据或数据结构
				dirFile = new File(updateDataPath + "/" + fileName);
			}
			//删除临时文件
			if(type.trim().equals("0")){//程序或数据结构
				String newVersion = fileName.split("_")[0];//  V1.0_sjzs.zip
				String attId = String.valueOf(new Date().getTime());
				sb.append("{\"file\":\"").append(fileName).append("\",\"attid\":\"").append(attId).append("\"}");
				FlashPaper.copyFile(file, dirFile);	
				
				this.saveUpdateLog(fileName, null, newVersion, 0);
			}else{//数据
				String newVersion = fileName.split("TO")[1].split("_")[0];//  V1.0TOV1.1_sql.zip
				String oldVersion = fileName.split("TO")[0];// 
				newVersion = newVersion.substring(1);
				oldVersion = oldVersion.substring(1);

				File file2 = new File(updateDataPath);
				String attId = String.valueOf(new Date().getTime());
				isHave = file2.exists();
				if(isHave){//如果文件已经存在
					sb.append("{\"file\":\"").append("haved").append("\",\"attid\":\"").append(attId).append("\"}");
				}else{
					sb.append("{\"file\":\"").append(fileName).append("\",\"attid\":\"").append(attId).append("\"}");

				}
				if(!isHave){
					FlashPaper.copyFile(file, dirFile);		
					this.saveUpdateLog(fileName, oldVersion, newVersion, 1);
				}
			}
			info = sb.toString();
			
				
		}else{//附件上传需要保存到数据库中
			String path = request.getSession().getServletContext().getRealPath("/files/sjzs/") ;
			imageFileName = String.valueOf(new Date().getTime());
			File dirFile = new File(path + "/doc/" + imageFileName + "."+ fileType);
			try {
				dirFile.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			//删除临时文件
//			FlashPaper.delAllFile(path + "/doc/");
//			FlashPaper.delAllFile(path + "/swf/");
			FlashPaper.copyFile(file, dirFile);
			
			systempath=request.getSession().getServletContext().getRealPath("WEB-INF\\config\\system.xml");
			prossInitPath = request.getSession().getServletContext().getRealPath("/plugins/FlashPaper2.2/");
//			if(!isPross){
//				initProgress();
//			}
//			FlashPaper.converter(imageFileName, imageFileName + "."+ fileType, path,prossInitPath,length1);
			FlashPaper.converter(imageFileName, imageFileName + "."+ fileType, path,systempath,length1);
			
			
			String swfPathString = path+ "/swf/" + imageFileName + ".swf";//转换成swf文件的路径
			
			File swfFile = new File(swfPathString);
			InputStream fileSwf= new FileInputStream(swfFile);
			
		    info = save(fileName,fileDoc,fileSwf,length1,swfFile.length(), fore);
		}
		System.err.println("**********"+info);
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(info);
		out.flush();
		out.close();
	}


	/**
	 * 方法描述：将上传的数据更新包版本信息更新到数据库<br/>
	 * 创建人：wxz <br/>
	 * 创建时间：2011-12-19 下午07:17:17<br/>
	 * 
	 * @return
	 * @version 1.0
	 */
	private int saveUpdateLog(String fileName, String oldVersion, String newVersion, int type) {
		
		
		this.delete(fileName);//防止数据库汇总有相同的文件名
		Connection conn = BaseDao.getConnection();
		PreparedStatement pstmt = null;
		StringBuilder sql = new StringBuilder();
		int num = 0; 
		try {

			sql.append("insert into updateDataLog (fileName,oldVersion,newVersion,type,uploadDate,updatedate) values (?,?,?,?,?,?) ");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, fileName);
			pstmt.setString(2, oldVersion);
			pstmt.setString(3, newVersion);
			pstmt.setInt(4, type);
			pstmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			pstmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			num = pstmt.executeUpdate();
			// 设置已经上传文件的信息，返回给客户端
			LOGGER.error("执行SQL语句成功：" + sql);
		} catch (SQLException e1) {
			LOGGER.error("执行SQL语句出错：" + sql);
			e1.printStackTrace();
		} finally {
			BaseDao.close(null, pstmt, conn);
		}
		return num;
	}
	/**
	 * 
	 * 描述：删除程序更新的记录，因为程序始终只有一个更新包<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-3-14 下午02:19:26 <br/>  
	 * @version   1.0
	 */
	private int delete(String fileName) {
		BaseDao baseDao = new BaseDao();
		String sql = "delete from updateDataLog where fileName = '"+ fileName + "' ";
		return baseDao.update(sql, null);

	}
	
	
	
	/**
	 * 方法描述：将上传的附件信息上传至数据库中<br/>
	 * 创建人：苏国庆 <br/>
	 * 创建时间：2011-12-19 下午07:17:17<br/>
	 * 
	 * @return
	 * @version 1.0
	 */
	private String save(String fileName, InputStream fileDoc, InputStream fileSwf, long fileLengthDoc,long lengthSwf ,String fore) {
		Connection conn = BaseDao.getConnection();
		PreparedStatement pstmt = null;
		StringBuilder sql = new StringBuilder();
		String guid = new BaseDao().getGUID();
		StringBuilder sb = new StringBuilder();
		try {
			if(fore == null || "".equals(fore)){
				sql.append("insert into sjzs_attach (attachId,attachName,attachContentDoc,attachContentSwf,uploadDate,updatedate) values (?,?,?,?,?,?) ");
			}else{
				sql.append("insert into sjzs_clientuploadattach (attachId,attachName,attachContentDoc,attachContentSwf,uploadDate) values (?,?,?,?,?) ");
			}

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, guid);
			pstmt.setString(2, fileName);
			pstmt.setBinaryStream(3, fileDoc, (int) fileLengthDoc);
			pstmt.setBinaryStream(4, fileSwf, (int) lengthSwf);
			pstmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			if(fore == null || "".equals(fore)){
				pstmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			}
			pstmt.executeUpdate();
			// 设置已经上传文件的信息，返回给客户端
			sb.append("{\"file\":\"").append(fileName).append("\",\"attid\":\"").append(guid).append("\"}");
			LOGGER.error("执行SQL语句成功：" + sql);
		} catch (SQLException e1) {
			LOGGER.error("执行SQL语句出错：" + sql);
			e1.printStackTrace();
		} finally {
			try {
				if (null != fileSwf) {
					fileSwf.close();
				}
				if (null != fileDoc) {
					fileDoc.close();
				}
			} catch (IOException e) {
				LOGGER.error("关闭文件流错误");
				LOGGER.error(DataUtil.getStackTraceAsString(e));;
			}
			BaseDao.close(null, pstmt, conn);
		}
		return sb.toString();
	}
	
	public void initProgress() {
		isPross = true;
		Runtime pro = Runtime.getRuntime();
		Process ps;
	    //prossInitPath = request.getSession().getServletContext().getRealPath("/plugins/FlashPaper2.2/");
		String initString= prossInitPath+"/init/init.bat"; 
		try {
			ps = pro.exec(initString);
			ps.waitFor();
			ps.destroy();
		} catch (Exception e) {
			try {
				isPross = false;
				throw new Exception("文件转换工具初始化异常！请刷新页面");
			} catch (Exception e1) {
				isPross = false;
				e1.printStackTrace();
			}
		} 
	}
}
