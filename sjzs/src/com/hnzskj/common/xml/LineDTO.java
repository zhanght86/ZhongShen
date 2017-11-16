package com.hnzskj.common.xml;

import java.util.List;

/**
 * 
 * @author 申雅各 流程图中xml的Mode实体
 * @author 申雅各
 * 流程图中xml的Line实体
 */
public class LineDTO {
	private final int L = 30;

	private String id;
	private String style;
	private String attr_prop_attri2;
	private String attr_prop_attri3;
	private String d;
	private String attr_prop_attri4;
	private String attr_prop_attri1;
	private String xBaseMode;
	private String wBaseMode;
	private String marker_end;
	private String brokenType;
	private String contentEditable;
	private String strokecolor;
	private double strokeweight;
	private int wIndex;
	private int xIndex;

	public LineDTO() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getAttr_prop_attri2() {
		return attr_prop_attri2;
	}

	public void setAttr_prop_attri2(String attrPropAttri2) {
		attr_prop_attri2 = attrPropAttri2;
	}

	public String getAttr_prop_attri3() {
		return attr_prop_attri3;
	}

	public void setAttr_prop_attri3(String attrPropAttri3) {
		attr_prop_attri3 = attrPropAttri3;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public String getAttr_prop_attri4() {
		return attr_prop_attri4;
	}

	public void setAttr_prop_attri4(String attrPropAttri4) {
		attr_prop_attri4 = attrPropAttri4;
	}

	public String getAttr_prop_attri1() {
		return attr_prop_attri1;
	}

	public void setAttr_prop_attri1(String attrPropAttri1) {
		attr_prop_attri1 = attrPropAttri1;
	}

	public String getxBaseMode() {
		return xBaseMode;
	}

	public void setxBaseMode(String xBaseMode) {
		this.xBaseMode = xBaseMode;
	}

	public String getwBaseMode() {
		return wBaseMode;
	}

	public void setwBaseMode(String wBaseMode) {
		this.wBaseMode = wBaseMode;
	}

	public String getMarker_end() {
		return marker_end;
	}

	public void setMarker_end(String markerEnd) {
		marker_end = markerEnd;
	}

	public String getBrokenType() {
		return brokenType;
	}

	public void setBrokenType(String brokenType) {
		this.brokenType = brokenType;
	}

	public String getContentEditable() {
		return contentEditable;
	}

	public void setContentEditable(String contentEditable) {
		this.contentEditable = contentEditable;
	}

	public String getStrokecolor() {
		return strokecolor;
	}

	public void setStrokecolor(String strokecolor) {
		this.strokecolor = strokecolor;
	}

	public double getStrokeweight() {
		return strokeweight;
	}

	public void setStrokeweight(double strokeweight) {
		this.strokeweight = strokeweight;
	}

	public int getwIndex() {
		return wIndex;
	}

	public void setwIndex(int wIndex) {
		this.wIndex = wIndex;
	}

	public int getxIndex() {
		return xIndex;
	}

	public void setxIndex(int xIndex) {
		this.xIndex = xIndex;
	}

	/**
	 *方法描述：获取线的起始点和结束点的坐标--开始（左，右）结束（左，右） <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-10 下午05:08:24
	 * 
	 *@return
	 *@version 1.0
	 */
	public int[][] getPointerS() {
		String str = this.d.trim();
		str = str.replaceAll("M", "");
		str = str.replaceAll("m", "");
		str = str.replaceAll("L", ",");
		str = str.replaceAll("z", "");
		String[] strs = str.trim().split(",");
		int i = strs.length;
		String m = strs[0].trim();
		String l = strs[i - 1].trim();
		int m1 = Integer.parseInt(m.split(" ")[0].trim());
		int m2 = Integer.parseInt(m.split(" ")[1].trim());
		int l1 = Integer.parseInt(l.split(" ")[0].trim());
		int l2 = Integer.parseInt(l.split(" ")[1].trim());
		return new int[][] { { m1, m2 }, { l1, l2 } };
	}

	/**
	 *方法描述：判断该图形是否是该线的开始部分 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-10 下午05:38:09
	 * 
	 *@return
	 *@version 1.0
	 */
	public boolean isBegin(ModeDTO mode) {
		boolean tag = false;
		if (this.getxBaseMode() != null && !"".equals(this.getxBaseMode())) {
			String str = this.getxBaseMode().trim();
			str = str.substring(6, str.length());
			if (str != null && !"".equals(str)) {
				int beginId = Integer.parseInt(str);
				if (beginId == mode.getId()) {
					tag = true;
				} else {
					tag = false;
				}
			}
			return tag;
		} else {
			/**
			 * 如果线跟节点没有连接用距离判断
			 * */
			int[][] modePointers = mode.getCentPointers();
			for (int[] point : modePointers) {
				int i = Math.abs(point[0] - this.getPointerS()[0][0]);
				int j = Math.abs(point[1] - this.getPointerS()[0][1]);
				if (i < L && j < L) {
					tag = true;
					break;
				}
			}
			return tag;
		}
	}

	/**
	 *方法描述：判断该图形是否是该线的结束部分 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-10 下午05:38:12
	 * 
	 *@return
	 *@version 1.0
	 */
	public boolean isEnd(ModeDTO mode) {
		boolean tag = false;
		if (this.getwBaseMode() != null && !"".equals(this.getwBaseMode())) {
			String str = this.getwBaseMode().trim();
			str = str.substring(6, str.length());
			if (str != null && !"".equals(str)) {
				int endId = Integer.parseInt(str);
				if (endId == mode.getId()) {
					tag = true;
				} else {
					tag = false;
				}
			}
			return tag;
		} else {
			int[][] modePointers = mode.getCentPointers();
			for (int[] point : modePointers) {
				int i = Math.abs(point[0] - this.getPointerS()[1][0]);
				int j = Math.abs(point[1] - this.getPointerS()[1][1]);
				if (i < L && j < L) {
					tag = true;
					break;
				}
			}
			return tag;
		}
	}

	/**
	 *方法描述：返回开始节点 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-10 下午05:44:18
	 * 
	 *@return
	 *@version 1.0
	 */
	public ModeDTO beginMode(List<ModeDTO> modeList) {
		ModeDTO beginMode = null;
		for (ModeDTO mode : modeList) {
			if (isBegin(mode)) {
				beginMode = mode;
			}
		}
		return beginMode;
	}

	/**
	 *方法描述：返回结束节点 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-10 下午05:44:21
	 * 
	 *@return
	 *@version 1.0
	 */
	public ModeDTO endMode(List<ModeDTO> modeList) {
		ModeDTO beginMode = null;
		for (ModeDTO mode : modeList) {
			if (isEnd(mode)) {
				beginMode = mode;
			}
		}
		return beginMode;
	}

	@Override
	public String toString() {
		return "LineDTO [attr_prop_attri1=" + attr_prop_attri1
				+ ", attr_prop_attri2=" + attr_prop_attri2
				+ ", attr_prop_attri3=" + attr_prop_attri3
				+ ", attr_prop_attri4=" + attr_prop_attri4 + ", brokenType="
				+ brokenType + ", contentEditable=" + contentEditable + ", d="
				+ d + ", id=" + id + ", marker_end=" + marker_end
				+ ", strokecolor=" + strokecolor + ", strokeweight="
				+ strokeweight + ", style=" + style + ", wBaseMode="
				+ wBaseMode + ", wIndex=" + wIndex + ", xBaseMode=" + xBaseMode
				+ ", xIndex=" + xIndex + "]";
	}

}
