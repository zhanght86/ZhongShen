/**
 * OA
 *com.hnzskj.persist.dao.flow.impl
 *2012-6-122012上午10:05:04
 *郑辉
 *
 */
package com.hnzskj.persist.dao.flow.impl;

import java.util.List;

import com.hnzskj.common.BaseDao;
import com.hnzskj.persist.bean.flow.Phrases;
import com.hnzskj.persist.dao.flow.PhrasesDao;

/**
 *创建人：郑辉
 *描述： 	
 *创建时间：2012-6-12 上午10:05:04
 *修改人：郑辉
 *修改时间：
 */
public class PhrasesDaoImpl extends BaseDao implements PhrasesDao {

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.flow.PhrasesDao#addInfo(com.hnzskj.persist.bean.flow.Phrases)
	 */
	@Override
	public int addInfo(Object[][] params) {
		// TODO Auto-generated method stub
		String sql ="insert into sys_phrases (phrases_info,emplId) values(?,?)";
		int num =this.updateBatch(sql, params, params.length);
		return num;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.flow.PhrasesDao#deleteInfo(int)
	 */
	@Override
	public int deleteInfo(String emplId) {
		// TODO Auto-generated method stub
		String sql ="delete sys_phrases where emplId=?";
		Object  [] params ={emplId};
		int num =this.update(sql, params);
		return num;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.flow.PhrasesDao#getPhrsesList(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Phrases> getPhrsesList(String emplId) {
		// TODO Auto-generated method stub
		String sql ="select id,phrases_info,emplId from sys_phrases where emplId=?";
		Object[] params ={emplId};
		List<Phrases> list =this.query(sql, Phrases.class, params);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.flow.PhrasesDao#updateInfo(com.hnzskj.persist.bean.flow.Phrases)
	 */
	@Override
	public int updateInfo(Phrases phrases) {
		// TODO Auto-generated method stub
		String sql ="update sys_phrases set phrases_info =? where id=?";
		Object [] params ={phrases.getPhrases_info(),phrases.getId()};
		int num =this.update(sql, params);		
		return num;
	}

}
