package com.hnzskj.service.fore.impl;

import java.util.List;

import com.hnzskj.persist.bean.attach.Attach;
import com.hnzskj.persist.bean.fore.ClientUploadAttachDTO;
import com.hnzskj.persist.dao.fore.ClientUploadAttachDao;
import com.hnzskj.service.fore.ClientUploadAttachService;

public class ClientUploadAttachServiceImpl implements ClientUploadAttachService {

	private ClientUploadAttachDao clientUploadAttachDao = null;

	/**
	 * @return the clientUploadAttachDao
	 */
	public ClientUploadAttachDao getClientUploadAttachDao() {
		return clientUploadAttachDao;
	}

	/**
	 * @param clientUploadAttachDao
	 *            the clientUploadAttachDao to set
	 */
	public void setClientUploadAttachDao(ClientUploadAttachDao clientUploadAttachDao) {
		this.clientUploadAttachDao = clientUploadAttachDao;
	}

	
	
	@Override
	public boolean delAttachById(String attid) {
		int result = 0;
		result = this.clientUploadAttachDao.delAttachById(attid);
		return result > 0;
	}

	@Override
	public ClientUploadAttachDTO findAttachById(String id) {
		ClientUploadAttachDTO  clientUploadAttachDTO = this.clientUploadAttachDao.findAttachById(id);
		return clientUploadAttachDTO;
	}

	@Override
	public List<ClientUploadAttachDTO> findAttachByName(String name) {
		List<ClientUploadAttachDTO> list = this.clientUploadAttachDao.findAttachByName(name);
		return list;
	}


}
