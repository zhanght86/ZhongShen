package com.hnzskj.web.systemxml;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;

import com.hnzskj.common.DataUtil;
import com.hnzskj.common.init.SysParamUtil;
import com.hnzskj.persist.bean.init.InitCodeTemp;
import com.hnzskj.service.systemxml.SystemXmlService;
import com.hnzskj.web.BaseAction;

public class SystemXmlAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(SystemXmlAction.class);
	private SystemXmlService systemXmlService; // SystemXmlService服务接口
	private String nodeName; // 父节点名称
	private String code; // code值
	private String newName; // 新名称，用于更新系统配置文件
	private String name;
	private String flag;//判断是管理员的数据字典还是超级管理员的数据字典
	/**
	 * 添加项目编码后的提示信息
	 */
	private String msg;
	
	/**
	 * 添加项目编码后的提示信息
	 * @return msg
	 */
	public String getMsg() {
		return msg;
	}
	
	/**
	 * 添加项目编码后的提示信息
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * 项目编码实体
	 */
	private InitCodeTemp codeTemp = new InitCodeTemp();
	
	/**
	 * 项目编码实体
	 * @return codeTemp
	 */
	public InitCodeTemp getCodeTemp() {
		return codeTemp;
	}
	
	/**
	 * 项目编码实体
	 * @param codeTemp the codeTemp to set
	 */
	public void setCodeTemp(InitCodeTemp codeTemp) {
		this.codeTemp = codeTemp;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public SystemXmlService getSystemXmlService() {
		return systemXmlService;
	}

	public void setSystemXmlService(SystemXmlService systemXmlService) {
		this.systemXmlService = systemXmlService;
	}

	/**
	 * 方法描述：超级管理员获取了系统配置文件中的内容，通过request，传递到页面上。
	 * 
	 * @throws DocumentException
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String listSystemXml() throws DocumentException {
		getRequest().setAttribute("kulb",
				systemXmlService.listSystemXml().get(0));
		getRequest().setAttribute("dxzt",
				systemXmlService.listSystemXml().get(1));
		getRequest().setAttribute("htlb",
				systemXmlService.listSystemXml().get(2));
		getRequest().setAttribute("htwblb",
				systemXmlService.listSystemXml().get(3));
		getRequest().setAttribute("jsfs",
				systemXmlService.listSystemXml().get(4));
		getRequest().setAttribute("lclb",
				systemXmlService.listSystemXml().get(5));
		getRequest().setAttribute("lcsp",
				systemXmlService.listSystemXml().get(6));
		getRequest().setAttribute("lczt",
				systemXmlService.listSystemXml().get(7));
		getRequest().setAttribute("lslzt",
				systemXmlService.listSystemXml().get(8));
		getRequest().setAttribute("mblb",
				systemXmlService.listSystemXml().get(9));
		getRequest().setAttribute("qbzl",
				systemXmlService.listSystemXml().get(10));
		getRequest().setAttribute("xmfjlb",
				systemXmlService.listSystemXml().get(11));
		getRequest().setAttribute("ymzhxx",
				systemXmlService.listSystemXml().get(12));
		getRequest().setAttribute("sjk",
				systemXmlService.listSystemXml().get(13));
		getRequest().setAttribute("htzt",
				systemXmlService.listSystemXml().get(14));
		getRequest().setAttribute("khzt",
				systemXmlService.listSystemXml().get(15));
		getRequest().setAttribute("khjb",
				systemXmlService.listSystemXml().get(16));
		getRequest().setAttribute("khxydj",
				systemXmlService.listSystemXml().get(17));
		getRequest().setAttribute("gyslb",
				systemXmlService.listSystemXml().get(18));
		getRequest().setAttribute("et",
				systemXmlService.listSystemXml().get(19));
		getRequest().setAttribute("base",
				systemXmlService.listSystemXml().get(20));
		getRequest().setAttribute("khxydj",
				systemXmlService.listSystemXml().get(21));
		getRequest().setAttribute("gyslb",
				systemXmlService.listSystemXml().get(22));
		getRequest().setAttribute("gysxydj",
				systemXmlService.listSystemXml().get(23));
		getRequest().setAttribute("codeTemp",
				systemXmlService.listSystemXml().get(24));
		getRequest().setAttribute("codeTempSize",
				((List<InitCodeTemp>)(systemXmlService.listSystemXml().get(24))).size());
		getRequest().setAttribute("kuckfw",
				systemXmlService.listSystemXml().get(25));
		getRequest().setAttribute("gysckfw",
				systemXmlService.listSystemXml().get(26));
		getRequest().setAttribute("xmckfw",
				systemXmlService.listSystemXml().get(27));
		getRequest().setAttribute("contractQuery",
				systemXmlService.listSystemXml().get(28));

		return LISTPAGE;
	}
	/**
	 * 方法描述：系统管理员获取了系统配置文件中的内容，通过request，传递到页面上。
	 * 
	 * @throws DocumentException
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String adminlistSystemXml() throws DocumentException {
		getRequest().setAttribute("kulb",
				systemXmlService.listSystemXml().get(0));
		getRequest().setAttribute("dxzt",
				systemXmlService.listSystemXml().get(1));
		getRequest().setAttribute("htlb",
				systemXmlService.listSystemXml().get(2));
		getRequest().setAttribute("htwblb",
				systemXmlService.listSystemXml().get(3));
		getRequest().setAttribute("jsfs",
				systemXmlService.listSystemXml().get(4));
		getRequest().setAttribute("lclb",
				systemXmlService.listSystemXml().get(5));
		getRequest().setAttribute("lcsp",
				systemXmlService.listSystemXml().get(6));
		getRequest().setAttribute("lczt",
				systemXmlService.listSystemXml().get(7));
		getRequest().setAttribute("lslzt",
				systemXmlService.listSystemXml().get(8));
		getRequest().setAttribute("mblb",
				systemXmlService.listSystemXml().get(9));
		getRequest().setAttribute("qbzl",
				systemXmlService.listSystemXml().get(10));
		getRequest().setAttribute("xmfjlb",
				systemXmlService.listSystemXml().get(11));
		getRequest().setAttribute("ymzhxx",
				systemXmlService.listSystemXml().get(12));
		getRequest().setAttribute("sjk",
				systemXmlService.listSystemXml().get(13));
		getRequest().setAttribute("htzt",
				systemXmlService.listSystemXml().get(14));
		getRequest().setAttribute("khzt",
				systemXmlService.listSystemXml().get(15));
		getRequest().setAttribute("khjb",
				systemXmlService.listSystemXml().get(16));
		getRequest().setAttribute("khxydj",
				systemXmlService.listSystemXml().get(17));
		getRequest().setAttribute("gyslb",
				systemXmlService.listSystemXml().get(18));
		getRequest().setAttribute("et",
				systemXmlService.listSystemXml().get(19));
		getRequest().setAttribute("base",
				systemXmlService.listSystemXml().get(20));
		getRequest().setAttribute("khxydj",
				systemXmlService.listSystemXml().get(21));
		getRequest().setAttribute("gyslb",
				systemXmlService.listSystemXml().get(22));
		getRequest().setAttribute("gysxydj",
				systemXmlService.listSystemXml().get(23));
		getRequest().setAttribute("codeTemp",
				systemXmlService.listSystemXml().get(24));
		getRequest().setAttribute("codeTempSize",
				((List<InitCodeTemp>)(systemXmlService.listSystemXml().get(24))).size());
		getRequest().setAttribute("kuckfw",
				systemXmlService.listSystemXml().get(25));
		getRequest().setAttribute("gysckfw",
				systemXmlService.listSystemXml().get(26));
		getRequest().setAttribute("xmckfw",
				systemXmlService.listSystemXml().get(27));
		getRequest().setAttribute("contractQuery",
				systemXmlService.listSystemXml().get(28));
		return "adminlistpage";
	}
	/**
	 * 添加一个节点
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String add() throws UnsupportedEncodingException {
		name = new String(name.getBytes("iso-8859-1"), "UTF-8"); // 设置编码格式
		if (systemXmlService.add(nodeName, name)) {
			if("1".equals(flag)){
				return ADDSUC;
			}else{
				return "adminDoAddSuc";
			}
		}
		return ERROR;
	}

	/**
	 * 删除一个节点
	 * 
	 * @return
	 * @throws DocumentException
	 */
	public String del() throws DocumentException {
		if (systemXmlService.del(nodeName, code)) {
			if("1".equals(flag)){
			return DELSUC;
			}else{
				return "adminDoDelSuc";
			}
		}
		return ERROR;
	}
	
	public String delByCodeTemp() throws DocumentException {
		try {
			//写的是code实际上传过来的grade。用grade唯一标识出项目编码。
			code=URLDecoder.decode(code,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			log.error(DataUtil.getStackTraceAsString(e));;
		}
		if (systemXmlService.delByCodeTemp(nodeName, code)){
			if("1".equals(flag)){
				return DELSUC;
			}else{
				return "adminDoDelSuc";
			}
		}
		return ERROR;
	}

	/**
	 * 修改一个节点
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String update() throws UnsupportedEncodingException {
		newName = new String(newName.getBytes("iso-8859-1"), "UTF-8"); // 设置编码格式
		if (systemXmlService.update(nodeName, code, newName)){
			if("1".equals(flag)){
		   return UPDATESUC;
			}else{
				return "adminDoUpdateSuc";
			}
		}else{
			return ERROR;
		}
	}
	
	/**
	 * 
	 * 方法描述：修改项目编码节点。<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-3-19 下午07:10:13<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public String updateByCodeTemp() throws UnsupportedEncodingException {
		//code当前grade使用。
		code = URLDecoder.decode(code,"utf-8");
		
		String name=URLDecoder.decode(codeTemp.getName(),"utf-8");
		codeTemp.setName(name);
		
		String newCode=URLDecoder.decode(codeTemp.getCode(),"utf-8");
		codeTemp.setCode(newCode);
		
		if (systemXmlService.updateByCodeTemp(nodeName, code, codeTemp)){
			if("1".equals(flag)){
				return UPDATESUC;
			}else{
				return "adminDoUpdateSuc";
			}
		}else{
			return ERROR;
		}
	}

	@Override
	public HttpServletRequest getRequest() {
		return super.getRequest();
	}
	
	/**
	 * 
	 * 方法描述：添加项目编码<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-3-20 上午10:28:24<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public String addCodeTemp(){
		
		if (systemXmlService.addCodeTemp(nodeName,codeTemp)) {
			msg="success";			
			return INPUT;
		}
		addActionError("添加项目编码失败");
		return ERROR;
	}
	
	/**
	 * 
	 * 方法描述：进入到项目编码添加页面<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-3-20 上午10:52:13<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public String goAddPage(){
		SysParamUtil sysParamUtil = new SysParamUtil();
		List<InitCodeTemp> codeTempList=sysParamUtil.getInitCodeTemp();
		StringBuffer sb = new StringBuffer();
		if(null!=codeTempList&&codeTempList.size()>0){
			for (InitCodeTemp initCodeTemp : codeTempList) {
				sb.append(initCodeTemp.getGrade()).append(",");
			}
			if(sb.length()>1){
				sb.delete(sb.length()-1, sb.length());
			}
		}
		codeTemp.setGrade(sb.toString());
		return ADDPAGE;
	}
	
	
}
