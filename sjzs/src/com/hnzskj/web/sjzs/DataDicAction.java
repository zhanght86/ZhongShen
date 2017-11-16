package com.hnzskj.web.sjzs;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.sjzs.DataDicDTO;
import com.hnzskj.persist.bean.sjzs.SjzhMenuTree;
import com.hnzskj.service.sjzs.DataDicService;
import com.hnzskj.service.sjzs.ShenJiZhuShouService;
import com.hnzskj.web.BaseAction;

@SuppressWarnings("serial")
public class DataDicAction extends BaseAction {

	
	private DataDicService dataDicService = null;
	
	private ShenJiZhuShouService sjzsService ;
	

	/**
	 *flag 作为一个标志，根据前台设置的flag值判断跳转页面
	 */
	private String flag = "";

	/**
	 * 法规实体类
	 */
	private DataDicDTO dataDicDto = new DataDicDTO();

	private Page<DataDicDTO> page = new Page<DataDicDTO>();
	
	

	public ShenJiZhuShouService getSjzsService() {
		return sjzsService;
	}


	public void setSjzsService(ShenJiZhuShouService sjzsService) {
		this.sjzsService = sjzsService;
	}


	/**
	 * @return the page
	 */
	public Page<DataDicDTO> getPage() {
		return page;
	}


	/**
	 * @return the dataDicDto
	 */
	public DataDicDTO getDataDicDto() {
		return dataDicDto;
	}


	/**
	 * @param dataDicDto the dataDicDto to set
	 */
	public void setDataDicDto(DataDicDTO dataDicDto) {
		this.dataDicDto = dataDicDto;
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
	 * @return the dataDicService
	 */
	public DataDicService getDataDicService() {
		return dataDicService;
	}


	/**
	 * @param dataDicService the dataDicService to set
	 */
	public void setDataDicService(DataDicService dataDicService) {
		this.dataDicService = dataDicService;
	}


	/**
	 * @return the dataDicDTO
	 */
	public DataDicDTO getDataDicDTO() {
		return dataDicDto;
	}


	/**
	 * @param dataDicDTO the dataDicDTO to set
	 */
	public void setDataDicDTO(DataDicDTO dataDicDTO) {
		this.dataDicDto = dataDicDTO;
	}


	/**
	 * @param page the page to set
	 */
	public void setPage(Page<DataDicDTO> page) {
		this.page = page;
	}

	/**
	 * 
	 * 描述：跳转到添加页面<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-3-25 下午05:15:06 <br/>  
	 * @version   1.0
	 */
	public String goToAddPage() {
		SjzhMenuTree parent = sjzsService.findMenuById(dataDicDto.getDicParentId());
		if(parent!=null)
			dataDicDto.setIndustry(parent.getMenuName());
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
		
		dataDicDto = this.dataDicService.getDataDicById(dataDicDto.getDicId());
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
//		page = this.dataDicService.searchDataDicByParentId(dataDicDto, page);
		page = this.dataDicService.searchByCondition("*", dataDicDto, page);
		returnString = "searchDataDics";
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
		page = this.dataDicService.searchByCondition("*", dataDicDto, page);
		returnString = "showsearchDataDics";
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
		page = this.dataDicService.searchByCondition("*", dataDicDto, page);
		return "searchDataDics";
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
		String returnString = "showDataDic";
		dataDicDto = this.dataDicService.getDataDicById(dataDicDto.getDicId());
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
	public String addDataDic() {
		dataDicDto.setDicId(new BaseDao().getGUID());
		dataDicDto.setNote1("0");//更新索引用
		String id = this.dataDicService.addDataDic(dataDicDto);
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
	public String updateDataDic() {
		dataDicDto.setNote1("0");//更新索引用
		int result = this.dataDicService.updateDataDic(dataDicDto);
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
	public String deleteDataDics() {
		String ids = dataDicDto.getDicId();
		int result = 0;
		if(ids!=null && !ids.trim().equals("")){
			String values[] = ids.split(",");
			for (int i = 0; i < values.length; i++) {
				result += this.dataDicService.deleteDataDic(values[i]);
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
		int result = this.dataDicService.deleteDataDic(dataDicDto.getDicId());
		if (result > 0) {
			return DELSUC;
		} else {
			return "fail";
		}
	}

}
