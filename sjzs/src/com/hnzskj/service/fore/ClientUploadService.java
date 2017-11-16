package com.hnzskj.service.fore;

import java.util.List;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.fore.ClientUploadAttachDTO;
import com.hnzskj.persist.bean.fore.ClientUploadDTO;
import com.hnzskj.persist.bean.fore.ClientUploadFFALDTO;
import com.hnzskj.persist.bean.fore.ClientUploadSJFGDTO;
import com.hnzskj.persist.bean.fore.ClientUploadSSFNDTO;
import com.hnzskj.persist.bean.system.Employee;

public interface ClientUploadService {
	
	/**
	 *方法描述：新增上传文档 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-25 下午02:24:44
	 * 
	 *@return
	 *@version 1.0
	 */ 
	public boolean addUpload(ClientUploadDTO clientUpload);
	
	/**
	 *方法描述：修改上传文档 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-25 下午02:26:07
	 * 
	 *@return
	 *@version 1.0
	 */ 
	public boolean updateUpload(ClientUploadDTO clientUpload);
	
	/**
	 *方法描述： 删除上传文档<br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-25 下午02:26:52
	 * 
	 *@return
	 *@version 1.0
	 */ 
	public boolean deleteUpload(ClientUploadDTO clientUpload);
	
	/**
	 *方法描述： <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-25 下午02:29:17
	 * 
	 *@return
	 *@version 1.0
	 */ 
	public ClientUploadDTO selectUploadById(String uploadId, String type);
	
	/**
	 *方法描述： <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-25 下午02:29:14
	 * 
	 *@return
	 *@version 1.0
	 */ 
	public List<ClientUploadDTO> selectUploadByClient(String ClientId);
	
	/**
	 * 
	 * 方法描述: 按条件进行分页查询<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-5-3 上午08:42:16
	 * @param
	 *
	 */
	public Page<ClientUploadDTO> searchByCondition(Page<ClientUploadDTO> page, ClientUploadDTO clientUpload);
	
	/**
	 * 
	 * 方法描述: 按条件进行分页查询  用户上传的审计法规<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-5-3 下午05:21:24
	 * @param
	 *
	 */
	public Page<ClientUploadSJFGDTO> searchSJFG(Page<ClientUploadSJFGDTO> page, ClientUploadSJFGDTO clientUpload);
	
	/**
	 * 
	 * 方法描述: 按条件进行分页查询  用户上传的实施方案<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-5-3 下午05:22:01
	 * @param
	 *
	 */
	public Page<ClientUploadSSFNDTO> searchSSFN(Page<ClientUploadSSFNDTO> page, ClientUploadSSFNDTO clientUpload);
	
	/**
	 * 
	 * 方法描述: 按条件进行分页查询  用户上传的审计方法<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-5-3 下午05:22:22
	 * @param
	 *
	 */
	public Page<ClientUploadFFALDTO> searchFFAN(Page<ClientUploadFFALDTO> page, ClientUploadFFALDTO clientUpload);
	
	/**
	 * 
	 * 方法描述: 批量数据迁移<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-5-3 下午05:23:55
	 * @param ids id数组
	 * @param type 数据分类
	 * @param employee 迁移数据人
	 *
	 */
	public boolean batchMoveData(String ids, String type, Employee employee);
	
	/**
	 * 
	 * 方法描述: 批量数据回迁<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-5-6 上午09:22:24
	 * @param ids id数组
	 * @param type 数据分类
	 * @param employee 回迁移数据人
	 *
	 */
	public boolean batchMoveDataBack(String ids, String type, Employee employee);
	
	public boolean changeCheckFlag(ClientUploadDTO clientUpload);
	
	public String getCurCheckUser(ClientUploadDTO clientUpload);
	
	public int updateClientUpNum(String clientId);
	
	public boolean deleteClientUp(String clientId,String dropStr);
	
	public Object objectByT(ClientUploadDTO clientUpload,String type);
	
	public ClientUploadAttachDTO findAttachs(String attachId);
}
