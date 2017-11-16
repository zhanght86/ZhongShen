/**
*com.hnzskj.persist.dao.system
 * 2012-10-13
*SqlDefine.java
*OA
*毛俊玲
 */
package com.hnzskj.persist.dao.system;

/**
 * @author 毛俊玲
 * 自定义sql的处理类
 *2012-10-13上午08:59:30
 */
public interface SqlDefineDao {
	//执行自定义的sql语句
	public int executeSql(String sql);

}
