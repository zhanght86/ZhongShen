/*
 * @项目名称: htglxt
 * @文件名称: SMSDaoImpl.java
 * @日期: 2011-9-7
 * @版权: 2011 河南中审科技有限公司 
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.persist.dao.system.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.system.SMS;
import com.hnzskj.persist.dao.system.SMSDao;

/**        
 * 
 * 类名称：SMSDaoImpl     <br/>
 * 类描述：<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-9-7 上午11:19:33   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-9-7 上午11:19:33   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */
public class SMSDaoImpl extends BaseDao implements SMSDao{

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.SMSDao#delete(int)
	 */
	@Override
	public int delete(int sid) {
		String sql = "delete from smslog where sid=?";
		Object[] params = new Object[]{sid};
		return update(sql, params);
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.SMSDao#delete(java.io.Serializable[])
	 */
	@Override
	public int delete(Serializable... sids) {
		StringBuffer idStr = new StringBuffer("");
		if (sids != null && sids.length > 0) {
			for (Object id : sids ) {
				idStr.append((Integer)id).append(",");
			}
			idStr.deleteCharAt(idStr.length() - 1);
		}
		String sql = "delete from smslog where sid in ( " + idStr.toString() + ")";
		int result = update(sql, null);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.SMSDao#getById(int)
	 */
	@Override
	public SMS getById(int sid) {
		String sql = "select sid, uid,ucellphone, ssendTime, scontent, stype, sinfoId,success from smslog where sid = ?";
		SMS sms = (SMS) get(sql, SMS.class, new Object[]{sid});
		return sms;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.SMSDao#getCount(java.lang.String, java.lang.Object[])
	 */
	@Override
	public int getCount(String sqlCondition, Object[] params) {
		int result = 0;
		String sql = "select count(sid) from smslog " 
			+  ("".equals(sqlCondition) || null == sqlCondition ? " " : sqlCondition);
		result = (Integer) getSingleValue(sql, params);
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.SMSDao#isSendSMS(com.hnzskj.persist.bean.system.SMS)
	 */
	@Override
	public boolean isSendSMS(SMS sMS) {
		Integer result = 0;
		String sqlCondition = " where sinfoId = ? and stype = ? and uid = ?";
		Object[] params = new Object[]{sMS.getSinfoId(), sMS.getStype(), sMS.getUid()};
		result = getCount(sqlCondition, params);
		if (result > 0) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.SMSDao#save(com.hnzskj.persist.bean.system.SMS)
	 */
	@Override
	public int save(SMS sMS) {
		String sql = "insert into smslog (uid, ssendTime, scontent, stype, sinfoId, ucellphone, success ) values (?, ?, ?, ?, ?, ?, ?)";
		Object[] params = new Object[]{
			sMS.getUid(),
			sMS.getSsendTime(),
			sMS.getScontent(),
			sMS.getStype(),
			sMS.getSinfoId(),
			sMS.getUcellphone(),
			sMS.isSuccess()
		};
		return update(sql, params);
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.SMSDao#searchSMS(com.hnzskj.common.Page, java.lang.String, java.lang.String, java.lang.Object[], java.util.LinkedHashMap)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Page<SMS> searchSMS(Page<SMS> page, String fields,
			String sqlCondition, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {
		List<SMS> empls = new ArrayList<SMS>();//封装查询结果集
		Integer totalRecords = 0;//记录查询的总记录数
		String sql = "";//查询的sql语句
		String countSql = "";//查询总记录数的sql语句
		Page<SMS> spage = new Page<SMS>();//临时变量，如果在page为null的情况下使用
		
		sqlCondition =  ("".equals(sqlCondition) || null == sqlCondition) ? " " : sqlCondition;
		countSql = "select count(sid) from smslog " + sqlCondition;
		
		if ( page != null) {//如果需要分页
			spage = page;
			/*//获得分页语句
			sql = getPagingSql("smslog", fields, "sid", 
					(spage.getCurPage() - 1) * spage.getMaxResult(), 
					spage.getMaxResult(), sqlCondition, orderby);*/
			//查询结果集
			empls = query(sql, SMS.class, queryParams);
		} else {//如果不需要分页
			sql = "select " +fields + " from smslog " + sqlCondition + buildOrderBy(orderby);
			//查询结果集
			empls = query(sql, SMS.class, queryParams);
		}
		//查询总记录数
		totalRecords = Integer.valueOf(getSingleValue(countSql, queryParams).toString());
		//设置总记录数
		spage.setList(empls);
		//设置结果集
		spage.setTotalRecords(totalRecords);
		return spage;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.SMSDao#deleteByHtIdAndTypeCode(int, java.lang.String)
	 */
	@Override
	public int deleteByHtIdAndTypeCode(int hbid, String spfail) {
		String sql = "delete from smslog where sinfoId=? and stype=?";
		int result = 0;
		result = update(sql, new Object[]{hbid, spfail});
		return result;
	}

}
