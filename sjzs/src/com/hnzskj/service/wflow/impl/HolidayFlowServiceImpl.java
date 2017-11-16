/*
 * @项目名称: OA
 * @文件名称: HolidayFlowServiceImpl.java
 * @日期: 2012-7-12
 * @版权: 2012 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.service.wflow.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.DataUtil;
import com.hnzskj.common.Page;
import com.hnzskj.common.annotation.Description;
import com.hnzskj.persist.bean.wflow.HolidayFlow;
import com.hnzskj.persist.dao.wflow.HolidayFlowDao;
import com.hnzskj.service.wflow.HolidayFlowService;

  /**        
 * 
 * 类名称：HolidayFlowServiceImpl
 * 类描述：<br/>
 * 创建人：郑辉  <br/>
 * 创建时间：2012-7-12 下午04:28:11 
 * 修改人：Administrator
 * 修改时间：
 * 修改备注：   
 * @version   1.0     
 */

public class HolidayFlowServiceImpl implements HolidayFlowService {
	private HolidayFlowDao holidayFlowDao =null;

	/**
	 * @return the holidayFlowDao
	 */
	public HolidayFlowDao getHolidayFlowDao() {
		return holidayFlowDao;
	}

	/**
	 * @param holidayFlowDao the holidayFlowDao to set
	 */
	public void setHolidayFlowDao(HolidayFlowDao holidayFlowDao) {
		this.holidayFlowDao = holidayFlowDao;
	}

	@Override
	@Description("插入")
	public String addInfo(HolidayFlow holiday) {
		// TODO Auto-generated method stub
		return holidayFlowDao.addInfo(holiday);
	}

	@Override
	@Description("修改")
	public int updateInfo(HolidayFlow holiday) {
		// TODO Auto-generated method stub
		return holidayFlowDao.updateInfo(holiday);
	}

	@Override
	@Description("获取")
	public HolidayFlow getInfo(int instanceNo) {
		// TODO Auto-generated method stub
		return holidayFlowDao.getInfoByNo(instanceNo);
	}
	@Override
	@Description("更新状态")
	public int updateStatusInfo(HolidayFlow holiday){
		return holidayFlowDao.updateStatusInfo(holiday);
	}
	
	@Override
	@Description("获取员工的状态")
	public HolidayFlow getInfoByStatus(String emplId){
		HolidayFlow sick =holidayFlowDao.getInfoByStatus(emplId);
		if(sick!=null){
		HolidayFlow nowHoliday=holidayFlowDao.getInfoByNo(sick.getInstance_no());
		nowHoliday.setBefor_instance_no(sick.getInstance_no());
			return nowHoliday;
		}
		return null;
	}
	
	@Override
	@Description("分页查询")
	public Page<HolidayFlow> searchHoliday(HolidayFlow holiday, Page<HolidayFlow> page) {
		String fields = "hd_Id,hd_Name,hd_OrgId,hd_begin_time,hd_end_time,hd_end_days," +
				"hd_type,hd_reason,hd_Sickleave_time ,hd_Sickleave_days,	" +
				"hd_timeout_time ,hd_timeout_days ,hd_desc ,hd_orderPerson ,instance_no,befor_instance_no";
		StringBuffer sb = new StringBuffer(" where 1 = 1 ");
		List<Object> queryParams = new ArrayList<Object>();
		if(holiday!=null){
			if (!"".equals(holiday.getHd_Name()) && null !=holiday.getHd_Name()) {
				sb.append(" and hd_name = ? ");
				queryParams.add(holiday.getHd_Name());
			}
			
			if (!"".equals(holiday.getNowMonth()) && null !=holiday.getNowMonth()) {
				sb.append(" and hd_begin_time like '%"+holiday.getNowYear()+ "-"+holiday.getNowMonth()+"%'");
						//queryParams.add(DataUtil.getNowDate().substring(0,5)+holiday.getNowMonth()+"%");
			}
			
			if (!"".equals(holiday.getHd_OrgId()) && null !=holiday.getHd_OrgId()) {
				sb.append(" and hd_OrgId in ("+holiday.getHd_OrgId()+") ");
			}
		}
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("hd_begin_time", "desc");
		
		page = holidayFlowDao.searchHolidayFlow(page, fields , sb.toString(), queryParams.toArray(),orderby);
		return page;
	}

	@Override
	@Description("请假判断")
	public boolean getHolidayCount(String emplId,int status) {
		// TODO Auto-generated method stub
		int result =holidayFlowDao.getHolidayCount(emplId,status);
		if(result!=0) return true;
		return false;
	}
	@Override
	@Description("请假删除")
	public int deleteinfo (String guid){
		return holidayFlowDao.deleteinfo(guid);
	}
	
	/**
	 * 类描述：获取请假列表中的年度字段
	 * 创建人：郑辉  <br/>
	 * 创建时间：2013-1-7 上午10:31:13 
	 * 修改人：
	 * 修改时间：2013-1-7 上午10:31:13  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public List<String> getHolidayYearList(){
		return holidayFlowDao.getHolidayYearList();
	}
}
