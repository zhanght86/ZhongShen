package com.hnzskj.service.fore;

import com.hnzskj.persist.bean.fore.ClientUploadSSFNDTO;

public interface ClientUploadSSFNService {
	public ClientUploadSSFNDTO selectSSFNById(String ssfnId);
	
	public boolean insertSSFN(ClientUploadSSFNDTO clientSSFN);
	
	
}
