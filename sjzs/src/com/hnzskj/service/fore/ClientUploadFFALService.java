package com.hnzskj.service.fore;

import com.hnzskj.persist.bean.fore.ClientUploadFFALDTO;

public interface ClientUploadFFALService {
	public ClientUploadFFALDTO selectFFALById(String ffalId);
	
	public boolean insertFFAL(ClientUploadFFALDTO clientFFAL);
	
}
