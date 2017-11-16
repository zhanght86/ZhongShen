
package com.hnzskj.persist.dao.sjzs;

import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.sjzs.SjzhMenuTree;

public interface ShenJiZhuShouDao {
	public int deleteMenus(String menuId);

	public SjzhMenuTree findMenuById(String menuId);

	public int addMenu(SjzhMenuTree sjzhMenuTree);

	public Page<SjzhMenuTree> searchMenu(String fields, LinkedHashMap<String, String> orderby,String condition);

	public int updateMenu(SjzhMenuTree sjzhMenuTree);

	public String findMenuParentCode(String jmcode);

	public int getItemsCount(String sql);
	public  List<SjzhMenuTree> findChildByParentAsync(String parentId);
	public  List<SjzhMenuTree> findChildByParentAsync2(String parentId);
	public  int findChildById(String parentId);
	public int addMenuExpan(SjzhMenuTree sjzhMenuTree);
	public int deleteMenusExpan(int type, String menuId, String name);
	public  int moveNotesBatch(String[] sqls, Object[][] params);
	
	/**
	 * 
	 * 方法描述: 添加一个菜单并返回菜单id<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-5-7 下午04:44:35
	 * @param
	 *
	 */
	public String addMenuAndReturnId(SjzhMenuTree sjzhMenuTree);
}