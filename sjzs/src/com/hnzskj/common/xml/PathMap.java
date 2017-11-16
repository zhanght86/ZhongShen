package com.hnzskj.common.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *类名称:PathMap.java <br/>
 *类描述：<br/>
 *创建人： 平西强<br/>
 *创建时间:2013-4-13下午12:04:42<br/>
 *修改人: Administrator <br/>
 *修改时间:2013-4-13下午12:04:42<br/>
 *修改备注: <br/>
 * 
 * @version 1.0
 *@param <E>
 */
public class PathMap extends HashMap<String, List<String>> {

	private static final long serialVersionUID = 1L;

	public PathMap() {
		super();
	}

	public PathMap(String key, List<String> valueList) {
		this.put(key, valueList);
	}

	/**
	 *方法描述：是否存在给定value <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-12 上午10:52:14
	 * 
	 *@return
	 *@version 1.0
	 */
	public boolean isHasValue(String modeId) {
		boolean tag = false;
		if (modeId == null || "".equals(modeId.trim())) {
			return false;
		} else {
			if (this.values().size() > 0) {
				for (List<String> valList : this.values()) {
					if (valList != null) {

						for (String value : valList) {
							if (modeId.equals(value)) {
								tag = true;
							}
						}
					}
				}
			}
			return tag;
		}
	}

	/**
	 *方法描述： 是否存在给定key <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-12 上午10:52:17
	 * 
	 *@return
	 *@version 1.0
	 */
	public boolean isHasKey(String modeId) {
		boolean tag = false;
		if (modeId == null || "".equals(modeId.trim())) {
			return false;
		} else {
			if (this.containsKey(modeId)) {
				tag = true;
			}
		}
		return tag;
	}

	/**
	 *方法描述：通过key获取value <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-12 上午10:52:48
	 * 
	 *@return
	 *@version 1.0
	 */
	public List<String> getValueByKey(String key) {
		return get(key);
	}

	/**
	 *方法描述：通过value获取key <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-12 上午10:53:05
	 * 
	 *@return
	 *@version 1.0
	 */
	public List<String> getKeyByValue(String value) {
		List<String> returnValue = new ArrayList<String>();
		if (this.values().size() > 0) {
			Iterator<Map.Entry<String, List<String>>> iter = this.entrySet()
					.iterator();
			while (iter.hasNext()) {
				Map.Entry<String, List<String>> entry = iter.next();
				List<String> valist = entry.getValue();
				if (valist != null) {
					for (String val : valist) {
						if (val.equals(value)) {
							returnValue.add(entry.getKey());
						}
					}
				}
			}
		}
		return returnValue;
	}

}
