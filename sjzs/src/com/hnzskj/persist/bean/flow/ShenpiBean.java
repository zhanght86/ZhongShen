/**
 * flow
 *com.hnzskj.persist.bean.flow
 *2012-4-92012上午08:47:26
 *郑辉
 *
 */
package com.hnzskj.persist.bean.flow;

/**
 *创建人：郑辉
 *描述： 	
 *创建时间：2012-4-9 上午08:47:26
 *修改人：郑辉
 *修改时间：
 */
public class ShenpiBean {
	private int sid;
	private String sname;
	private String ssex;
	private int instance_id;
	public ShenpiBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSsex() {
		return ssex;
	}
	public void setSsex(String ssex) {
		this.ssex = ssex;
	}
	public int getInstance_id() {
		return instance_id;
	}
	public void setInstance_id(int instanceId) {
		instance_id = instanceId;
	}
}
