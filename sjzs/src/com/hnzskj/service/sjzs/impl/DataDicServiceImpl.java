package com.hnzskj.service.sjzs.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.sjzs.DataDicDTO;
import com.hnzskj.persist.dao.sjzs.DataDicDao;
import com.hnzskj.service.sjzs.DataDicService;

public class DataDicServiceImpl implements DataDicService {

	private DataDicDao dataDicDao = null;
	

	/**
	 * @return the dataDicDao
	 */
	public DataDicDao getDataDicDao() {
		return dataDicDao;
	}

	/**
	 * @param dataDicDao the dataDicDao to set
	 */
	public void setDataDicDao(DataDicDao dataDicDao) {
		this.dataDicDao = dataDicDao;
	}

	@Override
	public String addDataDic(DataDicDTO dataDicDTO) {
		String id = this.dataDicDao.addDataDic(dataDicDTO);
		return id;
	}

	@Override
	public int deleteDataDic(String id) {
		int result =  this.dataDicDao.deleteDataDic(id);
		return result;
	}

	@Override
	public DataDicDTO getDataDicById(String id) {
		DataDicDTO dataDicDTO = this.dataDicDao.getDataDicById(id);
		return dataDicDTO;
	}

	@Override
	public Page<DataDicDTO> searchByCondition(String fields, DataDicDTO dataDicDTO, Page<DataDicDTO> page) {
		StringBuffer sb = new StringBuffer(" where 1 = 1 ");
		List<Object> queryParams = new ArrayList<Object>();
		if (!"".equals(dataDicDTO.getDicName()) && null != dataDicDTO.getDicName()) {
			sb.append(" and dicName like '%" + dataDicDTO.getDicName() +"%' ");
//			queryParams.add("%" + dataDicDTO.getDicName() + "%");
		}
		
		if (!"".equals(dataDicDTO.getDicId()) && null != dataDicDTO.getDicId()) {
//			sb.append(" and dicId in( ? )");
//			queryParams.add(dataDicDTO.getDicId());
			sb.append(" and dicId = '" + dataDicDTO.getDicId() + "' ");
		}
		queryParams.add(dataDicDTO.getDicParentId());
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put("dicOrder", "asc");
		page = this.dataDicDao.searchByCondition(page, fields, sb.toString(), queryParams.toArray(), orderBy);
		return page;
	}

	@Override
	public Page<DataDicDTO> searchDataDicByParentId(DataDicDTO dataDicDTO,
			Page<DataDicDTO> page) {
		Object[] params = new Object[]{dataDicDTO.getDicParentId()};
		String fields = "dicId,dicName,dicParentId,dicMemo,dicOrder,isdel,note1,note2 ";
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put("dicOrder", "asc");
		page = this.dataDicDao.searchDataDicByParentId(page, fields,null, params, orderBy);
		return page;
	}

	@Override
	public int updateDataDic(DataDicDTO dataDicDTO) {
		int result = this.dataDicDao.updateDataDic(dataDicDTO);
		return result;
	}

}
