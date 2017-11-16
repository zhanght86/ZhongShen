/*@目名称：sjzs
 *@文件名：QueryResult.java
 *@期：下午05:03:08
 *@权：2011 河南中审科技有限公司
 *@开发公司或单位:河南中审科技有限公司
 */
package com.hnzskj.persist.bean.fore;

import java.sql.Date;

/**
 *类名称:QueryResult 类描述：<br/>
 *创建人： 平西强<br/>
 *创建时间:2013-3-5下午05:03:08<br/>
 *修改人: Administrator<br/>
 *修改时间:2013-3-5下午05:03:08<br/>
 *修改备注: <br/>
 * 
 * @version 1.0
 *@param <E>
 */
public class QueryResult {
	private String id;
	private String title;
	private String content;
	private String type;
	private String date;
	private String attach;

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

}
