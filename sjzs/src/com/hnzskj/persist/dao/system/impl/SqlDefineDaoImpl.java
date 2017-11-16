/**
*com.hnzskj.persist.dao.system.impl
 * 2012-10-13
*SqlDefineDaoImpl.java
*OA
*毛俊玲
 */
package com.hnzskj.persist.dao.system.impl;

import com.hnzskj.common.BaseDao;
import com.hnzskj.persist.dao.system.SqlDefineDao;

/**
 * @author 毛俊玲
 *2012-10-13上午09:02:41
 */
public class SqlDefineDaoImpl extends BaseDao implements SqlDefineDao {

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.system.SqlDefineDao#executeSql(java.lang.String)
	 */
	@Override
	public int executeSql(String sql) {
		// TODO Auto-generated method stub
		int result = this.update(sql, null);
		return result;
	}

}
