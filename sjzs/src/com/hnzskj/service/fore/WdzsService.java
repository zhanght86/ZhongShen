package com.hnzskj.service.fore;

import java.util.List;

import com.hnzskj.persist.bean.fore.ClientInfoDTO;
import com.hnzskj.persist.bean.fore.SimpleMenuDTO;

public interface WdzsService {
	public List<SimpleMenuDTO> initMenu();
	
	public ClientInfoDTO getClientById(String clientId);
	
}
