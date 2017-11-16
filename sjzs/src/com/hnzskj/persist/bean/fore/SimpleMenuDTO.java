package com.hnzskj.persist.bean.fore;

public class SimpleMenuDTO {
	private String menuId;
	private String menuName;

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Override
	public String toString() {
		return "SimpleMenuDTO [menuId=" + menuId + ", menuName=" + menuName
				+ "]";
	}

}
