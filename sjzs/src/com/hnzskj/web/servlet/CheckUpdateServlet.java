package com.hnzskj.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.Constant;
import com.hnzskj.persist.bean.sjzs.UpdateDataLog;
import com.hnzskj.persist.bean.sjzs.UserKeyDTO;
import com.hnzskj.persist.dao.sjzs.impl.UpdateDataLogDaoImpl;
import org.apache.log4j.Logger;


public class CheckUpdateServlet extends HttpServlet {
	private static final  Logger LOGGER = Logger.getLogger(CheckUpdateServlet.class);
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		
		String flag = request.getParameter("flag");
		
		String retrunString = "";
		
		if(null !=flag && flag.trim().equals("deadLine")){//检测是否过期
			
			String userKeyId = request.getParameter("userKeyId");// 客户端数据版本
			retrunString = this.checkDeadLine(userKeyId);
			
		}else if(null !=flag && flag.trim().equals("P")){//检测程序更新
			String projectV = request.getParameter("projectV");// 客户端程序版本；
			retrunString = this.checkProject(projectV);
			     
		}else if(null !=flag && flag.trim().equals("D")){//检测数据更新
			String dataV = request.getParameter("dataV");// 客户端数据版本
			retrunString = this.checkData(dataV);
		}else{
			
			out.write("错误请求");
		}
		out.write(retrunString);
		out.flush();
		out.close();
	}

	
	private  String checkProject(String projectV){
		// 获取程序的最新版本
		UpdateDataLogDaoImpl updateDataLogDaoImpl = new  UpdateDataLogDaoImpl();
		UpdateDataLog updateDataLog = updateDataLogDaoImpl.selectNewVersionByType(0);
		String newVersionName = "";
		if (updateDataLog != null) {
			newVersionName = updateDataLog.getFileName();
		}else{
			newVersionName = "N";
		}
		return newVersionName;
		
	}
	
	private  String checkData(String dataV){
		dataV = dataV.substring(1);
		
		ArrayList<String> fileList = getUpdateFileName(dataV);
		
		StringBuffer fileName = new StringBuffer();
		if(fileList.size() <= 0){
			fileName.append("N");
		}else{
			for (int i = 0; i < fileList.size(); i++) {
				if(i != fileList.size()-1){
					fileName.append(fileList.get(i));
					fileName.append(";");
				}else{
					fileName.append(fileList.get(i));
				}
			}
		}
		
		
		return fileName.toString();
		
	}
	
	private ArrayList<String> getUpdateFileName(String oldVersion){
		ArrayList<String> list = new ArrayList<String>();
		Connection conn = BaseDao.getConnection();
		PreparedStatement pstmt = null;
		StringBuilder sql = new StringBuilder();
		ResultSet rs = null;
		try {

			sql.append("select DISTINCT fileName from updatedatalog where oldVersion>=? ORDER BY filename asc");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, oldVersion);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
			// 设置已经上传文件的信息，返回给客户端
			LOGGER.info("执行SQL语句成功：" + sql);
		} catch (SQLException e1) {
			LOGGER.error("执行SQL语句出错：" + sql);
			e1.printStackTrace();
		} finally {
			BaseDao.close(null, pstmt, conn);
		}
		
		return list;
	}
	private  String checkDeadLine(String userKeyId){
		
		String dateFlag = "-1";
		String userKeyDate = this.getUserKeyDate(userKeyId);
		Date currentDate = new Date();
		
		if (userKeyDate == null) {// 未销售
			dateFlag = "-1";
		} else {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			ParsePosition pos = new ParsePosition(0);
			ParsePosition pos1 = new ParsePosition(0);
			Date dt1 = formatter.parse(userKeyDate, pos);
			Date dt2 = formatter.parse(currentDate.toLocaleString(), pos1);
			long l = dt1.getTime() - dt2.getTime();
			if (l > Constant.userKeydeadLine) {// 过期 365天
				dateFlag = "0";
			} else {// 正常使用
				dateFlag = "1";
			}
		}
		return dateFlag;
		
	}
	
	private String getUserKeyDate(String userkeyId) {
		Connection conn = BaseDao.getConnection();
		PreparedStatement pstmt = null;
		StringBuilder sql = new StringBuilder();
		ResultSet rs = null;
		UserKeyDTO userKeyDTO = new UserKeyDTO();
		try {

			sql.append("select userKey,productionDate,saleDate,note1,note2 from sjzs_userKey  where id=? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userkeyId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				userKeyDTO = new UserKeyDTO();
				userKeyDTO.setUserKey(rs.getString(1));
				userKeyDTO.setProductionDate(rs.getString(2));
				userKeyDTO.setSaleDate(rs.getString(3));
				userKeyDTO.setNote1(rs.getString(4));
				userKeyDTO.setNote2(rs.getString(5));
			}
			// 设置已经上传文件的信息，返回给客户端
			LOGGER.info("执行SQL语句成功：" + sql);
		} catch (SQLException e1) {
			LOGGER.error("执行SQL语句出错：" + sql);
			e1.printStackTrace();
		} finally {
			BaseDao.close(null, pstmt, conn);
		}

		return userKeyDTO.getSaleDate();
	}

}
