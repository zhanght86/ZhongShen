/**
 * flow
 *com.hnzskj.persist.bean.flow
 *2012-4-62012上午08:53:55
 *郑辉
 *
 */
package com.hnzskj.persist.bean.flow;

/**
 *创建人：郑辉
 *描述： 	
 *创建时间：2012-4-6 上午08:53:55
 *修改人：郑辉
 *修改时间：
 */
public class Line {
	private int line_id;	//线路编号
	private int template_id;//模版编号
	private int tache_id;//当前环节
	private int end_tache_id;//结束环节
	private int line_type;//线路类型
	private String link_path;//线路属性
	private String lineLink_role  ;	//该角色可以使用当前连线
	private String lineLink_props ;	//连线的表属性设置
	public Line() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getLine_id() {
		return line_id;
	}
	public void setLine_id(int lineId) {
		line_id = lineId;
	}
	public int getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(int templateId) {
		template_id = templateId;
	}
	public int getTache_id() {
		return tache_id;
	}
	public void setTache_id(int tacheId) {
		tache_id = tacheId;
	}
	public int getEnd_tache_id() {
		return end_tache_id;
	}
	public void setEnd_tache_id(int endTacheId) {
		end_tache_id = endTacheId;
	}
	public int getLine_type() {
		return line_type;
	}
	public void setLine_type(int lineType) {
		line_type = lineType;
	}
	public String getLink_path() {
		return link_path;
	}
	public void setLink_path(String linkPath) {
		link_path = linkPath;
	}
	/**
	 * @return the lineLink_role
	 */
	public String getLineLink_role() {
		return lineLink_role;
	}
	/**
	 * @param lineLinkRole the lineLink_role to set
	 */
	public void setLineLink_role(String lineLinkRole) {
		lineLink_role = lineLinkRole;
	}
	/**
	 * @return the lineLink_props
	 */
	public String getLineLink_props() {
		return lineLink_props;
	}
	/**
	 * @param lineLinkProps the lineLink_props to set
	 */
	public void setLineLink_props(String lineLinkProps) {
		lineLink_props = lineLinkProps;
	}
}
