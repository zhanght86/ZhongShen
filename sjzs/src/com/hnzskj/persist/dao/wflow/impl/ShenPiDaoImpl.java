/**
 * flow
 *com.hnzskj.dao
 *2012-4-102012上午11:14:31
 *郑辉
 *
 */
package com.hnzskj.persist.dao.wflow.impl;

import com.hnzskj.common.BaseDao;
import com.hnzskj.persist.bean.flow.ShenpiBean;
import com.hnzskj.persist.dao.wflow.ShenPiDao;

/**
 *创建人：郑辉
 *描述： 	
 *创建时间：2012-4-10 上午11:14:31
 *修改人：郑辉
 *修改时间：
 */
public class ShenPiDaoImpl extends BaseDao implements ShenPiDao{
	ShenpiBean  pi =new ShenpiBean();
	
	public int addShenPi(ShenpiBean shen ){
		int num =0;
		String sql ="insert into shenpi (sname,ssex,instance_no) values(?,?,?)";
		Object[] parmas={shen.getSname(),shen.getSsex(),shen.getInstance_id()};
		num =this.update(sql, parmas);
		return num;
	}
	
	public ShenpiBean getShenPi(int id ){
		ShenpiBean bean =null;
		String sql ="select sid ,sname,ssex,instance_no from shenpi where instance_no=?";
		Object[] parmas={id};
		bean =(ShenpiBean)this.get(sql,ShenpiBean.class, parmas);
		return bean;
	}
}
