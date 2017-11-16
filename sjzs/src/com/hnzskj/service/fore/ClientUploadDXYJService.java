package com.hnzskj.service.fore;

import com.hnzskj.persist.bean.fore.ClientUploadDXYJDTO;

public interface ClientUploadDXYJService {
	public ClientUploadDXYJDTO selectDXYJById(String dxyjId);
	
	public boolean insertDXYJ(ClientUploadDXYJDTO clientDXYJ);
}
