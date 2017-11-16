package com.hnzskj.service.fore.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.Constant;
import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.attach.Attach;
import com.hnzskj.persist.bean.fore.ClientUploadAttachDTO;
import com.hnzskj.persist.bean.fore.ClientUploadDTO;
import com.hnzskj.persist.bean.fore.ClientUploadFFALDTO;
import com.hnzskj.persist.bean.fore.ClientUploadSJFGDTO;
import com.hnzskj.persist.bean.fore.ClientUploadSSFNDTO;
import com.hnzskj.persist.bean.sjzs.DataTransportDTO;
import com.hnzskj.persist.bean.sjzs.FangFaAnLi;
import com.hnzskj.persist.bean.sjzs.Law;
import com.hnzskj.persist.bean.sjzs.ShiShiFangAnDTO;
import com.hnzskj.persist.bean.system.Employee;
import com.hnzskj.persist.dao.fore.ClientUploadAttachDao;
import com.hnzskj.persist.dao.fore.ClientUploadDao;
import com.hnzskj.persist.dao.sjzs.AttachSJZSDao;
import com.hnzskj.persist.dao.sjzs.FangFaAnLiDao;
import com.hnzskj.persist.dao.sjzs.ShenJiFaGuiDao;
import com.hnzskj.persist.dao.sjzs.ShiShiFangAnDao;
import com.hnzskj.persist.dao.sjzs.TransportDataLogDao;
import com.hnzskj.service.fore.ClientUploadService;

public class ClientUploadServiceImpl implements ClientUploadService {
	private ClientUploadDao clientUploadDao;
	private FangFaAnLiDao fangFaAnLiDao;
	private ShenJiFaGuiDao shenJiFaGuiDao;
	private ShiShiFangAnDao shiShiFangAnDao;
	private ClientUploadAttachDao clientUploadAttachDao;
	private AttachSJZSDao attachSJZSDao;
	private TransportDataLogDao transportDataLogDao;
	

	public ClientUploadDao getClientUploadDao() {
		return clientUploadDao;
	}

	public void setClientUploadDao(ClientUploadDao clientUploadDao) {
		this.clientUploadDao = clientUploadDao;
	}

	@Override
	public boolean addUpload(ClientUploadDTO clientUpload) {
		if(clientUpload == null){
			return false;
		}else{
			return clientUploadDao.addUpload(clientUpload);
		}
	}

	@Override
	public boolean deleteUpload(ClientUploadDTO clientUpload) {
		if(clientUpload == null || clientUpload.getId() == null){
			return false;
		}else{
			return clientUploadDao.deleteUpload(clientUpload);
		}
	}

	@Override
	public List<ClientUploadDTO> selectUploadByClient(String clientId) {
		if(clientId == null ||"".equals(clientId)){
			return null;
		}else{
			return clientUploadDao.selectUploadByClient(clientId);
		}
	}

	@Override
	public ClientUploadDTO selectUploadById(String uploadId, String type) {
		if(uploadId == null || "".equals(uploadId)){
			return null;
		}else{
			return clientUploadDao.selectUploadById(uploadId,type);
		}
	}

	@Override
	public boolean updateUpload(ClientUploadDTO clientUpload) {
		if(clientUpload == null || clientUpload.getId()==null||"".equals(clientUpload.getId())){
			return false;
		}else{
			return clientUploadDao.updateUpload(clientUpload);
		}
	}

	@Override
	public Page<ClientUploadDTO> searchByCondition(Page<ClientUploadDTO> page,ClientUploadDTO clientUpload) {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("uploaddate", "desc");
		String fields = "id,caption,content,checkflag,uploaddate,type,attachid,clientid,clientname,isopen,curcheckuserid,curcheckusername";
		return clientUploadDao.searchByCondition(page, fields, clientUpload, orderby);
	}

	@Override
	public Page<ClientUploadFFALDTO> searchFFAN(Page<ClientUploadFFALDTO> page, ClientUploadFFALDTO clientUpload) {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("uploaddate", "desc");
		Object[] params = {clientUpload.getParentId()};
		StringBuffer sqlCondition = new StringBuffer(" where 1 = 1 ");
		if(clientUpload.getCaption()!=null&&!"".equals(clientUpload.getCaption().trim())){
			sqlCondition.append(" and caption like '%").append(clientUpload.getCaption().trim()).append("%'");
		}
		sqlCondition.append(" and checkFlag = ").append(clientUpload.getCheckFlag());
		return this.clientUploadDao.searchFFAN(page, "*", sqlCondition.toString(), params, orderby);
	}

	@Override
	public Page<ClientUploadSJFGDTO> searchSJFG(Page<ClientUploadSJFGDTO> page, ClientUploadSJFGDTO clientUpload) {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("uploaddate", "desc");
		Object[] params = {clientUpload.getParentId()};
		StringBuffer sqlCondition = new StringBuffer(" where 1 = 1 ");
		if(clientUpload.getCaption()!=null&&!"".equals(clientUpload.getCaption().trim())){
			sqlCondition.append(" and caption like '%").append(clientUpload.getCaption().trim()).append("%'");
		}
		sqlCondition.append(" and checkFlag = ").append(clientUpload.getCheckFlag());
		return this.clientUploadDao.searchSJFG(page, "*", sqlCondition.toString(), params, orderby);
	}

	@Override
	public Page<ClientUploadSSFNDTO> searchSSFN(Page<ClientUploadSSFNDTO> page, ClientUploadSSFNDTO clientUpload) {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("uploaddate", "desc");
		Object[] params = {clientUpload.getParentId()};
		StringBuffer sqlCondition = new StringBuffer(" where 1 = 1 ");
		if(clientUpload.getCaption()!=null&&!"".equals(clientUpload.getCaption().trim())){
			sqlCondition.append(" and caption like '%").append(clientUpload.getCaption().trim()).append("%'");
		}
		sqlCondition.append(" and checkFlag = ").append(clientUpload.getCheckFlag());
		return this.clientUploadDao.searchSSFN(page, "*", sqlCondition.toString(), params, orderby);
	}

	@Override
	public boolean changeCheckFlag(ClientUploadDTO clientUpload) {
		return clientUploadDao.changeCheckFlag(clientUpload)>0;
	}

	@Override
	public String getCurCheckUser(ClientUploadDTO clientUpload) {
		return clientUploadDao.getCurCheckUser(clientUpload);
	}
	/* 
	* <p>Title: updateClientUpNum</p>
	* <p>Description: </p>
	* @param clientId
	* @return 
	* @see com.hnzskj.service.fore.ClientUploadService#updateClientUpNum(java.lang.String) 
	*/ 
	@Override
	public int updateClientUpNum(String clientId) {
		return clientUploadDao.updateClientUpNum(clientId);
	}

	@Override
	public boolean deleteClientUp(String clientId,String dropStr) {
		String[] strs=dropStr.split(",");
		ClientUploadDTO clientUpload = new ClientUploadDTO();
		for(int i=0;i<strs.length;i++){
			String[] str=strs[i].split("_");
			String id=str[0];
			String type=str[1];
			String check = str[2];
			clientUpload.setId(id);
			clientUpload.setType(type);
			downNatlByCheck(clientId,check);
			clientUploadDao.deleteUpload(clientUpload);
		}
		return true;
	}
	
	/**
	 *方法描述：删除的时候判断是否通过审核，如果通过审核则扣分 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-5-3 下午03:50:50
	 * 
	 *@return
	 *@version 1.0
	 */ 
	public void downNatlByCheck(String clientId , String check){
		if("2".equals(check)){
			clientUploadDao.downNatlByCheck(clientId);
		}
	}

	@Override
	public Object objectByT(ClientUploadDTO clientUpload, String type) {
		Object obj = null;
		if(Constant.FFAL.equals(type)){
			ClientUploadFFALDTO uploadFfal = (ClientUploadFFALDTO)clientUpload;
			FangFaAnLi ffal = new FangFaAnLi();
			ffal.setId(uploadFfal.getId());
			ffal.setTitle(uploadFfal.getCaption());
			ffal.setAuthor(uploadFfal.getAuthor());
			ffal.setDepartment(uploadFfal.getDepartment());
			ffal.setFfalDateTime(uploadFfal.getFfalDateTime());
			ffal.setAwards(uploadFfal.getAwards());
			ffal.setAttachId(uploadFfal.getAttachId());
			ffal.setContent(uploadFfal.getContent());
			ffal.setUploadFlag(1);
			obj = ffal;
		}else if(Constant.SJFG.equals(type)){
			ClientUploadSJFGDTO uploadSjfg = (ClientUploadSJFGDTO)clientUpload;
			Law sjfg = new Law();
			sjfg.setLawId(uploadSjfg.getId());
			sjfg.setLawName(uploadSjfg.getCaption());
			sjfg.setLawNumber(uploadSjfg.getLawNumber());
			sjfg.setLawOrg(uploadSjfg.getLawOrg());
			sjfg.setLawTrade(uploadSjfg.getLawTrade());
			sjfg.setLawContent(uploadSjfg.getContent());
			sjfg.setLawGrade(uploadSjfg.getLawGrade());
			sjfg.setAttachId(uploadSjfg.getAttachId());
			obj = sjfg;			
		}else if(Constant.SJSS.equals(type)){
			ClientUploadSSFNDTO uploadSsfn = (ClientUploadSSFNDTO)clientUpload;
			ShiShiFangAnDTO ssfn = new ShiShiFangAnDTO();
			ssfn.setId(uploadSsfn.getId());
			ssfn.setName(uploadSsfn.getCaption());
			ssfn.setIndustry(uploadSsfn.getIndustry());
			ssfn.setAttachId(uploadSsfn.getAttachId());
			ssfn.setKeyWord(uploadSsfn.getKeyWord());
			ssfn.setWriteDate(uploadSsfn.getWriteDate());
			obj=ssfn;
		}
		return obj;
	}
	@Override
	public ClientUploadAttachDTO findAttachs(String attachId){
		return clientUploadDao.findAttachs(attachId);
	}

	@Override
	public boolean batchMoveData(String ids, String type, Employee employee) {
		String attachIds = "";
		List<DataTransportDTO> logList = new ArrayList<DataTransportDTO>();
		//结果集
		List<Boolean> results = new ArrayList<Boolean>();
		boolean result = false;
		
		//把用户上传数据迁移到对应的业务表中
		if(Constant.DXYJ.equals(type)){
			//定性依据暂无
//			List<ClientUploadDXYJDTO> list = new ArrayList<ClientUploadDXYJDTO>();
		}else if(Constant.FFAL.equals(type)){
			List<ClientUploadFFALDTO> clientUploads = clientUploadDao.selectFFALUploads(ids);
			for(ClientUploadFFALDTO upload : clientUploads){
				attachIds += upload.getAttachId() + ",";
				FangFaAnLi ffal = new FangFaAnLi(upload.getId(),upload.getCaption(),upload.getAuthor(),
												 upload.getDepartment(),upload.getFfalDateTime(),upload.getAwards(),
												 upload.getAttachId(),upload.getParentId(),upload.getContent(),
												 upload.getIsOpen()==0?1:2);
				result = fangFaAnLiDao.addFFAL(ffal) == null ? false : true;
				System.out.println(result);
				results.add(result);
				DataTransportDTO log = new DataTransportDTO(upload.getId(),upload.getAttachId(),upload.getClientId(),
															upload.getClientName(),employee.getEmplId(),employee.getEmplName(),
															Constant.FFAL,0);
				logList.add(log);
			}
		}else if(Constant.SJFG.equals(type)){
			List<ClientUploadSJFGDTO> clientUploads = clientUploadDao.selectSJFGUploads(ids);
			for(ClientUploadSJFGDTO upload : clientUploads){
				attachIds += upload.getAttachId() + ",";
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date temp = null;
				try {
					temp = sf.parse(upload.getLawDate());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				Law sjfg = new Law(upload.getId(),upload.getCaption(),upload.getLawNumber(),upload.getLawOrg(),
						           upload.getLawTrade(),upload.getContent(),new java.sql.Date(temp.getTime()),
						           upload.getLawGrade(),upload.getLawCategory(),upload.getParentId(),null,
						           upload.getAttachId(),upload.getIsOpen()==0?1:2);
				result = shenJiFaGuiDao.addLaw(sjfg) == null ? false : true;
				System.out.println("insert fg:"+result);
				results.add(result);
				DataTransportDTO log = new DataTransportDTO(upload.getId(),upload.getAttachId(),upload.getClientId(),
															upload.getClientName(),employee.getEmplId(),
															employee.getEmplName(),Constant.SJFG,0);
				logList.add(log);
			}
		}else if(Constant.SJSS.equals(type)){
			List<ClientUploadSSFNDTO> clientUploads = clientUploadDao.selectSSFNUploads(ids);
			for(ClientUploadSSFNDTO upload : clientUploads){
				attachIds += upload.getAttachId() + ",";
				ShiShiFangAnDTO ssfn = new ShiShiFangAnDTO(upload.getId(),upload.getCaption(),upload.getIndustry(),
														   upload.getAttachId(),upload.getKeyWord(),upload.getWriteDate(),
														   upload.getParentId(),upload.getAuthor(), upload.getIsOpen()==0?1:2);
				result = shiShiFangAnDao.addSSFA(ssfn) == null ? false : true;
				results.add(result);
				DataTransportDTO log = new DataTransportDTO(upload.getId(),upload.getAttachId(),upload.getClientId(),
															upload.getClientName(),employee.getEmplId(),
															employee.getEmplName(),Constant.SJSS,0);
				logList.add(log);
			}
		}
		
		System.out.println("~~~~~~~~~~attachIds:" + attachIds);
		//把对应的附件信息迁移到附件表中
		List<ClientUploadAttachDTO> clientUploadAttachs = clientUploadAttachDao.findAttachByIds(attachIds);
		List<Attach> attachs = new ArrayList<Attach>();
		for(ClientUploadAttachDTO uploadAttach : clientUploadAttachs){
			Attach attach = new Attach(uploadAttach.getAttachId(),uploadAttach.getAttachName(),
									   uploadAttach.getAttachContentDoc(),uploadAttach.getAttachContentSwf());
			attachs.add(attach);
//			result = attachSJZSDao.addAttach(attach)>0;
//			results.add(result);
//			System.out.println("insert attach:"+result);
		}
		result = attachSJZSDao.batchAddAttach(attachs) > 0;
		results.add(result);
		System.out.println("attach size:" + attachs.size());
		System.out.println("batch add attach:" + result);
		
		//更新用户上传数据状态为已迁移
		result = clientUploadDao.updateTransportFlag(ids, Constant.TRANSPORT_ED, type)>0;
		results.add(result);
		System.out.println("update:"+result);
		
		//添加数据迁移日志
		result = transportDataLogDao.batchAddLog(logList)>0;
		results.add(result);
		System.out.println("insert log:"+result);
		System.out.println("result:" + !results.contains(false));
		return !results.contains(false);
	}
	

	@Override
	public boolean batchMoveDataBack(String ids, String type, Employee employee) {
		String attachIds = "";
		List<DataTransportDTO> logList = new ArrayList<DataTransportDTO>();
		//结果集
		List<Boolean> results = new ArrayList<Boolean>();
		boolean result = false;
		
		//把迁移到的业务表中的数据删除
		if(Constant.DXYJ.equals(type)){
			//定性依据暂无
//			List<ClientUploadDXYJDTO> list = new ArrayList<ClientUploadDXYJDTO>();
		}else if(Constant.FFAL.equals(type)){
			List<ClientUploadFFALDTO> clientUploads = clientUploadDao.selectFFALUploads(ids);
			for(ClientUploadFFALDTO upload : clientUploads){
				attachIds += upload.getAttachId() + ",";
				DataTransportDTO log = new DataTransportDTO(upload.getId(),upload.getAttachId(),upload.getClientId(),
															upload.getClientName(),employee.getEmplId(),employee.getEmplName(),
															Constant.FFAL,0);
				logList.add(log);
			}
			result = fangFaAnLiDao.batchDeleteFFAL(ids, true) > 0;
			results.add(result);
			System.out.println("batch del ffal result:" + result);
		}else if(Constant.SJFG.equals(type)){
			List<ClientUploadSJFGDTO> clientUploads = clientUploadDao.selectSJFGUploads(ids);
			for(ClientUploadSJFGDTO upload : clientUploads){
				attachIds += upload.getAttachId() + ",";
				DataTransportDTO log = new DataTransportDTO(upload.getId(),upload.getAttachId(),upload.getClientId(),
															upload.getClientName(),employee.getEmplId(),
															employee.getEmplName(),Constant.SJFG,0);
				logList.add(log);
			}
			result = shenJiFaGuiDao.batchDeleteLaw(ids, true) > 0;
			results.add(result);
			System.out.println("batch del sjfg result:" + result);
		}else if(Constant.SJSS.equals(type)){
			List<ClientUploadSSFNDTO> clientUploads = clientUploadDao.selectSSFNUploads(ids);
			for(ClientUploadSSFNDTO upload : clientUploads){
				attachIds += upload.getAttachId() + ",";
				DataTransportDTO log = new DataTransportDTO(upload.getId(),upload.getAttachId(),upload.getClientId(),
															upload.getClientName(),employee.getEmplId(),
															employee.getEmplName(),Constant.SJSS,0);
				logList.add(log);
			}
			result = shiShiFangAnDao.batchDeleteSSFA(ids, true) > 0;
			results.add(result);
			System.out.println("batch del sjss result:" + result);
		}
		
		System.out.println("~~~~~~~~~~attachIds:" + attachIds);
		
		//把迁移到附件表中数据删除
		result = attachSJZSDao.batchDeleteAttach(attachIds, true) > 0;
		results.add(result);
		System.out.println("batch del attach:" + result);
		
		//更新用户上传数据状态为已迁移
		result = clientUploadDao.updateTransportFlag(ids, Constant.TRANSPORT_NO, type)>0;
		results.add(result);
		System.out.println("update:"+result);
		
		//添加数据迁移日志
		result = transportDataLogDao.batchAddLog(logList)>0;
		results.add(result);
		System.out.println("insert log:"+result);
		System.out.println("result:" + !results.contains(false));
		return !results.contains(false);
	}

	public FangFaAnLiDao getFangFaAnLiDao() {
		return fangFaAnLiDao;
	}

	public void setFangFaAnLiDao(FangFaAnLiDao fangFaAnLiDao) {
		this.fangFaAnLiDao = fangFaAnLiDao;
	}

	public ShenJiFaGuiDao getShenJiFaGuiDao() {
		return shenJiFaGuiDao;
	}

	public void setShenJiFaGuiDao(ShenJiFaGuiDao shenJiFaGuiDao) {
		this.shenJiFaGuiDao = shenJiFaGuiDao;
	}

	public ShiShiFangAnDao getShiShiFangAnDao() {
		return shiShiFangAnDao;
	}

	public void setShiShiFangAnDao(ShiShiFangAnDao shiShiFangAnDao) {
		this.shiShiFangAnDao = shiShiFangAnDao;
	}

	public ClientUploadAttachDao getClientUploadAttachDao() {
		return clientUploadAttachDao;
	}

	public void setClientUploadAttachDao(ClientUploadAttachDao clientUploadAttachDao) {
		this.clientUploadAttachDao = clientUploadAttachDao;
	}

	public AttachSJZSDao getAttachSJZSDao() {
		return attachSJZSDao;
	}

	public void setAttachSJZSDao(AttachSJZSDao attachSJZSDao) {
		this.attachSJZSDao = attachSJZSDao;
	}

	public TransportDataLogDao getTransportDataLogDao() {
		return transportDataLogDao;
	}

	public void setTransportDataLogDao(TransportDataLogDao transportDataLogDao) {
		this.transportDataLogDao = transportDataLogDao;
	}
	
}
