/**
 * flow
 *com.hnzskj.dao.flow
 *2012-4-62012上午11:19:18
 *郑辉
 *
 */
package com.hnzskj.persist.dao.flow;

import java.util.List;

import com.hnzskj.persist.bean.flow.Tache;

/**
 *创建人：郑辉
 *描述： 	
 *创建时间：2012-4-6 上午11:19:18
 *修改人：郑辉
 *修改时间：
 */
public interface TacheDao{
	/**
	 *创建人：郑辉
	 *描述：  该方法用来保存环节实例，如果存在，此模板下的环节，那么先删除	
	 *创建时间：2012-4-7 上午09:28:34
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： int
	 */
	public int addTacheInfo(Tache tache);
	
	/**
	 *创建人：郑辉
	 *描述： 	通过模板编号获取对象列表
	 *创建时间：2012-4-9 下午02:01:59
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： List<Tache>
	 */
	public List<Tache> getTacheByTempId(int tempno);
	
	/**
	 *创建人：郑辉
	 *描述： 通过模板编号和环节编号，获取环节对象	
	 *创建时间：2012-4-9 下午02:02:17
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： Tache
	 */
	public Tache getTacheByTempIdAndTacheId(int tempno,int tacheId);
	
	public int deleteTacheInfo(int tempno);
	
	public int updateAttatch(String template_no,String tacheId,String attachId);
}
