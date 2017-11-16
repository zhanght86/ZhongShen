package com.hnzskj.web.sjzs;



import com.hnzskj.common.BaseDao;
import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.attach.Attach;
import com.hnzskj.persist.bean.sjzs.Law;
import com.hnzskj.service.sjzs.AttachSJZSService;
import com.hnzskj.service.sjzs.ShenJiFaGuiService;
import com.hnzskj.web.BaseAction;

@SuppressWarnings("serial")
public class ShenJiFaGuiAction extends BaseAction {

	private ShenJiFaGuiService shenJiFaGuiService = null;
	private AttachSJZSService attachSJZSService = null;

	/**
	 *flag 作为一个标志，根据前台设置的flag值判断跳转页面
	 */
	private String flag = "";

	private String newFlag = "";
	/**
	 * 法规实体类
	 */
	private Law law = new Law();

	private Page<Law> page = new Page<Law>();

	private Attach attach;
	
	private String menuType;

	/**
	 * @return the shenJiFaGuiService
	 */
	public ShenJiFaGuiService getShenJiFaGuiService() {
		return shenJiFaGuiService;
	}

	/**
	 * @param shenJiFaGuiService
	 *            the shenJiFaGuiService to set
	 */
	public void setShenJiFaGuiService(ShenJiFaGuiService shenJiFaGuiService) {
		this.shenJiFaGuiService = shenJiFaGuiService;
	}

	/**
	 * @return the attachSJZSService
	 */
	public AttachSJZSService getAttachSJZSService() {
		return attachSJZSService;
	}

	/**
	 * @param attachSJZSService
	 *            the attachSJZSService to set
	 */
	public void setAttachSJZSService(AttachSJZSService attachSJZSService) {
		this.attachSJZSService = attachSJZSService;
	}

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
	public Law getLaw() {
		return law;
	}

	/**
	 * @param law
	 *            the law to set
	 */
	public void setLaw(Law law) {
		this.law = law;
	}

	/**
	 * @return the page
	 */
	public Page<Law> getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(Page<Law> page) {
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

	public String searchMessage() {
		String returnString = "";
//		page = this.shenJiFaGuiService.searchLawByParentId(law, page);
		page = this.shenJiFaGuiService.searchByCondition("*", law, page);
		returnString = "searchLaws";
		return returnString;
	}
	
	
	public String showsearchMessage() {
		String returnString = "";
		page = this.shenJiFaGuiService.searchByCondition("*", law, page);
		returnString = "showsearchLaws";
		return returnString;
	}
	
	public String searchByCondition() {
		page = this.shenJiFaGuiService.searchByCondition("*", law, page);
		return "searchLaws";
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
		law = this.shenJiFaGuiService.getLawById(law.getLawId());
		this.attach = (Attach) this.attachSJZSService.findAttachById(law
				.getAttachId());
		law.setAttach(attach);
		return returnString;
	}

	
	public String goToAddPage() {
		return ADDPAGE;
	}
	
	public String goToUpdatePage() {
		
		law = this.shenJiFaGuiService.getLawById(law.getLawId());
		this.attach = (Attach) this.attachSJZSService.findAttachById(law.getAttachId());
		law.setAttach(attach);
		return UPDATEPAGE;
	}
	
	
	/**
	 * 
	 * 描述：<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-2-25 下午04:48:42 <br/>
	 * 
	 * @version 1.0
	 */
	public String doAddLaw() {
		law.setLawId(new BaseDao().getGUID());
		System.err.println("law.getLawSort()=="+law.getLawSort());
		law.setNote1("0");//更新索引
		String id = this.shenJiFaGuiService.addLaw(law);
		if (id != null) {
			return "addsucs";
		} else {
			return "fail";
		}
	}

	public String doUpdateLaw() {
		law.setNote1("0");//更新索引
		int result = this.shenJiFaGuiService.updateLaw(law);
		if (result > 0) {
			return "updatesuc";
		} else {
			return "fail";
		}
	}

	public String deleteLaws() {
		String ids = law.getLawId();
		int result = 0;
		Law tempLaw = null;
		if(ids!=null && !ids.trim().equals("")){
			String values[] = ids.split(",");
			for (int i = 0; i < values.length; i++) {
				tempLaw = this.shenJiFaGuiService.getLawById(values[i]);
				int num = this.shenJiFaGuiService.deleteLaw(values[i]);
				if(tempLaw!=null && num >0){//删除附件
					this.attachSJZSService.delAttach(tempLaw.getAttachId());
				}
			  result+=num;
			}
		}		
		if (result > 0) {
			return DELSUC;
		} else {
			return "fail";
		}
	}	
	
	public String deleteLaw() {
		int result = this.shenJiFaGuiService.deleteLaw(law.getLawId());
		if (result > 0) {
			this.attachSJZSService.delAttach(law.getAttachId());
			return DELSUC;
		} else {
			return "fail";
		}
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	

}
