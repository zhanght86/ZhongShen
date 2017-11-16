package com.hnzskj.persist.bean.sjzs;

/**
 * 
 * 类名称：Organization <br/>
 * 类描述：<br/>
 * 创建人：wxz <br/>
 * 创建时间：2013-1-26 上午08:59:55 <br/>
 * 
 * @version 1.0
 */
public class SjzhMenuTree {

	private String menuId;// 菜单id

	private String menuName;// 菜单名称

	private String menuParent;// 父级菜单id

	private String menuParentName;// 父级菜单名称

	private String menuOrder;// 菜单排序

	private Integer menuType = Integer.valueOf("0");// 菜单类型

	private String note1;// 备用字段1

	private String note2="1";// 备用字段2

	public SjzhMenuTree() {
	}

	public SjzhMenuTree(String menuId, String mcname) {
		this.menuId = menuId;
		this.menuName = mcname;
	}

	public SjzhMenuTree(String menuId, String menuName, String menuParent,
			Integer mType) {
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuParent = menuParent;
		this.menuType = mType;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}


	public String getNote1() {
		return note1;
	}

	public void setNote1(String note1) {
		this.note1 = note1;
	}

	public String getNote2() {
		return note2;
	}

	public void setNote2(String note2) {
		this.note2 = note2;
	}

	
	
	
	/**
	 * @return the menuName
	 */
	public String getMenuName() {
		return menuName;
	}

	/**
	 * @param menuName the menuName to set
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	/**
	 * @return the menuParent
	 */
	public String getMenuParent() {
		return menuParent;
	}

	/**
	 * @param menuParent the menuParent to set
	 */
	public void setMenuParent(String menuParent) {
		this.menuParent = menuParent;
	}

	/**
	 * @return the menuParentName
	 */
	public String getMenuParentName() {
		return menuParentName;
	}

	/**
	 * @param menuParentName the menuParentName to set
	 */
	public void setMenuParentName(String menuParentName) {
		this.menuParentName = menuParentName;
	}

	/**
	 * @return the menuOrder
	 */
	public String getMenuOrder() {
		return menuOrder;
	}

	/**
	 * @param menuOrder the menuOrder to set
	 */
	public void setMenuOrder(String menuOrder) {
		this.menuOrder = menuOrder;
	}

	/**
	 * @return the menuType
	 */
	public Integer getMenuType() {
		return menuType;
	}

	/**
	 * @param menuType the menuType to set
	 */
	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	public String toString() {
		return "SjzhMenuTree [note1=" + this.note1 + ", note2=" + this.note2
				+ ", menuId=" + this.menuId + ", menuName=" + this.menuName
				+ ", menuOrder=" + this.menuOrder + ", menuParent=" + this.menuParent
				+ ", menuParentName=" + this.menuParentName + ", menuType=" + this.menuType
				+ "]";
	}

}
