package com.hnzskj.common.xml;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author 申雅各 流程图中xml的Mode实体
 */
public class ModeDTO {

	private int id;
	private String modeclass;
	private String modeContent;
	private String contentEditable;
	private String attr_prop_attri3;
	private String attr_prop_attri2;
	private String attr_prop_attri1;
	private String title;
	private String backImgSrc;
	private int top;
	private int left;
	private int zIndex;
	private int width;
	private int height;

	private boolean Show = false;
	private int depth; // 节点深度，默认0
	private int childs = 0; // 孩子节点数量
	private int parents = 0;//父亲节点数量
	
	public int getParents() {
		return parents;
	}

	public void setParents(int parents) {
		this.parents = parents;
	}

	public int getChilds() {
		return childs;
	}

	public void setChilds(int childs) {
		this.childs = childs;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public boolean isShow() {
		return Show;
	}

	public void setShow(boolean show) {
		Show = show;
	}

	public ModeDTO() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModeclass() {
		return modeclass;
	}

	public void setModeclass(String modeclass) {
		this.modeclass = modeclass;
	}

	public String getModeContent() {
		return modeContent;
	}

	public void setModeContent(String modeContent) {
		this.modeContent = modeContent;
	}

	public String getContentEditable() {
		return contentEditable;
	}

	public void setContentEditable(String contentEditable) {
		this.contentEditable = contentEditable;
	}

	public String getAttr_prop_attri3() {
		return attr_prop_attri3;
	}

	public void setAttr_prop_attri3(String attrPropAttri3) {
		attr_prop_attri3 = attrPropAttri3;
	}

	public String getAttr_prop_attri2() {
		return attr_prop_attri2;
	}

	public void setAttr_prop_attri2(String attrPropAttri2) {
		attr_prop_attri2 = attrPropAttri2;
	}

	public String getAttr_prop_attri1() {
		return attr_prop_attri1;
	}

	public void setAttr_prop_attri1(String attrPropAttri1) {
		attr_prop_attri1 = attrPropAttri1;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBackImgSrc() {
		return backImgSrc;
	}

	public void setBackImgSrc(String backImgSrc) {
		this.backImgSrc = backImgSrc;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getzIndex() {
		return zIndex;
	}

	public void setzIndex(int zIndex) {
		this.zIndex = zIndex;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 *方法描述：获得图形上下左右四个面的中点位置 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-10 下午05:10:26
	 * 
	 *@return
	 *@version 1.0
	 */
	public int[][] getCentPointers() {
		int[] topCenter = new int[2];
		int[] leftCenter = new int[2];
		int[] rightCenter = new int[2];
		int[] bottomCenter = new int[2];
		topCenter = new int[] { this.top - 2, this.left + this.width / 2 + 1 };
		leftCenter = new int[] { this.top + this.height / 2 + 1, this.left - 2 };
		rightCenter = new int[] { this.top + this.height / 2 + 1,
				this.left + this.width + 2 };
		bottomCenter = new int[] { this.top + this.height + 2,
				this.left + this.width / 2 + 1 };
		return new int[][] { topCenter, leftCenter, rightCenter, bottomCenter };
	}

	/**
	 *方法描述：判断线是否是节点的开始 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-10 下午05:46:45
	 * 
	 *@return
	 *@version 1.0
	 */
	public boolean isBegin(LineDTO line) {
		return line.isEnd(this);
	}

	/**
	 *方法描述：判断线是否是节点的结束 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-10 下午05:46:54
	 * 
	 *@return
	 *@version 1.0
	 */
	public boolean isEnd(LineDTO line) {
		return line.isBegin(this);
	}

	/**
	 *方法描述： <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-10 下午05:50:43
	 * 
	 *@return
	 *@version 1.0
	 */
	public List<LineDTO> beginLines(List<LineDTO> lineList) {
		List<LineDTO> lines = new ArrayList<LineDTO>();
		for (LineDTO line : lineList) {
			if (isBegin(line)) {
				lines.add(line);
			}
		}
		return lines;
	}

	/**
	 *方法描述： <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-10 下午05:50:54
	 * 
	 *@return
	 *@version 1.0
	 */
	public List<LineDTO> endLines(List<LineDTO> lineList) {
		List<LineDTO> lines = new ArrayList<LineDTO>();
		for (LineDTO line : lineList) {
			if (isEnd(line)) {
				lines.add(line);
			}
		}
		return lines;
	}

	@Override
	public String toString() {
		return "ModeDTO [Show=" + Show + ", attr_prop_attri1="
				+ attr_prop_attri1 + ", attr_prop_attri2=" + attr_prop_attri2
				+ ", attr_prop_attri3=" + attr_prop_attri3 + ", backImgSrc="
				+ backImgSrc + ", childs=" + childs + ", contentEditable="
				+ contentEditable + ", depth=" + depth + ", height=" + height
				+ ", id=" + id + ", left=" + left + ", modeContent="
				+ modeContent + ", modeclass=" + modeclass + ", title=" + title
				+ ", top=" + top + ", width=" + width + ", zIndex=" + zIndex
				+ "]";
	}

}
