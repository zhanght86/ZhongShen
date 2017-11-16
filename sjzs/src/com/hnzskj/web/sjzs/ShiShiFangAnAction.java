package com.hnzskj.web.sjzs;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.attach.Attach;
import com.hnzskj.persist.bean.sjzs.ShiShiFangAnDTO;
import com.hnzskj.service.sjzs.AttachSJZSService;
import com.hnzskj.service.sjzs.ShiShiFangAnService;
import com.hnzskj.web.BaseAction;

@SuppressWarnings("serial")
public class ShiShiFangAnAction extends BaseAction {

	
	private ShiShiFangAnService shiShiFangAnService = null;
	private AttachSJZSService attachSJZSService = null;
	private Attach attach = null;
	/**
	 *flag 作为一个标志，根据前台设置的flag值判断跳转页面
	 */
	private String flag = "";

	/**
	 * 法规实体类
	 */
	private ShiShiFangAnDTO shiShiFangAn = new ShiShiFangAnDTO();

	private Page<ShiShiFangAnDTO> page = new Page<ShiShiFangAnDTO>();
    private String menuType;

	/**
	 * @return the shiShiFangAnService
	 */
	public ShiShiFangAnService getShiShiFangAnService() {
		return shiShiFangAnService;
	}

	/**
	 * @param shiShiFangAnService the shiShiFangAnService to set
	 */
	public void setShiShiFangAnService(ShiShiFangAnService shiShiFangAnService) {
		this.shiShiFangAnService = shiShiFangAnService;
	}

	/**
	 * @return the shiShiFangAn
	 */
	public ShiShiFangAnDTO getShiShiFangAn() {
		return shiShiFangAn;
	}

	/**
	 * @param shiShiFangAn the shiShiFangAn to set
	 */
	public void setShiShiFangAn(ShiShiFangAnDTO shiShiFangAn) {
		this.shiShiFangAn = shiShiFangAn;
	}

	/**
	 * @return the page
	 */
	public Page<ShiShiFangAnDTO> getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(Page<ShiShiFangAnDTO> page) {
		this.page = page;
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
	 * @return the attach
	 */
	public Attach getAttach() {
		return attach;
	}

	/**
	 * @param attach the attach to set
	 */
	public void setAttach(Attach attach) {
		this.attach = attach;
	}

	/**
	 * @return the attachSJZSService
	 */
	public AttachSJZSService getAttachSJZSService() {
		return attachSJZSService;
	}

	/**
	 * @param attachSJZSService the attachSJZSService to set
	 */
	public void setAttachSJZSService(AttachSJZSService attachSJZSService) {
		this.attachSJZSService = attachSJZSService;
	}

	/**
	 * 
	 * 描述：跳转到添加页面<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-3-25 下午05:15:06 <br/>  
	 * @version   1.0
	 */
	public String goToAddPage() {
		return ADDPAGE;
	}
	/**
	 * 
	 * 描述：跳转到更新页面<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-3-25 下午05:15:06 <br/>  
	 * @version   1.0
	 */
	public String goToUpdatePage() {
		
		this.shiShiFangAn = this.shiShiFangAnService.getSSFAById(shiShiFangAn.getId());
		this.attach = (Attach) this.attachSJZSService.findAttachById(shiShiFangAn.getAttachId());
		this.shiShiFangAn.setAttach(attach);
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
//		page = this.shiShiFangAnService.searchSSFAByParentId(shiShiFangAn, page);
		page = this.shiShiFangAnService.searchByCondition("*", shiShiFangAn, page);
		returnString = "searchSSFAs";
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
		page = this.shiShiFangAnService.searchByCondition("*", shiShiFangAn, page);
		returnString = "showsearchSSFAs";
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
		page = this.shiShiFangAnService.searchByCondition("*",shiShiFangAn, page);
		return "searchSSFAs";
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
		String returnString = "showSSFAs";
		shiShiFangAn = this.shiShiFangAnService.getSSFAById(shiShiFangAn.getId());
		this.attach = (Attach) this.attachSJZSService.findAttachById(shiShiFangAn.getAttachId());
		shiShiFangAn.setAttach(attach);
		return returnString;
	}

	/**
	 * 
	 * 描述：添加<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-2-25 下午04:48:42 <br/>
	 * 
	 * @version 1.0
	 */
	public String addSSFA() {
		shiShiFangAn.setId(new BaseDao().getGUID());
		shiShiFangAn.setNote1("0");//更新索引用
		String id = this.shiShiFangAnService.addSSFA(shiShiFangAn);
		if (id != null) {
			return "addsucs";
		} else {
			return "fail";
		}
	}
	/**
	 * 
	 * 描述：修改<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-2-25 下午04:48:42 <br/>
	 * 
	 * @version 1.0
	 */
	public String updateSSFA() {
		shiShiFangAn.setNote1("0");//更新索引用
		int result = this.shiShiFangAnService.updateSSFA(shiShiFangAn);
		if (result > 0) {
			return "updatesuc";
		} else {
			return "fail";
		}
	}
	/**
	 * 
	 * 描述：批量删除<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-2-25 下午04:48:42 <br/>
	 * 
	 * @version 1.0
	 */
	public String deleteSSFAs() {
		String ids = shiShiFangAn.getId();
		int result = 0;
        ShiShiFangAnDTO temp = null;
		if(ids!=null && !ids.trim().equals("")){
			String values[] = ids.split(",");
			for (int i = 0; i < values.length; i++) {
				temp = this.shiShiFangAnService.getSSFAById(values[i]);
				int num = this.shiShiFangAnService.deleteSSFA(values[i]);
				if(temp!=null && num >0){//删除附件
					this.attachSJZSService.delAttach(temp.getAttachId());
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
	 * 创建时间：2013-2-25 下午04:48:42 <br/>
	 * 
	 * @version 1.0
	 */
	
	public String delete() {
		int result = this.shiShiFangAnService.deleteSSFA(shiShiFangAn.getId());
		if (result > 0) {
			this.attachSJZSService.delAttach(shiShiFangAn.getAttachId());
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
