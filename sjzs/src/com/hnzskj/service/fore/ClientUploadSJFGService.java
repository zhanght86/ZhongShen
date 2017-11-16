package com.hnzskj.service.fore;

import com.hnzskj.persist.bean.fore.ClientUploadSJFGDTO;

public interface ClientUploadSJFGService {
	public ClientUploadSJFGDTO selectSJFGById(String sjfgId);
	
	public boolean insertSJFG(ClientUploadSJFGDTO clientSJFG);
	
}
