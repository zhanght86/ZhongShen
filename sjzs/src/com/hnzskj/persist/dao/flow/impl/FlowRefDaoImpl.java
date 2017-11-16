package com.hnzskj.persist.dao.flow.impl;

import java.sql.Timestamp;
import java.util.List;

import com.hnzskj.common.BaseDao;
import com.hnzskj.persist.bean.flow.FlowRef;
import com.hnzskj.persist.dao.flow.FlowRefDao;


public class FlowRefDaoImpl extends BaseDao implements FlowRefDao {

	@Override
	public int addFlowRef(FlowRef flowRef) {
		String sql = "insert into sjdh_reference (id,sjdhId,xmlId,modeId,attachId,fangFaId," +
				     "deleteflag,updatedate,fangFaUrl,attachUrl,fangFaName,attachName) " +
				     "values (?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {this.getGUID(),flowRef.getSjdhId(),flowRef.getXmlId(),flowRef.getModeId(),
						   flowRef.getAttachId(),flowRef.getFangFaId(),0,
						   new Timestamp(System.currentTimeMillis()),flowRef.getFangFaUrl(),
						   flowRef.getAttachUrl(),flowRef.getFangFaName(),flowRef.getAttachName()};
		return update(sql,params);
	}

	@Override
	public int delFlowRef(String id) {
		String sql = "update sjdh_reference set deleteflag = -1 , updatedate = ? where id = ?";
		return this.update(sql, new Object[]{new Timestamp(System.currentTimeMillis()),id});
	}

	@Override
	public FlowRef getFlowRefById(String id) {
		String sql = "select * from sjdh_reference where id = ?";
		return (FlowRef)this.get(sql, FlowRef.class, new Object[]{id});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FlowRef> getFlowRefByXmlId(String xmlId) {
		String sql = "select * from sjdh_reference where xmlId = ? and deleteflag in (0,1)";
		return (List<FlowRef>)this.query(sql, FlowRef.class, new Object[]{xmlId});
	}

	@Override
	public int updFlowRef(FlowRef flowRef) {
		String sql = "update sjdh_reference set attachId = ?, fangFaId = ?," +
				     "deleteflag = ?, updatedate = ?, fangFaUrl = ?, attachUrl = ?, fangFaName = ?, attachName = ? " +
				     "where modeId = ? and xmlId = ?";
		Object[] params = {//flowRef.getSjdhId(),flowRef.getXmlId(),flowRef.getModeId(),
				   flowRef.getAttachId(),flowRef.getFangFaId(),1,
				   new Timestamp(System.currentTimeMillis()),flowRef.getFangFaUrl(),
				   flowRef.getAttachUrl(),flowRef.getFangFaName(),flowRef.getAttachName(),
				   flowRef.getModeId(),flowRef.getXmlId()};
		return this.update(sql, params);
	}

	@Override
	public int delFlowRefBySjdhId(String sjdhId) {
		String searchSql = "select id from sjdh_reference where sjdhId = ?";
		List<String> ids = this.getListSingleValue(searchSql, new Object[]{sjdhId});
		int result = 0;
		if(ids!=null&&ids.size()>0){
			StringBuffer sb = new StringBuffer();
			for(String id : ids){
				sb.append("'"+id+"',");
			}
			sb.deleteCharAt(sb.length()-1);
			String delSql = "update sjdh_reference set deleteflag = -1 , updatedate = '"+ new Timestamp(System.currentTimeMillis()) +"' where id in (" + sb.toString() + ")";
			result = this.update(delSql, null);
		}
		return result;
	}

	@Override
	public List<String> getAttachIds(String sjdhId) {
		String sql = "select attachid from sjdh_reference where sjdhId = ?";
		return this.getListSingleValue(sql, new Object[]{sjdhId});
	}

	@Override
	public FlowRef getFlowRefByXmlIdModeId(String xmlId, String modeId) {
		String sql  = "select id,sjdhId,xmlId,modeId,attachId,fangFaId,deleteflag,updatedate,fangfaUrl,attachUrl,fangFaName,attachName from sjdh_reference where xmlId=? and modeId=?;";
		Object[] params = new Object[]{xmlId,modeId};
		return (FlowRef)get(sql, FlowRef.class, params);
	}

}
