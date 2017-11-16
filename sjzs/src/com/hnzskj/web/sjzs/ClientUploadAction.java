package com.hnzskj.web.sjzs;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.hnzskj.common.Constant;
import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.fore.ClientUploadAttachDTO;
import com.hnzskj.persist.bean.fore.ClientUploadFFALDTO;
import com.hnzskj.persist.bean.fore.ClientUploadSJFGDTO;
import com.hnzskj.persist.bean.fore.ClientUploadSSFNDTO;
import com.hnzskj.persist.bean.system.Employee;
import com.hnzskj.service.fore.ClientUploadAttachService;
import com.hnzskj.service.fore.ClientUploadService;
import com.hnzskj.web.BaseAction;

@SuppressWarnings("serial")
public class ClientUploadAction extends BaseAction {
	private ClientUploadService clientUploadService;
	private ClientUploadAttachService clientUploadAttachService;
	private Page<ClientUploadSJFGDTO> pageSJFG = new Page<ClientUploadSJFGDTO>();
	private Page<ClientUploadSSFNDTO> pageSSFN = new Page<ClientUploadSSFNDTO>();
	private Page<ClientUploadFFALDTO> pageFFAL = new Page<ClientUploadFFALDTO>();
	private ClientUploadSJFGDTO sjfg;
	private ClientUploadSSFNDTO ssfn;
	private ClientUploadFFALDTO ffal;
	private ClientUploadAttachDTO clientUploadAttach;
	//菜单类别
	private String menuType;
	//附件id
	private String attachId;
	//查看附件时附件名
	private String imageFileName;
	//批量执行时id字符串  以,分隔
	private String batchData;
	
	/**
	 * 
	 * 方法描述: 各个模块用户上传文件列表<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-5-3 下午03:18:05
	 * @param
	 *
	 */
	public String list(){
		if("1".equals(menuType)){
			sjfg.setCheckFlag(Constant.CHECK_ED);
			pageSJFG = clientUploadService.searchSJFG(pageSJFG, sjfg);
			return "listSJFG";
		}if("3".equals(menuType)){
			ffal.setCheckFlag(Constant.CHECK_ED);
			pageFFAL = clientUploadService.searchFFAN(pageFFAL, ffal);
			return "listFFAL";
		}if("6".equals(menuType)){
			ssfn.setCheckFlag(Constant.CHECK_ED);
			pageSSFN = clientUploadService.searchSSFN(pageSSFN, ssfn);
			return "listSSFN";
		}
		return null;
	}
	
	/**
	 * 
	 * 方法描述: 查看用户上传附件信息<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-5-3 下午03:18:32
	 * @param
	 *
	 */
	public String showAtttachInfo(){
		if(attachId!=null&&!"".equals(attachId)){
			clientUploadAttach = clientUploadAttachService.findAttachById(attachId);
			String path = ServletActionContext.getServletContext().getRealPath("/files/sjzs/");
			imageFileName = String.valueOf(new Date().getTime());
			File dirFile = new File(path + "/swf/" + imageFileName + ".swf");
			try {
				dirFile.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			FlashPaper.delAllFile(path + "/swf/");
			try {
				FlashPaper.copyFile(clientUploadAttach.getAttachContentSwf(), dirFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "attachInfo";
	}
	
	/**
	 * 
	 * 方法描述: 批量数据迁移<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-5-4 下午05:28:02
	 * @param
	 *
	 */
	public String moveData(){
		Employee curEmpl = this.getEmplFromSession();
		boolean result = false;
		if(batchData!=null&&!"".equals(batchData)){
			if("1".equals(menuType)){
				result = clientUploadService.batchMoveData(batchData, Constant.SJFG, curEmpl);
			}if("3".equals(menuType)){
				result = clientUploadService.batchMoveData(batchData, Constant.FFAL, curEmpl);
			}if("6".equals(menuType)){
				result = clientUploadService.batchMoveData(batchData, Constant.SJSS, curEmpl);
			}
		}
		System.out.println("action result:" + result);
		PrintWriter pw = null;
		try {
			pw = this.getResponse().getWriter();
			if(result){
				pw.print("OK");
			}else{
				pw.print("NO");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * 方法描述: 批量数据回迁<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-5-6 上午10:11:16
	 * @param
	 *
	 */
	public String moveDataBack(){
		Employee curEmpl = this.getEmplFromSession();
		boolean result = false;
		if(batchData!=null&&!"".equals(batchData)){
			if("1".equals(menuType)){
				result = clientUploadService.batchMoveDataBack(batchData, Constant.SJFG, curEmpl);
			}if("3".equals(menuType)){
				result = clientUploadService.batchMoveDataBack(batchData, Constant.FFAL, curEmpl);
			}if("6".equals(menuType)){
				result = clientUploadService.batchMoveDataBack(batchData, Constant.SJSS, curEmpl);
			}
		}
		System.out.println("action result:" + result);
		PrintWriter pw = null;
		try {
			pw = this.getResponse().getWriter();
			if(result){
				pw.print("OK");
			}else{
				pw.print("NO");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ClientUploadService getClientUploadService() {
		return clientUploadService;
	}

	public void setClientUploadService(ClientUploadService clientUploadService) {
		this.clientUploadService = clientUploadService;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
//		if("1".equals(menuType))
//			this.menuType = Constant.SJFG;
//		if("3".equals(menuType))
//			this.menuType = Constant.FFAL;
//		if("6".equals(menuType))
//			this.menuType = Constant.SJSS;
	}

	public Page<ClientUploadSJFGDTO> getPageSJFG() {
		return pageSJFG;
	}

	public void setPageSJFG(Page<ClientUploadSJFGDTO> pageSJFG) {
		this.pageSJFG = pageSJFG;
	}

	public Page<ClientUploadSSFNDTO> getPageSSFN() {
		return pageSSFN;
	}

	public void setPageSSFN(Page<ClientUploadSSFNDTO> pageSSFN) {
		this.pageSSFN = pageSSFN;
	}

	public Page<ClientUploadFFALDTO> getPageFFAL() {
		return pageFFAL;
	}

	public void setPageFFAL(Page<ClientUploadFFALDTO> pageFFAL) {
		this.pageFFAL = pageFFAL;
	}

	public ClientUploadSJFGDTO getSjfg() {
		return sjfg;
	}

	public void setSjfg(ClientUploadSJFGDTO sjfg) {
		this.sjfg = sjfg;
	}

	public ClientUploadSSFNDTO getSsfn() {
		return ssfn;
	}

	public void setSsfn(ClientUploadSSFNDTO ssfn) {
		this.ssfn = ssfn;
	}

	public ClientUploadFFALDTO getFfal() {
		return ffal;
	}

	public void setFfal(ClientUploadFFALDTO ffal) {
		this.ffal = ffal;
	}

	public String getAttachId() {
		return attachId;
	}

	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}

	public ClientUploadAttachDTO getClientUploadAttach() {
		return clientUploadAttach;
	}

	public void setClientUploadAttach(ClientUploadAttachDTO clientUploadAttach) {
		this.clientUploadAttach = clientUploadAttach;
	}

	public ClientUploadAttachService getClientUploadAttachService() {
		return clientUploadAttachService;
	}

	public void setClientUploadAttachService(
			ClientUploadAttachService clientUploadAttachService) {
		this.clientUploadAttachService = clientUploadAttachService;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getBatchData() {
		return batchData;
	}

	public void setBatchData(String batchData) {
		this.batchData = batchData;
	}

}
