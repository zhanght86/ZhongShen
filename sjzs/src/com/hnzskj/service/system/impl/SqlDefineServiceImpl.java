/**
*com.hnzskj.service.system.impl
 * 2012-10-13
*SqlDefineServiceImpl.java
*OA
*毛俊玲
 */
package com.hnzskj.service.system.impl;

import com.hnzskj.common.annotation.Description;
import com.hnzskj.persist.dao.system.SqlDefineDao;
import com.hnzskj.service.system.SqlDefineService;

/**
 * @author 毛俊玲
 *2012-10-13上午09:04:36
 */
public class SqlDefineServiceImpl implements SqlDefineService {

	private SqlDefineDao sqlDefineDao;
	
	public SqlDefineDao getSqlDefineDao() {
		return sqlDefineDao;
	}

	public void setSqlDefineDao(SqlDefineDao sqlDefineDao) {
		this.sqlDefineDao = sqlDefineDao;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.SqlDefineService#executeSql(java.lang.String)
	 */
	@Override
	@Description("执行自定义的sql语句")
	public int executeSql(String sql) {
		// TODO Auto-generated method stub
		return sqlDefineDao.executeSql(sql);
	}

}
