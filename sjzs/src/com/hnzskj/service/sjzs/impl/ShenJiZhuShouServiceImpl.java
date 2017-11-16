/*
 * @项目名称: OA
 * @文件名称: HolidayFlowServiceImpl.java
 * @日期: 2012-7-12
 * @版权: 2012 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.service.sjzs.impl;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

import org.jsoup.Jsoup;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.Constant;
import com.hnzskj.common.SystemException;
import com.hnzskj.persist.bean.sjzs.SjzhMenuTree;
import com.hnzskj.persist.dao.sjzs.ShenJiZhuShouDao;
import com.hnzskj.persist.util.PropertiesUtil;
import com.hnzskj.service.sjzs.ShenJiZhuShouService;


/**
 * 
 * 类名称：HolidayFlowServiceImpl 类描述：<br/>
 * 创建人：郑辉 <br/>
 * 创建时间：2012-7-12 下午04:28:11 修改人：Administrator 修改时间： 修改备注：
 * 
 * @version 1.0
 */

public class ShenJiZhuShouServiceImpl implements ShenJiZhuShouService {
	private static Properties prop = null;
	private ShenJiZhuShouDao shenJiZhuShouDao = null;
	//记录缩进数目
	private Integer count = new Integer(0);
	/**
	 * @return the holidayFlowDao
	 */
	public ShenJiZhuShouDao ShenJiZhuShouDao() {
		return shenJiZhuShouDao;
	}

	/**
	 * @param holidayFlowDao
	 *            the holidayFlowDao to set
	 */
	public void setShenJiZhuShouDao(ShenJiZhuShouDao shenJiZhuShouDao) {
		this.shenJiZhuShouDao = shenJiZhuShouDao;
	}

	@Override
	public boolean deleteMenuByCodes(int type,String menuId) {
		
		String countSql = "select count(menuId) from sjzs_menuTree where deleteflag<> -1 and menuParent = '"+menuId+"'";
		Integer records = 0;
		records = records + Integer.parseInt( new BaseDao().getSingleValue(countSql, null).toString());
		records = records + checkMenuIsUsed(type,menuId);
		if ( records > 0 ) {
			throw new SystemException("当前菜单正在被使用不可删除！");
		}
		int result = 0;
		result = shenJiZhuShouDao.deleteMenus(menuId);
		return result == 1 ? true : false;
	}
	/**
	 * 
	 * 描述：判断当前菜单下是否有记录<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-3-28 下午12:07:17 <br/>  
	 * @version   1.0
	 */
	private int checkMenuIsUsed(int type ,String menuId){
		
		String sql = " select count(*) from ";
			if(type == 1){
				sql = sql+" sjzs_sjfg where lawSort = '"+menuId+"' ";
			}
			if(type == 2){
				sql = sql+" sjzs_dxyj where parentID = '"+menuId+"' ";		
			}
			if(type == 3){
				sql = sql+" sjzs_ffal where sort = '"+menuId+"' ";
			}
			if(type == 4){
				sql = sql+" sjzs_sjdh where typeNo = '"+menuId+"' ";
			}
			if(type == 5){
				sql = sql+" sjzs_dataDic where dicId = '"+menuId+"' ";
			}
			if(type == 6){
				sql = sql+" sjzs_ssfn where sort = '"+menuId+"' ";
			}
			
			sql = sql +" and deleteFlag <> -1 ";
			int count = shenJiZhuShouDao.getItemsCount(sql);
		return count;
	}
	
	
	@Override
	public SjzhMenuTree findMenuById(String  menuId) {
		SjzhMenuTree sjzhMenuTree = this.shenJiZhuShouDao.findMenuById(menuId);
		if(prop == null){
			prop = new PropertiesUtil("WEB-INF\\config\\moduleNames.properties").prop;
		}
		if ( null != sjzhMenuTree ) {//如果机构存在则找出其父机构
//			if (sjzhMenuTree.getMenuType() == 1) {
//				sjzhMenuTree.setMenuParentName( "审计法规" );
//			}else  if (sjzhMenuTree.getMenuType() == 2) {
//				sjzhMenuTree.setMenuParentName( "定性依据" );
//			}else  if (sjzhMenuTree.getMenuType() == 3) {
//				sjzhMenuTree.setMenuParentName( "方法案例" );
//			}else if (sjzhMenuTree.getMenuType() == 4) {
//				sjzhMenuTree.setMenuParentName( "审计导航" );
//			}else if (sjzhMenuTree.getMenuType() == 5) {
//				sjzhMenuTree.setMenuParentName( "审计事项" );
//			}else if (sjzhMenuTree.getMenuType() == 6) {
//				sjzhMenuTree.setMenuParentName( "审计实施方案" );
//			}else{
//				SjzhMenuTree parentMenu = shenJiZhuShouDao.findMenuById(sjzhMenuTree.getMenuParent());
//				if ( null != parentMenu ) {//如果父机构存在
//					sjzhMenuTree.setMenuParentName(parentMenu.getMenuName());
//				}
//			}
			if(sjzhMenuTree.getMenuType()!=null){
				sjzhMenuTree.setMenuParentName(prop.getProperty(sjzhMenuTree.getMenuType().toString()));
			}else{
				SjzhMenuTree parentMenu = shenJiZhuShouDao.findMenuById(sjzhMenuTree.getMenuParent());
				if ( null != parentMenu ) {//如果父机构存在
					sjzhMenuTree.setMenuParentName(parentMenu.getMenuName());
				}
			}
		}
		return sjzhMenuTree;
	}


	@Override
	public String getMenuJsonStr(String type) {
		SjzhMenuTree sm = null;
		if(prop == null){
			prop = new PropertiesUtil("WEB-INF\\config\\moduleNames.properties").prop;
		}
//		if (type.equals("1")) {
//			sm = new SjzhMenuTree("11111111", "审计法规", "0", 1);
//		} else if (type.equals("2")) {
//			sm = new SjzhMenuTree("22222222", "定型依据", "0", 2);
//		} else if (type.equals("3")) {
//			sm = new SjzhMenuTree("33333333", "方法案例", "0", 3);
//		} else if (type.equals("4")) {
//			sm = new SjzhMenuTree("44444444", "审计导航", "0", 4);
//		} else if (type.equals("5")) {
//			sm = new SjzhMenuTree("55555555", "审计事项", "0", 5);
//		}else if (type.equals("6")) {
//			sm = new SjzhMenuTree("66666666", "审计实施方案", "0", 6);
//		}else {
//			throw new SystemException("未知菜单类型！");
//		}
		if (type.equals("1")) {
			sm = new SjzhMenuTree("11111111", prop.getProperty("1"), "0", 1);
		} else if (type.equals("2")) {
			sm = new SjzhMenuTree("22222222", prop.getProperty("2"), "0", 2);
		} else if (type.equals("3")) {
			sm = new SjzhMenuTree("33333333", prop.getProperty("3"), "0", 3);
		} else if (type.equals("4")) {
			sm = new SjzhMenuTree("44444444", prop.getProperty("4"), "0", 4);
		} else if (type.equals("5")) {
			sm = new SjzhMenuTree("55555555", prop.getProperty("5"), "0", 5);
		}else if (type.equals("6")) {
			sm = new SjzhMenuTree("66666666", prop.getProperty("6"), "0", 6);
		}else {
			throw new SystemException("未知菜单类型！");
		}
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("menuType", "desc");
		orderby.put("menuOrder", "asc");
		List<SjzhMenuTree> menuList = this.shenJiZhuShouDao.searchMenu("*", orderby," where menuType = "+sm.getMenuType() + " ").getList();
		String str = buildJsonStr(sm, menuList );
		str = str.substring(0, str.lastIndexOf(","));
		return str;
	}

@Override
	public String getMenuJsonStrAsync(String type,String request) {
		SjzhMenuTree sm = null;
		if (type.equals("1")) {
			sm = new SjzhMenuTree("11111111", "审计法规", "0", 1);
		} else if (type.equals("2")) {
			sm = new SjzhMenuTree("22222222", "定型依据", "0", 2);
		} else if (type.equals("3")) {
			sm = new SjzhMenuTree("33333333", "方法案例", "0", 3);
		} else if (type.equals("4")) {
			sm = new SjzhMenuTree("44444444", "审计导航", "0", 4);
		} else if (type.equals("5")) {
			sm = new SjzhMenuTree("55555555", "审计事项", "0", 5);
		}else if (type.equals("6")) {
			sm = new SjzhMenuTree("66666666", "审计实施方案", "0", 6);
		}else {
			throw new SystemException("未知菜单类型！");
		}
		
		
		List<SjzhMenuTree> menuList=null;
		System.out.println(request+"这里是request");
		if (request == null) {
			
		} else if (request.equalsIgnoreCase("source")) {
			System.out.println("&&&&&&&&&&&&&&"+sm.getMenuId());
			 menuList = this.shenJiZhuShouDao.findChildByParentAsync2(sm.getMenuId());
		} else {
			System.out.println(request);
			 menuList = this.shenJiZhuShouDao.findChildByParentAsync(request);
		}
		
		
		System.out.println(menuList+"list");
		
		return  ConverListToJson(menuList);
	}

	
	@Override
	public boolean addMenu(SjzhMenuTree sjzhMenuTree) {
		//int result = this.shenJiZhuShouDao.addMenu(sjzhMenuTree);
		int result = this.shenJiZhuShouDao.addMenuExpan(sjzhMenuTree);
		return result>=1?true:false;
	}

	private String buildJsonStr( SjzhMenuTree sm, List<SjzhMenuTree> menuList) {
		StringBuilder jsonStr = new StringBuilder("");
		//在字符串的开头填充"\t"字符,使输出的字符串中格式存在缩进,方便查看
		jsonStr.append(getTabNum(count));
		jsonStr.append("{\"id\":").append("\"").append(sm.getMenuId()).append("\",");
		jsonStr.append("\"text\":").append("\"").append(sm.getMenuName()).append("\",");
		jsonStr.append("\"parentid\":").append("\"").append(sm.getMenuParent()).append("\",");
		jsonStr.append("\"menuType\":").append("\"").append(sm.getMenuType()).append("\",");
		jsonStr.append("\"leaf\":").append("\"").append(sm.getNote2()).append("\",");
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
//		System.out.println(jsonStr.toString());
		return jsonStr.toString();
	}
	
	
	/**
	 * 
	 * @param sm 
	 * @param menuList
	 * @return
	 * 
	 */
	
	
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
	    int size = this.shenJiZhuShouDao.findChildById(sm.getMenuId());
		if (size>0) {
			
			jsonStr.append("\"hasChildren\":").append("true");
		}
				jsonStr.append("}");		
			
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
	public String findMenuParentCode(String jmcode) {
		String parentCode = shenJiZhuShouDao.findMenuParentCode(jmcode);
		return parentCode;
	}

	@Override
	public boolean updateMenu(SjzhMenuTree sjzhMenuTree) {
			if(sjzhMenuTree.getMenuOrder().equals("")){
				sjzhMenuTree.setMenuOrder(null);
			}
			int result = shenJiZhuShouDao.updateMenu(sjzhMenuTree);
			return result ==1?true:false;
	}



	public String getConByHtml(String htmlCon) {
		String result = Jsoup.parse(htmlCon).text();
		return result;
	}

	@Override
	public ArrayList<SjzhMenuTree> findMenusByCondition(String sql) {
		
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("menuType", "desc");
		orderby.put("menuOrder", "asc");
		List<SjzhMenuTree> menuList = this.shenJiZhuShouDao.searchMenu("*", orderby,sql).getList();
		return (ArrayList<SjzhMenuTree>) menuList;
	}

	@Override
	public boolean deleteMenuByCodes(int type, String menuId, String name) {
		String countSql = "select count(menuId) from sjzs_menuTree where deleteflag<> -1 and menuParent = '"+menuId+"'";
		Integer records = 0;
		records = records + Integer.parseInt( new BaseDao().getSingleValue(countSql, null).toString());
		records = records + checkMenuIsUsed(type,menuId);
		if ( records > 0 ) {
			throw new SystemException("当前菜单正在被使用不可删除！");
		}
		int result = 0;
		result = shenJiZhuShouDao.deleteMenusExpan(type,menuId,name);
		return result >= 1 ? true : false;
	}

	@Override
	public boolean  moveNotesBatch(String type, String attIds, String parentid) {
		// TODO Auto-generated method stub
		int result=0;
		
		System.out.println(type);
		System.out.println(attIds);
		System.out.println(parentid);
		String ids[] = attIds.split(",");
		String arraySql[]=new String[ids.length];
		Object[][] params =new Object[ids.length][];
		String sql="";

		switch (Integer.parseInt(type)) {
		case 1:
      //审计法规
			for (int i = 0; i < ids.length; i++) {
				sql="update sjzs_clientupload_sjfg set parentId=? where id = ?";
				arraySql[i]=sql;
				params[i]=new Object[]{parentid,ids[i]};

				}
			break;
		case 2:
//定性依据
			for (int i = 0; i < ids.length; i++) {
				sql="update  sjzs_clientupload_dxyj set parentId=? where id = ?";
				arraySql[i]=sql;
				params[i]=new Object[]{parentid,ids[i]};

				}
			break;
		case 3:
//方法案例

			for (int i = 0; i < ids.length; i++) {
				sql="update sjzs_clientupload_ffal set parentId=? where id = ?";
				arraySql[i]=sql;
				params[i]=new Object[]{parentid,ids[i]};
				}
			
			break;
		case 6:
//实施方案

			for (int i = 0; i < ids.length; i++) {
				sql="update sjzs_clientupload_ssfn set parentId=? where id = ?";
				arraySql[i]=sql;
				params[i]=new Object[]{parentid,ids[i]};

				}
			break;

		default:
			break;
		}
		result= this.shenJiZhuShouDao.moveNotesBatch(arraySql, params);
		System.out.println(result);
	    return	result>=1 ? true:false;
	}

	@Override
	public String addMenuAndReturnId(SjzhMenuTree sjzhMenuTree) {
		return this.shenJiZhuShouDao.addMenuAndReturnId(sjzhMenuTree);
	}




}

