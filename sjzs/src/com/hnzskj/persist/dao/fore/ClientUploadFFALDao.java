package com.hnzskj.persist.dao.fore;

import com.hnzskj.persist.bean.fore.ClientUploadFFALDTO;

public interface ClientUploadFFALDao {
	public ClientUploadFFALDTO selectFFALById(String ffalId);
	
	public boolean insertFFAL(ClientUploadFFALDTO clientFFAL);
}	
