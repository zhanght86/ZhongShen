/*
 * @项目名称: OA
 * @文件名称: SoftWareItemsService.java
 * @日期: 2012-7-16
 * @版权: 2012 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.service.wflow.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.Page;
import com.hnzskj.common.annotation.Description;
import com.hnzskj.persist.bean.wflow.SoftWareFlow;
import com.hnzskj.persist.bean.wflow.SoftWareItems;
import com.hnzskj.persist.dao.wflow.SoftWareFlowDao;
import com.hnzskj.persist.dao.wflow.SoftWareItemsDao;
import com.hnzskj.service.wflow.SoftWareFlowService;

  /**        
 * 
 * 类名称：SoftWareItemsService
 * 类描述：<br/>
 * 创建人：郑辉  <br/>
 * 创建时间：2012-7-16 下午02:35:29 
 * 修改人：Administrator
 * 修改时间：
 * 修改备注：   
 * @version   1.0     
 */

public class SoftWareFlowServiceImpl implements SoftWareFlowService{
	private SoftWareFlowDao softWareFlowDao;
	private SoftWareItemsDao softWareItemsDao;
	/**
	 * @return the softWareFlowDao
	 */
	public SoftWareFlowDao getSoftWareFlowDao() {
		return softWareFlowDao;
	}

	/**
	 * @param softWareFlowDao the softWareFlowDao to set
	 */
	public void setSoftWareFlowDao(SoftWareFlowDao softWareFlowDao) {
		this.softWareFlowDao = softWareFlowDao;
	}

	/**
	 * @return the softWareItemsDao
	 */
	public SoftWareItemsDao getSoftWareItemsDao() {
		return softWareItemsDao;
	}

	/**
	 * @param softWareItemsDao the softWareItemsDao to set
	 */
	public void setSoftWareItemsDao(SoftWareItemsDao softWareItemsDao) {
		this.softWareItemsDao = softWareItemsDao;
	}

	@Override
	@Description("批量插入申请物品")
	public String addInfo(SoftWareFlow soft) {
		// TODO Auto-generated method stub
		int num=0;
		String guid =softWareFlowDao.addInfo(soft);
		if(!guid.equals("")||guid!=null){
			Object [] o1 =soft.getTep().split("ZSKJ__ZSKJ");
			//Object [] a =new Object[10];
			Object[][] params=new Object[o1.length][];
			
			for(int i =0 ;i<o1.length;i++){
				Object[] o2=o1[i].toString().split(",");
				params[i]=copyLength(o2,guid);
			}
			num =softWareItemsDao.addBatchInfo(params);
			if(num!=0) return guid;
		}
		
		return "";
	}
	/**
	 * 类描述：数组更新
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-7-16 下午04:32:45 
	 * 修改人：
	 * 修改时间：2012-7-16 下午04:32:45  
	 * 修改备注：   
	 * @version   1.0
	 */
	public Object [] copyLength(Object[] objs,String guid){
		Object [] obs =new Object[objs.length+1];
		for(int j =0;j<objs.length;j++){
			obs[j]=objs[j];
			obs[obs.length-1]=guid+"";
		}
		return obs;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.wflow.SoftWareFlowService#getInfo(int)
	 */
	@Override
	@Description("通过实例号获取对象")
	public SoftWareFlow getInfo(int instanceNo) {
		// TODO Auto-generated method stub
		SoftWareFlow soft =softWareFlowDao.getInfo(instanceNo);
		if(soft!=null){
			List<SoftWareItems> items=softWareItemsDao.getInfo(soft.getSf_id());
			soft.setItems(items);
		}
		return soft;
	}

  	@Override
	@Description("修改对象")
	public int updateInfo(SoftWareFlow soft) {
		// TODO Auto-generated method stub
		int num =softWareFlowDao.updateInfo(soft);
		if(num!=0){
			if((soft.getSf_id()!=null&&!soft.getSf_id().equals(""))&&!soft.getTep().equals("")){
				int result =softWareItemsDao.deleteInfo(soft.getSf_id());
				if(result !=0){		
					Object [] o1 =soft.getTep().split("ZSKJ__ZSKJ");
					//Object [] a =new Object[10];
					Object[][] params=new Object[o1.length][];
					
					for(int i =0 ;i<o1.length;i++){
						Object[] o2=o1[i].toString().split(",");
						params[i]=copyLength(o2,soft.getSf_id());
					}
					return softWareItemsDao.addBatchInfo(params);
				}
			}
		}
		return num;
	}

	@Override
	@Description("通过实例号修改")
	public int updateInstance(SoftWareFlow soft){
		int num =softWareFlowDao.updateInfo(soft);
		return num;
	}
	@Override
	@Description("分页查询")
	public Page<SoftWareFlow> search(SoftWareFlow soft, Page<SoftWareFlow> page) {
		String fields = "[sf_id],[sf_person],[sf_date],[sf_use],[instance_no],sf_orgId";
		StringBuffer sb = new StringBuffer(" where 1 = 1 ");
		List<Object> queryParams = new ArrayList<Object>();
		if(soft!=null){
			if (!"".equals(soft.getSf_person()) && null !=soft.getSf_person()) {
				sb.append(" and sf_person = ? ");
				queryParams.add(soft.getSf_person());
			}
			
			if (!"".equals(soft.getSf_OrgId()) && null !=soft.getSf_OrgId()) {
				sb.append(" and sf_orgId in ("+soft.getSf_OrgId()+") ");
			}
		}
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("sf_date", "desc");
		page = softWareFlowDao.search(page, fields , sb.toString(), queryParams.toArray(),orderby);
		return page;
	}
}

