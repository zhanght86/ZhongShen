package com.hnzskj.service.fore;

import java.util.List;

import com.hnzskj.persist.bean.fore.ClientUploadAttachDTO;

public interface ClientUploadAttachService {
	/**
	 * 根据附件id删除附件
	 * 
	 * @param attid
	 * @return
	 */
	public boolean delAttachById(String attid);


	/**
	 * 根据id查询附件
	 * 
	 * @param cfrId
	 * @return
	 */
	public ClientUploadAttachDTO findAttachById(String id);

	/**
	 * 根据id查询附件
	 * 
	 * @param cfrId
	 * @return
	 */
	public List<ClientUploadAttachDTO> findAttachByName(String name);

}
