/**
 * flow
 *com.hnzskj.dao
 *2012-4-102012上午11:14:31
 *郑辉
 *
 */
package com.hnzskj.persist.dao.wflow;

import com.hnzskj.persist.bean.flow.ShenpiBean;

/**
 *创建人：郑辉
 *描述： 	
 *创建时间：2012-4-10 上午11:14:31
 *修改人：郑辉
 *修改时间：
 */
public interface ShenPiDao{
	ShenpiBean  pi =new ShenpiBean();
	
	public int addShenPi(ShenpiBean shen );
	
	public ShenpiBean getShenPi(int id );
}
