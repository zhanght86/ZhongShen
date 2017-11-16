/*
 * @项目名称: OA
 * @文件名称: RcCodeServiceImpl.java
 * @日期: 2012-8-21
 * @版权: 2012 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.service.system.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.system.RcCode;
import com.hnzskj.persist.dao.system.RcCodeDao;
import com.hnzskj.service.system.RcCodeService;

  /**        
 * 
 * 类名称：RcCodeServiceImpl
 * 类描述：<br/>
 * 创建人：郑辉  <br/>
 * 创建时间：2012-8-21 上午11:34:17 
 * 修改人：Administrator
 * 修改时间：
 * 修改备注：   
 * @version   1.0     
 */

public class RcCodeServiceImpl implements RcCodeService {
	private RcCodeDao rcDao;
	/**
	 * @return the rcDao
	 */
	public RcCodeDao getRcDao() {
		return rcDao;
	}

	/**
	 * @param rcDao the rcDao to set
	 */
	public void setRcDao(RcCodeDao rcDao) {
		this.rcDao = rcDao;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.RcCodeService#addInfo(com.hnzskj.persist.bean.system.RcCode)
	 */
	@Override
	public int addInfo(RcCode rc) {
		// TODO Auto-generated method stub
		return rcDao.addInfo(rc);
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.RcCodeService#findParents(com.hnzskj.common.Page, com.hnzskj.persist.bean.system.RcCode)
	 */
	@Override
	public Page<RcCode> findParents(Page<RcCode> page, RcCode rc) {
		// TODO Auto-generated method stub
		String fields = "rc_id,rc_no,rc_name,rc_order,rc_desc,rc_type,rc_querySql,rc_Object";
		String sqlCondition=" where rc_parent is null";

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("rc_order", "desc");

		page = this.rcDao.findParents(page, fields, sqlCondition, null, orderby);
		return page;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.RcCodeService#findSonsByParent(java.lang.String)
	 */
	@Override
	public List<RcCode> findSonsByParent(String parentId) {
		// TODO Auto-generated method stub
		return rcDao.findSonsByParent(parentId);
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.RcCodeService#updateInfo(com.hnzskj.persist.bean.system.RcCode)
	 */
	@Override
	public int updateInfo(RcCode rc) {
		// TODO Auto-generated method stub
		return rcDao.updateInfo(rc);
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.RcCodeService#findInfo(java.lang.String)
	 */
	@Override
	public RcCode findInfo(String rcId) {
		// TODO Auto-generated method stub
		return rcDao.findRcCodeInfo(rcId);
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.RcCodeService#deleteByParentInfo(java.lang.String)
	 */
	@Override
	public int deleteByParentInfo(String rcId) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.RcCodeService#deleteInfo(java.lang.String)
	 */
	@Override
	public int deleteInfo(String rcId) {
		// TODO Auto-generated method stub
		return rcDao.deleteInfo(rcId);
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.RcCodeService#updateSonInfo(java.lang.String)
	 */
	@SuppressWarnings("static-access")
	@Override
	public int updateSonInfo(String jsonStr) {
		// TODO Auto-generated method stub
		JSONArray json =JSONArray.fromObject(jsonStr);
		@SuppressWarnings("unused")
		List<RcCode> is =new ArrayList<RcCode>();
		System.out.println(json.size());
		for(int i =0;i<json.size();i++){
			//Items t =(Items)json.get(i);
			
			
			JSONObject os =(JSONObject)json.get(i);
			Object o =os.toBean(os, RcCode.class);
			RcCode rc =(RcCode)o;
			if(rc.getClassType()==1){
				rcDao.addInfo(rc);
			}
			if(rc.getClassType()==2){
				rcDao.updateInfo(rc);
			}
			
			if(rc.getClassType()==3){
				rcDao.deleteInfo(rc.getRc_Id());
			}
			
			
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.RcCodeService#findRcCodeByNoInfo(java.lang.String)
	 */
	@Override
	public RcCode findRcCodeByNoInfo(String rcNo) {
		// TODO Auto-generated method stub
		return rcDao.findRcCodeByNoInfo(rcNo);
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.RcCodeService#getCodeObjects(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Object> getCodeObjects(String sql, String objClass) {
		// TODO Auto-generated method stub
		return rcDao.getCodeObjects(sql, objClass);
	}

}
