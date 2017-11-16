package com.hnzskj.persist.dao.sjzs;

import java.util.List;

import com.hnzskj.persist.bean.attach.Attach;


public interface AttachSJZSDao {
	/**
	 * 根据附件id删除附件
	 * 
	 * @param attid
	 * @return
	 */
	public int delAttach(String attid);

	/**
	 * 根据日志id查询附件列表
	 * 
	 * @param journalId
	 * @return
	 */
	public List<Attach> findAttachs(String journalId);

	/**
	 * 根据日志id删除附件
	 * 
	 * @param journalId
	 * @return
	 */
	public int delAttachs(String journalId);
	
	/**
	 * 根据Id查询附件列表
	 * @param cfrId
	 * @return
	 */
	public Attach findAttachById(String id);
	/**
	 * 根据JS中 id删除附件
	 * 
	 * @param journalId
	 * @return
	 */
	public int delAttachJS(String attid);
	
	/**
	 * 
	 * 方法描述: 插入一条附件数据<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-5-4 上午10:04:33
	 * @param
	 *
	 */
	public int addAttach(Attach attach);
	
	/**
	 * 
	 * 方法描述: 批量插入附件<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-5-6 上午09:58:46
	 * @param
	 *
	 */
	public int batchAddAttach(List<Attach> attachs);
	
	/**
	 * 
	 * 方法描述: 批量删除附件  <br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-5-6 上午09:26:32
	 * @param ids id数组  以 "id,id..."格式拼接
	 * @param phDel 是否物理删除  true-物理删除
	 *
	 */
	public int batchDeleteAttach(String ids, boolean phDel);
}
