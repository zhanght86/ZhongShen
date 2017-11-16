package com.hnzskj.web.fore;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.hnzskj.common.Constant;
import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.fore.ClientInfoDTO;
import com.hnzskj.persist.bean.fore.ClientUploadAttachDTO;
import com.hnzskj.persist.bean.fore.ClientUploadDTO;
import com.hnzskj.persist.bean.fore.ClientUploadDXYJDTO;
import com.hnzskj.persist.bean.fore.ClientUploadFFALDTO;
import com.hnzskj.persist.bean.fore.ClientUploadSJFGDTO;
import com.hnzskj.persist.bean.fore.ClientUploadSSFNDTO;
import com.hnzskj.persist.bean.fore.SimpleMenuDTO;
import com.hnzskj.persist.bean.sjzs.CheckLogDTO;
import com.hnzskj.persist.bean.sjzs.FangFaAnLi;
import com.hnzskj.persist.bean.sjzs.Law;
import com.hnzskj.persist.bean.sjzs.ShiShiFangAnDTO;
import com.hnzskj.persist.bean.system.Employee;
import com.hnzskj.service.fore.ClientUploadService;
import com.hnzskj.service.fore.WdzsService;
import com.hnzskj.service.sjzs.CheckLogService;
import com.hnzskj.web.BaseAction;
import com.hnzskj.web.sjzs.FlashPaper;
/**
 * 我的助手模块
 * 		我的上传
 * 		个人中心
 * */
public class WdzsAction extends BaseAction{
	private static final long serialVersionUID = 6075963735907134808L;
	private Page<ClientUploadDTO> pageUpload = new Page<ClientUploadDTO>();
	Page<CheckLogDTO> checkLogPage = new Page<CheckLogDTO>();
	private Employee empl ;
	private ClientInfoDTO client;
	
	private WdzsService wdzsService;
	private ClientUploadService clientUploadService;
	private CheckLogService checkLogService;
	private String type;
	private String parentId;
	private ClientUploadService clientUpload;
	private ClientUploadDXYJDTO uploadDxyj;
	private ClientUploadFFALDTO uploadFfal;
	private ClientUploadSJFGDTO uploadSjfg;
	private ClientUploadSSFNDTO uploadSsfn;
	
	private ClientUploadAttachDTO attach = new ClientUploadAttachDTO();
	private Law law = new Law();
	private FangFaAnLi fangFaAnLi = new FangFaAnLi();
	private ShiShiFangAnDTO ssfaMethod = new ShiShiFangAnDTO();
	private HashMap<String,String> menuList;
	private String upResult;
	private String dropStr;
	private String showStr;
	private String imageFileName;
	private String uploadId;
	
		
	public Page<CheckLogDTO> getCheckLogPage() {
		return checkLogPage;
	}
	public void setCheckLogPage(Page<CheckLogDTO> checkLogPage) {
		this.checkLogPage = checkLogPage;
	}
	public String getUploadId() {
		return uploadId;
	}
	public void setUploadId(String uploadId) {
		this.uploadId = uploadId;
	}
	public CheckLogService getCheckLogService() {
		return checkLogService;
	}
	public void setCheckLogService(CheckLogService checkLogService) {
		this.checkLogService = checkLogService;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public String getShowStr() {
		return showStr;
	}
	public void setShowStr(String showStr) {
		this.showStr = showStr;
	}
	public ClientUploadAttachDTO getAttach() {
		return attach;
	}
	public void setAttach(ClientUploadAttachDTO attach) {
		this.attach = attach;
	}
	public Law getLaw() {
		return law;
	}
	public void setLaw(Law law) {
		this.law = law;
	}
	public FangFaAnLi getFangFaAnLi() {
		return fangFaAnLi;
	}
	public void setFangFaAnLi(FangFaAnLi fangFaAnLi) {
		this.fangFaAnLi = fangFaAnLi;
	}
	public ShiShiFangAnDTO getSsfaMethod() {
		return ssfaMethod;
	}
	public void setSsfaMethod(ShiShiFangAnDTO ssfaMethod) {
		this.ssfaMethod = ssfaMethod;
	}
	public ClientInfoDTO getClient() {
		return client;
	}
	public void setClient(ClientInfoDTO client) {
		this.client = client;
	}
	public Employee getEmpl() {
		return empl;
	}
	public void setEmpl(Employee empl) {
		this.empl = empl;
	}
	public String getUpResult() {
		return upResult;
	}
	public void setUpResult(String upResult) {
		this.upResult = upResult;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}	
	
	public Map<String,String> getMenuList() {
		return menuList;
	}
	public void setMenuList(HashMap<String,String> menuList) {
		this.menuList = menuList;
	}
	public ClientUploadService getClientUploadService() {
		return clientUploadService;
	}
	public void setClientUploadService(ClientUploadService clientUploadService) {
		this.clientUploadService = clientUploadService;
	}
	public WdzsService getWdzsService() {
		return wdzsService;
	}
	public void setWdzsService(WdzsService wdzsService) {
		this.wdzsService = wdzsService;
	}	
	public Page<ClientUploadDTO> getPageUpload() {
		return pageUpload;
	}
	public void setPageUpload(Page<ClientUploadDTO> pageUpload) {
		this.pageUpload = pageUpload;
	}
	public ClientUploadService getClientUpload() {
		return clientUpload;
	}
	public void setClientUpload(ClientUploadService clientUpload) {
		this.clientUpload = clientUpload;
	}
	public ClientUploadDXYJDTO getUploadDxyj() {
		return uploadDxyj;
	}
	public void setUploadDxyj(ClientUploadDXYJDTO uploadDxyj) {
		this.uploadDxyj = uploadDxyj;
	}
	public ClientUploadFFALDTO getUploadFfal() {
		return uploadFfal;
	}
	public void setUploadFfal(ClientUploadFFALDTO uploadFfal) {
		this.uploadFfal = uploadFfal;
	}
	public ClientUploadSJFGDTO getUploadSjfg() {
		return uploadSjfg;
	}
	public void setUploadSjfg(ClientUploadSJFGDTO uploadSjfg) {
		this.uploadSjfg = uploadSjfg;
	}
	public ClientUploadSSFNDTO getUploadSsfn() {
		return uploadSsfn;
	}
	public void setUploadSsfn(ClientUploadSSFNDTO uploadSsfn) {
		this.uploadSsfn = uploadSsfn;
	}
	public String getDropStr() {
		return dropStr;
	}
	public void setDropStr(String dropStr) {
		this.dropStr = dropStr;
	}
	
	
	public String zsGrzs(){
		empl = (Employee)getSessoinAttr("employee");
		client = wdzsService.getClientById(empl.getEmplId());
		if(client ==null){
			client = this.initClientInfo();
		}
		ClientUploadDTO uploadDTO = new ClientUploadDTO();
		uploadDTO.setClientId(empl.getEmplId());
		uploadDTO.setCheckFlag(Constant.CHECK_ED);
		pageUpload.setMaxResult(12);
		pageUpload = clientUpload.searchByCondition(pageUpload, uploadDTO);
		return "zsGrzs";
	}
	/**
	 * 
	 * 描述：当数据库中没有用户的积分信息时进行初始化<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-5-4 上午09:30:14 <br/>  
	 * @version   1.0
	 */
	private ClientInfoDTO initClientInfo(){
		
		ClientInfoDTO clientInfoDTO = new ClientInfoDTO();
		clientInfoDTO.setCheckNum(0);
		clientInfoDTO.setDownNum(0);
		clientInfoDTO.setIntegral(0);
		clientInfoDTO.setRank("1");
		clientInfoDTO.setTitle("审计初级学徒");
		clientInfoDTO.setUploadNum(0);
		
		return clientInfoDTO;
	}
	
	public String zsWdsc(){
		Employee empl = null;
		empl = (Employee)getSessoinAttr("employee");
		ClientUploadDTO uploadDTO = new ClientUploadDTO();
		uploadDTO.setClientId(empl.getEmplId());
		uploadDTO.setCheckFlag(Constant.CHECK_ALL);
		pageUpload.setMaxResult(14);
		pageUpload = clientUpload.searchByCondition(pageUpload, uploadDTO);
		return "zsWdsc";
	}
	
	/**
	 *方法描述： 打开上传页面<br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-26 上午11:15:52
	 * 
	 *@return
	 *@version 1.0
	 */ 
	public String upFile(){		
		return "upfile";	
	}
	public String initMenu(){
		List<SimpleMenuDTO> menuLists = wdzsService.initMenu();
		menuList = new HashMap<String, String>();
		for(SimpleMenuDTO menu:menuLists){
			if(!"22222222".equals(menu.getMenuId())&&!"44444444".equals(menu.getMenuId())&&!"55555555".equals(menu.getMenuId())){
				menuList.put(menu.getMenuId(), menu.getMenuName());
			}
		}
		return "initMenu";
	}
	
	
	public String selectFile() {
		return "fileSelect";
	}
	public String upSingleFilePage() {
		return "upSinglePage";
	}
	//异步加载菜单
	
	
	
	/**
	 *方法描述：执行上传操作 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-26 上午11:16:27
	 *@return
	 *@version 1.0
	 */ 
	public String uploadFileYJ(){		
		uploadDxyj.setType(type);
		uploadDxyj.setParentId(parentId);
		boolean value = clientUploadService.addUpload(uploadDxyj);
		return this.returns(value);
	}
	
	public String uploadFileAL(){
		uploadFfal.setType(type);
		uploadFfal.setParentId(parentId);
		boolean value = clientUploadService.addUpload(uploadFfal);
		return this.returns(value);
	}

	public String uploadFileFG(){
		uploadSjfg.setType(type);
		uploadSjfg.setParentId(parentId);
		boolean value = clientUploadService.addUpload(uploadSjfg);
		return this.returns(value);
	}

	public String uploadFileEI(){
		uploadSsfn.setType(type);
		uploadSsfn.setParentId(parentId);
		boolean value = clientUploadService.addUpload(uploadSsfn);
		return this.returns(value);
	}
	public String deleteClientUp(){
		if(dropStr!= null){
			dropStr=dropStr.substring(1);
			clientUploadService.deleteClientUp(getEmplId(),dropStr);
			return "deleSuc";
		}else{
			return ERROR;
		}
	}
	
	
	public String getEmplId(){
		Employee empl =null;
		Object obj=getSessoinAttr("employee");
		if(obj !=null){
			empl = (Employee)obj;
			return empl.getEmplId();
		}else{
			return null;
		}
	}
	
	public String returns(boolean value){
		if(value){
			clientUploadService.updateClientUpNum(this.getEmplId());
			upResult = "上传成功！";
		}else{
			upResult = "上传失败！";
		}
		System.out.println(upResult);
		return "upMess";
	}
	
	/**
	 * 查看具体内容
	 * */
	
	public String showCont(){
		if(showStr==null){
			return "index";
		}else{
			if(getEmplId() == null){
				return "index";
			}else{
				String[] strs=showStr.split("_");
				String uploadId = strs[0];
				String type=strs[1];
				return getReturn(clientUploadService.selectUploadById(uploadId, type), type);
			}			
		}
	}
	
	public String getReturn(ClientUploadDTO clientUpload,String types){
		if(Constant.FFAL.equals(types)){
			fangFaAnLi = (FangFaAnLi)clientUploadService.objectByT(clientUpload, types);
			showAtttachInfo(clientUploadService.findAttachs(fangFaAnLi.getAttachId()));
			return "contFfal";
		}else if(Constant.SJFG.equals(types)){
			law = (Law)clientUploadService.objectByT(clientUpload, types);
			showAtttachInfo(clientUploadService.findAttachs(law.getAttachId()));
			return "contSjfg";
		}else if(Constant.SJSS.equals(types)){
			ssfaMethod = (ShiShiFangAnDTO)clientUploadService.objectByT(clientUpload, types);
			showAtttachInfo(clientUploadService.findAttachs(ssfaMethod.getAttachId()));
			return "contSsfn";
		}else{
			return "index";
		}
	}
	
	public String showAtttachInfo(ClientUploadAttachDTO attach) {
		
		String path = ServletActionContext.getServletContext().getRealPath("/files/sjzs/");
		imageFileName = String.valueOf(new Date().getTime());
		File dirFile = new File(path + "/swf/" +imageFileName + ".swf");
		
		try {
			dirFile.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		FlashPaper.delAllFile(path + "/swf/");
		try {
			FlashPaper.copyFile(attach.getAttachContentSwf(), dirFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "suc";
	}
	
	public String showCheckLog(){
		CheckLogDTO checkLog = new CheckLogDTO();
		checkLog.setInfoId(uploadId);
		checkLogPage = checkLogService.searchByCondition(checkLogPage,checkLog);
		return "showCheckLog";
		
	}
	
}
