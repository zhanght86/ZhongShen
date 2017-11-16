/*
 * @项目名称: OA
 * @文件名称: HolidayFlowDao.java
 * @日期: 2012-7-12
 * @版权: 2012 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.service.sjzs.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

import com.hnzskj.common.Page;
import com.hnzskj.common.annotation.Description;
import com.hnzskj.persist.bean.sjzs.SjdhMethod;
import com.hnzskj.persist.bean.sjzs.SjzhMenuTree;
import com.hnzskj.persist.dao.flow.FlowRefDao;
import com.hnzskj.persist.dao.sjzs.AttachSJZSDao;
import com.hnzskj.persist.dao.sjzs.ShenJiZhuShouDao;
import com.hnzskj.persist.dao.sjzs.SjdhMethodDao;
import com.hnzskj.persist.dao.sjzs.WorkFlowDao;
import com.hnzskj.persist.util.PropertiesUtil;
import com.hnzskj.service.sjzs.SjdhMethodService;

  /**        
 * 
 * 类名称：HolidayFlowDao
 * 类描述：<br/>
 * 创建人：郑辉  <br/>
 * 创建时间：2012-7-12 下午04:26:06 
 * 修改人：Administrator
 * 修改时间：
 * 修改备注：   
 * @version   1.0     
 */

public class SjdhMethodServiceImpl implements SjdhMethodService{
	private static Properties prop = null;
	
	private SjdhMethodDao sjdhDao ;
	private ShenJiZhuShouDao shenJiZhuShouDao;
	
	private WorkFlowDao workFlowDao;
	private FlowRefDao flowRefDao;
	private AttachSJZSDao attachSJZSDao;
	
	private Integer count = new Integer(0);
	
	
	public WorkFlowDao getWorkFlowDao() {
		return workFlowDao;
	}


	public void setWorkFlowDao(WorkFlowDao workFlowDao) {
		this.workFlowDao = workFlowDao;
	}


	public FlowRefDao getFlowRefDao() {
		return flowRefDao;
	}


	public void setFlowRefDao(FlowRefDao flowRefDao) {
		this.flowRefDao = flowRefDao;
	}

	public AttachSJZSDao getAttachSJZSDao() {
		return attachSJZSDao;
	}


	public void setAttachSJZSDao(AttachSJZSDao attachSJZSDao) {
		this.attachSJZSDao = attachSJZSDao;
	}


	public SjdhMethodDao getSjdhDao() {
		return sjdhDao;
	}

	
	public ShenJiZhuShouDao getShenJiZhuShouDao() {
		return shenJiZhuShouDao;
	}


	public void setShenJiZhuShouDao(ShenJiZhuShouDao shenJiZhuShouDao) {
		this.shenJiZhuShouDao = shenJiZhuShouDao;
	}


	public void setSjdhDao(SjdhMethodDao sjdhDao) {
		this.sjdhDao = sjdhDao;
	}

	@Override
	@Description("添加")
	public int addInfo(SjdhMethod sjdh) {
		// TODO Auto-generated method stub
		return sjdhDao.addInfo(sjdh);
	}

	@Override
	@Description("删除")
	public int delete(String id) {
		//根据审计导航id删除流程图和流程节点信息，删除节点信息时如果有附件也一并删除
		String[] ids = id.split(",");
		String newId = "";
		for(int i=0;i<ids.length;i++){
			if(ids[i]!=""&&ids!=null){
				workFlowDao.delWorkFlowBySjdhId(ids[i]);
				List<String> attachIds = flowRefDao.getAttachIds(ids[i]);
				if(attachIds!=null&&attachIds.size()>0){
					for(String attachId : attachIds){
						if(attachId!=null&&!"".equals(attachId)&&!" ".equals(attachId))
							attachSJZSDao.delAttachs(attachId);
					}
				}
				flowRefDao.delFlowRefBySjdhId(ids[i]);
			}
			newId += "'" + ids[i] + "',";
		}
		return sjdhDao.delete(newId);
	}

	@Override
	@Description("更新")
	public int update(SjdhMethod sjdh) {
		// TODO Auto-generated method stub
		return sjdhDao.update(sjdh);
	}
	@Description("分页查询")
	public Page<SjdhMethod> search(Page<SjdhMethod> page,SjdhMethod sjdh){
		StringBuffer sb = new StringBuffer();
		sb.append(" Where 1=1 ");
		String fields =" * ";
		List<String> params = new ArrayList<String>();
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		
		if(sjdh !=null){
//			if(sjdh.getTypeNo() !=null && !sjdh.getTypeNo().equals("")){
//				sb.append(" and typeNo = ?");
//				params.add(sjdh.getTypeNo());
//			}
			params.add(sjdh.getTypeNo());
			if(sjdh.getName() !=null && !sjdh.getName().equals("")){
				sb.append(" and name like '%" + sjdh.getName() + "%' ");
//				params.add("%"+sjdh.getName()+"%");
			}
		}
		orderBy.put("orderNum", "asc");
		
		return sjdhDao.searchSjdhMethodParentId(page, fields, sb.toString(), params.toArray(), orderBy);
	}
	/**
	 * 描述：修改审计导航中审计方法对应的流程图。
	 * 创建人:郑辉
	 * 创建时间： 2013-2-27 上午10:01:39
	 * 修改时间：
	 * 修改人：
	 */
	@Description("修改审计导航中审计方法对应的流程图")
	public int updateTempNO(String Id,int template_no){
		return sjdhDao.updateTempNO(Id, template_no);
	}
	
	@Description("查询对象")
	public SjdhMethod findById(String Id){
		return sjdhDao.findById(Id);
	}
	
	public String showSJFG() {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("menuType", "desc");
		orderby.put("menuOrder", "asc");
		List<SjzhMenuTree> menuList = this.shenJiZhuShouDao.searchMenu("*", orderby,null).getList();
		
		StringBuffer sb = new StringBuffer();
//		sb.append("[{\"id\":\"0\",\"text\":\"请选择\",\"parentid\":\"0\",\"menuType\":\"1\",\"children\":[");
		sb.append("[");
		SjzhMenuTree sm = null;
		
		//添加审计事项和审计实施方案
//		sm = new SjzhMenuTree("55555555", "审计事项", "0", 5);
//		String str = buildJsonStr(sm, menuList );
//		sb.append(str);
//		sm = new SjzhMenuTree("66666666", "审计实施方案", "0", 6);
//		str = buildJsonStr(sm, menuList );
//		sb.append(str);
//		sm = new SjzhMenuTree("11111111", "审计法规", "0", 1);
//		str = buildJsonStr(sm, menuList );
//		sb.append(str);
//		sm = new SjzhMenuTree("22222222", "定型依据", "0", 2);
//		str = buildJsonStr(sm, menuList );
//		sb.append(str);
//		sm = new SjzhMenuTree("33333333", "方法案例", "0", 3);
//		str = buildJsonStr(sm, menuList );
//		str=str.substring(0, str.lastIndexOf(","));
		if(prop == null){
			prop = new PropertiesUtil("WEB-INF\\config\\moduleNames.properties").prop;
			
		}
		sm = new SjzhMenuTree("55555555", prop.getProperty("5"), "0", 5);
		String str = buildJsonStr(sm, menuList );
		sb.append(str);
		sm = new SjzhMenuTree("66666666", prop.getProperty("6"), "0", 6);
		str = buildJsonStr(sm, menuList );
		sb.append(str);
		sm = new SjzhMenuTree("11111111", prop.getProperty("1"), "0", 1);
		str = buildJsonStr(sm, menuList );
		sb.append(str);
		sm = new SjzhMenuTree("22222222", prop.getProperty("2"), "0", 2);
		str = buildJsonStr(sm, menuList );
		sb.append(str);
		sm = new SjzhMenuTree("33333333", prop.getProperty("3"), "0", 3);
		str = buildJsonStr(sm, menuList );
		str=str.substring(0, str.lastIndexOf(","));
		sb.append(str);
//		sb.append("\n]}]\n");	
		sb.append("\n]\n");
		return sb.toString();
	}
	
	@Override
	public String showSJFGAnysc(String root) {
		List<SjzhMenuTree> menuList=null;
    if (root == null) {
			
		} else if (root.equalsIgnoreCase("source")) {
			//System.out.println("&&&&&&&&&&&&&&"+sm.getMenuId());
			 menuList = this.shenJiZhuShouDao.findChildByParentAsync("00000000");
		} else {
			System.out.println(root);
			 menuList = this.shenJiZhuShouDao.findChildByParentAsync(root);
		}
		
		
		//System.out.println(menuList+"list");
		
		return  ConverListToJson(menuList);
	}
	
	
public  String ConverListToJson(List<SjzhMenuTree> menuList) {

		
		StringBuilder jsonString = new StringBuilder();
		jsonString.append("[");
		
		if (menuList.isEmpty())
			return "";
		int i = 0;
		for (SjzhMenuTree treeNode : menuList) {
			if (i > 0) {
				jsonString.append(",");
			}
			jsonString.append(buildJsonStrAsync(treeNode));
			i++;
		}

		jsonString.append("]");
		return jsonString.toString();
	}
	
private String buildJsonStrAsync( SjzhMenuTree sm) {
	StringBuilder jsonStr = new StringBuilder("");
	//在字符串的开头填充"\t"字符,使输出的字符串中格式存在缩进,方便查看
	//jsonStr.append(getTabNum(count));
	jsonStr.append("{\"id\":").append("\"").append(sm.getMenuId()).append("\",");
	jsonStr.append("\"text\":").append("\"").append(sm.getMenuName()).append("\",");
	jsonStr.append("\"parentid\":").append("\"").append(sm.getMenuParent()).append("\",");
	jsonStr.append("\"menuType\":").append("\"").append(sm.getMenuType()).append("\",");
	
	//从给定的集合中查询出当前部门的子部门
	System.out.println(sm.getMenuName()+"***************"+sm.getMenuId());
	List<SjzhMenuTree>	 templist = this.shenJiZhuShouDao.findChildByParentAsync(sm.getMenuId());
	System.out.println("templist"+templist.size()+"======");
	if (templist.size()>0) {
		
		jsonStr.append("\"hasChildren\":").append("true");
	}
			jsonStr.append("}");		
		System.out.println(jsonStr.toString());
		return jsonStr.toString();
}
	
	
	private String buildJsonStr( SjzhMenuTree sm, List<SjzhMenuTree> menuList) {
		StringBuilder jsonStr = new StringBuilder("");
		//在字符串的开头填充"\t"字符,使输出的字符串中格式存在缩进,方便查看
		jsonStr.append(getTabNum(count));
		jsonStr.append("{\"id\":").append("\"").append(sm.getMenuId()).append("\",");
		jsonStr.append("\"text\":").append("\"").append(sm.getMenuName()).append("\",");
		jsonStr.append("\"parentid\":").append("\"").append(sm.getMenuParent()).append("\",");
		jsonStr.append("\"menuType\":").append("\"").append(sm.getMenuType()).append("\",");
		jsonStr.append("\"children\":").append("[");
		//从给定的集合中查询出当前部门的子部门
		List<SjzhMenuTree> t_list = findChildMenu(sm, menuList);
		if (t_list.isEmpty()) {//如果当前部门没有子部门
			jsonStr.append("]},\n");		
		} else {//如果存在子部门
			jsonStr.append("\t\n");
			//如果存在了部门,在增加一级缩进
			count++;
			for (int i = 0; i < t_list.size(); i++) {
				SjzhMenuTree t_sm = t_list.get(i);
				jsonStr.append(buildJsonStr(t_sm, menuList));
			}
			/*
			 * 因为JSON字符串间的各项之间是用","进行分隔,最后一项后面不再需要",",所以要删除最后一个","
			 */
			if (jsonStr.toString().lastIndexOf(",") == jsonStr.length() - 2) {//删除字符串末尾的最后一个","
				jsonStr.deleteCharAt(jsonStr.length() - 2);
			}
			//当前部门的子部门组织完毕,则减少缩进
			count--;
			jsonStr.append(getTabNum(count));
			jsonStr.append("]},\n");
		}
		return jsonStr.toString();
	}
	
	private List<SjzhMenuTree> findChildMenu(SjzhMenuTree sm, List<SjzhMenuTree> menuList) {
		List<SjzhMenuTree> childMenus = new ArrayList<SjzhMenuTree>();		
		for (int i = 0; i < menuList.size(); i++) {
			SjzhMenuTree tempMenu = menuList.get(i);
			if ( tempMenu.getMenuParent().equals(sm.getMenuId()) ) {//如果当前权限是power的子节点
				childMenus.add(tempMenu);
			}
		}
		return childMenus;
	}
	
	/**
	 * 方法描述：返回count个"\t"字符组成的字符串<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2012-3-20 下午05:47:21<br/>         
	 * @param count
	 * @return
	 * @version   1.0  
	 */
	private String getTabNum(Integer count) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < count; i++) {
			sb.append("\t");
		}
		return sb.toString();
	}


	@Override
	public int updateSJDHType(String id,String workFlowId) {
		int count = 0;
		count = sjdhDao.updateSJDHType(id,workFlowId);
		return count;
	}

}