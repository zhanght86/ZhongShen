/*
 * @项目名称: OA
 * @文件名称: ShenJiZhuShouService.java
 * @日期: 2012-7-12
 * @版权: 2012 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.service.sjzs;

import java.io.Serializable;
import java.util.ArrayList;

import com.hnzskj.persist.bean.sjzs.SjzhMenuTree;


public interface ShenJiZhuShouService {
	
	public boolean deleteMenuByCodes(int type,String menuId);
	public String findMenuParentCode(String jmcode);
	public SjzhMenuTree findMenuById(String menuId);
	public String getMenuJsonStr(String type);
	public String getMenuJsonStrAsync(String type,String  request);
	public boolean addMenu(SjzhMenuTree sjzhMenuTree);
	public boolean updateMenu(SjzhMenuTree sjzhMenuTree);
	public String getConByHtml(String htmlCon);
	public ArrayList<SjzhMenuTree> findMenusByCondition(String sql);
	public boolean deleteMenuByCodes(int type,String menuId,String name);
	public  boolean moveNotesBatch(String type, String  ids,String parentId);
	
	public String addMenuAndReturnId(SjzhMenuTree sjzhMenuTree);
}
