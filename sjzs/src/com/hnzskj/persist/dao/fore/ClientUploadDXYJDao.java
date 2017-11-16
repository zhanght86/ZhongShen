package com.hnzskj.persist.dao.fore;

import com.hnzskj.persist.bean.fore.ClientUploadDXYJDTO;

public interface ClientUploadDXYJDao {
	public ClientUploadDXYJDTO selectDXYJById(String dxyjId);
	
	public boolean insertDXYJ(ClientUploadDXYJDTO clientDXYJ);
}
