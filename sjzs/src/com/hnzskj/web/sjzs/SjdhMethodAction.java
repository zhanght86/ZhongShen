package com.hnzskj.web.sjzs;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.DataUtil;
import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.sjzs.SjdhMethod;
import com.hnzskj.service.sjzs.SjdhMethodService;
import com.hnzskj.web.BaseAction;

public class SjdhMethodAction extends BaseAction {
	private SjdhMethod sjdhMethod =new SjdhMethod();
	private SjdhMethodService sjdhService=null;
	Page<SjdhMethod> page = new Page<SjdhMethod>();
	public SjdhMethod getSjdhMethod() {
		return sjdhMethod;
	}
	public void setSjdhMethod(SjdhMethod sjdhMethod) {
		this.sjdhMethod = sjdhMethod;
	}
	public SjdhMethodService getSjdhService() {
		return sjdhService;
	}
	public void setSjdhService(SjdhMethodService sjdhService) {
		this.sjdhService = sjdhService;
	}
	public Page<SjdhMethod> getPage() {
		return page;
	}
	public void setPage(Page<SjdhMethod> page) {
		this.page = page;
	}
	
	public String toAddInfo(){
		sjdhMethod.setCreateTime(DataUtil.getNowDate());
		
		return "toAddPage";
	}
	public String doAddInfo(){
		BaseDao baseDao = new BaseDao();
		sjdhMethod.setNote1("0");//更新索引
		sjdhMethod.setTemplate_no(baseDao.getGUID());
		sjdhService.addInfo(sjdhMethod);
		return "addsucs";
	}
	
	/**
	 * 描述： 查询审计导航中的某个分类的审计方法
	 * 创建人:郑辉
	 * 创建时间： 2013-2-26 下午03:14:09
	 * 修改时间：
	 * 修改人：
	 */
	public String serachSjdhMethod(){
		page=sjdhService.search(page, sjdhMethod);
		return "list";
	}
	
	public String toUpdateInfo(){
		sjdhMethod=sjdhService.findById(sjdhMethod.getId());
		return "toUpdatePage";
	}
	public String doUpdateInfo(){
		sjdhMethod.setNote1("0");//更新索引
		sjdhService.update(sjdhMethod);
		return "updatesuc";
	} 
	
	public String delete(){
		sjdhService.delete(sjdhMethod.getId());
		return "success";
	}
}
