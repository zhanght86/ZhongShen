/**
 * flow
 *com.hnzskj.service
 *2012-4-102012上午11:22:29
 *郑辉
 *
 */
package com.hnzskj.service.wflow;

import com.hnzskj.persist.bean.flow.ShenpiBean;

/**
 *创建人：郑辉
 *描述： 	
 *创建时间：2012-4-10 上午11:22:29
 *修改人：郑辉
 *修改时间：
 */
public interface ShenPiService {

	public boolean  addInsertInfo(ShenpiBean shen);
	
	public ShenpiBean  getBeanByInstanceID(int id );
}
