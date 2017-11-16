package com.hnzskj.common.batch;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import com.hnzskj.common.BaseDao;

public class BatchExecl {
	public boolean bath(String sql, String[][] size) {

		Connection con = BaseDao.getConnection();
		// 关闭事务自动提交
		PreparedStatement pst = null;
		try {
			con.setAutoCommit(false);

			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SS");
			TimeZone t = sdf.getTimeZone();
			t.setRawOffset(0);
			sdf.setTimeZone(t);
			Long startTime = System.currentTimeMillis();
			pst = con.prepareStatement(sql);
			for (int i = 0; i < size.length; i++) {
				pst.setObject(1, new BaseDao().getGUID());
				for (int j = 0; j < size[i].length; j++) {

					pst.setObject(j + 2, size[i][j]);

					// 把一个SQL命令加入命令列表

				}
				// System.out.println(pst.);
				pst.addBatch();

			}
			// 执行批量更新
			pst.executeBatch();
			// 语句执行完毕，提交本事务
			con.commit();
			Long endTime = System.currentTimeMillis();
			System.out.println("用时："
					+ sdf.format(new Date(endTime - startTime)));

			return true;
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {

			try {
				pst.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
