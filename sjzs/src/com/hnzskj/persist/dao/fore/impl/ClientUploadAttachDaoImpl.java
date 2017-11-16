package com.hnzskj.persist.dao.fore.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hnzskj.common.BaseDao;
import com.hnzskj.persist.bean.fore.ClientUploadAttachDTO;
import com.hnzskj.persist.dao.fore.ClientUploadAttachDao;

public class ClientUploadAttachDaoImpl extends BaseDao implements ClientUploadAttachDao {

	@Override
	public int delAttachById(String attid) {

		String sql = "delete from sjzs_clientuploadattach where attachId = '" + attid + "'";
		return this.update(sql, null);
	}

	@Override
	public ClientUploadAttachDTO findAttachById(String id) {
		String sql = "select * from sjzs_clientuploadattach where attachId = '" + id + "'";
		conn = getConnection();
		pstmt = null;
		rs = null;
		ClientUploadAttachDTO attach = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				attach = new ClientUploadAttachDTO();
				attach.setAttachId(rs.getString("attachId"));
				attach.setAttachName(rs.getString("attachName"));
//				attach.setAttachType(rs.getString("attachType"));
				attach.setAttachContentDoc(rs.getBinaryStream("attachContentDoc"));
				attach.setAttachContentSwf(rs.getBinaryStream("attachContentSwf"));
				attach.setUploadDate(rs.getDate("uploadDate").toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(rs, pstmt, conn);
		}
		return attach;
	}

	@Override
	public List<ClientUploadAttachDTO> findAttachByName(String name) {
		String sql = "select * from sjzs_clientuploadattach where attachName=?";
		return (List<ClientUploadAttachDTO>) this.query(sql, ClientUploadAttachDTO.class, new Object[]{name});
	}

	@Override
	public List<ClientUploadAttachDTO> findAttachByIds(String ids) {
		if(ids!=null&&!"".equals(ids)){
			String newIds = "";
			String[] temp = ids.split(",");
			for(int i=0;i<temp.length;i++){
				newIds += "'" + temp[i] + "', ";
			}
			newIds = newIds.substring(0,newIds.length()-2);
			String sql = "select * from sjzs_clientuploadattach where attachId in (" + newIds + ")";
			System.out.println("select attach:" + sql);
			conn = getConnection();
			pstmt = null;
			rs = null;
			List<ClientUploadAttachDTO> attachs = new ArrayList<ClientUploadAttachDTO>();
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					ClientUploadAttachDTO attach = new ClientUploadAttachDTO();
					attach.setAttachId(rs.getString("attachId"));
					attach.setAttachName(rs.getString("attachName"));
//					attach.setAttachType(rs.getString("attachType"));
					attach.setAttachContentDoc(rs.getBinaryStream("attachContentDoc"));
					attach.setAttachContentSwf(rs.getBinaryStream("attachContentSwf"));
					attach.setUploadDate(rs.getDate("uploadDate").toString());
					attachs.add(attach);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				close(rs, pstmt, conn);
			}
			return attachs;
		}
		
		return null;
	}

}
