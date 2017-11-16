package com.hnzskj.persist.bean.fore;

/**
 *类名称:Content <br/>
 *类描述：用户登录以后在表格里显示的每条记录<br/>
 *创建人： 平西强<br/>
 *创建时间:2013-3-22上午09:38:46<br/>
 *修改人: <br/>
 *修改时间:2013-3-22上午09:38:46<br/>
 *修改备注: <br/>
 * 
 * @version 1.0
 *@param <E>
 */
public class Content {
	private String id; // 每个字段在表中的ID
	private String title; // 标题
	private String content; // 简介
	private String Date; // 创建日期
	private Object obj;
	private String hits;// 点击量，以后可能会用到

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public String getHits() {
		return hits;
	}

	public void setHits(String hits) {
		this.hits = hits;
	}

	@Override
	public String toString() {
		return "Content [Date=" + Date + ", content=" + content + ", hits="
				+ hits + ", id=" + id + ", obj=" + obj + ", title=" + title
				+ "]";
	}

}
