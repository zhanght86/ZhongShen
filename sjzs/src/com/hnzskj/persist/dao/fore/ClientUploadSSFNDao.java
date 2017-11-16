package com.hnzskj.persist.dao.fore;

import com.hnzskj.persist.bean.fore.ClientUploadSSFNDTO;

public interface ClientUploadSSFNDao {
	public ClientUploadSSFNDTO selectSSFNById(String ssfnId);
	
	public boolean insertSSFN(ClientUploadSSFNDTO clientSSFN);
}
