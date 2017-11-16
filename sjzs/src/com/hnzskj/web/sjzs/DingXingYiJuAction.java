package com.hnzskj.web.sjzs;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.attach.Attach;
import com.hnzskj.persist.bean.sjzs.DxyjLaw;
import com.hnzskj.service.sjzs.DingXingYiJuService;
import com.hnzskj.web.BaseAction;

@SuppressWarnings("serial")
public class DingXingYiJuAction extends BaseAction {

	private DingXingYiJuService dingXingYiJuService = null;

	/**
	 *flag 作为一个标志，根据前台设置的flag值判断跳转页面
	 */
	private String flag = "";

	private String newFlag = "";
	/**
	 * 法规实体类
	 */
	private DxyjLaw law = new DxyjLaw();

	private Page<DxyjLaw> page = new Page<DxyjLaw>();

	private Attach attach;
	private String menuType;

	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag
	 *            the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * @return the newFlag
	 */
	public String getNewFlag() {
		return newFlag;
	}

	/**
	 * @param newFlag
	 *            the newFlag to set
	 */
	public void setNewFlag(String newFlag) {
		this.newFlag = newFlag;
	}

	/**
	 * @return the law
	 */
	public DxyjLaw getLaw() {
		return law;
	}

	/**
	 * @param law
	 *            the law to set
	 */
	public void setLaw(DxyjLaw law) {
		this.law = law;
	}

	/**
	 * @return the page
	 */
	public Page<DxyjLaw> getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(Page<DxyjLaw> page) {
		this.page = page;
	}

	/**
	 * @return the attach
	 */
	public Attach getAttach() {
		return attach;
	}

	/**
	 * @param attach
	 *            the attach to set
	 */
	public void setAttach(Attach attach) {
		this.attach = attach;
	}

	/**
	 * @return the dingXingYiJuService
	 */
	public DingXingYiJuService getDingXingYiJuService() {
		return dingXingYiJuService;
	}

	/**
	 * @param dingXingYiJuService
	 *            the dingXingYiJuService to set
	 */
	public void setDingXingYiJuService(DingXingYiJuService dingXingYiJuService) {
		this.dingXingYiJuService = dingXingYiJuService;
	}

	public String showsearchMessage() {
		String returnString = "";
		page = this.dingXingYiJuService.searchByCondition("*", law, page);
		returnString = "showlist";
		return returnString;

	}

	
	public String searchMessage() {
		String returnString = "";
//		page = this.dingXingYiJuService.searchLawByParentId(law, page);
		page = this.dingXingYiJuService.searchByCondition("*", law, page);
		returnString = "list";
		return returnString;

	}
	
	public String searchByDepartment() {
		String returnString = "";
		page = this.dingXingYiJuService.searchByDepartment(law, page);
		returnString = "list";
		return returnString;

	}
	
	public String deleteLaws() {
		String ids = law.getId();
		int result = 0;
		if (ids != null && !ids.trim().equals("")) {
			String values[] = ids.split(",");
			for (int i = 0; i < values.length; i++) {
				int num = this.dingXingYiJuService.deleteLaw(values[i]);
				result += num;
			}
		}
		if (result > 0) {
			return DELSUC;
		} else {
			return FAIL;
		}
	}

	/**
	 * 
	 * 描述：<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-2-25 下午04:48:28 <br/>
	 * 
	 * @version 1.0
	 */
	public String getLawById() {
		String returnString = "showLaw";
		law = this.dingXingYiJuService.getLawById(law.getId());
		return returnString;
	}

	/**
	 * 
	 * 描述：<br/>
	 * 创建人：平西强 <br/>
	 * 创建时间：2013-2-28 下午04:48:42 <br/>
	 * 
	 * @version 1.0
	 */
	public String goToAddPage() {
		return ADDPAGE;
	}

	/**
	 * 
	 * 描述：<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-2-25 下午04:48:42 <br/>
	 * 
	 * @version 1.0
	 */
	public String doAddAuditDXYJ() {
		law.setId(new BaseDao().getGUID());
		law.setNote1("0");//更新索引
		String id = this.dingXingYiJuService.addLaw(law);
		if (id != null) {
			return "addsucs";
		} else {
			return FAIL;
		}
	}
	
	
	/**
	 * 
	 * 描述：<br/>
	 * 创建人：平西强 <br/>
	 * 创建时间：2013-3-2 上午08:48:42 <br/>
	 * 
	 * @version 1.0
	 */
	public String goToUpdatePage(){
		law = this.dingXingYiJuService.getLawById(law.getId());
		return UPDATEPAGE;
	}
	
	public String doUpdateDXYJ() {
		law.setNote1("0");//更新索引
		int result = this.dingXingYiJuService.updateLaw(law);
		if (result > 0) {
			return UPDATESUC;
		} else {
			return FAIL;
		}
	}

	public String deleteLaw() {
		int result = this.dingXingYiJuService.deleteLaw(law.getId());
		if (result > 0) {
			return DELSUC;
		} else {
			return FAIL;
		}
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

}
