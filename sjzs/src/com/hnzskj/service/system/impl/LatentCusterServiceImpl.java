/*
 * @项目名称: OA
 * @文件名称: LatentCusterServiceImpl.java
 * @日期: 2012-8-9 下午05:58:37  
 * @版权: 2012 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.service.system.impl;

import java.util.LinkedHashMap;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.system.LatentCuster;
import com.hnzskj.persist.dao.system.LatentCusterDao;
import com.hnzskj.service.system.LatentCusterService;

/**    
 * 项目名称：OA   <br/>
 * 类名称：LatentCusterServiceImpl.java   <br/>
 * 类描述： 潜在客户字典表业务层实现类  <br/>
 * 创建人：王亲臣   <br/>
 * 创建时间：2012-8-9 下午05:58:37   <br/>
 * 修改人：王亲臣   <br/>
 * 修改时间：2012-8-9 下午05:58:37   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class LatentCusterServiceImpl implements LatentCusterService {

	/**
	 * 数据库访问层接口注入
	 */
	private LatentCusterDao latentCusterDao = null;
	/**
	 * 数据库访问层接口注入
	 * @param latentCusterDao the latentCusterDao to set
	 */
	public void setLatentCusterDao(LatentCusterDao latentCusterDao) {
		this.latentCusterDao = latentCusterDao;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.LatentCusterService#addLatentCuster(com.hnzskj.persist.bean.system.LatentCuster)
	 */
	@Override
	public boolean addLatentCuster(LatentCuster latentCuster) {
		int result = latentCusterDao.addLatentCuster(latentCuster);
		return result>0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.LatentCusterService#delLatentCuster(java.lang.String)
	 */
	@Override
	public boolean delLatentCuster(String lcId) {
		int result = latentCusterDao.delLatentCuster(lcId);
		return result>0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.LatentCusterService#getLatentCuster(java.lang.String)
	 */
	@Override
	public LatentCuster getLatentCuster(String lcId) {
		LatentCuster latentCuster  = latentCusterDao.getLatentCuster(lcId);
		return latentCuster;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.LatentCusterService#searchLatentCuster(com.hnzskj.common.Page, com.hnzskj.persist.bean.system.LatentCuster)
	 */
	@Override
	public Page<LatentCuster> searchLatentCuster(Page<LatentCuster> page,
			LatentCuster latentCuster) {
		String fields=" LCID,LCTEXT,LCORDER,LCDATETIME,NOTE ";
		StringBuilder sb = new StringBuilder(" where 1 = 1 ");
		if(null!=latentCuster){
			//字典内容查询
			if(null!=latentCuster.getLcText()&&!"".equals(latentCuster.getLcText())){
				sb.append(" AND LCTEXT LIKE '%").append(latentCuster.getLcText()).append("%'");
			}
			
			//根据排序号查询
			if(latentCuster.getLcOrder()>0){
				sb.append(" AND LCORDER <=").append(latentCuster.getLcOrder());
			}
		}
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("LCORDER", "ASC");
		page = this.latentCusterDao.searchLatentCuster(page, fields, sb.toString(), null, orderby);
		return page;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.LatentCusterService#updLatentCuster(com.hnzskj.persist.bean.system.LatentCuster)
	 */
	@Override
	public boolean updLatentCuster(LatentCuster latentCuster) {
		int result = latentCusterDao.updLatentCuster(latentCuster);
		return result>0?true:false;
	}

}
