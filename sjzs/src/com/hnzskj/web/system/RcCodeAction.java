/*
 * @项目名称: OA
 * @文件名称: RcCodeAction.java
 * @日期: 2012-8-21
 * @版权: 2012 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.web.system;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.system.RcCode;
import com.hnzskj.service.system.RcCodeService;
import com.hnzskj.web.BaseAction;

  /**        
 * 
 * 类名称：RcCodeAction
 * 类描述：<br/>
 * 创建人：郑辉  <br/>
 * 创建时间：2012-8-21 上午11:38:11 
 * 修改人：Administrator
 * 修改时间：
 * 修改备注：   
 * @version   1.0     
 */

@SuppressWarnings("serial")
public class RcCodeAction extends BaseAction{
	private String closePage;
	private String refreshPage;
	private RcCode rcCode=new RcCode();
	private RcCodeService rcService;
	private Page<RcCode> page =new Page<RcCode>(); 
	private String selectNameId;
	List<RcCode> rcCodeSonList =new ArrayList<RcCode>();
	private String initParam;  //创建的字典默认选中的value 值
	/**
	 * @return the rcCode
	 */
	public RcCode getRcCode() {
		return rcCode;
	}
	/**
	 * @param rcCode the rcCode to set
	 */
	public void setRcCode(RcCode rcCode) {
		this.rcCode = rcCode;
	}
	/**
	 * @return the rcService
	 */
	public RcCodeService getRcService() {
		return rcService;
	}
	/**
	 * @param rcService the rcService to set
	 */
	public void setRcService(RcCodeService rcService) {
		this.rcService = rcService;
	}
	
	/**
	 * @return the page
	 */
	public Page<RcCode> getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(Page<RcCode> page) {
		this.page = page;
	}
	/**
	 * @return the selectNameId
	 */
	public String getSelectNameId() {
		return selectNameId;
	}
	/**
	 * @param selectNameId the selectNameId to set
	 */
	public void setSelectNameId(String selectNameId) {
		this.selectNameId = selectNameId;
	}
	/**
	 * @return the rcCodeSonList
	 */
	public List<RcCode> getRcCodeSonList() {
		return rcCodeSonList;
	}
	/**
	 * @param rcCodeSonList the rcCodeSonList to set
	 */
	public void setRcCodeSonList(List<RcCode> rcCodeSonList) {
		this.rcCodeSonList = rcCodeSonList;
	}
	/**
	 * @return the closePage
	 */
	public String getClosePage() {
		return closePage;
	}
	/**
	 * @param closePage the closePage to set
	 */
	public void setClosePage(String closePage) {
		this.closePage = closePage;
	}
	/**
	 * @return the refreshPage
	 */
	public String getRefreshPage() {
		return refreshPage;
	}
	/**
	 * @param refreshPage the refreshPage to set
	 */
	public void setRefreshPage(String refreshPage) {
		this.refreshPage = refreshPage;
	}
	
	public String findAllParentRcCode(){
		page=rcService.findParents(page, rcCode);
		return LISTPAGE;
	}
	
	/**
	 * @return the initParam
	 */
	public String getInitParam() {
		return initParam;
	}
	/**
	 * @param initParam the initParam to set
	 */
	public void setInitParam(String initParam) {
		this.initParam = initParam;
	}
	/**
	 * 类描述：去添加页
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-8-22 上午08:13:21 
	 * 修改人：
	 * 修改时间：2012-8-22 上午08:13:21  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public String toAddParentReCode(){
		return "parentPage";
	}
	
	/**
	 * 类描述：<br/>
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-8-22 上午08:13:16 
	 * 修改人：
	 * 修改时间：2012-8-22 上午08:13:16  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public String toAddSonReCode(){
		return "sonPage";
	}
	
	/**
	 * 类描述：添加字典目录
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-8-22 上午08:13:06 
	 * 修改人：
	 * 修改时间：2012-8-22 上午08:13:06  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public String doAddParentInfo(){
		this.rcService.addInfo(rcCode);
		return "succ";
	}
	
	/**
	 * 类描述：到字典目录修改页
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-8-22 上午08:12:49 
	 * 修改人：
	 * 修改时间：2012-8-22 上午08:12:49  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public String toUpdateParentInfo(){
		rcCode=rcService.findInfo(rcCode.getRc_Id());
		return "updateParent";
	}
	
	/**
	 * 类描述：编辑字典目录
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-8-22 上午08:12:37 
	 * 修改人：
	 * 修改时间：2012-8-22 上午08:12:37  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public String doUpdateParentInfo(){
		this.rcService.updateInfo(rcCode);
		return "succ";
	}
	
	/**
	 * 类描述：到字典设置页面
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-8-22 上午08:12:27 
	 * 修改人：
	 * 修改时间：2012-8-22 上午08:12:27  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public String toUpdateSonInfo(){
		rcCode=rcService.findInfo(rcCode.getRc_Id());
		rcCodeSonList =rcService.findSonsByParent(rcCode.getRc_no());
		if(rcCodeSonList!=null&&rcCodeSonList.size()>1){
			Collections.sort(rcCodeSonList, new Comparator<RcCode>() {
				public int compare(RcCode s1, RcCode s2) {
					Integer p1 = s1.getRc_order();
					Integer p2 = s2.getRc_order();
					return p1.compareTo(p2);
				}
			});
		}
		if(rcCodeSonList != null && rcCodeSonList.size() !=0){
			rcCode.setCodeTemp(JSONArray.fromObject(rcCodeSonList).toString());
		}
		return "updateSon";
	}
	
	/**
	 * 类描述：字典设置
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-8-22 上午08:12:00 
	 * 修改人：
	 * 修改时间：2012-8-22 上午08:12:00  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public String doUpdateSonInfo(){
		RcCode rc=rcService.findInfo(rcCode.getRc_Id());
		if(rc.getRc_type() == 0){
			rcService.updateSonInfo(rcCode.getCodeTemp());
			rcCodeSonList =rcService.findSonsByParent(rc.getRc_no());
		}else{
			rcCode.setRc_parent(rc.getRc_no());
			rcService.addInfo(rcCode);
			List<Object> objs =rcService.getCodeObjects(rcCode.getRc_querySql(), rcCode.getRc_Object());
			
		}
		if(rcCodeSonList!=null&&rcCodeSonList.size()>1){
			Collections.sort(rcCodeSonList, new Comparator<RcCode>() {
				public int compare(RcCode s1, RcCode s2) {
					Integer p1 = s1.getRc_order();
					Integer p2 = s2.getRc_order();
					return p1.compareTo(p2);
				}
			});
		}
		if(rcCodeSonList != null && rcCodeSonList.size() !=0){
			rcCode.setCodeTemp(JSONArray.fromObject(rcCodeSonList).toString());
		}
		return "updateSon";
	}
	
	/**
	 * 类描述：字典删除
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-8-22 上午08:12:14 
	 * 修改人：
	 * 修改时间：2012-8-22 上午08:12:14  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public String deleteInfo(){
		rcService.deleteInfo(rcCode.getRc_Id());
		page=rcService.findParents(page, rcCode);
		return LISTPAGE; 
	}
	
	public void createSelect() throws IOException{
		String name =this.getRequest().getParameter("name");
		RcCode rc =rcService.findRcCodeByNoInfo(selectNameId);
		StringBuffer sb =new StringBuffer();
		if(rc !=null){
			rcCodeSonList =rcService.findSonsByParent(rc.getRc_no());
			if(rcCodeSonList.size()!=0){
				Collections.sort(rcCodeSonList, new Comparator<RcCode>() {
					public int compare(RcCode s1, RcCode s2) {
						Integer p1 = s1.getRc_order();
						Integer p2 = s2.getRc_order();
						return p1.compareTo(p2);
					}
				});
				sb.append(rc.getRc_desc()+"<select id='"+name+"' name='"+name+"' onchange='getCodeInfo(this.value,this.name)'>");
				sb.append("<option value=''>").append("请选择").append("</option>");
				for(int i =0;i<rcCodeSonList.size();i++){
					RcCode c =rcCodeSonList.get(i);
					if(initParam.equals(c.getRc_key())){
						sb.append("<option value="+c.getRc_key()+" selected = selected>").append(c.getRc_value()).append("</option>");
					}else{
						sb.append("<option value="+c.getRc_key()+">").append(c.getRc_value()).append("</option>");
					}
				}
				sb.append("</select>");
			}
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(sb.toString());
	}
	
	/**
	 * 类描述：验证改变吗是否存在
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-8-25 上午08:45:47 
	 * 修改人：
	 * 修改时间：2012-8-25 上午08:45:47  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public void checkRcCode() throws IOException{
		int result =0;
		RcCode rc =rcService.findRcCodeByNoInfo(selectNameId);
		if(null != rc){
			result = 1;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(result);
	}
}
