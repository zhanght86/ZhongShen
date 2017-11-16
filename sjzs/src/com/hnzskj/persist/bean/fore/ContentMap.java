package com.hnzskj.persist.bean.fore;

import java.util.List;

/**
 *类名称:ContentMap.java <br/>
 *类描述：<br/>
 *创建人： 平西强<br/>
 *创建时间:2013-3-22上午09:45:46<br/>
 *修改人: <br/>
 *修改时间:2013-3-22上午09:45:46<br/>
 *修改备注: <br/>
 * 
 * @version 1.0
 *@param <E>
 */
public class ContentMap {

	private String type;
	private List<Content> contentList;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Content> getContentList() {
		return contentList;
	}

	public void setContentList(List<Content> contentList) {
		this.contentList = contentList;
	}

	@Override
	public String toString() {
		return "ContentMap [contentList=" + contentList + ", type=" + type
				+ "]";
	}
	
}
