/**
 * OA
 *com.hnzskj.service.flow
 *2012-6-122012上午10:05:36
 *郑辉
 *
 */
package com.hnzskj.service.flow;

import java.util.List;

import com.hnzskj.persist.bean.flow.Phrases;

/**
 *创建人：郑辉
 *描述： 	
 *创建时间：2012-6-12 上午10:05:36
 *修改人：郑辉
 *修改时间：
 */
public interface PhrasesService {
	/**
	 *创建人：郑辉
	 *描述：获取对应的常用语 	
	 *创建时间：2012-6-12 上午10:26:01
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： List<Phrases>
	 */
	public List<Phrases> getPhrsesList(String emplId);

	/**
	 *创建人：郑辉
	 *描述： 添加	
	 *创建时间：2012-6-12 上午10:28:45
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： int
	 */
	public int addInfo(Phrases phrases);
	
	/**
	 *创建人：郑辉
	 *描述： 修改	
	 *创建时间：2012-6-12 上午10:28:59
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： int
	 */
	public int updateInfo(Phrases phrases);
	
	/**
	 *创建人：郑辉
	 *描述： 删除	
	 *创建时间：2012-6-12 上午10:29:05
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： int
	 */
	public int deleteInfo(String emplId);
}
