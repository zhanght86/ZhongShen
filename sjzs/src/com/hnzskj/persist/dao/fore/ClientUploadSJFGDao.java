package com.hnzskj.persist.dao.fore;

import com.hnzskj.persist.bean.fore.ClientUploadSJFGDTO;

public interface ClientUploadSJFGDao {
	public ClientUploadSJFGDTO selectSJFGById(String sjfgId);
	
	public boolean insertSJFG(ClientUploadSJFGDTO clientSJFG);
	
	
}
