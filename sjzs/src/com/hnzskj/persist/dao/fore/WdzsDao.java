package com.hnzskj.persist.dao.fore;

import java.util.List;

import com.hnzskj.persist.bean.fore.ClientInfoDTO;
import com.hnzskj.persist.bean.fore.SimpleMenuDTO;

public interface WdzsDao {
	public List<SimpleMenuDTO> initMenu();
	
	public List<SimpleMenuDTO> getMenuByParent(String menuParentId);
	
	public ClientInfoDTO getClientById(String clientId);
}
