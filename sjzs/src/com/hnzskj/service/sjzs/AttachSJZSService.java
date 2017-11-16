package com.hnzskj.service.sjzs;

import java.util.List;

import com.hnzskj.persist.bean.attach.Attach;

public interface AttachSJZSService {
	/**
	 * 根据附件id删除附件
	 * 
	 * @param attid
	 * @return
	 */
	public boolean delAttach(String attid);
	/**
	 * js中根据附件id删除附件
	 * 
	 * @param attid
	 * @return
	 */
	public boolean delAttachJS(String attid);

	/**
	 * 根据日志id查询附件列表
	 * 
	 * @param journalId
	 * @return
	 */
	public List<Attach> findAttachs(String attachId);

	/**
	 * 根据日志id删除附件
	 * 
	 * @param journalId
	 * @return
	 */
	public boolean delAttachs(String attachId);
	/**
	 * 根据会议id查询附件列表
	 * @param cfrId
	 * @return
	 */
	public Attach findAttachById(String id);
	
	public boolean delAttachUpdate(String name,String path) ;
	public boolean deleteByname(String name,String path) ;}
