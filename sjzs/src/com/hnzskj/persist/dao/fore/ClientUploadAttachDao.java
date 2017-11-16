package com.hnzskj.persist.dao.fore;

import java.util.List;

import com.hnzskj.persist.bean.fore.ClientUploadAttachDTO;

public interface ClientUploadAttachDao {
	/**
	 * 根据附件id删除附件
	 * 
	 * @param attid
	 * @return
	 */
	public int delAttachById(String attid);


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
	
	/**
	 * 
	 * 方法描述: 根据id数组查询<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-5-4 上午09:50:16
	 * @param
	 *
	 */
	public List<ClientUploadAttachDTO> findAttachByIds(String ids);
}
