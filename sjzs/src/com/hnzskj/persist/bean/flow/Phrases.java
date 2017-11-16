/**
 * OA
 *com.hnzskj.persist.bean.flow
 *2012-6-122012上午10:03:13
 *郑辉
 *
 */
package com.hnzskj.persist.bean.flow;

/**
 *创建人：郑辉
 *描述：工作流常用语
 *创建时间：2012-6-12 上午10:03:13
 *修改人：郑辉
 *修改时间：
 */
public class Phrases {
	private int id;
	private String phrases_info;
	private String emplId;
	private String temp;
	public Phrases() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhrases_info() {
		return phrases_info;
	}
	public void setPhrases_info(String phrasesInfo) {
		phrases_info = phrasesInfo;
	}
	public String getEmplId() {
		return emplId;
	}
	public void setEmplId(String emplId) {
		this.emplId = emplId;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	
}
