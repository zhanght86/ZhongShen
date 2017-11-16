package com.hnzskj.common.batch;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import com.hnzskj.common.BaseDao;

public class BatchAttach {
	private static final Logger LOOGER = Logger.getLogger(BatchAttach.class);
	public boolean bath(String attachsql, String sql, Map<String, String> map,
			int type) throws SQLException {
		
		List<InputStreamDocSWF> list = new ArrayList<InputStreamDocSWF>();
		Iterator<String> iterator = map.keySet().iterator();
		System.out.println(map);
		while (iterator.hasNext()) {

			String str = iterator.next();// swf
			String temp = map.get(str); // txt
			String name = temp.substring(temp.lastIndexOf("\\") + 1, temp.lastIndexOf("."));
			
			String filename = temp.substring(temp.lastIndexOf("\\") + 1, temp.length());
			System.out.println(filename);

			try {

				File doc = new File(temp);

				int doclentgh = (int) doc.length();
				File swf = new File(str);
				int swflentgh = (int) swf.length();
				FileInputStream docInput = new FileInputStream(doc);
				FileInputStream swfInput = new FileInputStream(swf);

				list.add(new InputStreamDocSWF(docInput, swfInput, name, doclentgh, swflentgh,filename));

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("错误");
			}

		}

		Connection con = BaseDao.getConnection();
		PreparedStatement attachpst = null;
		PreparedStatement pst = null;
		// 关闭事务自动提交
		try {
			con.setAutoCommit(false);

			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SS");
			TimeZone t = sdf.getTimeZone();
			t.setRawOffset(0);
			sdf.setTimeZone(t);
			Long startTime = System.currentTimeMillis();
			attachpst = con.prepareStatement(attachsql);
			pst = con.prepareStatement(sql);
			String uuid = "";
			for (InputStreamDocSWF docswf : list) {
				uuid = new BaseDao().getGUID();
				pst.setString(1, new BaseDao().getGUID());
				pst.setString(2, docswf.getName());
				pst.setString(3, docswf.getName());
				pst.setString(4, uuid);

				attachpst.setString(1, uuid);
				attachpst.setString(2, docswf.getFileName());
				attachpst.setBinaryStream(3, docswf.getDoc(), docswf .getDoclentgh());
				attachpst.setBinaryStream(4, docswf.getSwf(), docswf .getSwflentgh());
				attachpst.addBatch();
				pst.addBatch();

			}

			// 执行批量更新
			attachpst.executeBatch();
			pst.executeBatch();
			// 语句执行完毕，提交本事务
			con.commit();
			Long endTime = System.currentTimeMillis();
			System.out.println("用时：" + sdf.format(new Date(endTime - startTime)));
			return true;
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false;
		} finally {
			attachpst.close();
			pst.close();
			con.close();
		}

	}

}
