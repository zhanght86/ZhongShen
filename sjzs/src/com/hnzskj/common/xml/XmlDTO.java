package com.hnzskj.common.xml;

import java.util.ArrayList;
import java.util.List;

public class XmlDTO {
	private String xmlId = null;
	private List<ModeDTO> modeList = new ArrayList<ModeDTO>();
	private List<LineDTO> lineList = new ArrayList<LineDTO>();
	private PathMap pathList = new PathMap();
	private List<List<String[]>> wholeList = new ArrayList<List<String[]>>();
	private int maxH;
	private int maxP;

	public int getMaxH() {
		return maxH;
	}

	public void setMaxH(int maxH) {
		this.maxH = maxH;
	}

	public List<List<String[]>> getWholeList() {
		return this.wholeList;
	}

	public int getMaxP() {
		return maxP;
	}

	public void setMaxP(int maxP) {
		this.maxP = maxP;
	}

	public XmlDTO(String xmlId, List<ModeDTO> modeList, List<LineDTO> lineList) {
		this.xmlId = xmlId;
		this.modeList = modeList;
		this.lineList = lineList;
		this.setPathMap();
		setModeDept();
		this.setWholeList();
		System.out.println("modeList-----" + this.modeList);
	}

	public String pringWholeList() {
		StringBuffer str = new StringBuffer();
		str.append("{");
		for (List<String[]> a : this.wholeList) {
			str.append("[");
			for (String[] b : a) {
				str.append("<");
				for (String s : b) {
					str.append(s + "--");
				}
				str.append(">");
			}
			str.append("]");
		}
		str.append("}");
		return str.toString();
	}

	public String getXmlId() {
		return xmlId;
	}

	public void setXmlId(String xmlId) {
		this.xmlId = xmlId;
	}

	public List<ModeDTO> getModeList() {
		return modeList;
	}

	public void setModeList(List<ModeDTO> modeList) {
		this.modeList = modeList;
	}

	public List<LineDTO> getLineList() {
		return lineList;
	}

	public void setLineList(List<LineDTO> lineList) {
		this.lineList = lineList;
	}

	public PathMap getPathMap() {
		return pathList;
	}

	/**
	 *方法描述：设置各个节点之间的关系 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-16 上午08:32:22
	 * 
	 *@return
	 *@version 1.0
	 */
	public void setPathMap() {
		for (ModeDTO mode : this.modeList) {
			List<LineDTO> lines = mode.endLines(this.lineList);
			List<String> child = new ArrayList<String>();
			if (lines.size() != 0) {
				for (LineDTO line : lines) {
					ModeDTO follow = line.endMode(modeList);
					if (follow != null) {
						String modID = follow.getId() + "";
						child.add(modID);
					}
				}
				mode.setChilds(child.size());
			} else {
				child = null;
			}
			this.pathList.put(mode.getId() + "", child);

		}
	}

	/**
	 *方法描述：根据节点获得该节点 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-16 上午08:31:54
	 * 
	 *@return
	 *@version 1.0
	 */
	public ModeDTO getModeById(String modeId) {
		ModeDTO mod = new ModeDTO();
		for (ModeDTO mode : modeList) {
			if (modeId.equals(mode.getId() + "")) {
				mod = mode;
			}
		}
		return mod;
	}

	/**
	 *方法描述：返回尾节点 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-10 下午06:05:44
	 * 
	 *@return
	 *@version 1.0
	 */
	public List<ModeDTO> endModeDTO() {
		List<ModeDTO> modes = new ArrayList<ModeDTO>();
		for (ModeDTO mode : modeList) {
			boolean tag = true;
			if (pathList.isHasKey(mode.getId() + "")) {
				tag = false;
			}
			if (tag)
				modes.add(mode);
		}
		return modes;
	}

	/**
	 *方法描述：返回头节点,根据是否查看返回头结点 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-10 下午06:05:44
	 * 
	 *@return
	 *@version 1.0
	 */
	public ModeDTO beginModeDTO(boolean show) {
		ModeDTO beginMode = null;
		for (ModeDTO mode : modeList) {
			boolean tag = false;
			if (mode.isShow() == show
					&& !pathList.isHasValue(mode.getId() + "")) {
				tag = true;
			}
			if (tag)
				beginMode = mode;
		}
		return beginMode;
	}

	/**
	 *方法描述：根据给定的节点ID获取下面的节点信息 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-10 下午04:50:43
	 * 
	 *@return
	 *@version 1.0
	 */
	public List<ModeDTO> next(String modeId) {
		List<ModeDTO> modes = new ArrayList<ModeDTO>();
		if (pathList.isHasKey(modeId)) {
			List<String> valist = pathList.getValueByKey(modeId);
			if (valist != null) {
				for (String mId : valist) {
					for (ModeDTO mode : modeList) {
						if (mId.equals(mode.getId() + "")) {
							modes.add(mode);
						}
					}
				}
			}

		}
		return modes;
	}

	/**
	 * 获得给定节点Id的上一个节点
	 * */
	public ModeDTO up(String modeId) {
		ModeDTO upMode = null;
		if (pathList.isHasValue(modeId)) {
			List<String> mIds = pathList.getKeyByValue(modeId);
			for (String mId : mIds) {
				for (ModeDTO mode : modeList) {
					if (mode.isShow() && mId.equals(mode.getId() + "")) {
						upMode = mode;
						break;
					}
				}
			}

		}
		return upMode;
	}

	public void setModeDept() {
		for (String key : this.pathList.keySet()) {
			this.getModeById(key).setDepth(this.setDeptById(key));
			this.getModeById(key).setChilds(setChildsById(key));
			this.getModeById(key).setParents(setParentsById(key));
		}
	}

	/**
	 *方法描述：设置指点节点Id的深度，并返回 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-16 下午04:26:31
	 * 
	 *@return
	 *@version 1.0
	 */
	public int setDeptById(String parentId) {
		ModeDTO mode = this.getModeById(parentId);
		List<String> values = this.pathList.getKeyByValue(parentId);
		if (values.size() > 0) {
			for(int i=0;i<values.size();i++){
				mode.setDepth(Math.max(mode.getDepth(), setDeptById(values
						.remove(i)) + 1));
				this.setMaxH(Math.max(this.getMaxH(), mode.getDepth()));
			}
			return mode.getDepth();
		} else {
			return this.getModeById(parentId).getDepth();
		}
	}

	/**
	 *方法描述： <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-17 上午09:08:33
	 * 
	 *@return
	 *@version 1.0
	 */ 
	public int setChildsById(String parentId) {
		ModeDTO mode = this.getModeById(parentId);
		List<String> values = this.pathList.getValueByKey(parentId);
		if (values != null) {
			if (values.size() == 0) {
				mode.setChilds(0);
				return 0;
			} else if (values.size() == 1) {
				if (this.pathList.getValueByKey(values.get(0)) != null) {
					int num = 0;
					for (String s : values) {
						num += (setChildsById(s));
					}
					mode.setChilds(num);
					return num;
				} else {
					return 1;
				}
			} else {
				int num = 0;
				for (String s : values) {
					num += (setChildsById(s));
				}
				mode.setChilds(num);
				return num;
			}
		} else {
			return 0;
		}
	}
	
	/**
	 *方法描述： <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-17 上午09:08:38
	 * 
	 *@return
	 *@version 1.0
	 */ 
	public int setParentsById(String parentId) {
		ModeDTO mode = this.getModeById(parentId);
		List<String> keys = this.pathList.getKeyByValue(parentId);
		if (keys != null) {
			if (keys.size() == 0) {
				mode.setParents(0);
				return 0;
			} else if (keys.size() == 1) {
				if (this.pathList.getKeyByValue(keys.get(0)).size()>0) {
					int num = 0;
					for (String s : keys) {
						num += (setParentsById(s));
					}
					mode.setParents(num);
					return num;
				} else {
					return 1;
				}
			} else {
				int num = 0;
				for (String s : keys) {
					num += (setParentsById(s));
				}
				mode.setParents(num);
				return num;
			}
		} else {
			return 0;
		}
	}

	/**
	 *方法描述：根据节点之间的关系对节点进行排序，都给定一个节点深度，头结点深度为1 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-16 上午08:34:01
	 * 
	 *@return
	 *@version 1.0
	 */
	public void setWholeList() {
		for (int i = 0; i <= this.maxH; i++) {
			int max = 0;
			List<String[]> PList = new ArrayList<String[]>();
			for (ModeDTO mode : modeList) {
				if (mode.getDepth() == i) {
					max++;
					PList.add(new String[] {mode.getChilds()+"",mode.getParents()+"", mode.getId() + "",
									mode.getTitle() });
				}
			}
			if (this.maxP < max) {
				this.maxP = max;
			}
			this.wholeList.add(PList);
		}
	}
	

	@Override
	public String toString() {
		return "XmlDTO [lineList=" + lineList + ", modeList=" + modeList
				+ ", pathList=" + pathList + "]";
	}

}
