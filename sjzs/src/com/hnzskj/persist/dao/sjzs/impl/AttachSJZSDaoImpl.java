package com.hnzskj.persist.dao.sjzs.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.hnzskj.common.BaseDao;
import com.hnzskj.persist.bean.attach.Attach;
import com.hnzskj.persist.dao.sjzs.AttachSJZSDao;

public class AttachSJZSDaoImpl extends BaseDao implements AttachSJZSDao {

	@Override
	public int delAttachs(String attachId) {
		// TODO Auto-generated method stub
//		String sql = "delete from sjzs_attach where attachId = '" + attachId + "'";
		String sql = "update sjzs_attach set deleteflag = -1 ,updatedate=? where attachId = '" + attachId + "'";
		Object[] params = new Object[]{new Timestamp(System.currentTimeMillis())};
		return this.update(sql, params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Attach> findAttachs(String attachId) {
		// TODO Auto-generated method stub
		List<Attach> list = new ArrayList<Attach>();
		String sql = "select attachId ,attachName,attachContentDoc,attachContentSwf,uploadDate from sjzs_attach where deleteflag in (0,1) and attachId = '"+attachId+"'";
		conn = getConnection();
		pstmt = null;
		rs = null;
		Attach attach = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				attach = new Attach();
				attach.setAttachId(rs.getString("attachId"));
				attach.setAttachName(rs.getString("attachName"));
//				attach.setAttachType(rs.getString("attachType"));
				attach.setAttachContentDoc(rs.getBinaryStream("attachContentDoc"));
				attach.setAttachContentSwf(rs.getBinaryStream("attachContentSwf"));
				attach.setUploadDate(rs.getDate("uploadDate").toString());
				list.add(attach);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public int delAttach(String attid) {
		// TODO Auto-generated method stub
//		String sql = "delete from sjzs_attach where attachId = '" + attid + "'";
		String sql = "update sjzs_attach set deleteflag = -1 ,updatedate=? where attachId = '" + attid + "'";
		Object[] params = new Object[]{new Timestamp(System.currentTimeMillis())};
		return this.update(sql, params);
	}
	/**
	 * 在上传附件时删除，物理删除
	 */
	@Override
	public int delAttachJS(String attid) {
		// TODO Auto-generated method stub
		String sql = "delete from sjzs_attach where attachId = '" + attid + "'";
		return this.update(sql, null);
	}


	
	@Override
	public Attach findAttachById(String cfrId) {
		List<Attach> list = new ArrayList<Attach>();
		String sql = "select attachId ,attachName,attachContentDoc,attachContentSwf,uploadDate from sjzs_attach where deleteflag in (0,1) and attachId = '"+cfrId+"'";
		 conn = getConnection();
		 pstmt = null;
		 rs = null;
		Attach attach = null;
		Attach returnAttach = new Attach();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				attach = new Attach();
				attach.setAttachId(rs.getString("attachId"));
				attach.setAttachName(rs.getString("attachName"));
//				attach.setAttachType(rs.getString("attachType"));
				attach.setAttachContentDoc(rs.getBinaryStream("attachContentDoc"));
				attach.setAttachContentSwf(rs.getBinaryStream("attachContentSwf"));
				attach.setUploadDate(rs.getDate("uploadDate").toString());
				list.add(attach);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(rs, pstmt, conn);
		}
		if(list.size()>0){
			returnAttach = list.get(0);
		}
		return returnAttach;
	}
	@SuppressWarnings("unchecked")
	public List<Attach> findAttachsById(String cfrId) {
//		String sql = "select * from sjzs_attach where attachId=?";
		String sql = "select * from sjzs_attach where deleteflag in (0,1) and attachId=?";
		return (List<Attach>) this.query(sql, Attach.class, new Object[]{cfrId});
	}

	@Override
	public int addAttach(Attach attach) {
		String sql = "insert into sjzs_attach(attachId,attachName,attachContentDoc,attachContentSwf,uploadDate,updatedate,deleteflag) values (?,?,?,?,?,?,?)";
		Object[] params = {attach.getAttachId(),attach.getAttachName(),attach.getAttachContentDoc(),
				           attach.getAttachContentSwf(),new Timestamp(System.currentTimeMillis()),
				           new Timestamp(System.currentTimeMillis()),0
						  };
		return this.update(sql, params);
	}

	@Override
	public int batchAddAttach(List<Attach> attachs) {
		String sql = "insert into sjzs_attach(attachId,attachName,attachContentDoc,attachContentSwf,uploadDate,updatedate,deleteflag) values (?,?,?,?,?,?,?)";
		Object[][] params = new Object[attachs.size()][];
		for(int i=0;i<attachs.size();i++){
			Attach attach = attachs.get(i);
			Object[] temp = {attach.getAttachId(),attach.getAttachName(),attach.getAttachContentDoc(),
				             attach.getAttachContentSwf(),new Timestamp(System.currentTimeMillis()),
				             new Timestamp(System.currentTimeMillis()),0
				             };
			params[i] = temp;
		}
		return this.updateBatch(sql, params, attachs.size());
	}
	
	@Override
	public int batchDeleteAttach(String ids, boolean phDel) {
		String sql = "";
		if(phDel){
			sql = "delete from sjzs_attach where attachId in (" + buildIds(ids) + ")";
		}else{
			sql = "update sjzs_attach set deleteflag = -1 , updatedate = '" + new Timestamp(System.currentTimeMillis()) + "' where attachId in (" + buildIds(ids) + ")";
		}
		return this.update(sql, null);
	}

	private String buildIds(String ids){
		String newIds = "";
		if(ids!=null&&!"".equals(ids)){
			String[] temp = ids.split(",");
			for(int i=0;i<temp.length;i++){
				newIds += "'" + temp[i] + "', ";
			}
			newIds = newIds.substring(0,newIds.length()-2);
		}
		return newIds;
	}
}
