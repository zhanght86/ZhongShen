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
import com.hnzskj.persist.bean.wflow.RepairFlow;
import com.hnzskj.persist.dao.wflow.RepairFlowDao;
import com.hnzskj.service.wflow.RepairFlowService;

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

public class RepairFlowServiceImpl implements RepairFlowService{
	private RepairFlowDao repairFlowDao;

	/**
	 * @return the repairFlowDao
	 */
	public RepairFlowDao getRepairFlowDao() {
		return repairFlowDao;
	}

	/**
	 * @param repairFlowDao the repairFlowDao to set
	 */
	public void setRepairFlowDao(RepairFlowDao repairFlowDao) {
		this.repairFlowDao = repairFlowDao;
	}


	@Override
	@Description("插入数据")
	public String addInfo(RepairFlow repair) {
		// TODO Auto-generated method stub
		return repairFlowDao.addInfo(repair);
	}


	@Override
	@Description("通过实例号获取信息")
	public RepairFlow getInfo(int instanceNo) {
		// TODO Auto-generated method stub
		return repairFlowDao.getInfoByNo(instanceNo);
	}

	@Override
	@Description("分页查询")
	public Page<RepairFlow> search(RepairFlow repair, Page<RepairFlow> page) {
		// TODO Auto-generated method stub
		String fields = "rp_id,rp_orgId,rp_person,rp_date,rp_desc,rp_items,rp_type,instance_no";
		StringBuffer sb = new StringBuffer(" where 1 = 1 ");
		List<Object> queryParams = new ArrayList<Object>();
		if(repair!=null){
			if (!"".equals(repair.getRp_person()) && null !=repair.getRp_person()) {
				sb.append(" and rp_person = ? ");
				queryParams.add(repair.getRp_person());
			}
			
			if (!"".equals(repair.getRp_orgId()) && null !=repair.getRp_orgId()) {
				sb.append(" and rp_orgId in ("+repair.getRp_orgId()+") ");
			}
		}
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("rp_date", "desc");
		page = repairFlowDao.search(page, fields , sb.toString(), queryParams.toArray(),orderby);
		return page;
	}


	@Override
	@Description("更新数据")
	public int updateInfo(RepairFlow repair) {
		// TODO Auto-generated method stub
		return repairFlowDao.updateInfo(repair);
	}
}

