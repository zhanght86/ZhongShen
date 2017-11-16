package com.hnzskj.persist.dao.fore;

import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.fore.ClientUploadAttachDTO;
import com.hnzskj.persist.bean.fore.ClientUploadDTO;
import com.hnzskj.persist.bean.fore.ClientUploadFFALDTO;
import com.hnzskj.persist.bean.fore.ClientUploadSJFGDTO;
import com.hnzskj.persist.bean.fore.ClientUploadSSFNDTO;

public interface ClientUploadDao {
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
	 * 方法描述: 按条件分页查询<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-4-25 下午03:28:46
	 * @param
	 *
	 */
	public Page<ClientUploadDTO> searchByCondition(Page<ClientUploadDTO> page, String fields, ClientUploadDTO clientUpload, LinkedHashMap<String, String> orderby);
	
	/**
	 * 
	 * 方法描述: 按条件进行分页查询  用户上传的审计法规<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-5-4 上午08:06:02
	 * @param
	 *
	 */
	public Page<ClientUploadSJFGDTO> searchSJFG(Page<ClientUploadSJFGDTO> page, String fields, String sqlCondition, Object[] params, LinkedHashMap<String, String> orderby);
	
	/**
	 * 
	 * 方法描述: 按条件进行分页查询  用户上传的实施方案<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-5-4 上午08:06:09
	 * @param
	 *
	 */
	public Page<ClientUploadSSFNDTO> searchSSFN(Page<ClientUploadSSFNDTO> page, String fields, String sqlCondition, Object[] params, LinkedHashMap<String, String> orderby);
	
	/**
	 * 
	 * 方法描述: 按条件进行分页查询  用户上传的审计方法<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-5-4 上午08:06:23
	 * @param
	 *
	 */
	public Page<ClientUploadFFALDTO> searchFFAN(Page<ClientUploadFFALDTO> page, String fields, String sqlCondition, Object[] params, LinkedHashMap<String, String> orderby);
	
	/**
	 * 
	 * 方法描述: 批量数据迁移<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-5-4 上午08:06:52
	 * @param ids id数组
	 * @param type 数据分类
	 *
	 */
	public boolean batchMoveData(String ids, String type);
	
	/**
	 * 
	 * 方法描述: 根据id数组进行查询  用户上传的审计方法<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-5-4 上午08:16:10
	 * @param
	 *
	 */
	public List<ClientUploadFFALDTO> selectFFALUploads(String ids);
	
	/**
	 * 
	 * 方法描述: 根据id数组进行查询  用户上传的实施方案<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-5-4 上午09:16:22
	 * @param
	 *
	 */
	public List<ClientUploadSSFNDTO> selectSSFNUploads(String ids);
	
	/**
	 * 
	 * 方法描述: 根据id数组进行查询  用户上传的审计法规<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-5-4 上午09:16:58
	 * @param
	 *
	 */
	public List<ClientUploadSJFGDTO> selectSJFGUploads(String ids);
	
	/**
	 * 
	 * 方法描述: 批量更新数据迁移状态<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-5-4 下午01:53:32
	 * @param
	 *
	 */
	public int updateTransportFlag(String ids,Integer flag, String type);
	
	public int changeCheckFlag(ClientUploadDTO clientUpload);
	
	public String getCurCheckUser(ClientUploadDTO clientUpload);
	
	public int updateClientUpNum(String clientId);
	
	public ClientUploadAttachDTO findAttachs(String attachId);
	
	public void downNatlByCheck(String clientId);
}
