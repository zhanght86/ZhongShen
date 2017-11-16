package com.hnzskj.web.sjzs;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.hnzskj.common.BaseDao;
import com.hnzskj.web.BaseAction;

@SuppressWarnings("serial")
public class UserKeyBingAction extends BaseAction {
	private static final Logger LOGGER = Logger.getLogger(UserKeyBingAction.class);
	
	private String userKey;
	
	private String productionDate;
	
	private String saleDate;
	
	private String userKeyId;
	

	
	/**
	 * @return the userKeyId
	 */
	public String getUserKeyId() {
		return userKeyId;
	}

	/**
	 * @param userKeyId the userKeyId to set
	 */
	public void setUserKeyId(String userKeyId) {
		this.userKeyId = userKeyId;
	}

	/**
	 * @return the userKey
	 */
	public String getUserKey() {
		return userKey;
	}

	/**
	 * @param userKey the userKey to set
	 */
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	/**
	 * @return the productionDate
	 */
	public String getProductionDate() {
		return productionDate;
	}

	/**
	 * @param productionDate the productionDate to set
	 */
	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}

	/**
	 * @return the saleDate
	 */
	public String getSaleDate() {
		return saleDate;
	}

	/**
	 * @param saleDate the saleDate to set
	 */
	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}

	public String gotoGetUserKeyPage(){
		return "getUserKeyPage";
	}
	
	
	
	public void saveUserKey(){
		Connection conn = BaseDao.getConnection();
		PreparedStatement pstmt = null;
		StringBuilder sql = new StringBuilder();
		StringBuilder deleteSql = new StringBuilder();
		PrintWriter printWriter = null;
		try {
			this.getResponse().setCharacterEncoding("utf-8");
			printWriter = this.getResponse().getWriter();
			this.getResponse().setContentType("text/html");
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(this.userKeyId ==null || this.userKeyId.trim().equals("")){
			LOGGER.error("userKeyId保存失败：主键为空！" );
			printWriter.print("<script language='javascript'>  alert('保存失败！');history.go(-1);</script>");
		}else{
			deleteSql.append("  delete from sjzs_userkey  where userKey = '");
			deleteSql.append(this.userKey);
			deleteSql.append("' ");
			BaseDao baseDao = new BaseDao();
			baseDao.update(deleteSql.toString(), null);
		}
		
		int num = 0;
		try {

			sql.append("insert into sjzs_userkey (id,userKey,productionDate,saleDate) values (?,?,?,?) ");
			if(this.userKeyId ==null || this.userKeyId.trim().equals("") ){
				LOGGER.error("userKey保存失败：主键为空！" );
				printWriter.print("<script language='javascript'>  alert('保存失败！生成主键失败！');history.go(-1);</script>");
			}else if(userKey == null || userKey.trim().equals("")){
				LOGGER.error("userKey保存失败：主键为空！" );
				printWriter.print("<script language='javascript'>  alert('保存失败！请插入U盘！');history.go(-1);</script>");
			}
			else{
				pstmt = conn.prepareStatement(sql.toString());
				
				pstmt.setString(1, this.userKeyId);
				pstmt.setString(2, this.userKey);
				pstmt.setString(3, this.productionDate);
				pstmt.setString(4, this.saleDate);
				num = pstmt.executeUpdate();
				// 设置已经上传文件的信息，返回给客户端
				LOGGER.error("执行SQL语句成功：" + sql);
			}
		} catch (SQLException e1) {
			LOGGER.error("执行SQL语句出错：" + sql);
			e1.printStackTrace();
		} finally {
			BaseDao.close(null, pstmt, conn);
		}
		
		printWriter.print("<script language='javascript'>  alert('保存成功！');history.go(-1);</script>");
		printWriter.flush();
		printWriter.close();
	}
	
	public void checkSaveStatus() throws SQLException{
		Connection conn = BaseDao.getConnection();
		PreparedStatement pstmt = null;
		StringBuilder sql = new StringBuilder();
		int num = 0;
		boolean returnFlag = false;
		ResultSet rs  = null;
		try {

			sql.append(" select count(*) from  sjzs_userkey where id=? ");
			
			if(this.userKeyId ==null || this.userKeyId.trim().equals("")){
				LOGGER.error("userKey保存失败：主键为空！" );
			}else{
				pstmt = conn.prepareStatement(sql.toString());
				
				pstmt.setString(1, this.userKeyId);
			    rs = pstmt.executeQuery();
				while(rs.next()){
					num = rs.getInt(1);
				}
				// 设置已经上传文件的信息，返回给客户端
				LOGGER.info("执行SQL语句成功：" + sql);
			}
		} catch (SQLException e1) {
			LOGGER.error("执行SQL语句出错：" + sql);
			e1.printStackTrace();
		} finally {
			rs.close();
			BaseDao.close(null, pstmt, conn);
		}
		if(num>0){
			returnFlag = true;	
		}
		try {
			PrintWriter writer = this.getResponse().getWriter();
			writer.print(returnFlag);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
