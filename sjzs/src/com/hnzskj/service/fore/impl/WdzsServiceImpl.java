package com.hnzskj.service.fore.impl;

import java.util.List;

import com.hnzskj.persist.bean.fore.ClientInfoDTO;
import com.hnzskj.persist.bean.fore.SimpleMenuDTO;
import com.hnzskj.persist.dao.fore.WdzsDao;
import com.hnzskj.service.fore.WdzsService;

public class WdzsServiceImpl implements WdzsService {
	private WdzsDao wdzsDao;

	public WdzsDao getWdzsDao() {
		return wdzsDao;
	}

	public void setWdzsDao(WdzsDao wdzsDao) {
		this.wdzsDao = wdzsDao;
	}

	@Override
	public List<SimpleMenuDTO> initMenu() {
		
		return wdzsDao.initMenu();
	}

	@Override
	public ClientInfoDTO getClientById(String clientId) {
		return wdzsDao.getClientById(clientId);
	}
	
	
}
