/**
 * flow
 *com.hnzskj.service
 *2012-4-102012上午11:22:29
 *郑辉
 *
 */
package com.hnzskj.service.wflow.impl;

import com.hnzskj.persist.bean.flow.ShenpiBean;
import com.hnzskj.persist.dao.wflow.ShenPiDao;
import com.hnzskj.service.wflow.ShenPiService;

/**
 *创建人：郑辉
 *描述： 	
 *创建时间：2012-4-10 上午11:22:29
 *修改人：郑辉
 *修改时间：
 */
public class ShenPiServiceImpl implements ShenPiService{
	ShenPiDao shenDao ;
	
	public ShenPiDao getShenDao() {
		return shenDao;
	}

	public void setShenDao(ShenPiDao shenDao) {
		this.shenDao = shenDao;
	}

	public boolean  addInsertInfo(ShenpiBean shen){
		boolean falg =false;
		int num =shenDao.addShenPi(shen);
		if(num != 0){
			falg =true;
		}
		return falg;
	}
	
	public ShenpiBean  getBeanByInstanceID(int id ){
		ShenpiBean bean =shenDao.getShenPi(id);
		return bean;
	}
}
