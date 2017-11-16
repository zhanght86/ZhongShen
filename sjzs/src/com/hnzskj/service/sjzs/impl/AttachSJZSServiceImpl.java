package com.hnzskj.service.sjzs.impl;

import java.io.File;
import java.util.List;

import com.hnzskj.common.annotation.Description;
import com.hnzskj.persist.bean.attach.Attach;
import com.hnzskj.persist.dao.sjzs.AttachSJZSDao;
import com.hnzskj.persist.dao.sjzs.UpdateDataLogDao;
import com.hnzskj.service.sjzs.AttachSJZSService;

public class AttachSJZSServiceImpl implements AttachSJZSService {

	private AttachSJZSDao attachSJZSDao = null;
	private UpdateDataLogDao updateDataLogDao = null;


	/**
	 * @return the updateDataLogDao
	 */
	public UpdateDataLogDao getUpdateDataLogDao() {
		return updateDataLogDao;
	}

	/**
	 * @param updateDataLogDao the updateDataLogDao to set
	 */
	public void setUpdateDataLogDao(UpdateDataLogDao updateDataLogDao) {
		this.updateDataLogDao = updateDataLogDao;
	}

	/**
	 * @return the attachSJZSDao
	 */
	public AttachSJZSDao getAttachSJZSDao() {
		return attachSJZSDao;
	}

	/**
	 * @param attachSJZSDao the attachSJZSDao to set
	 */
	public void setAttachSJZSDao(AttachSJZSDao attachSJZSDao) {
		this.attachSJZSDao = attachSJZSDao;
	}

	@Override
	@Description("根据日志id删除关联的附件信息")
	public boolean delAttachs(String attachId) {
		// TODO Auto-generated method stub
		int result = 0;
		result = attachSJZSDao.delAttachs(attachId);
		return result > 0;
	}

	@Override
	@Description("根据日志id查找关联的所有附件")
	public List<Attach> findAttachs(String attachId) {
		// TODO Auto-generated method stub
		List<Attach> list = attachSJZSDao.findAttachs(attachId);
		return list;
	}

	@Override
	@Description("根据附件id删除附件")
	public boolean delAttach(String attid) {
		// TODO Auto-generated method stub
		int result = 0;
		result = attachSJZSDao.delAttach(attid);
		return result > 0;
	}



	public Attach findAttachById(String attachId) {
		Attach attach = (Attach) attachSJZSDao.findAttachById(attachId);
		return attach;
	}

	@Override
	public boolean delAttachUpdate(String name,String path) {
		boolean flag = false;
		String tempPath = path.replace("#", ":");
		tempPath = tempPath.replace("@", "\\");
//		tempPath = tempPath+"\\"+name;
		File file = new File(tempPath);
		flag = file.delete();
		if(flag){
			this.updateDataLogDao.deleteByName(name);
		}
		if(!file.exists()){
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean delAttachJS(String attid) {
		int result = 0;
		result = attachSJZSDao.delAttachJS(attid);
		return result > 0;
	}

	@Override
	public boolean deleteByname(String name, String path) {
		System.err.println("00000");
		return false;
	}
}
