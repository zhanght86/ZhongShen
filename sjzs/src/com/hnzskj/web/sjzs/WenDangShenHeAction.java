package com.hnzskj.web.sjzs;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.hnzskj.common.Constant;
import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.fore.ClientUploadAttachDTO;
import com.hnzskj.persist.bean.fore.ClientUploadDTO;
import com.hnzskj.persist.bean.fore.ClientUploadDXYJDTO;
import com.hnzskj.persist.bean.fore.ClientUploadFFALDTO;
import com.hnzskj.persist.bean.fore.ClientUploadSJFGDTO;
import com.hnzskj.persist.bean.fore.ClientUploadSSFNDTO;
import com.hnzskj.persist.bean.sjzs.CheckLogDTO;
import com.hnzskj.persist.bean.system.Employee;
import com.hnzskj.service.fore.ClientUploadAttachService;
import com.hnzskj.service.fore.ClientUploadService;
import com.hnzskj.service.sjzs.CheckLogService;
import com.hnzskj.web.BaseAction;

@SuppressWarnings("serial")
public class WenDangShenHeAction extends BaseAction{
	private ClientUploadService clientUploadService;
	private CheckLogService checkLogService;
	private ClientUploadAttachService clientUploadAttachService;
	private Page<ClientUploadDTO> page = new Page<ClientUploadDTO>();
	
	private ClientUploadDTO clientUpload = new ClientUploadDTO();
	private ClientUploadDXYJDTO dxyj;
	private ClientUploadFFALDTO ffal;
	private ClientUploadSJFGDTO sjfg;
	private ClientUploadSSFNDTO ssfn;
	private CheckLogDTO checkLog;
	private ClientUploadAttachDTO clientAttach;
	
	//当前系统用户
	private Employee curUser;
	//文档类型
	private String type;
	//查看附件时的临时名称
	private String imageFileName;
	//用户上传信息id
	private String uploadId;
	//附件id
	private String attachId;
	//审核意见
	private String checkAdvice;
	
	//批量审核时接收的id和type的数组
	private String batchData;
	//批量审核结果
	private int result;

	/**
	 * 
	 * 方法描述: 审核列表<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-4-26 下午06:15:20
	 * @param
	 *
	 */
	public String list() {
		page = clientUploadService.searchByCondition(page, clientUpload);
		return "list";
	}
	
	/**
	 * 
	 * 方法描述: 打开某条记录的审核页面   更新审核状态，插入审核记录<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-4-27 下午02:31:50
	 * @param
	 *
	 */
	public String toCheck(){
		if(Constant.FFAL.equals(type)){
			ffal = (ClientUploadFFALDTO)clientUploadService.selectUploadById(uploadId, type);
			updateChecking(ffal);
			addCheckLog(ffal);
			return "checkFFAL";
		}else if(Constant.SJFG.equals(type)){
			sjfg = (ClientUploadSJFGDTO)clientUploadService.selectUploadById(uploadId, type);
			updateChecking(sjfg);
			addCheckLog(sjfg);
			return "checkSJFG";
		}else if(Constant.DXYJ.equals(type)){
			dxyj = (ClientUploadDXYJDTO)clientUploadService.selectUploadById(uploadId, type);
			updateChecking(dxyj);
			addCheckLog(dxyj);
			return "checkDXYJ";
		}else if(Constant.SJSS.equals(type)){
			ssfn = (ClientUploadSSFNDTO)clientUploadService.selectUploadById(uploadId, type);
			updateChecking(ssfn);
			addCheckLog(ssfn);
			return "checkSJSS";
		}
		return null;
	}
	
	/**
	 * 
	 * 方法描述: 审核功能    放弃-清除审核人信息;更新记录，插入审核记录<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-4-27 下午06:55:06
	 * @param
	 *
	 */
	public String check(){
		ClientUploadDTO temp = changeChildType(type);
		temp = isGiveUpCheck(temp);
		clientUploadService.updateUpload(temp);
		addCheckLog(temp);
		return "showList";
	}
	
	/**
	 * 
	 * 方法描述: 超级管理员对所有情况都可以审核<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-4-28 下午04:02:00
	 * @param
	 *
	 */
	public String checkAll(){
		ClientUploadDTO temp = changeChildType(type);
		temp = isGiveUpCheck(temp);
		clientUploadService.updateUpload(temp);
		addCheckLog(temp);
		return "showList";
	}
	
	/**
	 * 
	 * 方法描述: 类型转换<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-4-28 下午02:22:55
	 * @param
	 *
	 */
	private ClientUploadDTO changeChildType(String type){
		ClientUploadDTO temp = new ClientUploadDTO();
		if(Constant.FFAL.equals(type)){
			temp = ffal;
		}else if(Constant.SJFG.equals(type)){
			temp = sjfg;
		}else if(Constant.DXYJ.equals(type)){
			temp = dxyj;
		}else if(Constant.SJSS.equals(type)){
			temp = ssfn;
		}
		return temp;
	}
	
	/**
	 * 
	 * 方法描述: 批量审核   页面传入数据格式"id,type;id,type;..."  查询出每条信息，更新审核人，插入审核记录<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-4-28 下午01:55:41
	 * @param
	 *
	 */
	public String checkBatch(){
		if(batchData!=null&&!"".equals(batchData)){
			String[] temps = batchData.split(";");
			for(int i=0;i<temps.length;i++){
				String[] temp = temps[i].split(",");
				clientUpload = clientUploadService.selectUploadById(temp[0], temp[1]);
				clientUpload.setCheckFlag(result);
				curUser = getEmplFromSession();
				clientUpload.setCurCheckUserId(curUser.getEmplId());
				clientUpload.setCurCheckUserName(curUser.getEmplName());
				clientUploadService.updateUpload(clientUpload);
				addCheckLog(clientUpload);
			}
		}
		return "showList";
	}

	/**
	 * 
	 * 方法描述: 审核放弃时清除当前审核用户信息  对已修改的不做保存<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-4-28 上午11:02:00
	 * @param
	 *
	 */
	private ClientUploadDTO isGiveUpCheck(ClientUploadDTO clientUpload){
		if(Constant.CHECK_NO == clientUpload.getCheckFlag()){
			clientUpload = clientUploadService.selectUploadById(clientUpload.getId(), clientUpload.getType());
			clientUpload.setCheckFlag(Constant.CHECK_NO);
			clientUpload.setCurCheckUserId(null);
			clientUpload.setCurCheckUserName(null);
			//打回的也不做内容上的修改    处理人设置为当前用户     主要是针对超级管理员可以对所有状态的数据进行审核
		}else if(Constant.CHECK_BACK == clientUpload.getCheckFlag()){
			clientUpload = clientUploadService.selectUploadById(clientUpload.getId(), clientUpload.getType());
			curUser = this.getEmplFromSession();
			clientUpload.setCurCheckUserId(curUser.getEmplId());
			clientUpload.setCurCheckUserName(curUser.getEmplName());
			clientUpload.setCheckFlag(Constant.CHECK_BACK);
		}
		return clientUpload;
	}
	
	/**
	 * 
	 * 方法描述: 更新审核中状态<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-4-27 下午02:41:16
	 * @param
	 *
	 */
	private void updateChecking(ClientUploadDTO clientUpload){
		curUser = getEmplFromSession();
		clientUpload.setCheckFlag(Constant.CHECK_ING);
		clientUpload.setCurCheckUserId(curUser.getEmplId());
		clientUpload.setCurCheckUserName(curUser.getEmplName());
		clientUploadService.updateUpload(clientUpload);
	}
	
	/**
	 * 
	 * 方法描述: 添加审核记录<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-4-28 上午11:28:18
	 * @param
	 *
	 */
	private void addCheckLog(ClientUploadDTO clientUpload){
		curUser = getEmplFromSession();
		checkLog = new CheckLogDTO(curUser.getEmplId(),curUser.getEmplName(),clientUpload.getClientId(),
				clientUpload.getClientName(),clientUpload.getId(),clientUpload.getCaption(),clientUpload.getAttachId(),
				checkAdvice,clientUpload.getCheckFlag());
		checkLogService.addCheckLog(checkLog);
	}
	
	/**
	 * 
	 * 方法描述: 查看附件信息<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-4-27 下午05:49:20
	 * @param
	 *
	 */
	public String showAttachInfo(){
		if(attachId!=null&&!"".equals(attachId)){
			clientAttach = clientUploadAttachService.findAttachById(attachId);
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
				FlashPaper.copyFile(clientAttach.getAttachContentSwf(), dirFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "attachInfo";
	}
	
	/**
	 * 
	 * 方法描述: 删除附件信息，并更新原信息<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-4-28 上午11:29:21
	 * @param
	 *
	 */
	public String delAttach(){
		ClientUploadDTO temp = clientUploadService.selectUploadById(uploadId, type);
		boolean result = clientUploadAttachService.delAttachById(temp.getAttachId());
		PrintWriter out = null;
		try {
			out = getResponse().getWriter();
			if(result){
				temp.setAttachId(null);
				clientUploadService.updateUpload(temp);
				out.print(true);
			}else{
				out.print(false);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * 方法描述: 判断当前信息是否正在被审核<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-4-28 下午03:29:19
	 * @param
	 *
	 */
	public String isChecking(){
		ClientUploadDTO temp = clientUploadService.selectUploadById(uploadId, type);
		String checkUserId = temp.getCurCheckUserId();
		PrintWriter out = null;
		try {
			out = getResponse().getWriter();
			if(checkUserId!=null&&!"".equals(checkUserId)){
				curUser = this.getEmplFromSession();
				if(!curUser.getEmplId().equals(checkUserId)){
					out.print(false);
				}else{
					out.print(true);
				}
			}else{
				out.print(true);
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

	public CheckLogService getCheckLogService() {
		return checkLogService;
	}

	public void setCheckLogService(CheckLogService checkLogService) {
		this.checkLogService = checkLogService;
	}

	public Page<ClientUploadDTO> getPage() {
		return page;
	}

	public void setPage(Page<ClientUploadDTO> page) {
		this.page = page;
	}

	public ClientUploadDTO getClientUpload() {
		return clientUpload;
	}

	public void setClientUpload(ClientUploadDTO clientUpload) {
		this.clientUpload = clientUpload;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public ClientUploadAttachDTO getClientAttach() {
		return clientAttach;
	}

	public void setClientAttach(ClientUploadAttachDTO clientAttach) {
		this.clientAttach = clientAttach;
	}

	public ClientUploadDXYJDTO getDxyj() {
		return dxyj;
	}

	public void setDxyj(ClientUploadDXYJDTO dxyj) {
		this.dxyj = dxyj;
	}

	public ClientUploadFFALDTO getFfal() {
		return ffal;
	}

	public void setFfal(ClientUploadFFALDTO ffal) {
		this.ffal = ffal;
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

	public CheckLogDTO getCheckLog() {
		return checkLog;
	}

	public void setCheckLog(CheckLogDTO checkLog) {
		this.checkLog = checkLog;
	}

	public Employee getCurUser() {
		return curUser;
	}

	public void setCurUser(Employee curUser) {
		this.curUser = curUser;
	}

	public String getUploadId() {
		return uploadId;
	}

	public void setUploadId(String uploadId) {
		this.uploadId = uploadId;
	}

	public ClientUploadAttachService getClientUploadAttachService() {
		return clientUploadAttachService;
	}

	public void setClientUploadAttachService(
			ClientUploadAttachService clientUploadAttachService) {
		this.clientUploadAttachService = clientUploadAttachService;
	}

	public String getAttachId() {
		return attachId;
	}

	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}

	public String getCheckAdvice() {
		return checkAdvice;
	}

	public void setCheckAdvice(String checkAdvice) {
		this.checkAdvice = checkAdvice;
	}

	public String getBatchData() {
		return batchData;
	}

	public void setBatchData(String batchData) {
		this.batchData = batchData;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
	
}
