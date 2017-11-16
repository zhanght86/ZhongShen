/**
 * flow
 *com.hnzskj.dao.flow
 *2012-4-62012上午11:19:18
 *郑辉
 *
 */
package com.hnzskj.persist.dao.flow.impl;

import java.sql.Timestamp;
import java.util.List;

import com.hnzskj.common.BaseDao;
import com.hnzskj.persist.bean.flow.*;
import com.hnzskj.persist.dao.flow.TacheDao;

/**
 *创建人：郑辉
 *描述： 	
 *创建时间：2012-4-6 上午11:19:18
 *修改人：郑辉
 *修改时间：
 */
public class TacheDaoImpl extends BaseDao implements TacheDao{
	/**
	 *创建人：郑辉
	 *描述：  该方法用来保存环节实例，如果存在，此模板下的环节，那么先删除	
	 *创建时间：2012-4-7 上午09:28:34
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： int
	 */
	@SuppressWarnings("unchecked")
	public int addTacheInfo(Tache tache){
		String sql ="select Template_id from sys_tache where template_id =? and tache_id =?";
		Object [] param ={tache.getTemplate_id(),tache.getTache_id()};
		List list =this.query(sql, Tache.class, param);
		if(list.size()!=0){
			sql ="delete from sys_tache where template_id =? and tache_id =?";
			this.update(sql, param);
		}
		sql ="insert into SYS_TACHE (" +
				"TEMPLATE_ID," +
				"TACHE_ID," +
				"TACHE_TYPE," +
				"TACHE_NAME," +
				"TACHE_DESCRIPTION," +
				"ROLES_ID," +
				"ROLES_NAME," +
				"DEPT_ID," +
				"EMP_NAMES," +
				"guid," +
				"DAYS," +
				"HOURS," +
				"MINUTES," +
				"MEMO," +
				"XPOSITION," +
				"YPOSITION," +
				"is_back," +
				"DOTTYPE," +
				"nameModel,attach ,updatedate) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object [] params={
				tache.getTemplate_id(),
				tache.getTache_id(),
				tache.getTache_type(),
				tache.getTache_name(),
				tache.getTache_DESCRIPTION(),
				tache.getRoles_id(),
				tache.getRoles_name(),
				tache.getDept_id(),
				tache.getEmp_names(),
				tache.getGuid(),
				tache.getDays(),
				tache.getHours(),
				tache.getMinutes(),
				tache.getMemo(),
				tache.getXposition(),
				tache.getYposition(),
				tache.getIs_back(),
				tache.getDotType(),
				tache.getNameModel(),
				tache.getAttach(),
				new Timestamp(System.currentTimeMillis())
				};
		int num =this.update(sql, params);
		if(0 != num) return num;
		return 0;
	}
	
	/**
	 *创建人：郑辉
	 *描述： 	通过模板编号获取对象列表
	 *创建时间：2012-4-9 下午02:01:59
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： List<Tache>
	 */
	@SuppressWarnings("unchecked")
	public List<Tache> getTacheByTempId(int tempno){
		List<Tache> tacheList =null;
		String sql ="select template_id,tache_id,tache_type,tache_name,TACHE_DESCRIPTION,emp_names,guid,dottype,days,hours,minutes,MEMO,is_back,XPOSITION,YPOSITION ,attach, namemodel,roles_id,roles_name from sys_tache where template_id=? order by TACHE_ID asc";
		Object [] params ={tempno};
		tacheList=this.query(sql, Tache.class, params);
		if(null !=tacheList&&0!=tacheList.size()){
			return tacheList;
		}
		return null;
	}
	
	/**
	 *创建人：郑辉
	 *描述： 通过模板编号和环节编号，获取环节对象	
	 *创建时间：2012-4-9 下午02:02:17
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： Tache
	 */
	public Tache getTacheByTempIdAndTacheId(int tempno,int tacheId){
		String sql ="select template_id,tache_id,tache_type,tache_name,TACHE_DESCRIPTION,attach,emp_names,guid,dottype,days,hours,minutes,MEMO,is_back,XPOSITION,YPOSITION,nameModel,roles_id from sys_tache where template_id=? and tache_id=? order by TACHE_ID asc";
		Object [] params ={tempno,tacheId};
		Tache tache=(Tache)this.get(sql, Tache.class, params);		
		return tache;
	}
	
	public int deleteTacheInfo(int tempno){
		String sql ="delete from sys_tache where template_id=?";
		Object [] params ={tempno};
		int num =this.update(sql, params);
		return num;
	}
	
	public int updateAttatch(String template_no,String tacheId,String attachId){
		String sql ="update sys_tache set attach =? ,updatedate = ? where template_id =? and tache_Id = ?";
		Object [] params ={attachId,new Timestamp(System.currentTimeMillis()),template_no,tacheId};
		return this.update(sql, params);
	}
}
