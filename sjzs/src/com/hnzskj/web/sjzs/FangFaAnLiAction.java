package com.hnzskj.web.sjzs;


import com.hnzskj.common.BaseDao;
import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.attach.Attach;
import com.hnzskj.persist.bean.sjzs.FangFaAnLi;
import com.hnzskj.service.sjzs.AttachSJZSService;
import com.hnzskj.service.sjzs.FangFaAnLiService;
import com.hnzskj.web.BaseAction;

@SuppressWarnings("serial")
public class FangFaAnLiAction extends BaseAction {

	private FangFaAnLiService fangFaAnLiService = null;
	private AttachSJZSService attachSJZSService = null;

	/**
	 *flag 作为一个标志，根据前台设置的flag值判断跳转页面
	 */
	private String flag = "";

	private String newFlag = "";
	/**
	 * 法规实体类
	 */
	private FangFaAnLi ffal= new FangFaAnLi();

	private Page<FangFaAnLi> page = new Page<FangFaAnLi>();
     private String menuType;
	/**
	 * @return the page
	 */
	public Page<FangFaAnLi> getPage() {
		return page;
	}

	private Attach attach;

	
	
	/**
	 * @return the fangFaAnLiService
	 */
	public FangFaAnLiService getFangFaAnLiService() {
		return fangFaAnLiService;
	}

	/**
	 * @param fangFaAnLiService the fangFaAnLiService to set
	 */
	public void setFangFaAnLiService(FangFaAnLiService fangFaAnLiService) {
		this.fangFaAnLiService = fangFaAnLiService;
	}

	/**
	 * @return the ffal
	 */
	public FangFaAnLi getFfal() {
		return ffal;
	}

	/**
	 * @param ffal the ffal to set
	 */
	public void setFfal(FangFaAnLi ffal) {
		this.ffal = ffal;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(Page<FangFaAnLi> page) {
		this.page = page;
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
	 * 
	 * 描述：跳转到添加页面 <br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-3-25 下午05:11:16 <br/>  
	 * @version   1.0
	 */
	public String goToAddPage() {
		return ADDPAGE;
	}
	/**
	 * 
	 * 描述：跳转到更新页面<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-3-25 下午05:11:45 <br/>  
	 * @version   1.0
	 */
	public String goToUpdatePage() {
		
		ffal = this.fangFaAnLiService.getFFALById(ffal.getId());
		this.attach = (Attach) this.attachSJZSService.findAttachById(ffal.getAttachId());
		ffal.setAttach(attach);
		return "updatePage";
	}
	/**
	 * 
	 * 描述：根据父级菜单查询所有的记录，分页显示<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-3-25 下午05:12:01 <br/>  
	 * @version   1.0
	 */
	public String searchMessage() {
		String returnString = "";
//		page = this.fangFaAnLiService.searchFFALByParentId(ffal, page);
		page = this.fangFaAnLiService.searchByCondition("*", ffal, page);
		returnString = "searchFFALs";
		return returnString;

	}
   /**
    * 
    * 描述：审计导航中的节点根据条件查询<br/>
    * 创建人：wenxuanzhen <br/>
    * 创建时间：2013-3-25 下午05:12:30 <br/>  
    * @version   1.0
    */
	public String showsearchMessage() {
		String returnString = "";
		page = this.fangFaAnLiService.searchByCondition("*", ffal, page);
		returnString = "showsearchFFALs";
		return returnString;
	}
	/**
	 * 
	 * 描述：显示列表页面的搜索查询<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-3-25 下午05:13:22 <br/>  
	 * @version   1.0
	 */
	public String searchByCondition() {
		page = this.fangFaAnLiService.searchByCondition("*", ffal, page);
		return "searchFFALs";
	}
	
	/**
	 * 
	 * 描述：通过ID获得<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-2-25 下午04:48:28 <br/>
	 * 
	 * @version 1.0
	 */
	public String getById() {
		String returnString = "showFFAL";
		ffal = this.fangFaAnLiService.getFFALById(ffal.getId());
		this.attach = (Attach) this.attachSJZSService.findAttachById(ffal.getAttachId());
		ffal.setAttach(attach);
		return returnString;
	}

	/**
	 * 
	 * 描述：添加方法<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-2-25 下午04:48:42 <br/>
	 * 
	 * @version 1.0
	 */
	public String doAdd() {
		ffal.setId(new BaseDao().getGUID());
		ffal.setNote1("0");//更新索引
		String id = this.fangFaAnLiService.addFFAL(ffal);
		if (id != null) {
			return "addsucs";
		} else {
			return "fail";
		}
	}
	/**
	 * 
	 * 描述：更新记录<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-3-25 下午05:14:09 <br/>  
	 * @version   1.0
	 */
	public String doUpdate() {
		ffal.setNote1("0");//更新索引
		int result = this.fangFaAnLiService.updateFFAL(ffal);
		if (result > 0) {
			return "updatesuc";
		} else {
			return "fail";
		}
	}
   /**
    * 
    * 描述：多个删除<br/>
    * 创建人：wenxuanzhen <br/>
    * 创建时间：2013-3-25 下午05:14:29 <br/>  
    * @version   1.0
    */
	public String deleteFFALs() {
		String ids = ffal.getId();
		int result = 0;
		FangFaAnLi tempFFAL = null;
		if(ids!=null && !ids.trim().equals("")){
			String values[] = ids.split(",");
			for (int i = 0; i < values.length; i++) {
				tempFFAL = this.fangFaAnLiService.getFFALById(values[i]);
				int num = this.fangFaAnLiService.deleteFFAL(values[i]);
				if(tempFFAL!=null && num >0){//删除附件
					this.attachSJZSService.delAttach(tempFFAL.getAttachId());
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
	
	/**
	 * 
	 * 描述：单个删除<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-3-25 下午05:14:40 <br/>  
	 * @version   1.0
	 */
	public String delete() {
		int result = this.fangFaAnLiService.deleteFFAL(ffal.getId());
		if (result > 0) {
			this.attachSJZSService.delAttach(ffal.getAttachId());
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
