package com.hnzskj.service.sjzs.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.sjzs.ShiShiFangAnDTO;
import com.hnzskj.persist.dao.sjzs.ShiShiFangAnDao;
import com.hnzskj.service.sjzs.ShiShiFangAnService;

public class ShiShiFangAnServiceImpl implements ShiShiFangAnService {

	private ShiShiFangAnDao shiShiFangAnDao = null;
	
	
	
	/**
	 * @return the shiShiFangAnDao
	 */
	public ShiShiFangAnDao getShiShiFangAnDao() {
		return shiShiFangAnDao;
	}

	/**
	 * @param shiShiFangAnDao the shiShiFangAnDao to set
	 */
	public void setShiShiFangAnDao(ShiShiFangAnDao shiShiFangAnDao) {
		this.shiShiFangAnDao = shiShiFangAnDao;
	}

	@Override
	public String addSSFA(ShiShiFangAnDTO shiShiFangAn) {
		String id = this.shiShiFangAnDao.addSSFA(shiShiFangAn);
		return id;
	}

	@Override
	public int deleteSSFA(String id) {
		int result =  this.shiShiFangAnDao.deleteSSFA(id);
		return result;
	}

	@Override
	public ShiShiFangAnDTO getSSFAById(String id) {
		ShiShiFangAnDTO shiShiFangAnDTO = this.shiShiFangAnDao.getSSFAById(id);
		return shiShiFangAnDTO;
	}

	@Override
	public Page<ShiShiFangAnDTO> searchByCondition(String fields, ShiShiFangAnDTO shiShiFangAn, Page<ShiShiFangAnDTO> page) {
		StringBuffer sb = new StringBuffer(" where 1 = 1 ");
		List<Object> queryParams = new ArrayList<Object>();
		if (!"".equals(shiShiFangAn.getName()) && null != shiShiFangAn.getName()) {
			sb.append(" and name like '%" + shiShiFangAn.getName() + "%' ");
//			queryParams.add("%" + shiShiFangAn.getName() + "%");
		}
		
		if (!"".equals(shiShiFangAn.getId()) && null != shiShiFangAn.getId()) {
//			sb.append(" and id in( ? )");
//			queryParams.add(shiShiFangAn.getId());
			sb.append(" and id = '" + shiShiFangAn.getId() + "' ");
		}
		queryParams.add(shiShiFangAn.getSort());
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put("name", "asc");
		page = shiShiFangAnDao.searchByCondition(page, fields, sb.toString(), queryParams.toArray(), orderBy);
		return page;
	}

	@Override
	public Page<ShiShiFangAnDTO> searchSSFAByParentId(ShiShiFangAnDTO shiShiFangAn, Page<ShiShiFangAnDTO> page) {
		Object[] params = new Object[]{shiShiFangAn.getSort()};
		String fields = "id,name,industry,attachid,keyword,sort,writedate ";
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put("name", "asc");
		page = this.shiShiFangAnDao.searchSSFAByParentId(page, fields,null, params, orderBy);
		return page;
	}

	@Override
	public int updateSSFA(ShiShiFangAnDTO shiShiFangAn) {
		int result = this.shiShiFangAnDao.updateSSFA(shiShiFangAn);
		return result;
	}

}
