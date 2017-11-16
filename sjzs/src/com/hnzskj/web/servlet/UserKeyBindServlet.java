package com.hnzskj.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hnzskj.common.BaseDao;

public class UserKeyBindServlet extends HttpServlet {
	/**        
	 * 类名称：AddressBookAction  在制作优盘时回写U盘的key，绑定U盘和用户   <br/>
	 * 类描述：<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-3-12 上午10:00:18  <br/>  
	 * 修改备注：     <br/>
	 * @version   1.0     
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(UserKeyBindServlet.class);
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userKey = request.getParameter("userKey");
		String productiuonDate = request.getParameter("productiuonDate");
		String saleDate = request.getParameter("saleDate");
		this.saveUserKey(userKey,productiuonDate,saleDate);
	}
	
	
	private int saveUserKey(String userKey,String productiuonDate,String saleDate) {
		Connection conn = BaseDao.getConnection();
		PreparedStatement pstmt = null;
		StringBuilder sql = new StringBuilder();
		int num = 0;
		try {

			sql.append("insert into sjzs_userKey (userKey,productiuonDate,saleDate) values (?,?,?) ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userKey);
			pstmt.setString(2, productiuonDate);
			pstmt.setString(3, saleDate);
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

}
