/*
 * @项目名称: htglxt
 * @文件名称: SMSServiceImpl.java
 * @日期: 2011-9-7
 * @版权: 2011 河南中审科技有限公司 
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.service.system.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.system.SMS;
import com.hnzskj.persist.dao.system.SMSDao;
import com.hnzskj.service.system.SMSService;

/**        
 * 
 * 类名称：SMSServiceImpl     <br/>
 * 类描述：<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-9-7 上午11:39:03   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-9-7 上午11:39:03   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */
public class SMSServiceImpl implements SMSService {
	private SMSDao sMSDao;
	
	/**
	 * @return the sMSDao
	 */
	public SMSDao getsMSDao() {
		return sMSDao;
	}

	/**
	 * @param sMSDao the sMSDao to set
	 */
	public void setsMSDao(SMSDao sMSDao) {
		this.sMSDao = sMSDao;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.SMSService#add(com.hnzskj.persist.bean.system.SMS)
	 */
	@Override
	public boolean add(SMS sMS) {
		int result = 0;
		result = sMSDao.save(sMS);
		if (result > 0) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.SMSService#delete(int)
	 */
	@Override
	public boolean delete(int id) {
		int result = 0;
		result = sMSDao.delete(id);
		return result == 1 ? true : false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.SMSService#delete(java.io.Serializable[])
	 */
	@Override
	public boolean delete(Serializable... ids) {
		int result = 0;
		result = sMSDao.delete(ids);
		return result == ids.length ? true : false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.SMSService#deleteByHtIdAndTypeCode(int, java.lang.String)
	 */
	@Override
	public int deleteByHtIdAndTypeCode(int hbid, String spfail) {
		int result = 0;
		result = sMSDao.deleteByHtIdAndTypeCode(hbid, spfail);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.SMSService#findById(int)
	 */
	@Override
	public SMS findById(int sid) {
		SMS sMS = sMSDao.getById(sid);
		return sMS;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.SMSService#searchSMS(com.hnzskj.persist.bean.system.SMS, com.hnzskj.common.Page)
	 */
	@Override
	public Page<SMS> searchSMS(SMS sMS, Page<SMS> page) {
		StringBuffer sqlCondition = new StringBuffer(" where 1=1 ");
		List<Object> params = new ArrayList<Object>();//封装查询的字段所对应的值
		if ( null != sMS.getUid()) {
			sqlCondition.append(" and uid = ? ");
			params.add(sMS.getUid());
		}
		if (null != sMS.getSsendTime() ) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sqlCondition.append(" and ssendTime >= ? and ssendTime < ?");
			params.add(sdf.format( sMS.getSsendTime() ));
			long nextTime = sMS.getSsendTime().getTime() + 24 * 60 * 60 * 1000;
			params.add(sdf.format( new Date(nextTime) ));
		}
		if ( null != sMS.getScontent() && !"".equals(sMS.getScontent()) ) {
			sqlCondition.append(" and operation like ? ");
			params.add("%" + sMS.getScontent() + "%");
		}
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("ssendTime", "desc");
		page = sMSDao.searchSMS(page, "*", sqlCondition.toString(), params.toArray(), orderby);
		return page;
	}

}
