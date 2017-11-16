/**
 * OA
 *com.hnzskj.service.flow.impl
 *2012-6-122012上午10:05:57
 *郑辉
 *
 */
package com.hnzskj.service.flow.impl;

import java.util.List;

import com.hnzskj.common.annotation.Description;
import com.hnzskj.persist.bean.flow.Phrases;
import com.hnzskj.persist.dao.flow.PhrasesDao;
import com.hnzskj.service.flow.PhrasesService;

/**
 *创建人：郑辉
 *描述： 	
 *创建时间：2012-6-12 上午10:05:57
 *修改人：郑辉
 *修改时间：
 */
public class PhrasesServiceImpl implements PhrasesService {
	private PhrasesDao phraseDao =null;

	public PhrasesDao getPhraseDao() {
		return phraseDao;
	}

	public void setPhraseDao(PhrasesDao phraseDao) {
		this.phraseDao = phraseDao;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.flow.PhrasesService#addInfo(com.hnzskj.persist.bean.flow.Phrases)
	 */
	@Override
	@Description("添加")
	public int addInfo(Phrases phrases) {
		// TODO Auto-generated method stub
		String temp =phrases.getTemp();
		if(temp.equals("")){return 0;}
		Object [] o1 =temp.split("_");
		//Object [] a =new Object[7];
		Object[][] params=new Object[o1.length][];
		for(int i =0 ;i<o1.length;i++){
			Object[] o2=o1[i].toString().split(",");
			params[i]=o2;
		}
		int num=0;
		num=phraseDao.addInfo(params);
		return num;
	}

	
	/* (non-Javadoc)
	 * @see com.hnzskj.service.flow.PhrasesService#deleteInfo(int)
	 */
	@Override
	@Description("删除常用语")
	public int deleteInfo(String emplId) {
		// TODO Auto-generated method stub
		return phraseDao.deleteInfo(emplId);
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.flow.PhrasesService#getPhrsesList(java.lang.String)
	 */
	@Override
	@Description("获取常用语")
	public List<Phrases> getPhrsesList(String emplId) {
		// TODO Auto-generated method stub
		return phraseDao.getPhrsesList(emplId);
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.flow.PhrasesService#updateInfo(com.hnzskj.persist.bean.flow.Phrases)
	 */
	@Override
	@Description("修改常用语")
	public int updateInfo(Phrases phrases) {
		// TODO Auto-generated method stub
		return phraseDao.updateInfo(phrases);
	}
}
