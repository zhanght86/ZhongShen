/*
 * @项目名称: OA
 * @文件名称: SoftWareItemsdAO.java
 * @日期: 2012-7-16
 * @版权: 2012 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.persist.dao.wflow.impl;

import java.util.List;

import com.hnzskj.common.BaseDao;
import com.hnzskj.persist.bean.wflow.SoftWareItems;
import com.hnzskj.persist.dao.wflow.SoftWareItemsDao;

  /**        
 * 
 * 类名称：SoftWareItemsdAO
 * 类描述：<br/>
 * 创建人：郑辉  <br/>
 * 创建时间：2012-7-16 下午02:29:17 
 * 修改人：Administrator
 * 修改时间：
 * 修改备注：   
 * @version   1.0     
 */

public class SoftWareItemsDaoImpl extends BaseDao implements SoftWareItemsDao{

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.wflow.SoftWareItemsDao#addInfo(com.hnzskj.persist.bean.wflow.SoftWareItems)
	 */
	@Override
	public int addInfo(SoftWareItems items) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.wflow.SoftWareItemsDao#addBatchInfo(java.lang.Object[])
	 */
	@Override
	public int addBatchInfo(Object [][] params) {
		// TODO Auto-generated method stub
		String sql ="INSERT INTO dbo.software_items " +
				"(sf_items_name,sf_size,sf_dept,sf_apply_num,sf_real_num,sf_price,sf_total_money,sf_id)" +
				" VALUES (?,?,?,?,?,?,?,?)";
		int num =this.updateBatch(sql, params, params.length);
		
		return num;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.wflow.SoftWareItemsDao#deleteInfo(int)
	 */
	@Override
	public int deleteInfo(String id) {
		// TODO Auto-generated method stub
		String sql ="delete software_items where sf_id=?";
		Object[] params={id};
		int num =this.update(sql, params);
		return num;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.wflow.SoftWareItemsDao#getInfo(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SoftWareItems> getInfo(String id) {
		// TODO Auto-generated method stub
		String sql ="select sf_items_name,sf_size,sf_dept" +
					",sf_apply_num,sf_real_num,sf_price,sf_total_money,sf_id " +
					"from  dbo.software_items " +
					"where sf_id=?";
		Object[] params ={id};
		List<SoftWareItems> list =(List<SoftWareItems>)this.query(sql, SoftWareItems.class, params);
		return list;
	}
}
